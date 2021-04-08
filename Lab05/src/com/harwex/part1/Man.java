package com.harwex.part1;

import java.util.Random;

public class Man extends Thread {
    private final Shower shower;
    private final Gender gender;
    private final int timeInShower;
    private static int id = 0;

    public Man(Shower shower) {
        super("Anon");

        var random = new Random();
        timeInShower = random.nextInt(700);
        this.shower = shower;
        gender = Gender.GetRandomGender();
        setName(gender.name() + " " + id++);
        System.out.println(getName() + " ready to take a shower");
    }

    public Shower getShower() {
        return shower;
    }

    public Gender getGender() {
        return gender;
    }

    public int getTimeInShower() {
        return timeInShower;
    }

    @Override
    public void run() {
        try {
            shower.ShowerIn(this);
            Thread.sleep(3000);
            shower.ShowerOut(this);
        }
        catch (InterruptedException ignored) {
        }
    }
}

enum Gender {
    Male, Female;

    public static Gender GetRandomGender() {
        var random = new Random();
        return Gender.values()[random.nextInt(values().length)];
    }

    public static Gender GetAnotherGender(Gender gender) {
        var newGender = Gender.Male;
        if (newGender.equals(gender))
            newGender = Gender.Female;
        return newGender;
    }
}