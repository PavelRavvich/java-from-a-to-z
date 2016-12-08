package ru.pravvich.lesson_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5213);
            System.out.println("Ждем подключение к серверу");
            Socket socket = serverSocket.accept();
            System.out.println("Подключение состоялось");

            InputStream input = socket.getInputStream();
            //OutputStream output = socket.getOutputStream();

            DataInputStream in = new DataInputStream(input);
            //DataOutputStream out = new DataOutputStream(output);

            while (true) {
                //принимаем
                int data = in.read();

                //Отправляем это сообщение обратно.
                //out.writeUTF(massage);
                //out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void unpackingFile(byte[] bytes) {
        System.out.println("Куда положить файл?");
        //Scanner scanner = new Scanner(System.in);
        //ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        //File file = new File(scanner.nextLine());
        //int o = b.read();


    }
}
