package ru.pravvich.lesson_6.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.lang.String.format;

class MoveFiles {

    static void copy(File source, String target) {
        if (source.exists()) {
            File dest = new File(target);
            try {

                if (Files.copy(source.toPath(), dest.toPath()).toFile().exists()) {
                    System.out.println(format("%s %s\n%s", source.getName(), "Успешно скопирован в:", target));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Файл не существует.");
        }
    }

    static void move(File source, String target) {

        copy(source, target);

        if (source.delete()) {
            System.out.println(format("%s %s", source.getAbsolutePath(), "Успешно удален"));
        } else {
            System.out.println("Не удалось удалить оригинальный файл.");
        }
    }


}