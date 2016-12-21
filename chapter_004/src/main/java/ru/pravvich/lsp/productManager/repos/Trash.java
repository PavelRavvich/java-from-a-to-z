package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * Determines trash, repo for bad products.
 */
public class Trash implements Repo {

    /**
     * List contain bad products.
     */
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public String getName() {
        return "trash";
    }
}
