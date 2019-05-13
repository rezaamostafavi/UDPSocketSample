package servlet;

import info.User;
import info.Users;
import org.json.JSONObject;
import udp.UDPServer;
import utils.MyLogs;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.xml.ws.WebEndpoint;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * Created by rezaa on 5/12/2019.
 */

@ServerEndpoint("/websocket")
public class MyWebSocket {

    private static final MyLogs LOGGER = MyLogs.getLogger(MyWebSocket.class.getName());

    @OnOpen
    public void OnOpen(Session session) {
        LOGGER.info("Client Connect.");
    }

    @OnClose
    public void OnClose(Session session) {
        Users.removeUserBySession(session);
        LOGGER.info("Client Disconnect.");
    }

    @OnMessage
    public void OnMessage(String message, Session session) {
        try {
            String address = session.getId();
            LOGGER.info("RECEIVED from " + address + " : " + message);
            JSONObject obj = new JSONObject(message);
            String id = obj.optString("id");
            String type = obj.optString("type");
            if (type.equals("start")) {
                User user = new User(id, session);
                Users.users.put(id, user);
            }
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
    }

}
