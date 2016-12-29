package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;


/**
 * This repo for products which not may be recycling. And his date expire is and.
 */
public class UpdateTrash extends Trash {

    /**
     * Check ney be product recycling or not.
     * @param product product for check on reducibility.
     * @return result check on reducibility. True is yes. False is no.
     */
    private boolean checkRecycling(Product product) {
        return product.getFlag().equals("canReproduce");
    }

    /**
     * Check whether add product in repo or no.
     * @param product product for check.
     * @return True is add. False is no add.
     */
    @Override
    public boolean isAppropriate(Product product) {
        return (product.checkQuality() > 100) && !checkRecycling(product);
    }
}
