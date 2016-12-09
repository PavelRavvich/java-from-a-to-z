package ru.pravvich.lesson_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.String.format;

public class Server {


    public static void main(String[] args) {
    }

    ServerSocket serverSocket;
    Socket socket;

    void initServerSocket() {
        try {
            this.serverSocket = new ServerSocket(5213);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initSocket() {
        try {
            this.socket = this.serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void download(String nameFile) {
        try (InputStream input = this.socket.getInputStream()){
            // available() - возвращает колличество сейчас доступных байтов
            byte[] buffer = new byte[input.available()];
            // читаем
            int i = input.read(buffer);
            while (i != -1) {
                i = input.read(buffer);
            }
            this.createFile(nameFile,buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean createFile(String nameFile, byte[] buffer) {
        File file = new File(format("%s/%s",Paths.REPO.getPath(),nameFile));
        if (!file.exists()) {
            try (OutputStream out = new FileOutputStream(file)) {
                out.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.exists();
    }
}
