package presenters.interfaces;

import models.products.Product;

/**
 * Views factory
 */
public interface ViewFactory {
    /**
     * Creates product view
     * @param type product type
     * @param <T> product type
     * @return product view
     */
    <T extends Product> ProductView<T> getProductView(Class<T> type);

    /**
     * Creates new product view
     * @return new product view
     */
    NewProductView getNewProductView();
}
