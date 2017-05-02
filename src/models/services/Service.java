package models.services;

/**
 * Service class
 */
public final class Service {
    private String name;

    private double price = Double.MIN_VALUE;

    /**
     * Creates service
     * @param name service name
     * @param price service price
     */
    public Service(final String name, final double price) {
        setName(name);
        setPrice(price);
    }

    /**
     * Gets service name
     * @return service name
     */
    public String getName() {
        return name;
    }

    private void setName(final String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("name can't be empty or whitespace");
        }

        this.name = name;
    }

    /**
     * Gets service price
     * @return service price
     */
    public double getPrice() {
        return price;
    }

    private void setPrice(final double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price");
        }

        this.price = price;
    }
}