package ru.pravvich.lesson_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {

    }

    ServerSocket serverSocket;
    Socket socket;

    void initSocket() {
        try {
            this.serverSocket = new ServerSocket(5213);
            this.socket = this.serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void download(String nameFile) {
        try (InputStream input = this.socket.getInputStream()){
            // Саздаём пустой массив байтов из available()
            // available() - возвращает колличество сейчас доступных байтов
            byte[] buffer = new byte[input.available()];
            // читаем
            int i = input.read(buffer);
            while (i != -1) {
                i = input.read(buffer);
            }

            File file = new File(Paths.REPO.getPath() + "/" + nameFile);

            // Саздаём OutputStream из файла  ...
            OutputStream outStream = new FileOutputStream(file);

            // Записываем массив байтов в файл
            outStream.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
