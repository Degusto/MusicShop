package models.products;

import javax.xml.bind.annotation.*;

/**
 * Guitar product type
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class Guitar extends Product {
    @XmlElement
    private int fretCount = 1;

    @XmlElement
    private int stringCount = 1;

    @XmlElement
    private int pickupCount = 1;

    @XmlElement
    private GuitarType guitarType;

    @Override
    public Product copy() {
        final Guitar guitar = new Guitar();

        guitar.setId(getId());
        guitar.setDescription(getDescription());
        guitar.setName(getName());
        guitar.setPrice(getPrice());
        guitar.setImagePath(getImagePath());
        guitar.setFretCount(getFretCount());
        guitar.setGuitarType(getGuitarType());
        guitar.setStringCount(getStringCount());
        guitar.setPickupCount(getPickupCount());
        guitar.setStockCount(getStockCount());

        return guitar;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && guitarType != null && stringCount >= 0 && fretCount >= 0 && pickupCount >= 0;
    }

    /**
     * Sets guitar's type
     * @return guitarType guitarType
     */
    public GuitarType getGuitarType() {
        return guitarType;
    }

    /**
     * Gets guitar's type
     * @param guitarType guitarType
     */
    public void setGuitarType(final GuitarType guitarType) {
        this.guitarType = guitarType;
    }

    /**
     * Gets string count
     * @return string count
     */
    public int getStringCount() {
        return stringCount;
    }

    /**
     * Sets string count
     * @param stringCount string count
     */
    public void setStringCount(final int stringCount) {
        this.stringCount = stringCount;
    }

    /**
     * Gets fret count
     * @return fret count
     */
    public int getFretCount() {
        return fretCount;
    }

    /**
     * Sets fret count
     * @param fretCount fret count
     */
    public void setFretCount(final int fretCount) {
        this.fretCount = fretCount;
    }

    /**
     * Gets pickup count
     * @return pickup count
     */
    public int getPickupCount() {
        return pickupCount;
    }

    /**
     * Sets pickup count
     * @param pickupCount pickup count
     */
    public void setPickupCount(final int pickupCount) {
        this.pickupCount = pickupCount;
    }
}

