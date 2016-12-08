package ru.pravvich.lesson_6.client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.oracle.tools.packager.IOUtils.deleteRecursive;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ViewFileSystemTest {

    private static File dir;

    @BeforeClass
    public static void beforeClass() throws IOException {
        dir = Files.createTempDirectory(null).toFile();
    }

    /**
     * @see ViewFileSystem#seeCatalog(File)
     */
    @Test
    public void whenFileIsCatalogAndSeeCatalogRunThenPrintCatalogContains() {
        ViewFileSystem view = new ViewFileSystem();
        File file = new File(dir, "test");
        file.mkdir();
        view.seeCatalog(dir);
        // не знаю что тут проверять ассертом
    }

    /**
     * @see ViewFileSystem#moveUp(File)
     */
    @Test
    public void whenFileIsCatalogAndMoveDownRunThenReturnFileInterior() {
        ViewFileSystem view = new ViewFileSystem();
        File file = new File(dir, "test");
        file.mkdir();
        File result = view.moveDown(dir, "test");
        assertThat(result, is(file));
    }

    /**
     * @see ViewFileSystem#moveUp(File)
     */
    @Test
    public void whenMoveUpRunThenReturnNewFileOfParentCatalog() {
        ViewFileSystem view = new ViewFileSystem();
        File file = new File(dir, "test");
        file.mkdir();
        File file1 = new File(file, "test1");
        file.mkdir();
        File result = view.moveUp(file1);
        assertThat(result, is(file));
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