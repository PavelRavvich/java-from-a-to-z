package ru.pravvich.lsp.productManager.controllers;

import ru.pravvich.lsp.productManager.products.Product;
import ru.pravvich.lsp.productManager.repos.*;

import java.util.ArrayList;

/**
 * Control distribution products for different repo.
 */
public class ControlQuality implements Controller {

    /**
     * Markers on every repo.
     */
    private static Repo warehouse;
    private static Repo shop;
    private static Repo trash;
    private static Repo reserveWarehouse;
    private static Repo updateWarehouse;
    private static Repo updateTrash;
    private static Repo recycling;
    private static Repo icebox;
    private static Repo noIceboxWarehouse;
    private static Repo noIceboxReserveWarehouse;

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

    public static Repo getNoIceboxReserveWarehouse() {
        return noIceboxReserveWarehouse;
    }

    public static Repo getNoIceboxWarehouse() {
        return noIceboxWarehouse;
    }

    public static Repo getIcebox() {
        return icebox;
    }

    public static Repo getUpdateTrash() {
        return updateTrash;
    }

    public static Repo getRecycling() {
        return recycling;
    }

    public static Repo getWarehouse() {
        return warehouse;
    }

    public static Repo getShop() {
        return shop;
    }

    public static Repo getTrash() {
        return trash;
    }

    public static Repo getUpdateWarehouse() {
        return updateWarehouse;
    }

    public static Repo getReserveWarehouse() {
        return reserveWarehouse;
    }

    /**
     * For list which contain all repositories type.
     *
     * @return list with contain all repositories type.
     */
    ArrayList<Repo> getRepo() {
        return this.repo;
    }

    /**
     * Init list which contain all repositories type.
     */
    private void initRepo() {
        this.repo.add(warehouse = new Warehouse());
        this.repo.add(shop = new Shop());
        this.repo.add(trash = new Trash());
        this.repo.add(reserveWarehouse = new ReserveWarehouse());
        this.repo.add(updateWarehouse = new UpdateWarehouse());
        this.repo.add(updateTrash = new UpdateTrash());
        this.repo.add(recycling = new Recycling());
        this.repo.add(icebox = new Icebox());
        this.repo.add(noIceboxWarehouse = new NoIceboxWarehouse());
        this.repo.add(noIceboxReserveWarehouse = new NoIceboxReserveWarehouse());
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
        for (Repo item : this.repo) {
            if (item.isAppropriate(product)) {
                item.getProducts().add(product);
            }
        }
    }
}
