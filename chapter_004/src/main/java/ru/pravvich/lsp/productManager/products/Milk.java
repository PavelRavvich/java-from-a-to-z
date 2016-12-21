package ru.pravvich.lsp.productManager.products;

import java.util.Calendar;

/**
 * Class determines object milk.
 */
public class Milk extends Food {

    /**
     * Default constructor from Food.
     * @param createDate - date create food.
     * @param expireDate - expiry date.
     */
    public Milk(Calendar createDate, Calendar expireDate) {
        super(createDate, expireDate);
    }

    @Override
    public String getName() {
        return "milk";
    }
}
