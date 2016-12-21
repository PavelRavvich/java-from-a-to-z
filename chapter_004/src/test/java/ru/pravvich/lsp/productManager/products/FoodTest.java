package ru.pravvich.lsp.productManager.products;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for class food.
 */
public class FoodTest {

    /**
     * @see Food#getFlag() If expire less 25% or equal 0%.
     */
    @Test
    public void whenExpireLess_25_percentDateThenReturnWarehouseFlag() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH);
        int expireMonth = currentDate.get(Calendar.MONTH) + 10;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);
        String result = food.getFlag();

        assertThat(result, is("warehouse"));
    }

    /**
     * @see Food#getFlag() If expire less 75% but more 25%.
     */
    @Test
    public void whenExpireLess_75_AndMore_25_PercentDateThenReturnShopFlag() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 5;
        int expireMonth = currentDate.get(Calendar.MONTH) + 5;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);
        String result = food.getFlag();

        assertThat(result, is("shop"));
    }

    /**
     * @see Food#getFlag() If expire less 100% but more 75%.
     */
    @Test
    public void whenExpireLess_100_AndMore_75_PercentDateThenReturnShopFlag() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 10;
        int expireMonth = currentDate.get(Calendar.MONTH) + 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);
        String result = food.getFlag();

        assertThat(result, is("shop"));
    }

    /**
     * @see Food#getFlag() If expire date is over.
     */
    @Test
    public void whenExpireMore_100_PercentDateThenReturnTrashFlag() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 10;
        int expireMonth = currentDate.get(Calendar.MONTH) - 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);
        String result = food.getFlag();

        assertThat(result, is("trash"));
    }
}
