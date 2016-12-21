package ru.pravvich.lsp.productManager.controllers;

import org.junit.Test;
import ru.pravvich.lsp.productManager.products.Milk;
import ru.pravvich.lsp.productManager.products.Product;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class testing method class ControlQuality, method uploadInRepo(Product product).
 */
public class ControlQualityTest {

    /**
     * @see ControlQuality#uploadInRepo(Product) If expires date betwen 0% and 25%,
     * product go in warehous.
     */
    @Test
    public void whenExpireLess_25_percentDateThenProductAddInWarehouseFieldProducts() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH);
        int expireMonth = currentDate.get(Calendar.MONTH) + 10;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);

        ControlQuality control = new ControlQuality();
        control.uploadInRepo(food);

        assertThat(food, is(control.getRepo().get(0).getProducts().get(0)));
    }

    /**
     * @see ControlQuality#uploadInRepo(Product) If expires date between 25% and 75%,
     * product go in shop.
     */
    @Test
    public void whenExpireLess_75_AndMore_25_PercentDateThenProductAddInShopFieldProducts() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 5;
        int expireMonth = currentDate.get(Calendar.MONTH) + 5;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);
        Product food = new Milk(createDate, expireDate);
        ControlQuality control = new ControlQuality();
        control.uploadInRepo(food);
        assertThat(food, is(control.getRepo().get(1).getProducts().get(0)));
    }

    /**
     * @see ControlQuality#uploadInRepo(Product) If expires date between 75% and 100%,
     * product go in shop.
     */
    @Test
    public void whenExpireLess_100_AndMore_75_PercentDateThenProductAddInShopFieldProductsAndAddDiscount() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 10;
        int expireMonth = currentDate.get(Calendar.MONTH) + 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);
        food.setPrise(100);
        food.setDiscount(50);

        ControlQuality control = new ControlQuality();
        control.uploadInRepo(food);

        assertThat(food, is(control.getRepo().get(1).getProducts().get(0)));
        assertThat(control.getRepo().get(1).getProducts().get(0).getPrise(), is(50));
    }

    /**
     * @see ControlQuality#uploadInRepo(Product) case for expire date,
     * is over and product go in trash.
     */
    @Test
    public void whenExpireMore_100_PercentDateThenProductAddInTrash() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 10;
        int expireMonth = currentDate.get(Calendar.MONTH) - 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);

        ControlQuality control = new ControlQuality();
        control.uploadInRepo(food);

        assertThat(food, is(control.getRepo().get(2).getProducts().get(0)));
    }
}
