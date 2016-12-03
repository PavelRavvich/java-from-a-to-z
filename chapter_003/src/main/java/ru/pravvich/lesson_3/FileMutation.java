package ru.pravvich.lesson_3;

import java.io.*;
import java.util.Scanner;

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
        this.writeContent(dst, sb.toString());
    }

    // Записываем контент в файл
    void writeContent(File file, String content) {
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            fileOut.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Читаем из файла
    private String read(String pathToFile) {
        try (Scanner scanner = new Scanner(new FileInputStream(pathToFile))) {
            String tmp = "";
            int count = 0;
            while (count != this.countingLines(pathToFile)) {
                tmp = tmp + scanner.nextLine() + "\n";
                count++;
            }
            return tmp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Считаем колличество строк в файле
    private int countingLines(String pathToFile) {
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            while (bufferedReader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}