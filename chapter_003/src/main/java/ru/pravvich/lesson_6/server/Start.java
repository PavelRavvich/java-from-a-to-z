package ru.pravvich.lesson_6.server;

public class Start {
    private Server server = new Server();

    public static void main(String[] args) {
//        Input input = new Input();
//        String start = "/Users/pavel/Desktop/test";
//        input.select(new File(start));

        Start start = new Start();
        start.startServer();
    }

    private void startServer() {
        this.server.initServerSocket();
        String command = this.server.getCommand();
        server.acceptSocket();

        while (!"q".equals(command)) {
            if (command.contains("d -f ")) {
                if (this.server.download(toPath(command,"d -f "))) {
                    System.out.println(String.format("Файл %s был загружен на сервер.",command));
                }
            }
            command = this.server.getCommand();
        }
    }

    private static String toPath(String massage, String regExp) {
        String[] arr = massage.split(regExp);
        StringBuilder sb = new StringBuilder();
        for (String word : arr) {
            sb.append(word);
        }
        return new String(sb);
    }
}
