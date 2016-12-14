package ru.pravvich.lesson_7.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;

    public static void main(String[] args) {
        new Server().startServer();
    }

    private void initServerSocket() {
        try {
            this.serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void socketAccept() {
        try {
            this.socket = this.serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServer() {
        this.initServerSocket();
        System.out.println("Wait connection...");
        this.socketAccept();
        System.out.println("Connection accept");

        try (InputStream in = this.socket.getInputStream();
             OutputStream out = this.socket.getOutputStream()) {

            String massage = this.getMassage(in);

            while (!"q".equals(massage)) {

                // поиск по регулярному выражению
                if ("-r".equals(massage)) {
                    // получение полного имени директории в которой будет поиск:
                    Dir dir = new Dir(this.getMassage(in));
                    //Введите имя файла для поиска:
                    massage = this.getMassage(in);
                    ArrayList<String> target = dir.searchRegExp(massage);
                    this.writeList(out, target);

                // поиск по полному имени файла
                } else if ("-f".equals(massage)) {
                    // получение полного имени директории в которой будет поиск:
                    Dir dir = new Dir(this.getMassage(in));
                    //Введите имя файла для поиска:
                    massage = this.getMassage(in);
                    ArrayList<String> target = dir.searchByName(massage);
                    this.writeList(out, target);

                // копирование файла в нужное мето
                } else if ("-o".equals(massage)) {
                    // принимаем путь к копируемому файлу
                    massage = this.getMassage(in);
                    this.upload(massage, (FileOutputStream) out);
                } else {
                    System.out.println("Нет такой команды");
                }
                // выбор нового действия
                massage = this.getMassage(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // получение сообщения из сокета
    private String getMassage(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        return dis.readUTF();
    }

    // отправляем объект в сокет
    private void writeList(OutputStream out, ArrayList<String> list) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(list);
    }

    // отправка файла в сокет
    private void upload(String path, FileOutputStream out) {
        try (FileInputStream in = new FileInputStream(new File(path))) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(data);
            }

            out.write(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}