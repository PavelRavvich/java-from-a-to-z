package ru.pravvich.parseFile;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Print print = new PrintOrders();
        print.getDataFrom("/Users/pavel/Desktop/mapByBook.xml");
        print.start();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }
}
