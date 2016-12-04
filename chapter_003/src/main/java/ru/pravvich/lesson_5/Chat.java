package ru.pravvich.lesson_5;

import java.io.*;
import java.util.Random;

class Chat {
    private String path;
    private String fileContent = "";
    private Random random = new Random();
    private String logBotAnswer;

    Chat(String path) {
        this.path = path;
        this.writeFile();
    }

    // читаем файл с авто-ответами
    private void writeFile() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(this.path), "UTF-8"))) {
            String sub;
            while ((sub = br.readLine()) != null) {
                this.fileContent = String.format("%s%s\n", this.fileContent, sub);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // флаги и логика ответов
    boolean select(String key, boolean botOn) {
        if (key.equals("стоп")) {
            botOn = false;
            this.logBotAnswer = "";
        }
        if (botOn) System.out.println(this.getRandomAnswer());
        if (key.equals("продолжить")) botOn = true;
        return botOn;
    }

    // для записи в лог диалога
    String getBotAnswer() {
        return this.logBotAnswer;
    }

    // возвращаем случайный ответ(разбив по строчкам)
    private String getRandomAnswer() {
        String[] contentArr = this.fileContent.split("\n");
        int index = this.random.nextInt(contentArr.length - 1);
        this.logBotAnswer = contentArr[index];
        return contentArr[index];
    }

    // запись беседы в файл
    void bigBrotherSee(String data) {
        File file = new File("/Users/pavel/Desktop/protocol.txt");
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(data.getBytes("UTF8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}