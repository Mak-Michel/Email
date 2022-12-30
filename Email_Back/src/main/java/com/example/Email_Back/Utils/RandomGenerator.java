package com.example.Email_Back.Utils;

public class RandomGenerator {
    public static String generateId(){
        StringBuilder tempStr = new StringBuilder();
        String usedChars = "abcdefghijklmnopqrsktuvwsyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for(int i = 0; i < 15; i++)
            tempStr.append(usedChars.charAt((int) Math.floor(Math.random() * usedChars.length())));
        return tempStr.toString();
    }
}