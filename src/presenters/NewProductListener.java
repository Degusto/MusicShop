package presenters;

import models.products.Product;

/**
 * Event listener for new product
 */
interface NewProductListener {
    /**
     * Callback invoked when new product is added
     * @param product new product
     */
    void add(Product product);
}
