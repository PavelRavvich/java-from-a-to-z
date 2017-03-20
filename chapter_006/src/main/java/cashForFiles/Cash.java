package cashForFiles;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cash {
    private Map<SoftReference<String>, SoftReference<String>> cash = new HashMap<>();
    private String pathToFolder;

    public Cash(String pathToFolder) {
        this.pathToFolder = pathToFolder;
    }

    public String getFileContent(String name) {
        SoftReference<String> reference = cash.get(new SoftReference<>(name));
        if (reference != null) {
            return reference.get();
        }

        String pathToFile = String.format("%s%s%s",
                pathToFolder, File.separatorChar, name);
        File file = new File(pathToFile);

        if (!file.exists()) {
            return "file not exist";
        }

        FileRead reader = new FileRead(file);
        String content = reader.read();

        cash.put(new SoftReference<>(name), new SoftReference<>(content));
        return content;
    }
}
