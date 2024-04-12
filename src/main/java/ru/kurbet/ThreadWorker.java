package ru.kurbet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadWorker extends Thread {
    private final String name;

    public ThreadWorker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            log.info("{}", name);
            try {
                sleep(5000L);
            } catch (InterruptedException e) {
                log.debug("{} interrupted", name);
                return;
            }
        }
    }
}
