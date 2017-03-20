package cashForFiles;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

import static com.oracle.tools.packager.IOUtils.deleteRecursive;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileReadTest {
    private static File dir;

    @BeforeClass
    public static void beforeClass() throws IOException {
        dir = Files.createTempDirectory(null).toFile();
    }

    @Test
    public void whenFileReadThenStringContentOfFileReturn() throws IOException {
        File directory = new File(dir, "test");
        directory.mkdir();
        File file = new File(String.format("%s%s%s",
                directory.getAbsolutePath(), File.separatorChar, "f.txt"));

        file.createNewFile();

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.write("test".getBytes());
        }

        FileRead fileRead = new FileRead(file);
        String result = fileRead.read();
        assertThat(result, is("test"));
    }

    @AfterClass
    public static void afterClass() throws IOException {
        if (dir == null) {
            return;
        }
        deleteRecursive(dir);
        System.out.println(dir.exists());
    }
}