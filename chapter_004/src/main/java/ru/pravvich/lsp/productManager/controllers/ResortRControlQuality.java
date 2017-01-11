package ru.pravvich.lsp.productManager.controllers;

import ru.pravvich.lsp.productManager.products.Product;
import ru.pravvich.lsp.productManager.repos.Repo;

import java.util.ArrayList;

/**
 * Resort all Products.
 */
public class ResortRControlQuality extends RControlQuality implements Resort {

    /**
     * Resort all products.
     * If expire date not actual, products move in rite repo.
     */
    public void resort() {
        ArrayList<Product> productsAll = getAllProducts();
        cleanAllRepo();
        productsAll.forEach(this::uploadInRepo);
    }

    /**
     * Add all products in one list.
     * @return list which contain all products.
     */
    private ArrayList<Product> getAllProducts() {
        ArrayList<Product> result = new ArrayList<>();
        for (Repo repo : this.repo) {
            result.addAll(repo.getProducts());
        }
        return result;
    }

    /**
     * Clean all repositories.
     */
    private void cleanAllRepo() {
        for (Repo repo : this.repo) {
            repo.getProducts().clear();
        }
    }

    // only for test
    public static void setWarehouse(Product product) {
        warehouse.getProducts().add(product);
    }
}
