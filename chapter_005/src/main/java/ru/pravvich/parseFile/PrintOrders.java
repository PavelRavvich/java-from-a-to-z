package ru.pravvich.parseFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintOrders {
    private Sort sort;

    public PrintOrders(Sort sort) {
        this.sort = sort;
    }
//
//    public void print() {
//        for (Map.Entry<String, Map<String, Order>> orderList : sort.sortData().entrySet()) {
//            for (Map.Entry<String, Order> order : orderList.getValue().entrySet()) {
//                for (Order order1 : new ArrayList<Order>(order))
//                System.out.println(String.format(
//                        "%s \t %s \t %s \t %s", order.getOperation(),
//                        order.getBook(), order.getVolume(), order.getPrice()
//                ));
//            }
//        }
//    }

//    public static void main(String[] args) {
//        Input input = new InputFile("/Users/pavel/Desktop/order1.xml");
//        Sort sort = new SortData(input);
//        sort.sortData();
//        PrintOrders printOrders = new PrintOrders(sort);
//        printOrders.print();
//    }
}
