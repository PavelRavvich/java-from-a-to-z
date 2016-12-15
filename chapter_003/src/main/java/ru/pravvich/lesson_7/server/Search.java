package ru.pravvich.lesson_7.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class Search {
    public static void main(String[] args) {
        new Search().start();
    }

    private void start() {
        System.out.println("Помощь: \"h\"");
        ArrayList<String> target = null;
        String command;
        try (Scanner scanner = new Scanner(System.in)) {
            command = scanner.nextLine();
            while (!"q".equals(command)) {
                if ("-f".equals(command)) {
                    // можно сделать
                    System.out.println("Введите полное имя директории для поиска в формате \"absolute path.../dir\":");
                    Dir dir = new Dir(scanner.nextLine());
                    System.out.println("Введите имя файла для поиска:");
                    target = dir.searchByName(scanner.nextLine());
                    target.forEach(System.out::println);
                } else if ("-r".equals(command)) {
                    System.out.println("Введите полное имя директории для поиска в формате \"absolute path.../dir\":");
                    Dir dir = new Dir(scanner.nextLine());
                    System.out.println("Введите часть имени файла для поиска:");
                    target = dir.searchRegExp(scanner.nextLine());
                    System.out.println("Найдено:");
                    target.forEach(System.out::println);
                } else if ("-o".equals(command)) {
                    if (target != null) {
                        System.out.println("Введите полное имя директории куда сохранить файл в формате \"absolute path.../dir/\":");
                        String path = scanner.nextLine();
                        System.out.println("Введите имя файла в формате \"file.extension\":");
                        String name = scanner.nextLine();
                        // получаем каталог куда сохранять
                        if (this.exist(path, name)) {
                            this.write(target, path + name);
                        } else {
                            System.out.println("Действие не возможно.");
                        }
                    }
                } else if ("h".equals(command)) {
                    printManual();
                } else {
                    System.err.println("Неизвестная команда.");
                    printManual();
                }
                command = scanner.nextLine();
            }
        }
    }



    private boolean exist(String path, String name) {
        String[] pathArr = path.split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i != (pathArr.length - 1); i++) {
            sb.append(pathArr[i]);
        }
        String dir = new String(sb);
        //проверяем существует ли директория && проверяем нет ли уже такого файла
        return new File(dir).isDirectory() && !(new File(format("%s%s", path, name)).exists());
    }

    private static void printManual() {
        System.out.println(
                "Искать файл по полному имени: \"-f\"\nИскать файл по регулярному выражению: \"-r\"\nЗаписать файл: \"-o\"");
    }

    // пишем текст в файл
    private void write(ArrayList<String> paths, String repo) {
        try (FileOutputStream out = new FileOutputStream(repo)) {
            for (String path : paths) {
                out.write(format("%s\n",path).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
