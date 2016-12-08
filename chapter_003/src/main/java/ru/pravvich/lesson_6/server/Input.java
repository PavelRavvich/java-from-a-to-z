package ru.pravvich.lesson_6.server;

import java.io.File;
import java.util.Scanner;

public class Input {
    private ViewFileSystem view = new ViewFileSystem();
    private Scanner scanner = new Scanner(System.in);

    void select(File thisPosition) {

        String key= this.scanner.nextLine();

        while (!key.equals("q")) {
            if (key.equals("cd ..")) {
                thisPosition = this.view.moveUp(thisPosition);
            } else if (key.equals("ls")) {
                this.view.seeCatalog(thisPosition);
            } else if (key.equals("copy")) {
                File src = new File(this.action());
                System.out.println("Введите имя директории для копирования файла в формате: 'Absolute_path... /file'");
                MoveFiles.copy(src,this.scanner.nextLine());
            } else if (key.equals("move")) {
                File src = new File(this.action());
                System.out.println("Введите имя директории для переноса файла в формате: 'Absolute_path... /file'");
                MoveFiles.move(src,this.scanner.nextLine());
                thisPosition = this.view.moveDown(thisPosition,key);
            } else {
                thisPosition = this.view.moveDown(thisPosition,key);
            }
            key = this.scanner.nextLine();
        }
        System.out.println("Goodbye!");
    }

    private String action() {
        System.out.println("Введите имя файла:");
        String fileName = this.scanner.nextLine();
        System.out.println(fileName);
        return String.format("%s/%s",ViewFileSystem.path,fileName);
    }
}