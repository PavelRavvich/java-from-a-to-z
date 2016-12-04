package ru.pravvich.lesson_6;

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
            OutputStream output = socket.getOutputStream();
            DataInputStream in = new DataInputStream(input);
            DataOutputStream out = new DataOutputStream(output);
            while (true) {
                String massage = in.readUTF();
                System.out.println(String.format("Мы получили следуещее сообщение:\n%s", massage));
                System.out.println("Отправляем это сообщение обратно.");
                out.writeUTF(massage);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
