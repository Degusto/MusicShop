package models.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Keyboard instrument class
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class KeyboardInstrument extends PromotionalProduct {
    @XmlElement
    private int keyCount;

    @XmlElement
    private KeyboardInstrumentType keyboardInstrumentType;

    @Override
    public Product copy() {
        final KeyboardInstrument instrument = new KeyboardInstrument();

        instrument.setId(getId());
        instrument.setName(getName());
        instrument.setPrice(getOriginalPrice());
        instrument.setKeyCount(getKeyCount());
        instrument.setImagePath(getImagePath());
        instrument.setDescription(getDescription());
        instrument.setStockCount(getStockCount());
        instrument.setKeyboardInstrumentType(getKeyboardInstrumentType());
        instrument.setPromotion(getPromotion() != null ? getPromotion().copy() : null);

        return instrument;
    }

    @Override
    public boolean isValid(){
        return super.isValid() && keyCount >= 0 && keyboardInstrumentType != null;
    }

    /**
     * Gets keyboard instrument type
     * @return keyboard instrument type
     */
    public KeyboardInstrumentType getKeyboardInstrumentType() {
        return keyboardInstrumentType;
    }

    /**
     * Sets keyboard instrument type
     * @param keyboardInstrumentType keyboard instrument type
     */
    public void setKeyboardInstrumentType(final KeyboardInstrumentType keyboardInstrumentType) {
        this.keyboardInstrumentType = keyboardInstrumentType;
    }

    /**
     * Gets key count
     * @return key count
     */
    public int getKeyCount() {
        return keyCount;
    }

    /**
     * Sets key count
     * @param keyCount key count
     */
    public void setKeyCount(final int keyCount) {
        this.keyCount = keyCount;
    }
}

