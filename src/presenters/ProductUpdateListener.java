package presenters;

import models.products.Product;

/**
 * Listener for product's update
 */
interface ProductUpdateListener {
    /**
     * Updates product
     * @param product product to update
     */
    void update(Product product);
}
