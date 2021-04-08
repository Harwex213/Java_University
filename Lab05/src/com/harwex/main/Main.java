package com.harwex.main;

import com.harwex.part1.Man;
import com.harwex.part1.Shower;
import com.harwex.part2.Car;
import com.harwex.part2.Parking;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        //TaskOne();

        TaskTwo();
    }

    public static void TaskOne() {
        var shower = new Shower(2);

        for (int i = 0; i < 5; i++) {
            new Man(shower).start();
        }
    }

    public static void TaskTwo() {
        var parking = new Parking(new Semaphore(1), new Semaphore(1));

        for (int i = 0; i < 3; i++) {
            new Car(parking).start();
        }
    }
}
