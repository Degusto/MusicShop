package presenters.interfaces;

import models.products.Product;
import models.services.Service;

import java.util.List;

/**
 * Interface for shop view
 */
public interface MainView{
    /**
     * Sets shop's products
     * @param products products to show
     */
    void setProducts(List<Product> products);

    /**
     * Sets shop's repair services
     * @param services repair services
     */
    void setRepairServices(List<Service> services);

    /**
     * Sets shop's services
     * @param services services
     */
    void setServices(List<Service> services);
}
