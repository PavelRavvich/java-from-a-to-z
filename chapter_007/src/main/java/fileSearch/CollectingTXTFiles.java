package fileSearch;

import java.io.File;
import java.util.Objects;
import java.util.Queue;

public class CollectingTXTFiles implements Collector {
    private final String root;
    private final Queue<File> files;

    public CollectingTXTFiles(final String root,
                              final Queue<File> files) {
        this.root = root;
        this.files = files;
    }

    @Override
    public void collectTXTFromRoot() throws IllegalArgumentException {
        final File root = new File(this.root);

        if (!root.exists() || !root.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "Directory: '%s' not found", this.root));
        }

        this.selectTXTFiles(root);
    }

    private void selectTXTFiles(final File file) {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }

        if (file.getName().endsWith(".txt") &&
                !file.isDirectory()) {

            this.files.add(file);
        }

        if (file.isDirectory()) {
            final File[] childes = file.listFiles();
            assert Objects.nonNull(childes);

            for (File f : childes) {
                selectTXTFiles(f);
            }
        }
    }
}
