package ru.pravvich.lesson_3;

import java.io.*;

class FileMutation {
    // сортируем строки по возрастанию длинны
    void sort(File src, File dst) {
        String[] source = this.read(src.getAbsolutePath()).split("\n");
        for (int i = (source.length - 1); i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (source[j].length() > source[j + 1].length()) {
                    String tmp = source[j + 1];
                    source[j + 1] = source[j];
                    source[j] = tmp;
                }
            }
        }

        // собираем обратно в строку
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i != source.length; i++) {
            sb.append(source[i]).append("\n");
        }

        // записываем
        this.writeContent(dst.getAbsolutePath(), sb.toString());
    }

    // читаем файл
    private String read(String path) {
        String result = "";
        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            int stream = file.read();
            while (stream != -1) {
                result = result + ((char) stream);
                stream = file.read();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Записываем контент в файл
    void writeContent(String path, String content) {
        try (RandomAccessFile file = new RandomAccessFile(path, "rw")) {
            file.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}