package com.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private final BlockingQueue<double[]> queue;

    private static final Set<String> generatedNumbersSet = new HashSet<>();

    public Producer(BlockingQueue<double[]> queue) {
        this.queue = queue;
    }

    // create a function get random number range from 1 to 99
    public int getRandomNumber() {
        return (int) (Math.random() * 99) + 1;
    }

    public static int[] generateRandomNumbers() {
        while (true) {
            int[] randomNumbers = new int[8];
            int sum = 0;
            Random random = new Random();
            for (int i = 0; i < 7; i++) {
                int max = 100 - sum - (7 - i); // 确保剩余的数有足够的空间
                randomNumbers[i] = random.nextInt(max) + 1;
                sum += randomNumbers[i];
            }

            randomNumbers[7] = 100 - sum;
            String arrayString = arrayToString(randomNumbers);
            if (!generatedNumbersSet.contains(arrayString)) {
                generatedNumbersSet.add(arrayString);
                return randomNumbers;
            }
        }
    }

    public static double[] generateRandomDoubleNumbers() {
        while (true) {
            double[] randomNumbers = new double[8];
            double sum = 0;
            Random random = new Random();
            // 生成前七个随机且不为0的0.5倍数
            for (int i = 0; i < 7; i++) {
                double max = 100 - sum - (7 - i) * 0.5; // 确保剩余的数有足够的空间
                randomNumbers[i] = (random.nextInt((int) (max * 2)) + 1) * 0.5;
                sum += randomNumbers[i];
            }

            randomNumbers[7] = 100 - sum;
            String arrayString = arrayToString2(randomNumbers);
            if (!generatedNumbersSet.contains(arrayString)) {
                generatedNumbersSet.add(arrayString);
                return randomNumbers;
            }
        }
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num).append(",");
        }
        return sb.toString();
    }

    private static String arrayToString2(double[] array) {
        StringBuilder sb = new StringBuilder();
        for (double num : array) {
            sb.append(num).append(",");
        }
        return sb.toString();
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                // int[] randomNumbers = generateRandomNumbers();
                double[] randomNumbers2 = generateRandomDoubleNumbers();
                queue.put(randomNumbers2);
                i++;
                if (i % 1000000 == 0) {
                    System.out.println("生产者生产了: " + i);
                    System.out.println("队列中还剩: " + queue.size());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}