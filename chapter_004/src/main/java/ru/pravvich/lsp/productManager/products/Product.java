package ru.pravvich.lsp.productManager.products;

import java.util.Calendar;

/**
 * Determines all type products.
 */
public interface Product {


    Calendar getExpireDate();
    void setExpireDate(Calendar expireDate);

    String getName();
    void setName(String name);

    Calendar getCreateDate();
    void setCreateDate(Calendar date);

    int getPrise();
    void setPrise(int prise);

    int getDiscount();
    void setDiscount(int discount);

    int checkQuality();
    void calculateDiscount();
}
