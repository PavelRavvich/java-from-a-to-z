package ru.pravvich.parseFile;

import java.util.Collection;
import java.util.List;

interface Deal {
    Collection<Order> automaticDeals();
    List<Order> getSortedByPriceCollectionBooksWith(
            final String book,
            final String operation
    );
}
