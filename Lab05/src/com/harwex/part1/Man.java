package com.harwex.part1;

import java.util.Random;

public class Man extends Thread {
    private final Shower shower;
    private final Gender gender;
    private static int id = 0;

    public Man(Shower shower) {
        super("Anon");
        this.shower = shower;
        gender = Gender.GetRandomGender();
        setName("Man " + id++ + ", gender: " + gender.name());
    }

    public Shower getShower() {
        return shower;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public void run() {
        try {
            shower.ShowerIn();
            Thread.sleep(3000);
            shower.ShowerOut();
        }
        catch (InterruptedException ignored) {
        }
    }
}

enum Gender {
    Male, Female;

    public static Gender GetRandomGender() {
        var random = new Random();
        return Gender.values().clone()[random.nextInt(values().length)];
    }
}