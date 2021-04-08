package com.harwex.part1;

import java.util.ArrayList;

public class Shower {
    private final int showerSize;
    private final ArrayList<Man> menInShower;

    public Shower(int showerSize) {
        this.showerSize = showerSize;
        menInShower = new ArrayList<>();
    }

    public synchronized int getShowerSize() {
        return showerSize;
    }

    public synchronized int getCurrentSize() {
        return menInShower.size();
    }

    public synchronized void ShowerIn(Man man) throws InterruptedException {
        while (menInShower.size() == showerSize || CheckGender(man.getGender())) {
            wait(500);
        }
        menInShower.add(man);
        System.out.println(Thread.currentThread().getName() + " now taking a shower");
        System.out.println("People in shower: " + menInShower.size());
    }

    public synchronized void ShowerOut(Man man) throws InterruptedException {
        menInShower.remove(man);
        System.out.println(Thread.currentThread().getName() + " finished taking a shower");
        System.out.println("People in shower: " + menInShower.size());
        notify();
    }

    private boolean CheckGender(Gender gender) {
        return !menInShower.isEmpty() && menInShower.get(0).getGender() != gender;
    }
}
