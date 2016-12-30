package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.controllers.ControlQuality;
import ru.pravvich.lsp.productManager.products.Product;

public class ReserveWarehouse extends UpdateWarehouse {

    @Override
    public String getName() {
        return "warehouse";
    }

    @Override
    public boolean isAppropriate(Product product) {
        return ControlQuality.getUpdateWarehouse().getProducts()
                .size() == 8 && (product.checkQuality() < 26);
    }
}
