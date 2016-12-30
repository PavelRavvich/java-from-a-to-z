package ru.pravvich.lsp.productManager.repos;

import org.junit.Test;
import ru.pravvich.lsp.productManager.controllers.ControlQuality;
import ru.pravvich.lsp.productManager.products.Milk;
import ru.pravvich.lsp.productManager.products.Product;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class UpdateReserveWarehouseTest {

    /**
     * @see UpdateWarehouse#getName()  If expire less 25% or equal 0%.
     */
    @Test
    public void whenExpireLess_25_percentDateThenProductAddInUpdateWarehouseInFieldProducts() {
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

        assertThat(food, is(ControlQuality.getUpdateWarehouse().getProducts().get(0)));
    }

    /**
     * @see ReserveWarehouse#getName() If amount of food in UpdateWarehouse better 10,
     * product go in ReserveWarehouse.
     */
    @Test
    public void whenExpireLess_25_percentDateAndMore_9_ProductsAddInWarehouseThenProductAddInReserveWarehouse() {
        Calendar currentDate = Calendar.getInstance();

        int year = currentDate.get(Calendar.YEAR);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int createMonth = currentDate.get(Calendar.MONTH);
        int expireMonth = currentDate.get(Calendar.MONTH) + 10;

        Calendar createDate = new GregorianCalendar(year, createMonth, day);
        Calendar expireDate = new GregorianCalendar(year, expireMonth, day);

        Product food = new Milk(createDate, expireDate);

        ControlQuality control = new ControlQuality();

        for (int i = 0; i != 12; i++) {
            control.uploadInRepo(food);
        }

        assertThat(ControlQuality.getUpdateWarehouse().getProducts().size(),is(9));
        assertThat(ControlQuality.getReserveWarehouse().getProducts().size(),is(3));
    }
}
