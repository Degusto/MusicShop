package models.products;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Product base class
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Product {
    @XmlElement
    private String name;

    @XmlElement
    private double price;

    @XmlElement
    private int stockCount;

    @XmlElement
    private String description;

    @XmlElement
    private String imagePath = "";

    @XmlElement
    private String id = UUID.randomUUID().toString();

    /**
     * Deep clone of product
     * @return deep copy of product
     */
    public abstract Product copy();

    /**
     * Checks whether product is valid
     * @return whether product is valid
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isValid() {
        final File image = new File(getImagePath());

        return image.exists() && name.trim().length() > 0 && price >= 0 && stockCount >= 0;
    }

    /**
     * Gets product id
     * @return product id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets product id
     * @param id id
     */
    void setId(final String id) {
        this.id = id;
    }

    /**
     * Reads image from image path and returns product image
     * @return product image
     * @throws IOException standard IOException
     */
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(new File(imagePath));
    }

    /**
     * Gets product's name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets product's name
     * @param name product name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets product's price
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets product's price
     * @param price product price
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * Gets product image path
     * @return product image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets product's image path
     * @param imagePath product image path
     */
    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets product's description
     * @return product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets product's description
     * @param description product description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets product's stock count
     * @return product stock count
     */
    public int getStockCount() {
        return stockCount;
    }

    /**
     * Sets product's stock count
     * @param stockCount product stock count
     */
    public void setStockCount(final int stockCount) {
        this.stockCount = stockCount;
    }
}