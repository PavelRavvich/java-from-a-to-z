package ru.pravvich.lesson_6.client;

import java.io.*;
import java.net.Socket;
//"d -f /Users/pavel/Desktop/test/client/root.txt"
public class Client {
    private Socket socket;

    public static void main(String[] args) {
        int port = 5213;
        String address = "localhost";

        Client client = new Client();
        client.initSocket(address,port);
        client.sendCommand();
        client.upload("/Users/pavel/Desktop/test/client/root.txt");
    }

    private void initSocket(String address, int port) {
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendCommand() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

             OutputStream out = socket.getOutputStream();
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(out,"UTF8"))
        ) {

            String command = reader.readLine();
            writer.write(command); //пошла на сервер
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upload(String path) {
        try (FileOutputStream out = (FileOutputStream) this.socket.getOutputStream();
             FileInputStream in = new FileInputStream(new File(path))) {

            int data;
            while ((data = in.read()) != -1) {
                out.write(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
