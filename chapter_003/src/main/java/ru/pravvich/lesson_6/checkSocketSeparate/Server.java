package ru.pravvich.lesson_6.checkSocketSeparate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

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
        System.out.println("Wait...");
        this.socketAccept();
        System.out.println("ok");
        String massage;

        try (InputStream in = this.socket.getInputStream();
             OutputStream out = this.socket.getOutputStream()) {

            // Вот тут передаю in
            massage = this.getMassage(in);

            while (!"q".equals(massage)) {
                System.out.println(massage);
                massage = this.getMassage(in);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getMassage(InputStream in) throws IOException {

        // ВОТ ТУТ ПРИНИМАЮ in НО ЧТО-ТО ИДЕТ НЕ ТАК:
        BufferedReader br = new BufferedReader(
                new InputStreamReader(in,"UTF8"));

        return br.readLine();
    }
}
