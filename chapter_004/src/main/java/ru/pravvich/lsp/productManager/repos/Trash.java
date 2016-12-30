package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * Determines trash, repo for bad products.
 */
public class Trash extends Warehouse {

    @Override
    public String getName() {
        return "trash";
    }

    @Override
    public boolean isAppropriate(Product product) {
        return (product.checkQuality() > 100);
    }
}
