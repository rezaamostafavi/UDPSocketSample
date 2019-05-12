package info;

import java.net.InetAddress;

/**
 * Created by rezaa on 5/11/2019.
 */
public class User {
    private String id;
    private InetAddress inetAddress;
    private int clientPort;

    public User(String id, InetAddress inetAddress, int clientPort) {
        this.id = id;
        this.inetAddress = inetAddress;
        this.clientPort = clientPort;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
