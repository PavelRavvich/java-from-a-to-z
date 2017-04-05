package fileSearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ParallelSearch implements Parallel {
    // обернутая ссылка на класс хранящий результат поиска на полях
    private AtomicReference<Consumer<File>> consumer;
    // останавливает все потоки (вызывает cancel в цикле по threads).
    private final Stopper stopper;
    // лист запущенных потоков.
    private final List<Future<File>> threads;
    // размер пула потоков.
    private final int amountThreads;
    // очередь в которой храняться файлы.
    private final Queue<File> container;
    private final ExecutorService service;

    public ParallelSearch(final int amountThreads, final Consumer<File> consumer) {
        this.service = Executors.newFixedThreadPool(amountThreads);
        this.amountThreads = amountThreads;

        this.container = new LinkedTransferQueue<>();
        this.consumer =  new AtomicReference<>(consumer);
        this.threads =   new ArrayList<>(amountThreads);
        this.stopper =   new Stopper();
    }

    @Override
    public AtomicReference<Consumer<File>> getConsumer() {
        return consumer;
    }


    @Override
    public void killConsumerReference() {
        this.consumer = null;
    }

    @Override
    public void startParallelSearch(
            final String pathToRoot,
            final String targetText) {

        this.searchByFileSystem(pathToRoot);
        this.searchByFilesContent(targetText);
    }

    // поток обходит файловую систему в поисках txt файлов и добавляет в очередь.
    private void searchByFileSystem(final String pathToRoot) {
        final Thread searchByFileSystem = new Thread(new Runnable() {
            @Override
            public void run() {
                new CollectingTXTFiles(
                        pathToRoot, container)
                        .collectTXTFromRoot();
            }
        });
        searchByFileSystem.start();
        try {
            searchByFileSystem.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // добавляем в пул потоки для поиска строки по содержимому txt файлов
    private void searchByFilesContent(final String targetText) {
        for (int i = 0; i < (this.amountThreads - 1); i++) {
            final Future<File> taskRead =
                    this.service.submit(new Callable<File>() {
                        @Override
                        public File call() throws Exception {
                            return new SearchByFileContent(container, stopper, ParallelSearch.this)
                                    .scanQueue(targetText);
                        }
                    });
            // добавляем в потоки поиск контенту
            this.threads.add(taskRead);
        }
    }

    /**
     * Останавливает все потоки ссылка его экземпляр есть у всех потоков.
     * Если один из потоков найдет искомую строку, вызовет stopAllSearchingThread().
     */
    class Stopper {
        void stopAllSearchingThread() {
            for (Future future : threads) {
                future.cancel(true);
            }
            service.shutdown();
        }
    }
}
