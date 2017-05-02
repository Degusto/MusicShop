package presenters.interfaces;

import models.products.Product;
import presenters.ProductPresenter;

/**
 * Interface for product's view
 * @param <T> product type
 */
public interface ProductView<T extends Product> {
    /**
     * Shows product's details
     * @param product product to show
     * @param newProduct indicates whether view is new/edit mode
     */
    void showProduct(T product, boolean newProduct);

    /**
     * Sets presenter instance
     * @param productPresenter presenter instance
     */
    void setPresenter(ProductPresenter<T> productPresenter);

    /**
     * Closes view
     */
    void close();
}
