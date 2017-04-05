package fileSearch;

import java.io.File;
import java.util.function.Consumer;

public interface ConsumerGetter<F extends File> extends Consumer<F> {
    F getResult();
}
