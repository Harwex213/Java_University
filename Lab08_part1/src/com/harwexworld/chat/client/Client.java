package com.harwexworld.chat.client;

import com.harwexworld.chat.server.ServerChat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final Scanner scanner = new Scanner(System.in);
    private String message = "";

    public Client(InetAddress addr) {
        try {
            socket = new Socket(addr, ServerChat.PORT);
            System.out.println("Connection established" );
        }
        catch (IOException e) {
            System.err.println("Connection failed");
        }

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            run();
        }
        catch (IOException | InterruptedException e) {
            try {
                socket.close();
            }
            catch (IOException e2) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void run() throws InterruptedException, IOException {
        try {
            Runnable read = ()-> {
                while (true) {
                    try {
                        message = in.readLine();
                        if(message.equals("END"))
                            break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println(message);
                }
            };

            Thread reader = new Thread(read,"reader");
            reader.start();

            do {
                message = scanner.nextLine();
                this.out.println(message);
            } while (!message.equals("END"));
            reader.interrupt();

        } finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

}