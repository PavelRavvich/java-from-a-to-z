package fileSearch;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;

public class ParallelSearchTest {
    private static File dir;
    private final static String SEP = File.separator;

    @BeforeClass
    public static void beforeClass() throws IOException {
        dir = Files.createTempDirectory(null).toFile();
        File file1 = new File(String.format(
                "%s%s%s", dir.getAbsolutePath(), SEP, "text1.txt"));
        file1.createNewFile();

        File subDir = new File(String.format(
                "%s%s%s", dir.getAbsolutePath(), SEP, "subDir"));
        subDir.mkdir();

        File file2 = new File(String.format(
                "%s%s%s", subDir.getAbsolutePath(), SEP, "test2.txt"));
        file2.createNewFile();

        try (RandomAccessFile rm = new RandomAccessFile(file2, "rw")) {
            rm.writeUTF("test");
        }
    }

    @Test
    public void whenFileWithTargetContentExistThenConsumerHaveTargetFileOnField() throws ExecutionException, InterruptedException {
        ConsumerGetter<File> consumer = new ConsumerOfSearch();
        Parallel parallel = new ParallelSearch(5, consumer);
        parallel.startParallelSearch(dir.getAbsolutePath(), "test");

        int sec = 20;
        while (sec != 0) {
            if (consumer.getResult() != null) break;
            Thread.currentThread().sleep(1000);
            sec--;
        }

        final File result = consumer.getResult();
        Assert.assertThat(result.getName(), is("test2.txt"));
    }

    @Test
    public void whenFileWithTargetStringNotExistThen() throws InterruptedException {
        ConsumerGetter<File> consumer = new ConsumerOfSearch();
        Parallel parallel = new ParallelSearch(5, consumer);
        parallel.startParallelSearch(dir.getAbsolutePath(), "no exist text");

        int sec = 1;
        while (sec != 0) {
            if (consumer.getResult() != null) break;
            Thread.currentThread().sleep(1000);
            sec--;
        }

        final File result = consumer.getResult();
        Assert.assertNull(result);
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