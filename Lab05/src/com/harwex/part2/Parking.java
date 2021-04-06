package com.harwex.part2;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Parking {
    private final ArrayList<Semaphore> parkingLots;

    public Parking(Semaphore ... semaphore) {
        parkingLots = new ArrayList<>();
        parkingLots.addAll(Arrays.asList(semaphore));
    }

    public void ParkCar(Car car) throws InterruptedException {
        for (var parkingLot:
             parkingLots) {
            int parkingNumber =  parkingLots.indexOf(parkingLot);
            System.out.println(car.getName() + " trying to park in parking number " +  parkingNumber);
            if (parkingLot.tryAcquire(car.getWaitingTime(), TimeUnit.SECONDS)) {
                System.out.println(car.getName() + " now is parked in parking number " + parkingNumber);
                Thread.sleep(3000);
                System.out.println(car.getName() + " finished parking in parking number " + parkingNumber);
                parkingLot.release();
                return;
            }
            System.out.println(car.getName() + " go to next park");
        }
    }
}
