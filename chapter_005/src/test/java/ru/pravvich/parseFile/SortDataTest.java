package ru.pravvich.parseFile;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortDataTest {
    @Test
    public void whenCollectionOfCollectionsCallAndDifferentBooksInThenEachBookGoInHisSubCollection() {
        Sort sort = new SortData(new ArrayList<>());
        Collection<Order> orders = new ArrayList<>();
        orders.add(new Order("book-2","sell", 10, 5.5f, 1));
        orders.add(new Order("book-1","sell", 20, 5.5f, 1));
        Collection<Collection<Order>> everyBookHaveHisCollection =
                sort.getCollectionOfCollectionsByBook(orders);

        int result = everyBookHaveHisCollection.size();
        assertThat(result, is(2));
    }

    @Test
    public void whenMergeEachBookCallThenAllBookWithEqualsBookAndPriseMerge() {
        Sort sort = new SortData(new ArrayList<>());
        Collection<Collection<Order>> collectionsOrders = new ArrayList<>();

        Collection<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.5f, 1));
        orders.add(new Order("book-1","sell", 20, 5.5f, 1));
        orders.add(new Order("book-1","buy", 20, 6.5f, 1));
        collectionsOrders.add(orders);

        Collection<Order> orders1 = new ArrayList<>();
        orders1.add(new Order("book-2","buy", 10, 5.5f, 1));
        orders1.add(new Order("book-2","buy", 20, 5.5f, 1));
        orders1.add(new Order("book-2","buy", 20, 6.5f, 1));
        collectionsOrders.add(orders1);


        Collection<Order> res = sort.mergeEachBook(collectionsOrders);
        List<Order> result = new ArrayList<>(res);
        assertThat(result.size(), is(4));
        assertThat(result.get(0).getVolume(),is(30));
    }


}