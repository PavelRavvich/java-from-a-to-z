package ru.pravvich.lesson_6.server;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.String.format;

public class Server {


    public static void main(String[] args) {
        Server server = new Server();
        server.initServerSocket();
        server.initSocket();
        server.download("root.txt");
    }

    private ServerSocket serverSocket;
    private Socket socket;

    private void initServerSocket() {
        try {
            this.serverSocket = new ServerSocket(5213);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSocket() {
        try {
            this.socket = this.serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download(String nameFile) {
        try (InputStream input = this.socket.getInputStream()){

            // available() - возвращает колличество сейчас доступных байтов
            byte[] buffer = new byte[input.available()];
            // читаем
            int i = input.read();
            while (i != 0) {
                System.out.println("1.6");
                i = input.read(buffer);
            }
            this.createFile(nameFile,buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean createFile(String nameFile, byte[] buffer) {
        System.out.println("1.7");
        File file = new File(format("%s/%s",Paths.REPO.getPath(),nameFile));
        if (!file.exists()) {
            try (OutputStream out = new FileOutputStream(file)) {
                System.out.println("1.8");
                out.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.exists();
    }
}
