package ru.pravvich.lesson_6.checkSocketSeparate;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        new Client().startClient();
    }

    private Socket socket;

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
                out.flush();
                command = this.sendMassage(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sendMassage(OutputStream out) throws IOException {
        BufferedReader readCons = new BufferedReader(
                new InputStreamReader(System.in));

        // А ТУТ ДОЛЖЕН ПРИНЯТЬ НО ЧТО-ТО ИДЕТ НЕ ТАК:
        BufferedWriter send = new BufferedWriter(
                new OutputStreamWriter(out,"UTF8"));

        String command = readCons.readLine();
        send.write(command);
        return command;
    }
}
