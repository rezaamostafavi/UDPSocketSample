package udp;

import info.User;
import info.Users;
import org.json.JSONException;
import org.json.JSONObject;
import utils.MyLogs;

import java.net.*;
import java.util.logging.Logger;

/**
 * Created by rezaa on 5/11/2019.
 */
public class UDPServer {

    public static final MyLogs LOGGER = MyLogs.getLogger(UDPServer.class.getName());
    private static DatagramSocket socket;

    public static void start(int port) {
        try {
            socket = new DatagramSocket(port);
            LOGGER.info("UDP Server Start On Port: " + port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                    byte[] receiveData = new byte[1024];
                    LOGGER.info("wait for receive Data.");
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);
                    String sentence = new String(receivePacket.getData(), "UTF-8").replace("\0", "");
                    ;
                    SocketAddress address = receivePacket.getSocketAddress();
                    LOGGER.info("RECEIVED from " + address + " : " + sentence);
                    JSONObject obj = new JSONObject(sentence);
                    String id = obj.optString("id");
                    String type = obj.optString("type");
                    if (type.equals("start")) {
                        InetAddress IPAddress = receivePacket.getAddress();
                        int clientPort = receivePacket.getPort();
                        User user = new User(id, IPAddress, clientPort);
                        Users.users.put(id, user);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.warning(e.getMessage());
                }
            }

        }).start();
    }

    public static void sendPush() {
        for (String id : Users.users.keySet()) {
            JSONObject obj = new JSONObject();
            User user = Users.users.get(id);
            try {
                obj.put("serverTime", System.currentTimeMillis());
                if (user.getInetAddress() != null) {
                    byte[] sendData = obj.toString().getBytes("UTF-8");
                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, user.getInetAddress(), user.getClientPort());
                    socket.send(sendPacket);
                    LOGGER.info("Send Message to Client id = " + id + " - address" + user.getInetAddress().getCanonicalHostName());
                } else if (user.getSession() != null) {
                    user.getSession().getBasicRemote().sendText(obj.toString());
                    LOGGER.info("Send Message to Client id = " + id + " - address" + user.getSession().getId());
                }
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
            }
        }
    }
}
