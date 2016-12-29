package ru.pravvich.lsp.productManager.products;

import java.util.Calendar;

/**
 * Class determines types products which may be recycling.
 */
public class ReproducingProduct extends Food {

    /**
     * Default constructor from Food.
     *
     * @param createDate - date create food.
     * @param expireDate - expiry date.
     */
    public ReproducingProduct(Calendar createDate, Calendar expireDate) {
        super(createDate, expireDate);
    }

    /**
     * Get origin flag for all products which may be recycling.
     *
     * @return flag for products which may be recycling.
     */
    @Override
    public String getFlag() {
        return "canReproduce";
    }
}
