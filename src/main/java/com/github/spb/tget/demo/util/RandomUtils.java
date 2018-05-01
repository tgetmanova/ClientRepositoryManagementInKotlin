package com.github.spb.tget.demo.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvqxyz" +
            ",./?'|][{}()-_=+*&^:;#@$%\"0123456789";
    private static final String ALPHABETIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvqxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = ",./?'|][{}()-_=+*&^:;#@$%\"";

    private static long TICKS_AT_EPOCH = 621355968000000000L;

    private static Random random = new Random(System.currentTimeMillis() * 10000 + TICKS_AT_EPOCH);

    public static Boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    public static Integer getRandomInteger() {
        return random.nextInt();
    }

    public static String generateRandomString(int charactersCount, String charactersSet) {
        List chars = Arrays.asList(charactersSet.split(""));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < charactersCount; i++) {
            stringBuilder.append(getRandomElement(chars));
        }
        return stringBuilder.toString();
    }

    public static String getRandomString(int charactersCount){
        return generateRandomString(charactersCount, ALL_CHARS);
    }

    public static String getRandomAlpanumeric(int charactersCount){
        return generateRandomString(charactersCount, ALPHABETIC.concat(NUMERIC));
    }

    public static String getRandomAlphabetic(int charactersCount){
        return generateRandomString(charactersCount, ALPHABETIC);
    }

    public static String getRandomNumeric(int charactersCount){
        return generateRandomString(charactersCount, NUMERIC);
    }

    public static Object getRandomElement(Collection collection) {
        int i = random.nextInt(collection.size());
        return collection.toArray()[i];
    }
}