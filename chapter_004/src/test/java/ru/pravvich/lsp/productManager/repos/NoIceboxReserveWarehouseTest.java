package ru.pravvich.lsp.productManager.repos;

import org.junit.Test;
import ru.pravvich.lsp.productManager.controllers.ControlQuality;
import ru.pravvich.lsp.productManager.products.Milk;
import ru.pravvich.lsp.productManager.products.Product;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class check work repositories:
 * @see NoIceboxWarehouse - base repo.
 * @see NoIceboxReserveWarehouse - reserve repo. Get products after firsts 9 products in base repo.
 */
public class NoIceboxReserveWarehouseTest {

    /**
     * @see NoIceboxReserveWarehouse#isAppropriate(Product) reserve repo. Products,
     * go her then in base repo amount product equal 9.
     * @see NoIceboxWarehouse#isAppropriate(Product) - base repo. Firsts 9 products,
     * go in her.
     */
    @Test
    public void whenExpireLess_25_percentDateAndAfter_9_ProductsAddInNoIceboxWarehouseThenProductAddInNoIceboxReserveWarehouse() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH);
        int expireMonth = currentDate.get(Calendar.MONTH) + 10;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);

        ControlQuality control = new ControlQuality();

        int j = 0,m = 0, p = 0;

        for (int i = 0; i != 12; i++) {
            m = ControlQuality.getNoIceboxWarehouse().getProducts().size();
            j = ControlQuality.getNoIceboxReserveWarehouse().getProducts().size();
            control.uploadInRepo(food);
            j = ControlQuality.getNoIceboxReserveWarehouse().getProducts().size();
            j = ControlQuality.getNoIceboxReserveWarehouse().getProducts().size();
        }

        assertThat(ControlQuality.getNoIceboxWarehouse().getProducts().size(), is(9));
        assertThat(ControlQuality.getNoIceboxReserveWarehouse().getProducts().size(), is(3));
    }

}