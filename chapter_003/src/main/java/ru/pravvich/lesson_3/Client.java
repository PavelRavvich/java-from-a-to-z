package ru.pravvich.lesson_3;

import java.io.File;

public class Client {
    public static void main(String[] args) {
        String content = "12345\n123\n1234\n12\n1";
        FileMutation fileMutation = new FileMutation();

        String pathSrc = "/Users/pavel/Desktop/source.txt";
        String pathDst = "/Users/pavel/Desktop/detonation.txt";

        File src = new File(pathSrc);
        fileMutation.writeContent(src.getAbsolutePath(), content);
        File dst = new File(pathDst);
        fileMutation.sort(src, dst);
    }
}