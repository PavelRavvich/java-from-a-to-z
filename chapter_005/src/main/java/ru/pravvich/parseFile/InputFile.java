package ru.pravvich.parseFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class InputFile {
    private String pathToFile;

    private List<String> buy = new LinkedList<>();
    private List<String> sell = new LinkedList<>();
    private List<String> delete = new LinkedList<>();

    public InputFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    void readFile() {
        File file = new File(pathToFile);

        try (BufferedReader buff = new BufferedReader(new FileReader(file))){

            String content = buff.readLine();

            while (buff.readLine() != null) {

                if (content.contains("SELL")) {
                    sell.add(content);
                }

                if (content.contains("BUY")) {
                    buy.add(content);
                }

                if (content.contains("DeleteOrder")) {
                    delete.add(content);
                }

                content = buff.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
