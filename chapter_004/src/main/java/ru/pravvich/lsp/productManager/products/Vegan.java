package ru.pravvich.lsp.productManager.products;

import java.util.Calendar;

public class Vegan extends Food {

    /**
     * Default constructor from Food.
     *
     * @param createDate - date create food.
     * @param expireDate - expiry date.
     */
    public Vegan(Calendar createDate, Calendar expireDate) {
        super(createDate, expireDate);
    }

    /**
     * Generate flag for determines name repo for this product.
     *
     * @return flag which signal is repo name.
     */
    @Override
    public String getFlag() {
        return "ice";
    }
}
