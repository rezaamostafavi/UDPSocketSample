package info;

import info.User;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rezaa on 5/11/2019.
 */
public class Users {

    public static final Map<String, User> users = new HashMap<>();

    public static void removeUserBySession(Session session) {
        users.forEach((s, user) -> {
            if (user.getSession().equals(session)) {
                users.remove(s);
                return;
            }
        });
    }
}
