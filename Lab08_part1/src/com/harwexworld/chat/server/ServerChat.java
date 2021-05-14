package com.harwexworld.chat.server;

import java.io.*;
import java.net.ServerSocket;

@SuppressWarnings("InfiniteLoopStatement")
public class ServerChat {
    public static final int PORT = 8102;

    public static void main(String[] args) throws IOException {

        try (var serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server Started");

            while (true) {
                var socket = serverSocket.accept();
                System.out.println("Connected new user");

                try {
                    var server = new ServerThread(socket);
                    ServerThread.connectedUsers.add(server);
                } catch (IOException e) {
                    socket.close();
                }
            }
        }
    }

}