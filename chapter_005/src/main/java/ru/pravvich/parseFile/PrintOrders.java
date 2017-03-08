package ru.pravvich.parseFile;

import java.util.*;

public class PrintOrders {
    private Sort sort;

    public List<Order> initDataStructure(String path) {
        Input input = new InputFile(path);
        long start = System.currentTimeMillis();
        Collection<Order> allOrders = input.readFile();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);

        sort = new SortData();

        Collection<Collection<Order>> collectionCollection =
                sort.getCollectionOfCollectionsByBook(allOrders);



        Collection<Order> mergedOrders =
                sort.mergeEachBook(collectionCollection);

        return sort.automaticDeals(new ArrayList<>(mergedOrders));
    }

    public void print(String book, String operation) {
        sort.getSortedByPriceCollectionBooksWith(book, operation)
                .forEach(System.out::println);
    }

    public void start() {
        print("book-1", "BUY");
        print("book-1", "SELL");
        System.out.println();
        print("book-2", "BUY");
        print("book-2", "SELL");
        System.out.println();
        print("book-3", "BUY");
        print("book-3", "SELL");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        PrintOrders printOrders = new PrintOrders();
        printOrders.initDataStructure("/Users/pavel/Desktop/orders.xml");
        printOrders.start();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }
}
