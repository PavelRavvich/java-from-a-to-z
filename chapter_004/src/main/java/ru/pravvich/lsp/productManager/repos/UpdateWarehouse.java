package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * This class was create for replace class Warehouse.
 * Master give new condition: we have products more then Warehouse repo can contains,
 * and this class replace warehouse.
 */
public class UpdateWarehouse extends Warehouse {

    @Override
    public String getName() {
        return "warehouse";
    }

    /**
     * This repo contain only 10 products, all next after 10, go in ReserveWarehouse.
     * @return "" if filling equal 10 products.
     */
    @Override
    public boolean isAppropriate(Product product) {
        return this.getProducts().size() < 9 && (product.checkQuality() < 26);
    }
}
