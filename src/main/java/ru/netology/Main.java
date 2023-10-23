package ru.netology;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger count_3 = new AtomicInteger(0);
    private static AtomicInteger count_4 = new AtomicInteger(0);
    private static AtomicInteger count_5 = new AtomicInteger(0);

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindromes(String word) {
        return word.equals((new StringBuffer(word)).reverse().toString());
    }

    public static boolean isSameLetter(String word) {
        int j = 0;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(j) != word.charAt(i)) {
                return false;
            }
            j++;
        }
        return true;
    }

    public static boolean isAlphabet(String word) {
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) < word.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindromeThread = new Thread(() -> {
            for (String nick : texts) {
                if (isPalindromes(nick)) {
                    switch (nick.length()) {
                        case 3:
                            count_3.incrementAndGet();
                            break;
                        case 4:
                            count_4.incrementAndGet();
                            break;
                        case 5:
                            count_5.incrementAndGet();
                            break;
                    }
                }
            }
        });

        Thread sameLetterThread = new Thread(() -> {
            for (String nick : texts) {
                if (isSameLetter(nick)) {
                    switch (nick.length()) {
                        case 3:
                            count_3.incrementAndGet();
                            break;
                        case 4:
                            count_4.incrementAndGet();
                            break;
                        case 5:
                            count_5.incrementAndGet();
                            break;
                    }
                }
            }
        });

        Thread alphabetThread = new Thread(() -> {
            for (String nick : texts) {
                if (isAlphabet(nick)) {
                    switch (nick.length()) {
                        case 3:
                            count_3.incrementAndGet();
                            break;
                        case 4:
                            count_4.incrementAndGet();
                            break;
                        case 5:
                            count_5.incrementAndGet();
                            break;
                    }
                }
            }
        });

        palindromeThread.start();
        sameLetterThread.start();
        alphabetThread.start();

        palindromeThread.join();
        sameLetterThread.join();
        alphabetThread.join();

        System.out.println("Красивых слов с длиной 3: " + count_3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + count_4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + count_5.get() + " шт");

    }
}

