package ru.pravvich.lsp.productManager.controllers;

import org.junit.Test;
import ru.pravvich.lsp.productManager.products.ReproducingProduct;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test for ResortRControlQuality.
 */
public class ResortRControlQualityTest {
    @Test
    public void whenPlaceProductOutdatedThenResortTransfersInActualRepo() {
        Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 5;
        int expireMonth = currentDate.get(Calendar.MONTH) + 5;
        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);
        ReproducingProduct food = new ReproducingProduct(createDate, expireDate);
        ResortRControlQuality control = new ResortRControlQuality();

        ResortRControlQuality.setWarehouse(food);
        control.resort();

        assertThat(food, is(control.getRepo().get(1).getProducts().get(0)));
    }
}