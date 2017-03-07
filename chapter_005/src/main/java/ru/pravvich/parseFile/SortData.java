package ru.pravvich.parseFile;

import java.util.*;

class SortData implements Sort {
    private Collection<Order> allOrders;

    @Override
    public Collection<Order> getAllOrders() {
        return allOrders;
    }

    SortData(Collection<Order> allOrders) {
        this.allOrders = allOrders;
    }

    @Override
    public Collection<Collection<Order>> getCollectionOfCollectionsByBook(Collection<Order> orders) {
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

    @Override
    public Collection<Order> mergeEachBook(Collection<Collection<Order>> orders) {
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
}