package hw1;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread thread = new Thread(worker);

        if (counter.get() % 2 == 0) {
            thread.start();
        }

        if (counter.get() % 2 != 0) {
            thread.start();
        }
    }

    static class Worker implements Runnable {

        @Override
        public void run() {

            while (counter.get() != 20) {
            counter.incrementAndGet();
                System.out.println("Counter is " + counter);
            }
        }
    }
}

