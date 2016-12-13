package ru.pravvich.lesson_6.client;

import java.io.*;
import java.net.Socket;

import static java.lang.String.format;

//"d -f /Users/pavel/Desktop/test/server/root.txt"
//"u -f /Users/pavel/Desktop/test/client/root.txt"
public class Client {

    private Socket socket;

    public static void main(String[] args) {
        new Client().startClient();
    }

    private void connections() {
        try {
            this.socket = new Socket("localhost",5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClient() {
        this.connections();

        try (InputStream in = this.socket.getInputStream();
             OutputStream out = this.socket.getOutputStream()) {

            // вот тут передаю out
            String command = this.sendMassage(out);

            while (!"q".equals(command)) {
                System.out.println(command);

                // загружаем на сервер
                if (command.contains("u -f ")) {
                    this.upload(command.replace("u -f ", ""), ((FileOutputStream) out));

                    // скачиваем с сервера
                } else if (command.contains("d -f ")) {
                    String clientPath = this.toClientPath(command);
                    System.out.println(clientPath);
                    if (this.download(clientPath, (FileInputStream) in)) {
                        System.out.println(format("Файл:\n%s\nуспешно создан.",clientPath));
                    }
                } else if (command.equals("ls") || command.contains("cd ")) {
                    print(this.getObj(in));
                } else {
                    System.out.println("Неизвестная команда");
                }

                out.flush();
                command = this.sendMassage(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // принимаем массив строк из сокета
    private String[] getObj(InputStream objIn) throws IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(objIn);
            return (String[]) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    // загружаем файл из сокета и записываем на свой диск
    private boolean download(String path, FileInputStream in) {
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

    // преобразуем в путь для клиентского диска
    private String toClientPath(String clientPath) {
        String[] arr = clientPath.split("/");
        return format("/Users/pavel/Desktop/test/client/%s",arr[arr.length - 1]);
    }

    // отправка сообщения в сокет
    private String sendMassage(OutputStream out) throws IOException {
        BufferedReader readCons = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dos = new DataOutputStream(out);

        String command = readCons.readLine();
        dos.writeUTF(command);
        return command;
    }

    // отправка файла в сокет
    private void upload(String path, FileOutputStream out) {
        try (FileInputStream in = new FileInputStream(new File(path))) {

            int data;
            while ((data = in.read()) != -1) {
                out.write(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(String[] list) {
        for (String item : list) {
            System.out.println(item);
        }
    }
}