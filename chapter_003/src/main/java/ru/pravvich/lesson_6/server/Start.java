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
        System.out.println("Ждем подключения");
        this.server.acceptSocket();
        String command = this.server.getCommand();

        while (!"q".equals(command)) {
            if (command.contains("d -f ")) {
                if (this.server.download(toPathServerRepo(command))) {
                    System.out.println(String.format(
                            "Файл:\n%s\nбыл загружен на сервер.",command));
                }
            }
            break;
            //command = this.server.getCommand();
        }
    }

    // меняем клиентский путь к файлу на путь к репозиторию сервера
    private static String toPathServerRepo(String massage) {
        String[] arr = massage.split("/");
        return Paths.REPO.getPath() + arr[arr.length - 1];
    }
}