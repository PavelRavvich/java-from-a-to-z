package counterSpaces;

import java.util.Timer;
import java.util.TimerTask;

public class CounterOfSpace {

    /**
     * Text for process.
     */
    private final String textForAnalysis;

    /**
     * Determines time for execute count.
     * When the execution fails at the given time,
     * then threads counters for spaces and words stop.
     * Time format: milliseconds.
     */
    private final long timeLimiter;

    /**
     * Counters for spaces and words. This is result program.
     */
    private int counterOfSpaces;
    private int counterOfWords;

    /**
     * Threads for parallel count spaces and words.
     */
    private Thread threadForSpaceCount;
    private Thread threadForWordsCount;

    /**
     * Stop timer.
     */
    private final Timer timer;
    private final TimerUntilInterruptAll stop;

    /**
     * Guarantees synchronous start of timer and counter-threads.
     */
    private volatile boolean startProcess = false;

    /**
     * Guarantees out in console message of interrupt penultimate before "Finish program" massage.
     */
    private volatile boolean finishProcess = false;

    /**
     * Default constructor.
     * @param textForAnalysis for process.
     * @param timeLimiter time for execute count.
     */
    public CounterOfSpace(final String textForAnalysis, final long timeLimiter) {
        this.textForAnalysis = textForAnalysis;
        this.timeLimiter = timeLimiter;
        this.timer = new Timer();
        this.stop = new TimerUntilInterruptAll();
    }

    /**
     * Start algorithm count words and spaces in separate threads.
     */
    void startProgram() {
        System.out.println("Start program");

        this.countWords();
        this.countSpaces();

        this.timer.schedule(this.stop, this.timeLimiter);

        this.threadForSpaceCount.start();
        this.threadForWordsCount.start();

        try {
            this.threadForSpaceCount.join();
            this.threadForWordsCount.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop.interruptMassage();

        System.out.println("Finish program");
    }

    /**
     * Count amount spaces. Work in separate thread.
     * @return amount spaces.
     */
    private int countSpaces() {
        this.threadForSpaceCount = new Thread(new Runnable() {
            @Override
            public void run() {
                if (textForAnalysis.length() == 0) {
                    return;
                }

                startProcess = true;
                for (char c : textForAnalysis.toCharArray()) {
                    if (threadForSpaceCount.isInterrupted() ||
                            threadForWordsCount.isInterrupted()) {

                        finishProcess = true;
                        return;
                    }

                    if (c == ' ') {
                        counterOfSpaces++;
                        System.out.println(String.format(
                                "counterOfSpaces %s", counterOfSpaces));
                    }
                }
            }
        });

        return this.counterOfSpaces;
    }

    /**
     * Count amount words. Work in separate thread.
     * @return amount words.
     */
    private int countWords() {
        this.threadForWordsCount = new Thread(new Runnable() {
            @Override
            public void run() {
                if (textForAnalysis.length() == 0) {
                    return;
                }

                startProcess = true;
                String[] words = textForAnalysis.split(" ");
                for (String word : words) {
                    if (threadForSpaceCount.isInterrupted() ||
                            threadForWordsCount.isInterrupted()) {

                        finishProcess = true;
                        return;
                    }

                    if (!word.equals(" ")) {
                        counterOfWords++;
                        System.out.println(String.format(
                                "counterOfWords %s", counterOfWords));
                    }
                }
            }
        });

        return this.counterOfWords;
    }

    /**
     * Class stop threads threadForWordsCount and threadForSpaceCount.
     */
    private class TimerUntilInterruptAll extends TimerTask {
        @Override
        public void run() {
            while (!startProcess) {
                try {
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            threadForSpaceCount.interrupt();
            threadForWordsCount.interrupt();
        }

        private void interruptMassage() {
            if (finishProcess) {
                System.out.println(
                        "Time is over! Threads have been stopped.");
            }
        }
    }
}
