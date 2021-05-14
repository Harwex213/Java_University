package com.harwexwolrd.internetConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

public class InternetConnection {
    public static void main(String[] args) {
        URL url = null;

        try {
            url  = new URL("https://www.youtube.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(url == null) {
            throw new RuntimeException();
        }

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String buf;
            while((buf = bufferedReader.readLine()) != null) {
                System.out.println(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
