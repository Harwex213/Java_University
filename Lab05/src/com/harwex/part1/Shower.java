package com.harwex.part1;

public class Shower {
    private final int showerSize;
    private int currentSize;

    public Shower(int showerSize) {
        this.showerSize = showerSize;
    }

    public synchronized int getShowerSize() {
        return showerSize;
    }

    public synchronized int getCurrentSize() {
        return currentSize;
    }

    public synchronized void ShowerIn() throws InterruptedException {
        while (currentSize == showerSize) {
            wait();
        }
        currentSize++;
        System.out.println(Thread.currentThread().getName() + " now taking a shower");
        System.out.println("People in shower: " + currentSize);
    }

    public synchronized void ShowerOut() throws InterruptedException {
        currentSize--;
        System.out.println(Thread.currentThread().getName() + " finished taking a shower");
        System.out.println("People in shower: " + currentSize);
        notify();
    }
}
