package cashForFiles;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Cash {
    private Map<String, String> cashOfFiles = new HashMap<>();
    private String pathToFolder;

    public Cash(String pathToFolder) {
        this.pathToFolder = pathToFolder;
    }

    public String getFileContent(String name) {
        String content = cashOfFiles.get(name);
        if (content != null) {
            return content;
        }

        String pathToFile = String.format("%s%s%s",
                pathToFolder, File.separatorChar, name);
        File file = new File(pathToFile);

        if (!file.exists()) {
            return "file not exist";
        }

        FileRead reader = new FileRead(file);
        content = reader.read();

        cashOfFiles.put(name, content);

        return content;
    }
}
