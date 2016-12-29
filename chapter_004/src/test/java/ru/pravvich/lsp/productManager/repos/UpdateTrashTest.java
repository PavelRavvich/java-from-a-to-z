package ru.pravvich.lsp.productManager.repos;

import org.junit.Test;
import ru.pravvich.lsp.productManager.controllers.ControlQuality;
import ru.pravvich.lsp.productManager.products.Milk;
import ru.pravvich.lsp.productManager.products.Product;
import ru.pravvich.lsp.productManager.products.ReproducingProduct;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class UpdateTrashTest {

    /**
     * @see Recycling#isAppropriate(Product) - repo.
     * @see ReproducingProduct#getFlag() product.
     */
    @Test
    public void whenExpireMore_100_PercentExpireAndCanBeReproduceThenProductGoInRecyclingRepo() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 10;
        int expireMonth = currentDate.get(Calendar.MONTH) - 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product canReproduce = new ReproducingProduct(createDate, expireDate);
        ControlQuality control = new ControlQuality();
        control.uploadInRepo(canReproduce);

        assertThat(canReproduce, is(ControlQuality.getRecycling().getProducts().get(0)));
    }

    /**
     * @see UpdateTrash#isAppropriate(Product)  - repo.
     */
    @Test
    public void whenExpireMore_100_PercentExpireAndCanNotBeReproduceThenProductGoInUpdateTrash() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH) - 10;
        int expireMonth = currentDate.get(Calendar.MONTH) - 1;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product canNotReproduce = new Milk(createDate, expireDate);
        ControlQuality control = new ControlQuality();
        control.uploadInRepo(canNotReproduce);

        assertThat(canNotReproduce, is(ControlQuality.getUpdateTrash().getProducts().get(0)));
    }
}