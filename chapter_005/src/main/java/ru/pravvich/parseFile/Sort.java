package ru.pravvich.parseFile;

import java.util.Collection;

interface Sort extends Deal {
    Collection<Order> mergeEachBook(Collection<Collection<Order>> orders);
    Collection<Collection<Order>> getCollectionOfCollectionsByBook(Collection<Order> orders);
}
