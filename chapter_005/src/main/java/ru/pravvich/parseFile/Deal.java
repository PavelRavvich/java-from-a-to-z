package ru.pravvich.parseFile;

import java.util.Collection;
import java.util.List;

interface Deal {
    List<Order> automaticDeals(List<Order> orders);
    List<Order> getSortedByPriceCollectionBooksWith(
            final String book,
            final String operation
    );
}
