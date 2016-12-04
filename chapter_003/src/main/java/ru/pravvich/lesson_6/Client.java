package ru.pravvich.lesson_6;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port = 5213;
        String address = "127.0.0.1";
        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            System.out.println("Подключаемся к серверу " + port);
            Socket socket = new Socket(address,port);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            DataInputStream in = new DataInputStream(input);
            DataOutputStream out = new DataOutputStream(output);
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите фразу для передачи серверу:");
            while (true) {
                String massage = buff.readLine();
                out.writeUTF(massage);
                out.flush();
                massage = in.readUTF();
                System.out.println("Сервер прислал в ответ " + massage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
