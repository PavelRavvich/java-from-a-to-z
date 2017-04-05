package fileSearch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CollectingTXTFilesTest {
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
                "%s%s%s", subDir.getAbsolutePath(), SEP, "text2.txt"));
        file2.createNewFile();
    }


    @Test
    public void whenCollectTXTFilesWorkThenAllTXTFilesFromRootINDepthGoInQueue() {
        Queue<File> files = new ConcurrentLinkedQueue<>();
        Collector collecting =
                new CollectingTXTFiles(dir.getAbsolutePath() , files);

        collecting.collectTXTFromRoot();

        File result01 = files.poll();
        File result02 = files.poll();

        assertThat(result01.getName(), is("text2.txt"));
        assertThat(result02.getName(), is("text1.txt"));
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