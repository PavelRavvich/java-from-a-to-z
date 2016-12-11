package ru.pravvich.lesson_6.server;

import java.io.*;

public class Start {
    private Server server = new Server();

    public static void main(String[] args) {
        Start start = new Start();
        start.startServer();
    }

    private void startServer() {
        System.out.println("Ждем подключения к серверу...");
        this.server.initServerSocket();
        this.server.acceptSocket();
        System.out.println("Подключение установлено.");

        try (InputStream in = this.server.getSocket().getInputStream();
             OutputStream out = this.server.getSocket().getOutputStream();
             BufferedReader stringIn = new BufferedReader(new InputStreamReader(in, "UTF8"))
        ) {

            String command = this.server.getCommand(stringIn);
            System.out.println(command);
            while (!"q".equals(command)) {
                System.out.println("whole");
                if (command.contains("d -f ")) {
                    System.out.println("first if");
                    if (this.server.download(toPathServerRepo(command), ((FileInputStream) in))) {
                        System.out.println(String.format("Файл:\n%s\nбыл загружен на сервер.", command));
                    }
                }

                out.flush();
                command = this.server.getCommand(stringIn);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // меняем клиентский путь к файлу на путь к репозиторию сервера
    private static String toPathServerRepo(String massage) {
        String[] arr = massage.split("/");
        String newPath = Paths.REPO.getPath() + arr[arr.length - 1];
        System.out.println(newPath);
        return newPath;
    }
}