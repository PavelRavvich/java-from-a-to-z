package cashForFiles;

import java.io.BufferedReader;
import java.io.*;

public class FileRead {
    private File file;

    public FileRead(File file) {
        this.file = file;
    }

    public String read() {
        String line;
        StringBuffer sb = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(sb);
    }
}
