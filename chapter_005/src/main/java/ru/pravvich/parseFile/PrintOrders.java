package ru.pravvich.parseFile;

import java.util.*;

public class PrintOrders {
    private Input input;
    private Sort sort;
    private Deal autoDeals;
    private List<Order> listForPrint;

    public PrintOrders(Sort sort, AutomaticDeal autoDeals) {
        listForPrint = new LinkedList<>();
        this.autoDeals = autoDeals;
        this.sort = sort;
    }

    public void print() {
        input = new InputFile("");
        Collection<Order> allOrder;
        sort = new SortData(allOrder = input.readFile());

    }

}
