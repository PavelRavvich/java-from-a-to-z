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
        client.start();
    }

    private void start() {
        try (OutputStream out = socket.getOutputStream();
             BufferedWriter stringWrite = new BufferedWriter(
                     new OutputStreamWriter(out,"UTF8"))
        ){

            String command = this.sendCommand(stringWrite);
            while (!"q".equals(command)) {
                if (command.contains("d -f ")) {
                    this.upload(this.commandToPath(command, "d -f "), (FileOutputStream) out);
                    System.out.println("Файл отправлен");
                }

                out.flush();
                command = this.sendCommand(stringWrite);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String commandToPath(String command, String regExp) {
        String[] arr = command.split(regExp);
        System.out.println(arr[1]);
        return arr[1];
    }

    private void initSocket(String address, int port) {
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sendCommand(BufferedWriter writer) {
        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String command = reader.readLine();
            writer.write(command); //пошла на сервер
            return command;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

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
}
