package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * Determines warehouse, repo for products.
 */
public class Warehouse implements Repo {

    /**
     * List contain products.
     */
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public String getName() {
        return "warehouse";
    }

    @Override
    public boolean isAppropriate(Product product) {
        return (product.checkQuality() < 26);
    }
}
