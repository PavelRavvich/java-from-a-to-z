package ru.pravvich.lsp.productManager.controllers;

import org.junit.Test;
import ru.pravvich.lsp.productManager.products.Milk;
import ru.pravvich.lsp.productManager.products.Product;
import ru.pravvich.lsp.productManager.products.ReproducingProduct;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for RControlQuality class.
 */
public class RControlQualityTest {
    @Test
    public void whenThen() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 10;
        int expireMonth = currentDate.get(Calendar.MONTH) - 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        ReproducingProduct food = new ReproducingProduct(createDate, expireDate);
        RControlQuality rControlQuality = new RControlQuality();

        rControlQuality.uploadInRepo(food);

        assertThat(food, is(rControlQuality.getRepo().get(6).getProducts().get(0)));
    }
}