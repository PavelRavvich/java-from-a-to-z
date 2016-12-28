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
     * @see Food#checkQuality() for check percent epire.
     */
    @Test
    public void whenFoodCreateThenCheckQualityReturnExpirePercent() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 1;
        int expireMonth = currentDate.get(Calendar.MONTH) + 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);

        int result = food.checkQuality();
        assertThat(result, is(50));
    }
}
