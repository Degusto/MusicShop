package models.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class PercussionInstrument extends PromotionalProduct {
    @XmlElement(name = "soundHeightDefined")
    private boolean soundHeightDefined;

    @XmlElement(name = "percussionInstrumentType")
    private PercussionInstrumentType percussionInstrumentType;

    @Override
    public Product copy() {
        final PercussionInstrument instrument = new PercussionInstrument();

        instrument.setId(getId());
        instrument.setDescription(getDescription());
        instrument.setPercussionInstrumentType(getPercussionInstrumentType());
        instrument.setPromotion(getPromotion() != null ? getPromotion().copy() : null);
        instrument.setImagePath(getImagePath());
        instrument.setName(getName());
        instrument.setStockCount(getStockCount());
        instrument.setSoundHeightDefined(getSoundHeightDefined());
        instrument.setPrice(getOriginalPrice());

        return instrument;
    }

    @Override
    public boolean isValid(){
        return super.isValid() && percussionInstrumentType != null;
    }

    /**
     * Gets percussion instrument type
     * @return percussion instrument type
     */
    public PercussionInstrumentType getPercussionInstrumentType() {
        return percussionInstrumentType;
    }

    /**
     * Sets percussion instrument type
     * @param percussionInstrumentType percussion instrument type
     */
    public void setPercussionInstrumentType(final PercussionInstrumentType percussionInstrumentType) {
        this.percussionInstrumentType = percussionInstrumentType;
    }

    /**
     * Gets sound height defined
     * @return sound height defined
     */
    public boolean getSoundHeightDefined() {
        return soundHeightDefined;
    }

    /**
     * Sets sound height defined
     * @param soundHeightDefined sound height defined
     */
    public void setSoundHeightDefined(final boolean soundHeightDefined) {
        this.soundHeightDefined = soundHeightDefined;
    }
}

