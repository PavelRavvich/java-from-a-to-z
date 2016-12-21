package ru.pravvich.lsp.productManager.controllers;

import ru.pravvich.lsp.productManager.products.Product;

/**
 * Control distribution products for different repo.
 */
interface Controller {

    /**
     * Determines where repo to send product.
     * If expire date less 25% product send in warehouse.
     * If expire date better 25% and less 100% send in shop.
     * If If expire date better 100% send in trash.
     *
     * @param product - for send in repo.
     */
    void uploadInRepo(Product product);
}
