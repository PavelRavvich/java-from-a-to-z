package ru.pravvich.parseFile;

import java.util.*;
import java.util.stream.Collectors;

class SortData implements Sort {
    private List<Order> committedDeal;

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

    @Override
    public List<Order> getSortedByPriceCollectionBooksWith(
            final String book,
            final String operation
    ) {

        List<Order> orders =
                committedDeal.parallelStream().filter(order -> book
                        .equals(order.getBook()) &&
                        operation.equals(order.getOperation()))
                        .collect(Collectors.toList());

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

    @Override
    public List<Order> automaticDeals(List<Order> orders) {
        List<Order> result = new LinkedList<>();
        for (int i = 0; i < orders.size(); i++) {
            for (int j = i + 1 ;j < orders.size(); j++) {
                deal(orders.get(i), orders.get(j));
            }
        }

        result.addAll(orders.stream().filter(
                order -> 0 != order.getPrice())
                .collect(Collectors.toList()));

        return committedDeal = result;
    }

    private void deal(Order fstOrder, Order scdOrder) {
        if (dealIsImpossible(fstOrder, scdOrder)) {
            return;
        }

        if (fstOrder.getVolume() < scdOrder.getVolume()) {
            int volume = scdOrder.getVolume() - fstOrder.getVolume();
            scdOrder.setVolume(volume);
            fstOrder.setPrice(0);
        } else if (fstOrder.getVolume() > scdOrder.getVolume()) {
            int volume = fstOrder.getVolume() - scdOrder.getVolume();
            fstOrder.setVolume(volume);
            scdOrder.setPrice(0);
        } else {
            fstOrder.setPrice(0);
            scdOrder.setPrice(0);
        }
    }

    private boolean dealIsImpossible(Order fst, Order snd) {
        return fst.getBook().equals(snd.getBook()) ||
                fst.getOperation().equals(snd.getOperation()) ||
                fst.getPrice() != snd.getPrice();
    }
}