package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

public class NoIceboxWarehouse extends Warehouse {

    /**
     * This repo contain only 9 products, all next after 9, go in NoIceboxReserveWarehouse.
     * @return "" if filling equal 9 products.
     */
    @Override
    public boolean isAppropriate(Product product) {
        return  this.products.size() < 9 &&
                (product.checkQuality() < 26) &&
                this.checkIceFlag(product);
    }

    private boolean checkIceFlag(Product product) {
        return !product.getFlag().equals("ice") &&
                !product.getFlag().equals("canReproduce");
    }
}
