package fileSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Queue;

public class SearchByFileContent implements SearchByFile {
    private final Queue<File> paths;
    private final ParallelSearch.Stopper stopper;
    private final Parallel parallelSearch;

    public SearchByFileContent(final Queue<File> paths,
                               final ParallelSearch.Stopper stopper,
                               final Parallel parallelSearch) {
        this.stopper = stopper;
        this.paths = paths;
        this.parallelSearch = parallelSearch;
    }

    @Override
    public File scanQueue(final String target) {
        while (this.paths.size() != 0 && !Thread.currentThread().isInterrupted()) {
            final File result = getFileWhichContains(target, this.paths.poll());
            if (result != null && !result.getName().equals("-1")) {
                if (parallelSearch.getConsumer() != null) {
                    synchronized (parallelSearch.getConsumer()) {
                        if (this.parallelSearch.getConsumer().get() != null) {
                            this.parallelSearch.getConsumer().get().accept(result);
                            this.parallelSearch.killConsumerReference();
                            this.stopper.stopAllSearchingThread();
                            return result;
                        }
                    }
                }
            }
        }
        return new File("-1");
    }

    private File getFileWhichContains(final String target, final File file) {
        final StringBuilder sb = new StringBuilder();
        if (file == null) return new File("-1");
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))
        ) {

            String line;
            while (Objects.nonNull(line = reader.readLine())) {
                sb.append(line);
            }

            if (new String(sb).contains(target)) {
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("-1");
    }
}
