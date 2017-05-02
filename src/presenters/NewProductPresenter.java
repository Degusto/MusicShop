package presenters;

import models.products.Product;
import presenters.interfaces.NewProductView;
import utilities.Guard;

/**
 * New product view controller
 */
public final class NewProductPresenter {
    private final NewProductView view;
    private NewProductListener listener;

    /**
     * Dependencies constructor
     * @param newProductView new product view
     */
    public NewProductPresenter(final NewProductView newProductView) {
        Guard.notNull(newProductView, "view");

        this.view = newProductView;

        newProductView.setPresenter(this);
    }

    /**
     * Adds listener and invokes its when new product is added
     * @param listener listener
     */
    public void addNewProductListener(final NewProductListener listener) {
        Guard.notNull(listener, "listener");

        this.listener = listener;
    }

    /**
     * Adds new product
     * @param product product to add
     */
    public void addProduct(final Product product) {
        if (listener == null) {
            return;
        }

        product.setName("New");

        listener.add(product);

        view.close();
    }

    /**
     * Shows view
     */
    public void show() {
        this.view.showView();
    }
}
