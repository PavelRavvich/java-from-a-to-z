package ru.pravvich.lesson_3;

import java.io.File;
import java.io.FileNotFoundException;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        String content = "12345\n123\n1234\n12\n1";
        FileMutation fileMutation = new FileMutation();

        String pathSrc = "/Users/pavel/Desktop/source.txt";
        String pathDst = "/Users/pavel/Desktop/detonation.txt";

        File src = new File(pathSrc);
        fileMutation.writeContent(src, content);

        File dst = new File(pathDst);
        fileMutation.sort(src, dst);
    }
}