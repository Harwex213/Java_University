package com.harwexworld.chat.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientStart {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("Enter IP server");
        var in = new Scanner(System.in);

        new Client(InetAddress.getByName(in.nextLine()));
    }

}