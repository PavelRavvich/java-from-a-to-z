package ru.pravvich.lsp.productManager.controllers;

import ru.pravvich.lsp.productManager.products.ReproducingProduct;

interface Reproduce {

    /**
     * Use for add reproducing product.
     *
     * @param product for add.
     */
    void uploadInRepo(ReproducingProduct product);
}
