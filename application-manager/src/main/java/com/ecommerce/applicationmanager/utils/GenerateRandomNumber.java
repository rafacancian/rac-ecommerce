package com.ecommerce.applicationmanager.utils;

import java.util.Random;

public class GenerateRandomNumber {

    private GenerateRandomNumber() {
    }

    public static Double execute(int min, int max) {
        Random random = new Random();
        return random.doubles(min, (max + 1)).findFirst().getAsDouble();
    }
}
