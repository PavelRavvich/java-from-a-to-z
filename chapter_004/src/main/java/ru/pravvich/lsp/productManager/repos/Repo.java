package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * Determines base method for all repositories for products.
 */
public interface Repo {

    /**
     * List contain products.
     */
    ArrayList<Product> getProducts();

    /**
     * Get name repo.
     * @return name repo.
     */
    String getName();

    /**
     * Check whether add product in repo or no.
     * @param product product for check.
     * @return True is add. False is no add.
     */
    boolean isAppropriate(Product product);

}
