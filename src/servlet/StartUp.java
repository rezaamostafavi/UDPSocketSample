package servlet;

import sun.rmi.runtime.Log;
import udp.UDPServer;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by rezaa on 5/11/2019.
 */
public class StartUp extends javax.servlet.http.HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        UDPServer.start(8080);
        UDPServer.start(80);
        UDPServer.start(5000);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
