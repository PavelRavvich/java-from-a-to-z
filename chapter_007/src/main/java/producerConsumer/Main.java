package producerConsumer;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        for (int i = 0; i < 5; i++) {
            integers[i] = i;
        }

        Queue<Integer> sharedQueue = new ArrayBlockingQueue<>(10);

        Producer<Integer> producer = new Producer(sharedQueue, integers);
        Thread prodThread = new Thread(producer, "Producer");

        Consumer<Integer> consumer = new Consumer(sharedQueue);
        Thread consThread = new Thread(consumer, "Consumer");
        prodThread.start();
        consThread.start();

        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.setSrc(new Integer[] {10,30,40,50,60,70,80,90,100});

        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Integer i : consumer.getResult()) {
            System.out.println(i + " - result");
        }
    }
}
