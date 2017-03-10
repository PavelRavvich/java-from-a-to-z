package ru.pravvich.parseFile;

import java.util.*;

public class PrintOrders implements Print {
    private Sort sort;

    @Override
    public List<Order> getDataFrom(String path) {
        Input input = new InputFile(path);
        Collection<Order> allOrders = input.readFile();
        sort = new SortData();

        Collection<Collection<Order>> collectionCollection =
                sort.getCollectionOfCollectionsByBook(allOrders);

        Collection<Order> mergedOrders =
                sort.mergeEachBook(collectionCollection);

        return sort.automaticDeals(new ArrayList<>(mergedOrders));
    }

    private void printData(String book, String operation) {
        sort.getSortedByPriceCollectionBooksWith(book, operation)
                .forEach(System.out::println);
    }

    @Override
    public void start() {
        printData("book-1", "BUY");
        printData("book-1", "SELL");
        System.out.println();
        printData("book-2", "BUY");
        printData("book-2", "SELL");
        System.out.println();
        printData("book-3", "BUY");
        printData("book-3", "SELL");
    }
}
