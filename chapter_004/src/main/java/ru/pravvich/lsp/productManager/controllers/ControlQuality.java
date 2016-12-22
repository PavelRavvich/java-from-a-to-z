package ru.pravvich.lsp.productManager.controllers;

import ru.pravvich.lsp.productManager.products.Product;
import ru.pravvich.lsp.productManager.repos.*;

import java.util.ArrayList;

/**
 * Control distribution products for different repo.
 */
public class ControlQuality implements Controller {

    /**
     * Contain all repositories type.
     */
    private ArrayList<Repo> repo = new ArrayList<>();

    public Repo getWarehouse() {
        return warehouse;
    }

    public Repo getShop() {
        return shop;
    }

    public Repo getTrash() {
        return trash;
    }

    private static Repo warehouse;
    private static Repo shop;
    private static Repo trash;

    /**
     * Default constructor.
     */
    public ControlQuality() {
        this.initRepo();
    }

    /**
     * For list which contain all repositories type.
     *
     * @return list with contain all repositories type.
     */
    public ArrayList<Repo> getRepo() {
        return this.repo;
    }

    /**
     * Init list which contain all repositories type.
     */
    private void initRepo() {
        this.repo.add(warehouse = new Warehouse());
        this.repo.add(shop = new Shop());
        this.repo.add(trash = new Trash());
    }

    /**
     * Determines where repo to send product.
     * If expire date less 25% product send in warehouse.
     * If expire date better 25% and less 100% send in shop.
     * If If expire date better 100% send in trash.
     *
     * @param product - for send in repo.
     */
    @Override
    public void uploadInRepo(Product product) {
        String flag = product.getFlag();
        for (Repo item : this.repo) {
            if (flag.equals(item.getName())) {
                item.getProducts().add(product);
                break;
            }
        }
    }

    /**
     * Search exist product in repo.
     * @param product product for search.
     * @return true - if any repo contain product. False - if product do not find.
     */
    public boolean isAppropriate(Product product) {
        for (Repo r : this.repo) {
            for (Product p : r.getProducts()) {
                if (product.equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }
}
