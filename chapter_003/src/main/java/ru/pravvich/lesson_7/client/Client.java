package ru.pravvich.lesson_7.client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class Client {

    private Socket socket;

    public static void main(String[] args) {
        new Client().startClient();
    }

    private static void printManual() {
        System.out.println(
                "Искать файл по полному имени: \"-f\"\nИскать файл по регулярному выражению: \"-r\"\nЗаписать файл: \"-o\"");
    }

    // отделяем имя файла от остального пути
    private static String cutFileName(String src) {
        String[] splitPath = src.split("/");
        return splitPath[splitPath.length - 1];
    }

    private void initSocket() {
        try {
            this.socket = new Socket("localhost", 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClient() {
        this.initSocket();
        System.out.println("Connection accept");
        printManual();
        try (InputStream in = this.socket.getInputStream();
             OutputStream out = this.socket.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {

            String massage = scanner.nextLine();
            this.sendMassage(out, massage);

            while (!"q".equals(massage)) {

                // поиск по полному имени файла или по регулярному выражению
                if (("-f").equals(massage) || ("-r").equals(massage)) {
                    System.out.println("Введите полное имя директории для поиска:");
                    this.sendMassage(out, scanner.nextLine());
                    System.out.println("Введите имя файла для поиска:");
                    this.sendMassage(out, scanner.nextLine());
                    this.getObj(in).forEach(System.out::println);

                // запись файла - получаем из сокета и пишем на свой диск
                } else if ("-o".equals(massage)) {
                    System.out.println("Введите полный путь к файлу-ресурсу в формате \".../file.extension\" :");
                    // src отправляем на сервер путь к файлу-ресурсу
                    String src = scanner.nextLine();
                    System.out.println("Введите полное имя директории куда сохранить файл в формате \".../dir\" : ");
                    // target отстается в клиенте - часть пути к файлу без /имя.расширение
                    String target = format("%s/%s", scanner.nextLine(), cutFileName(src));
                    this.sendMassage(out, src);
                    if (this.download(target, (FileInputStream) in)) {
                        System.out.println("Файл записан.\n");
                    }

                // получаем мануал по ключу
                } else if ("-h".equals(massage)) {
                    printManual();
                } else {
                    System.err.println("Неизвесная команда, попробуте снова.\nПрочитайте мануал еще раз :");
                    printManual();
                }

                massage = scanner.nextLine();
                this.sendMassage(out, massage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // отправка сообщения в сокет
    private void sendMassage(OutputStream out, String massage) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(massage);
    }

    //принимаем объект в сокет
    private ArrayList<String> getObj(InputStream objIn) throws IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(objIn);
            return (ArrayList<String>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return new ArrayList<>();
    }

    // загружаем файл из сокета и записываем на свой диск
    private boolean download(String path, FileInputStream in) {
        File target;
        try (FileOutputStream out = new FileOutputStream(target = new File(path))) {

            int data;
            while ((data = in.read()) != 255) {
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
