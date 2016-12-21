package ru.pravvich.lsp.productManager.products;

import java.util.Calendar;

/**
 * Determines food. All food have expire date.
 */
public class Food implements Product {

    /**
     * Name food.
     */
    private String name;

    /**
     * Date where product create.
     */
    private Calendar createDate;

    /**
     * Expire date for this product.
     */
    private Calendar expireDate;

    /**
     * Prise this product.
     */
    private int prise;

    /**
     * Discount for this product.
     */
    private int discount;

    /**
     * Default constructor from Food.
     *
     * @param createDate - date create food.
     * @param expireDate - expiry date.
     */
    public Food(Calendar createDate, Calendar expireDate) {
        this.createDate = createDate;
        this.expireDate = expireDate;
    }

    @Override
    public Calendar getExpireDate() {
        return this.expireDate;
    }

    @Override
    public void setExpireDate(Calendar expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Calendar getCreateDate() {
        return this.createDate;
    }

    @Override
    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    @Override
    public int getPrise() {
        return this.prise;
    }

    @Override
    public void setPrise(int prise) {
        this.prise = prise;
    }

    @Override
    public int getDiscount() {
        return this.discount;
    }

    @Override
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Calculate how much was spent before the expiry date.
     *
     * @return percent how much was spent before the expiry date.
     */
    private int checkQuality() {
        Calendar currentDate = Calendar.getInstance();
        // differenceOne – разница между датой изготовления и датой окончания срока
        long differenceOne = this.expireDate.getTimeInMillis() - this.createDate.getTimeInMillis();
        // differenceTwo – разница между датой изготовления и текущей датой.
        long differenceTwo = currentDate.getTimeInMillis() - this.createDate.getTimeInMillis();
        // искомое число – это отношение differenceTwo к differenceOne (в долях)
        return (int) (((float) differenceTwo / differenceOne) * 100);
    }

    /**
     * Generate flag for determines name repo for this product.
     *
     * @return flag which signal is repo name.
     */
    @Override
    public String getFlag() {
        String result;
        int i = this.checkQuality();
        if ((i > 0 && i < 26) || i == 0) {
            result = "warehouse";
        } else if (i > 25 && i < 76) {
            result = "shop";
        } else if (i > 74 && i < 101) {
            this.calculateDiscount();
            result = "shop";
        } else {
            result = "trash";
        }
        return result;
    }

    private void calculateDiscount() {
        this.prise = this.prise - this.discount;
    }
}
