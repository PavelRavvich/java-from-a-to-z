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
        Sort sort = new SortData();
        Collection<Order> orders = new ArrayList<>();
        orders.add(new Order("book-2","sell", 5.5f, 10, 1));
        orders.add(new Order("book-1","sell", 5.5f, 20, 1));
        Collection<Collection<Order>> everyBookHaveHisCollection =
                sort.getCollectionOfCollectionsByBook(orders);

        int result = everyBookHaveHisCollection.size();
        assertThat(result, is(2));
    }

    @Test
    public void whenMergeEachBookCallThenAllBookWithEqualsBookAndPriseMerge() {
        Sort sort = new SortData();
        Collection<Collection<Order>> collectionsOrders = new ArrayList<>();

        Collection<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.5f, 10, 1));
        orders.add(new Order("book-1","sell", 5.5f, 20, 1));
        orders.add(new Order("book-1","buy", 6.5f, 20, 1));
        collectionsOrders.add(orders);

        Collection<Order> orders1 = new ArrayList<>();
        orders1.add(new Order("book-2","buy", 5.5f, 10, 1));
        orders1.add(new Order("book-2","buy", 5.5f, 20, 1));
        orders1.add(new Order("book-2","buy", 6.5f, 20, 1));
        collectionsOrders.add(orders1);


        Collection<Order> res = sort.mergeEachBook(collectionsOrders);
        List<Order> result = new ArrayList<>(res);
        assertThat(result.size(), is(4));
        assertThat(result.get(0).getVolume(),is(30));
    }

    @Test
    public void whenAutomaticDealCallAndAllOrdersWithDifferentBooksOperationAndEqualsPriceThenDealPasses() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.4f, 10, 1));
        orders.add(new Order("book-1","sell", 5.5f, 20, 1));
        orders.add(new Order("book-2","buy", 5.5f, 20, 1));
        Deal sort = new SortData();

        Collection<Order> res = sort.automaticDeals(orders);

        assertThat(res.size(), is(1));
    }

    @Test
    public void whenAutomaticDealCallAndOneOrderDeleteThenSizeCollectionReducedByOne() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.5f, 10, 1));
        orders.add(new Order("book-2","buy", 5.5f, 20, 1));
        Deal sort = new SortData();

        Collection<Order> collection = sort.automaticDeals(orders);

        assertThat(collection.size(), is(1));
    }

    @Test
    public void whenAutomaticDealCallAndOneOrderDeleteThenVolumesMerge() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.5f, 10, 1));
        orders.add(new Order("book-2","buy", 5.5f, 20, 1));
        Deal sort = new SortData();

        Collection<Order> collection = sort.automaticDeals(orders);

        float volumeResult = new ArrayList<>(collection).get(0).getVolume();
        assertThat(volumeResult, is(10f));
    }

    @Test
    public void whenAutomaticDealCallAndTwoOrderDeleteThenSizeCollectionReducedByTwo() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.5f, 10, 1));
        orders.add(new Order("book-2","buy", 5.5f, 10, 1));
        Deal sort = new SortData();

        Collection<Order> collection = sort.automaticDeals(orders);

        assertThat(collection.size(), is(0));
    }

    @Test
    public void whenAutomaticDealCallAndTreeOrderDeleteThenSizeCollectionReducedByThree() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.5f, 10, 1));
        orders.add(new Order("book-2","buy", 5.5f, 10, 1));
        orders.add(new Order("book-1","sell", 6.5f, 5, 1));
        orders.add(new Order("book-2","buy", 6.5f, 10, 1));
        Deal sort = new SortData();

        Collection<Order> collection = sort.automaticDeals(orders);

        assertThat(collection.size(), is(1));
    }

    @Test
    public void whenAutomaticDealCallAndAllOrdersHaveSameBookThenNothingHappens() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.5f, 10, 1));
        orders.add(new Order("book-1","buy", 5.5f, 30, 1));
        orders.add(new Order("book-1","sell", 5.5f, 5, 1));
        orders.add(new Order("book-1","buy", 5.5f, 20, 1));
        Deal sort = new SortData();

        Collection<Order> collection = sort.automaticDeals(orders);

        assertThat(collection.size(), is(4));
    }

    @Test
    public void whenGetSortedByPriceCallThenReturnListOfOrdersWithSameBook() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.2f, 10, 1));
        orders.add(new Order("book-1","buy", 7.5f, 30, 1));
        orders.add(new Order("book-1","sell", 5.5f, 5, 1));
        orders.add(new Order("book-1","buy", 4.5f, 20, 1));
        Deal deal = new SortData();

        deal.automaticDeals(orders);
        List<Order> sortedOrderWithSameBook =
                deal.getSortedByPriceCollectionBooksWith("book-1", "sell");

        String resultBook = sortedOrderWithSameBook.get(0).getBook();

        assertThat(resultBook, is("book-1"));
    }

    @Test
    public void whenGetSortedByPriceCallThenReturnListOfOrdersWithSameOperation() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.2f, 10, 1));
        orders.add(new Order("book-1","buy", 7.5f, 30, 1));
        orders.add(new Order("book-1","sell", 5.5f, 5, 1));
        orders.add(new Order("book-1","buy", 4.5f, 20, 1));
        Deal deal = new SortData();

        deal.automaticDeals(orders);
        List<Order> sortedOrderWithSameBook =
                deal.getSortedByPriceCollectionBooksWith("book-1", "sell");

        String resultBook = sortedOrderWithSameBook.get(0).getOperation();

        assertThat(resultBook, is("sell"));
    }

    @Test
    public void whenGetSortedByPriceCallThenReturnListOfOrdersSortedByPrise() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 5.2f, 10, 1));
        orders.add(new Order("book-1","buy", 7.5f, 30, 1));
        orders.add(new Order("book-1","sell", 5.5f, 5, 1));
        orders.add(new Order("book-1","buy", 4.5f, 20, 1));
        Deal deal = new SortData();
        deal.automaticDeals(orders);
        List<Order> sortedOrders =
                deal.getSortedByPriceCollectionBooksWith("book-1", "sell");

        boolean result = sortedOrders.get(0).getPrice() <
                sortedOrders.get(1).getPrice();

        assertTrue(result);
    }
}