package com.harwex.part2;

import java.util.ArrayList;
import java.util.Random;

public class Car extends Thread {
    private final Parking parking;
    private final int waitingTime;
    private static int id = 0;

    public Car(Parking parking) {
        super("Anon");

        var random = new Random();
        waitingTime = random.nextInt(4);
        this.parking = parking;
        setName("Car" + id++);
    }

    public Parking getParking() {
        return parking;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public void run() {
        try {
            parking.ParkCar(this);
        }
        catch (InterruptedException ignored) {
        }
    }
}
