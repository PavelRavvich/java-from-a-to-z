package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

public class NoIceboxWarehouse extends UpdateWarehouse {

    /**
     * This repo contain only 10 products, all next after 10, go in NoIceboxReserveWarehouse.
     * @return "" if filling equal 10 products.
     */
    @Override
    public boolean isAppropriate(Product product) {
        return  this.getProducts().size() < 9 &&
                (product.checkQuality() < 26) &&
                this.checkIceFlag(product);
    }

    private boolean checkIceFlag(Product product) {
        return !product.getFlag().equals("ice") &&
                !product.getFlag().equals("canReproduce");
    }
}
