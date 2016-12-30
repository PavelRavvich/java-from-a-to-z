package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * Determines shop, repo for products.
 */
public class Shop extends Warehouse {
    @Override
    public String getName() {
        return "shop";
    }

    @Override
    public boolean isAppropriate(Product product) {
        if (product.checkQuality() > 26 && product.checkQuality() < 76) {
            return true;
        } else if (product.checkQuality() > 75 && product.checkQuality() < 100) {
            product.calculateDiscount();
            return true;
        }
        return false;
    }
}
