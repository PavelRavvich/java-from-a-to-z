package ru.pravvich.lsp.productManager.controllers;

import ru.pravvich.lsp.productManager.products.ReproducingProduct;
import ru.pravvich.lsp.productManager.repos.Repo;

public class RControlQuality extends ControlQuality implements Reproduce {

    /**
     * Use for add reproducing product.
     *
     * @param product for add.
     */
    @Override
    public void uploadInRepo(ReproducingProduct product) {
        for (Repo item : this.repo) {
            if (item.isAppropriate(product)) {
                item.getProducts().add(product);
            }
        }
    }
}
