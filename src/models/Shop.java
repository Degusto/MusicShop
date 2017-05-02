package models;

import models.products.*;
import models.services.RepairService;
import models.services.Service;
import utilities.Guard;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Shop class
 */
@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Shop {
    @XmlElementWrapper(name = "products")
    @XmlElements({
            @XmlElement(name = "guitar", type = Guitar.class),
            @XmlElement(name = "soundEquipment", type = SoundEquipment.class),
            @XmlElement(name = "keyboardInstrument", type = KeyboardInstrument.class),
            @XmlElement(name = "percussionInstrument", type = PercussionInstrument.class)
    })
    private final List<Product> products;

    private final RepairService repairService;

    /**
     * Creates shop
     */
    public Shop() {
        this(new ArrayList<>());
    }

    /**
     * Creates shop with given products
     * @param products products
     */
    public Shop(final List<Product> products) {
        Guard.notNull(products, "products");

        this.products = products;
        this.repairService = new RepairService();
    }

    /**
     * Gets products
     * @return products
     */
    public List<Product> getProducts() {
        return products.stream().collect(Collectors.toList());
    }

    /**
     * Add products
     * @param product product to add
     */
    public void addProduct(final Product product) {
        products.add(product);
    }

    /**
     * Removes products
     * @param productId product id
     */
    public void removeProduct(final String productId) {
        products.remove(getProduct(productId));
    }

    /**
     * Gets product
     * @param productId product id
     * @return product
     */
    public Product getProduct(final String productId) {
        final Optional<Product> product = products.stream().filter(p -> p.getId().equals(productId)).findFirst();

        if(!product.isPresent()){
            throw new NoSuchElementException("Product not found");
        }

        return product.get();
    }

    /**
     * Gets repair service
     * @return repair service
     */
    public RepairService getRepairService() {
        return repairService;
    }

    /**
     * Gets services
     * @return services
     */
    public List<Service> getServices(){
        final ArrayList<Service> services = new ArrayList<>();

        services.add(new Service("Guitar lessons", 50));
        services.add(new Service("Renting sound equipment", 599));
        services.add(new Service("Renting sound equipment with operation", 699));

        return services;
    }
}
