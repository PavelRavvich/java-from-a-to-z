package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * Determines shop, repo for products.
 */
public class Shop implements Repo {

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
        return "shop";
    }
}
