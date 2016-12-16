package ru.pravvich.lesson_7PlusCommand;

import ru.pravvich.lesson_7PlusCommand.command.HelpText;

import java.io.File;
import java.util.ArrayList;

public class Dir {
    private ArrayList<String> files = new ArrayList<>();

    public Dir(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            this.initContentDir(file);
        } else {
            System.out.println(HelpText.DORDONTEXIST.getText());
        }
    }

    // записываем в отдельный лист все файлы
    private void initContentDir(File folder) {
        File[] folderEntries = folder.listFiles();
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    initContentDir(entry);
                } else {
                    this.files.add(entry.getAbsolutePath());
                }
            }
        }
    }

    // ищем пути содержащие имя файла (получается регулярка)
    public ArrayList<String> searchRegExp(String regExp) {
        ArrayList<String> targetFiles = new ArrayList<>();
        for (String item : this.files) {
            if (item.contains(regExp) && new File(item).isFile()) {
                targetFiles.add(item);
            }
        }
        return targetFiles;
    }

    // ищем пути содержащие имя файла
    public ArrayList<String> searchByName(String name) {
        ArrayList<String> targetFiles = new ArrayList<>();
        for (String item : this.files) {
            String[] separatePath = item.split("/");
            if (name.equals(separatePath[separatePath.length - 1])) {
                targetFiles.add(item);
            }
        }
        return targetFiles;
    }
}
