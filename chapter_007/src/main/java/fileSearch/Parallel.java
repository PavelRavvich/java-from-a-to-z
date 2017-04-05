package fileSearch;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public interface Parallel {
    void startParallelSearch(final String pathToRoot, final String targetText);
    void killConsumerReference();
    AtomicReference<Consumer<File>> getConsumer();
}
