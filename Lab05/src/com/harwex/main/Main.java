package com.harwex.main;

import com.harwex.part1.Man;
import com.harwex.part1.Shower;

public class Main {
    public static void main(String[] args) {
        var shower = new Shower(2);

        for (int i = 0; i < 4; i++) {
            new Man(shower).start();
        }
    }
}
