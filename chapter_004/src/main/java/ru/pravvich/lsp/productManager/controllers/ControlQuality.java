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
        this.repo.add(new Warehouse());
        this.repo.add(new Shop());
        this.repo.add(new Trash());
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
}
