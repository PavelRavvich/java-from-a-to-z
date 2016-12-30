package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.controllers.ControlQuality;
import ru.pravvich.lsp.productManager.products.Product;

/**
 * Update ReserveWarehouse for save products when base repo reached the limit.
 */
public class NoIceboxReserveWarehouse extends NoIceboxWarehouse {

    @Override
    public String getName() {
        return "NoIceboxReserveWarehouse";
    }

    /**
     * Check base repo reached the limit.
     * @param product product for check.
     * @return result base repo limit and product quality.
     */
    @Override
    public boolean isAppropriate(Product product) {
        return ControlQuality.getNoIceboxWarehouse().getProducts()
                .size() > 8 && (product.checkQuality() < 26);
    }
}
