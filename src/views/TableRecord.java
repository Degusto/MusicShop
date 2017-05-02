package views;

import utilities.Guard;

/**
 * Product view model
 */
class TableRecord {
    private final String name;
    private final double price;

    /**
     * Creates table record
     * @param name product name
     * @param price product price
     */
    public TableRecord(final String name, final double price) {
        Guard.notNull(name, "name");

        this.name = name;
        this.price = price;
    }

    /**
     * Gets product name
     * @return product name
     */
    String getName() {
        return name;
    }

    /**
     * Gets product price
     * @return product price
     */
    double getPrice() {
        return price;
    }
}
