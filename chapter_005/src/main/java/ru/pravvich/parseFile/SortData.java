package ru.pravvich.parseFile;

import java.util.*;
import java.util.stream.Collectors;

class SortData implements Sort {
    private Collection<Order> allOrders;

    SortData(Collection<Order> allOrders) {
        this.allOrders = allOrders;
        sortData();
    }

    private void sortData() {
        // сырая коллекция
        System.out.println("all : "+allOrders.size());

        Collection<Collection<Order>> collectionOfCollections =
                getCollectionOfCollections(allOrders);

        System.out.println(collectionOfCollections.size());

        // группируем однопрайсовые заявки
        allOrders = mergeEachBook(collectionOfCollections);
        System.out.println("merge : "+allOrders.size());

        // автоматические сделки
        allOrders = automaticDeals(new LinkedList<>(allOrders));
        System.out.println(allOrders.size());
    }

    @Override
    public List<Order> getCollectionOfOrdersWith(String book, String operation) {
        List<Order> orders =  allOrders.parallelStream()
                .filter(order -> book.equals(order.getBook()) &&
                        operation.equals(order.getOperation()))
                .collect(Collectors.toList());

        System.out.println(orders.size());
        sortOrdersByPrise(orders);
        return orders;
    }

    private void sortOrdersByPrise(List<Order> orders) {
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                Float fstVal = o1.getPrice();
                Float scdVal = o2.getPrice();
                return fstVal.compareTo(scdVal);
            }
        });
    }

    private Collection<Collection<Order>> getCollectionOfCollections(Collection<Order> orders) {
        Map<String, Collection<Order>> booksKey = new HashMap<>();
        orders.forEach(order -> {
            Collection<Order> orderCollection = booksKey.get(order.getBook());
            if (orderCollection == null) {
                orderCollection = new LinkedList<>();
                orderCollection.add(order);
                booksKey.put(order.getBook(), orderCollection);
            } else {
                orderCollection.add(order);
            }
        });
        return booksKey.values();
    }

    private Collection<Order> mergeEachBook(Collection<Collection<Order>> orders) {
        List<Order> result = new LinkedList<>();
        for (Collection<Order> currentBook : orders) {
            Collection<Order> merged = mergeOrdersByPriceAndOperation(currentBook);
            result.addAll(merged);
        }
        return result;
    }

    private Collection<Order> mergeOrdersByPriceAndOperation(Collection<Order> orders) {
        Map<Float, Order> orderMap = new HashMap<>();
        //Проходим по старым ордерам
        orders.forEach(order -> {
            //Пытаемся по прайсу ордера найти уже существующий
            Order o = orderMap.get(order.getPrice());
            if (o != null && Objects.equals(o.getOperation(), order.getOperation())) {
                o.setVolume(o.getVolume() + order.getVolume());
            } else {
                orderMap.put(order.getPrice(), order);
            }
        });

        return orderMap.values();
    }

    private Collection<Order> automaticDeals(List<Order> orders) {
        List<Order> ordersForDelete = new LinkedList<>();
        for (Order fstOrder : orders) {
            for (Order scdOrder : orders) {
                List<Order> forDelete = deal(fstOrder, scdOrder);
                ordersForDelete.addAll(forDelete);
            }
        }

        ordersForDelete.forEach(orders::remove);
        return orders;
    }

    private List<Order> deal(Order fstOrder, Order scdOrder) {
        List<Order> ordersForDelete = new LinkedList<>();
        if (transactionIsImpossible(fstOrder, scdOrder)) {
            return ordersForDelete;
        }

        if (fstOrder.getVolume() < scdOrder.getVolume()) {
            int volume = scdOrder.getVolume() - fstOrder.getVolume();
            scdOrder.setVolume(volume);
            ordersForDelete.add(fstOrder);
        } else if (fstOrder.getVolume() > scdOrder.getVolume()) {
            int volume = fstOrder.getVolume() - scdOrder.getVolume();
            fstOrder.setVolume(volume);
            ordersForDelete.add(scdOrder);
        } else {
            ordersForDelete.add(fstOrder);
            ordersForDelete.add(scdOrder);
        }
        return ordersForDelete;
    }

    private boolean transactionIsImpossible(Order fst, Order snd) {
        return fst.getBook().equals(snd.getBook()) &&
                fst.getOperation().equals(snd.getOperation()) &&
                !(fst.getPrice() == snd.getPrice());
    }

    public static void main(String[] args) {
        Input input = new InputFile("/Users/pavel/Desktop/orders.xml");
        Sort sort = new SortData(input.readFile());
        List<Order> list = sort.getCollectionOfOrdersWith("book-1","SELL");
        for (Order order : list) {
            System.out.println(order);
        }
    }
}