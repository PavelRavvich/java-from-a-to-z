package ru.pravvich.lesson_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.String.format;

class Server {

    private ServerSocket serverSocket;
    private Socket socket;

    String getCommand() {
        String command = "error";
        try (InputStream in = this.socket.getInputStream();
             BufferedReader buff = new BufferedReader(
                     new InputStreamReader(in,"UTF8"))
        ) {

            command = buff.readLine();
            return command;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("Строка команды не получила значения от клиента.");
        return command;
    }

    void initServerSocket() {
        try {
            this.serverSocket = new ServerSocket(5213);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void acceptSocket() {
        try {
            this.socket = this.serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean download(String path) {
        File target;
        try (FileInputStream in = (FileInputStream) this.socket.getInputStream();
             FileOutputStream out = new FileOutputStream(target = new File(path))) {

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

    // меняем клиентский путь к файлу на путь к репозиторию сервера
//    private File createPath(String oldPath) {
//        String[] arrPath = oldPath.split("/");
//        return new File(format("%s/%s",Paths.REPO.getPath(),
//                arrPath[arrPath.length - 1]));
//    }













//    boolean download(String nameFile) {
//        try (InputStream input = this.socket.getInputStream()){
//
//            byte[] buffer = new byte[input.available()];//available()колличество доступных байтов
//            int i = input.read();
//            while (i != 0) {
//                i = input.read(buffer);
//            }
//            return this.createFile(nameFile,buffer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


//    private boolean createFile(String nameFile, byte[] buffer) {
//        System.out.println("1.7");
//        File file = new File(format("%s/%s",Paths.REPO.getPath(),nameFile));
//        if (!file.exists()) {
//            try (OutputStream out = new FileOutputStream(file)) {
//                System.out.println("1.8");
//                out.write(buffer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return file.exists();
//    }
}
