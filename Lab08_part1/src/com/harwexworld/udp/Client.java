package com.harwexworld.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {

    public static void main(String[] args) throws Exception {

        // The default port
        int clientport = 7777;
        String host = "localhost";

        if (args.length >= 1) {
            clientport = Integer.parseInt(args[0]);
        }
        System.out.println("Usage: UDPClient " + "Now using host = " + host + ", Port# = " + clientport);

        InetAddress ia = InetAddress.getByName(host);

        SenderThread sender = new SenderThread(ia, clientport);
        sender.start();
        ReceiverThread receiver = new ReceiverThread(sender.getSocket());
        receiver.start();
    }
}

class SenderThread extends Thread {

    private InetAddress serverIPAddress;
    private DatagramSocket udpClientSocket;
    private boolean stopped = false;
    private int serverport;

    public SenderThread(InetAddress address, int serverport) throws SocketException {
        this.serverIPAddress = address;
        this.serverport = serverport;
        // Create client DatagramSocket
        this.udpClientSocket = new DatagramSocket();
        this.udpClientSocket.connect(serverIPAddress, serverport);
    }

    public void halt() {
        this.stopped = true;
    }

    public DatagramSocket getSocket() {
        return this.udpClientSocket;
    }

    public void run() {
        try {
            // Create input stream
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (stopped)
                    return;

                // Message to send
                String clientMessage = inFromUser.readLine();

                if (clientMessage.equals("."))
                    break;

                // Create byte buffer to hold the message to send
                byte[] sendData = new byte[1024];

                // Put this message into our empty buffer/array of bytes
                sendData = clientMessage.getBytes();

                // Create a DatagramPacket with the data, IP address and port number
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverport);

                // Send the UDP packet to server
                udpClientSocket.send(sendPacket);

                Thread.yield();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

class ReceiverThread extends Thread {

    private DatagramSocket udpClientSocket;
    private boolean stopped = false;

    public ReceiverThread(DatagramSocket ds) throws SocketException {
        this.udpClientSocket = ds;
    }

    public void halt() {
        this.stopped = true;
    }

    public void run() {

        // Create a byte buffer/array for the receive Datagram packet
        byte[] receiveData = new byte[1024];

        while (true) {
            if (stopped)
                return;

            // Set up a DatagramPacket to receive the data into
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                // Receive a packet from the server (blocks until the packets are received)
                udpClientSocket.receive(receivePacket);

                // Extract the reply from the DatagramPacket
                String serverReply = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // print to the screen
                System.out.println("UDPClient: Response from Server: \"" + serverReply + "\"\n");

                Thread.yield();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}