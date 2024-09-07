package com.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {
    private final BlockingQueue<double[]> queue;
    private final List<Township> townships;
    private final List<String> targetNames;

    public Consumer(BlockingQueue<double[]> queue, List<Township> townships, List<String> targetNames) {
        this.queue = queue;
        this.townships = townships;
        this.targetNames = targetNames;
    }

    @Override
    public void run() {
        try {
            while (true) {
                double[] randomNumbers = queue.take();
                if (randomNumbers[0] == -1) {
                    queue.put(randomNumbers); // 传递结束标志给其他消费者
                    break;
                }
                if (validateResult(townships, randomNumbers)) {
                    System.out.printf("找到结果: %d, %d, %d, %d, %d, %d, %d, %d%n", randomNumbers[0], randomNumbers[1], randomNumbers[2], randomNumbers[3], randomNumbers[4], randomNumbers[5], randomNumbers[6], randomNumbers[7]);
                    for (Township township : townships) {
                        System.out.println(township);
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean validateResult(List<Township> townships, double[] randomNumbers) {
        for (Township township : townships) {
            township.calcResult2(randomNumbers[0], randomNumbers[1], randomNumbers[2], randomNumbers[3], randomNumbers[4], randomNumbers[5], randomNumbers[6], randomNumbers[7]);
        }

        try {
            // 按照result升序排序
            Collections.sort(townships, Comparator.comparingDouble(Township::getResult));
           
            for (int i = 0; i < 10; i++) {
                if (!targetNames.contains(townships.get(i).getName())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("计算错误: " + e.getMessage());
            for (Township township : townships) {
                System.out.println(township);
            }
            return false;
        }
    }
}