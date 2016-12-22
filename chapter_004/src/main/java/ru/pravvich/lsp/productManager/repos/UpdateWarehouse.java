package ru.pravvich.lsp.productManager.repos;

/**
 * This class was create for replace class Warehouse.
 * Master give new condition: we have products more then Warehouse repo can contains,
 * and this class replace warehouse.
 */
public class UpdateWarehouse extends Warehouse {

    /**
     * This repo contain only 10 products, all next after 10, go in ReserveWarehouse.
     * @return "" if filling equal 10 products.
     */
    @Override
    public String getName() {
        if (this.getProducts().size() == 9) {
            return "";
        } else {
            return "warehouse";
        }
    }
}
