package ru.pravvich.parseFile;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AutomaticDealTest {
    @Test
    public void whenAutomaticDealCallAndAllOrdersWithDifferentBooksOperationAndEqualsPriceThenDealPasses() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.4f, 1));
        orders.add(new Order("book-1","sell", 20, 5.5f, 1));
        orders.add(new Order("book-2","buy", 20, 5.5f, 1));
        Deal sort = new AutomaticDeal(orders);

        Collection<Order> res = sort.automaticDeals();

        assertThat(res.size(), is(1));
    }

    @Test
    public void whenAutomaticDealCallAndOneOrderDeleteThenSizeCollectionReducedByOne() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.5f, 1));
        orders.add(new Order("book-2","buy", 20, 5.5f, 1));
        Deal sort = new AutomaticDeal(orders);

        Collection<Order> collection = sort.automaticDeals();

        assertThat(collection.size(), is(1));
    }

    @Test
    public void whenAutomaticDealCallAndOneOrderDeleteThenVolumesMerge() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.5f, 1));
        orders.add(new Order("book-2","buy", 20, 5.5f, 1));
        Deal sort = new AutomaticDeal(orders);

        Collection<Order> collection = sort.automaticDeals();

        float volumeResult = new ArrayList<>(collection).get(0).getVolume();
        assertThat(volumeResult, is(10f));
    }

    @Test
    public void whenAutomaticDealCallAndTwoOrderDeleteThenSizeCollectionReducedByTwo() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.5f, 1));
        orders.add(new Order("book-2","buy", 10, 5.5f, 1));
        Deal sort = new AutomaticDeal(orders);

        Collection<Order> collection = sort.automaticDeals();

        assertThat(collection.size(), is(0));
    }

    @Test
    public void whenAutomaticDealCallAndTreeOrderDeleteThenSizeCollectionReducedByThree() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.5f, 1));
        orders.add(new Order("book-2","buy", 10, 5.5f, 1));
        orders.add(new Order("book-1","sell", 5, 6.5f, 1));
        orders.add(new Order("book-2","buy", 10, 6.5f, 1));
        Deal sort = new AutomaticDeal(orders);

        Collection<Order> collection = sort.automaticDeals();

        assertThat(collection.size(), is(1));
    }

    @Test
    public void whenAutomaticDealCallAndAllOrdersHaveSameBookThenNothingHappens() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.5f, 1));
        orders.add(new Order("book-1","buy", 30, 5.5f, 1));
        orders.add(new Order("book-1","sell", 5, 5.5f, 1));
        orders.add(new Order("book-1","buy", 20, 5.5f, 1));
        Deal sort = new AutomaticDeal(orders);

        Collection<Order> collection = sort.automaticDeals();

        assertThat(collection.size(), is(4));
    }

    @Test
    public void whenGetSortedByPriceCallThenReturnListOfOrdersWithSameBook() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.2f, 1));
        orders.add(new Order("book-1","buy", 30, 7.5f, 1));
        orders.add(new Order("book-1","sell", 5, 5.5f, 1));
        orders.add(new Order("book-1","buy", 20, 4.5f, 1));
        Deal deal = new AutomaticDeal(orders);
        deal.automaticDeals();
        List<Order> sortedOrderWithSameBook =
                deal.getSortedByPriceCollectionBooksWith("book-1", "sell");

        String resultBook = sortedOrderWithSameBook.get(0).getBook();

        assertThat(resultBook, is("book-1"));
    }

    @Test
    public void whenGetSortedByPriceCallThenReturnListOfOrdersWithSameOperation() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.2f, 1));
        orders.add(new Order("book-1","buy", 30, 7.5f, 1));
        orders.add(new Order("book-1","sell", 5, 5.5f, 1));
        orders.add(new Order("book-1","buy", 20, 4.5f, 1));
        Deal deal = new AutomaticDeal(orders);
        deal.automaticDeals();
        List<Order> sortedOrderWithSameBook =
                deal.getSortedByPriceCollectionBooksWith("book-1", "sell");

        String resultBook = sortedOrderWithSameBook.get(0).getOperation();

        assertThat(resultBook, is("sell"));
    }

    @Test
    public void whenGetSortedByPriceCallThenReturnListOfOrdersSortedByPrise() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("book-1","sell", 10, 5.2f, 1));
        orders.add(new Order("book-1","buy", 30, 7.5f, 1));
        orders.add(new Order("book-1","sell", 5, 5.5f, 1));
        orders.add(new Order("book-1","buy", 20, 4.5f, 1));
        Deal deal = new AutomaticDeal(orders);
        deal.automaticDeals();
        List<Order> sortedOrders =
                deal.getSortedByPriceCollectionBooksWith("book-1", "sell");

        boolean result = sortedOrders.get(0).getPrice() <
                sortedOrders.get(1).getPrice();

        assertTrue(result);
    }
}