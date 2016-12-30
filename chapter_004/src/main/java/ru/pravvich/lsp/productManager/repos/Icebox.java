package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.products.Product;

import java.util.ArrayList;

/**
 * This repo for products which require icebox.
 */
public class Icebox extends Warehouse {

    @Override
    public String getName() {
        return "Icebox";
    }

    /**
     * Check product need he icebox or not.
     * @param product product for check.
     * @return If product's flag is "ice" and product's expire date less 26 return true.
     * All else cases return false.
     */
    @Override
    public boolean isAppropriate(Product product) {
        return (this.checkFlag(product)) && (product.checkQuality() < 26);
    }

    /**
     * Check flag product's.
     * @param product product for check.
     * @return If product's flag is "ice". Else return false.
     */
    private boolean checkFlag(Product product) {
        return product.getFlag().equals("ice");
    }
}
