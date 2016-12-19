package ru.pravvich.lesson_7PlusCommand;

import ru.pravvich.lesson_7PlusCommand.command.HelpText;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represent root directory for search. And have base methods for
 * work which her, and her contains:
 *
 * @see #searchRegExp(String) - search file by RegExp.
 * @see #searchByName(String) - search file by full name.
 * @see #initContentDir(File) - use only in default constuctor, for
 * record all files which contain in root dir for search. The entire depth.
 */
public class Dir {

    /**
     * Save all files which contain root directory for search.
     * Filled in using the method:
     *
     * @see #initContentDir(File).
     */
    private ArrayList<String> files = new ArrayList<>();

    /**
     * Default constructor. Check exist directory.
     *
     * @param path this path directory for search.
     */
    public Dir(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            this.initContentDir(file);
        } else {
            System.out.println(HelpText.DORDONTEXIST.getText());
        }
    }

    /**
     * Method walk over all directory folder content, and add in
     * ArrayList files all object which not be directory,
     * And result - all files content in param folder and all subfolder
     * go to this.files.
     *
     * @param folder - Folder is root for search.
     */
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

    /**
     * Find by RegExp.
     *
     * @param regExp RegExp for find files which content in path RegExp.
     * @return list contain all files which contain RegExp in path.
     */
    public ArrayList<String> searchRegExp(String regExp) {
        ArrayList<String> targetFiles = new ArrayList<>();
        for (String item : this.files) {
            if (item.contains(regExp) && new File(item).isFile()) {
                targetFiles.add(item);
            }
        }
        return targetFiles;
    }

    /**
     * Find cells in this.files which have param name in end path.
     *
     * @param name full filename for search.
     * @return list with String paths which contain in end param name.
     */
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
