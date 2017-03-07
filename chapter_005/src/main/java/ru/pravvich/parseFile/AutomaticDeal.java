package ru.pravvich.parseFile;

import java.util.*;
import java.util.stream.Collectors;

class AutomaticDeal implements Deal {
    private List<Order> orders;
    private List<Order> committedDeal;

    AutomaticDeal(ArrayList<Order> orders) {
        this.orders = orders;
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
    public Collection<Order> automaticDeals() {
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
