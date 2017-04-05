package fileSearch;

import java.io.File;
import java.util.Scanner;

public class StartApp {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}

class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    String getAnswer() {
        return scanner.nextLine();
    }
}

class Menu {
    private ConsoleInput consoleInput = new ConsoleInput();

    void start() {
        System.out.println("Enter directory : ");
        final String dir = consoleInput.getAnswer();
        System.out.println("Enter target text : ");
        final String targetText = consoleInput.getAnswer();
        ConsumerGetter<File> consumer = new ConsumerOfSearch();
        Parallel parallel = new ParallelSearch(5, consumer);
        parallel.startParallelSearch(dir, targetText);

        int sec = 4;
        while (sec != 0) {
            if (consumer.getResult() != null) break;
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sec--;
        }

        if (consumer.getResult() == null) {
            System.out.println("Time is wait is over. File not exist.");
        } else {
            System.out.println("target text contains in : "
                    + consumer.getResult().getAbsolutePath());
        }
    }

}