package ru.pravvich.lsp.productManager.repos;

import org.junit.Test;
import ru.pravvich.lsp.productManager.controllers.ControlQuality;
import ru.pravvich.lsp.productManager.products.Product;
import ru.pravvich.lsp.productManager.products.Vegan;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for Icebox repo.
 */
public class IceboxTest {

    /**
     * @see ControlQuality#uploadInRepo(Product) upload in repo.
     * @see Icebox#isAppropriate(Product) controle addinion products.
     */
    @Test
    public void whenExpireLess_25_percentDateAndProductNeedIceboxThenProductAddInIceboxInFieldProducts() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH);
        int expireMonth = currentDate.get(Calendar.MONTH) + 10;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Vegan(createDate, expireDate);

        ControlQuality control = new ControlQuality();
        control.uploadInRepo(food);

        assertThat(food, is(ControlQuality.getIcebox().getProducts().get(0)));
    }
}