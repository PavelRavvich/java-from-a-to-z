package ru.pravvich.lsp.productManager.repos;

import ru.pravvich.lsp.productManager.controllers.ControlQuality;

public class ReserveWarehouse extends Warehouse {

    @Override
    public String getName() {
        if (ControlQuality.getUpdateWarehouse().getProducts().size() == 9) {
            return "warehouse";
        }
        return "";
    }
}
