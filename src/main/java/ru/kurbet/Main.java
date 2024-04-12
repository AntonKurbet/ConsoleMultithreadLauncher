package ru.kurbet;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        long stop;
        int numThreads = 2;
        var workers = new ThreadWorker[numThreads];

        var sc = new Scanner(System.in);
        System.out.println("started (ready threads: " + numThreads + ")");
        System.out.println("commands: " + Arrays.toString(Commands.values()));
        System.out.println("input command: ");
        while (sc.hasNext()) {
            System.out.println("input command: ");
            var command = sc.next();

            switch (Commands.of(command)) {
                case START -> {
                    start = System.currentTimeMillis();
                    new Thread(() -> startThreads(workers)).start();
                }
                case STOP -> {
                    stopThreads(workers);
                    stop = System.currentTimeMillis();
                    log.info("STOP: Work time: {} milliseconds", stop - start);
                }
                case EXIT -> {
                    stopThreads(workers);
                    return;
                }
            }
        }
    }

    private static void startThreads(ThreadWorker[] workers) {
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new ThreadWorker("Thread " + i);
            workers[i].start();
        }
        for (var threadWorker : workers) {
            try {
                threadWorker.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    private static void stopThreads(ThreadWorker[] workers) {
        for (int i = 0; i < workers.length; i++) {
            workers[i].interrupt();
        }
    }
}