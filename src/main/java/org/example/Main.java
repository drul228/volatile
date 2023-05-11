package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger number3 = new AtomicInteger(0);
    public static AtomicInteger number4 = new AtomicInteger(0);
    public static AtomicInteger number5 = new AtomicInteger(0);

    public static boolean allSame(String str) {
        return str != null && !str.isEmpty() && str.chars().allMatch(c -> str.charAt(0) == c);
    }

    public static boolean isPalindrome(String word) {
        int length = word.length();
        for (int i = 0; i < (length / 2); i++) {
            if (word.charAt(i) != word.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGrowing(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1))
                return false;
        }
        return true;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void generateThreads(String[] texts) {
        new Thread(() -> {
            for (String text :
                    texts) {
                if (allSame(text)) {
                    switch (text.length()) {
                        case 3:
                            number3.getAndIncrement();
                            continue;
                        case 4:
                            number4.getAndIncrement();
                            continue;
                        case 5:
                            number5.getAndIncrement();
                            continue;

                    }
                }
            }
        }).start();
        new Thread(() -> {
            for (String text :
                    texts) {
                if (isPalindrome(text)) {
                    switch (text.length()) {
                        case 3:
                            number3.getAndIncrement();
                            break;
                        case 4:
                            number4.getAndIncrement();
                            break;
                        case 5:
                            number5.getAndIncrement();
                            break;
                    }
                }
            }

        }).start();
        new Thread(() -> {
            for (String text :
                    texts) {
                if (isGrowing(text)) {
                    switch (text.length()) {
                        case 3:
                            number3.getAndIncrement();
                            break;
                        case 4:
                            number4.getAndIncrement();
                            break;
                        case 5:
                            number5.getAndIncrement();
                            break;
                        default:
                            continue;
                    }
                }
            }

        }).start();
    }

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        generateThreads(texts);
        System.out.println(number3);
        System.out.println(number4);
        System.out.println(number5);
    }
}