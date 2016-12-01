package ru.pravvich.lesson_2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String origin;
    private Scanner scanner;
    private ArrayList<String> forDelete = new ArrayList<>();
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    public static void main(String[] args) throws IOException {
        Censure censure = new Censure();
        Client client = new Client();
        client.startApp();
        censure.dropAbuses(client.input(), client.output, client.toArray());
        System.out.println(client.result());
    }

    private String[] toArray() {
        String[] result = new String[this.forDelete.size()];
        for (int i = 0; i != this.forDelete.size(); i++) {
            result[i] = this.forDelete.get(i);
        }
        return result;
    }

    private void startApp() {
        System.out.println("Набор символов для редактирования:");
        this.origin = this.getScanner();
        System.out.println("Символы которые нужно удалить (\"ok\" для завершения ввода):");
        String answer;
        do {
            answer = this.getScanner();
            if (!"ok".equals(answer)) this.forDelete.add(answer);
        } while (!"ok".equals(answer));
    }

    private String getScanner() {
        this.scanner = new Scanner(System.in);
        return this.scanner.nextLine();
    }

    private ByteArrayInputStream input() {
        byte[] bytes = new byte[0];
        try {
            bytes = origin.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(bytes);
    }

    private String result() throws UnsupportedEncodingException {
        byte[] res = this.output.toByteArray();
        return new String(res, "UTF8");
    }

}
