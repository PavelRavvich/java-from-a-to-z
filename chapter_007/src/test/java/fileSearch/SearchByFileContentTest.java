package fileSearch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchByFileContentTest {
    private static File dir;
    private final static String SEP = File.separator;

    @BeforeClass
    public static void beforeClass() throws IOException {
        dir = Files.createTempDirectory(null).toFile();
    }

    @Test
    public void whenNoOneFileFromQueueNotContainsTargetSubStringThenReturnFileWithNameFlag() throws IOException {
        final Queue<File> queue = new ConcurrentLinkedQueue<>();
        final File file = new File(dir.getAbsolutePath() + SEP + "test.txt");
        if (file.createNewFile()) {
            try (RandomAccessFile r = new RandomAccessFile(file, "rw")) {
                r.writeUTF("text for test");
            }
        }

        queue.add(file);

        final ConsumerOfSearch consumer = new ConsumerOfSearch();

        final ParallelSearch parallelSearch = new ParallelSearch(3, consumer);
        final ParallelSearch.Stopper stopper = parallelSearch.new Stopper();

        final SearchByFile searchByFile = new SearchByFileContent(queue, stopper, parallelSearch);
        final File result = searchByFile.scanQueue("no exist text");
        assertThat(result.getName(), is("-1"));
    }

    @Test
    public void whenFileFromQueueContainsTargetSubStringThenReturnFileWhichContains() throws IOException {
        Queue<File> queue = new ConcurrentLinkedQueue<>();
        File file = new File(dir.getAbsolutePath() + SEP + "test.txt");
        if (file.createNewFile()) {
            try (RandomAccessFile r = new RandomAccessFile(file, "rw")) {
                r.writeUTF("text for test");
            }
        }

        queue.add(file);
        final ConsumerOfSearch consumer = new ConsumerOfSearch();

        final ParallelSearch parallelSearch = new ParallelSearch(3, consumer);
        final ParallelSearch.Stopper stopper = parallelSearch.new Stopper();

        final SearchByFile searchByFile = new SearchByFileContent(queue, stopper, parallelSearch);

        final File result = searchByFile.scanQueue("text");
        assertThat(result.getName(), is("test.txt"));
    }

    @Test
    public void whenTwoFilesFromQueueContainsTargetSubStringThenReturnFileWhichContains() throws IOException {
        final Queue<File> queue = new ConcurrentLinkedQueue<>();
        File file = new File(dir.getAbsolutePath() + SEP + "test.txt");
        if (file.createNewFile()) {
            try (RandomAccessFile r = new RandomAccessFile(file, "rw")) {
                r.writeUTF("text for test");
            }
        }
        final File file1 = new File(dir.getAbsolutePath() + SEP + "test1.txt");
        file1.createNewFile();


        queue.add(file1);
        queue.add(file);

        final ConsumerOfSearch consumer = new ConsumerOfSearch();

        final ParallelSearch parallelSearch = new ParallelSearch(3, consumer);
        final ParallelSearch.Stopper stopper = parallelSearch.new Stopper();

        final SearchByFile searchByFile = new SearchByFileContent(queue, stopper, parallelSearch);

        final File result = searchByFile.scanQueue("text");
        assertThat(result.getName(), is("test.txt"));
    }

    @AfterClass
    public static void afterClass() throws IOException {
        recursiveDelete(dir);
    }

    public static void recursiveDelete(File file) {
        if (!file.exists()) return;

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        // вызываем метод delete() для удаления файлов и пустых(!) папок
        file.delete();
    }
}