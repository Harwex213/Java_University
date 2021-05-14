package com.harwexworld.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("InfiniteLoopStatement")
public class ServerThread extends Thread {

    private final BufferedReader in;
    private final PrintWriter out;
    private String userName;
    public static List<ServerThread> connectedUsers = new ArrayList<>();

    public ServerThread(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        start();
    }

    public void run() {
        try {
            out.println("Connected");
            out.println("Enter name user");
            userName = in.readLine();

            try {
                while (true) {
                    String str = in.readLine();
                    for (ServerThread s : connectedUsers) {
                        if (s.userName.equals(this.userName)) {
                            continue;
                        }
                        s.out.println(userName + ": " + str);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
