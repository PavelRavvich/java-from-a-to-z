package ru.pravvich.lesson_6.client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port = 5213;
        String address = "127.0.0.1";
        Client client = new Client();
        client.initSocket(address,port);
        client.upload("/Users/pavel/Desktop/test/client/root.txt");

    }

    private Socket socket;

    private void initSocket(String address, int port) {
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upload(String path) {
        try (OutputStream out = this.socket.getOutputStream();
             FileInputStream fileIn = new FileInputStream(new File(path));
             BufferedInputStream buffer = new BufferedInputStream(fileIn)) {

            byte[] bytes = new byte[(int) new File(path).length()];
            buffer.read(bytes, 0, bytes.length);
            out.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
