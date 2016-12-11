package ru.pravvich.lesson_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    private ServerSocket serverSocket;
    private Socket socket;

    Socket getSocket() {
        return socket;
    }

    void initServerSocket() {
        try {
            this.serverSocket = new ServerSocket(5213);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void acceptSocket() {
        try {
            this.socket = this.serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Получаем строку от клиента через сокет
    String getCommand(InputStream in) {

        String command = "error";
        try (BufferedReader stringIn = new BufferedReader(new InputStreamReader(in,"UTF8"))) {
            command = stringIn.readLine();
            System.out.println(command);
            return command;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("Строка команды не получила значения от клиента.");
        return command;
    }

    // загружаем файл из сокета и записываем на свой диск
    boolean download(String path, FileInputStream in) {
        File target;
        try (FileOutputStream out = new FileOutputStream(target = new File(path))) {

            int data;
            while ((data = in.read()) != -1) {
                out.write(data);
            }

            if (target.exists())
                return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}