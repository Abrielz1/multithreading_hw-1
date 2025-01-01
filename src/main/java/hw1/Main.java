package hw1;

public class Main {

    private static volatile Integer counter = 0;

    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread thread = new Thread(worker);

        if (counter % 2 == 0) {
            thread.start();
        }

        if (counter % 2 != 0) {
            thread.start();
        }
    }

    static class Worker implements Runnable {

        @Override
        public void run() {

            while (counter != 20) {
            counter++;
                System.out.println("Counter is " + counter);
            }
        }
    }
}

