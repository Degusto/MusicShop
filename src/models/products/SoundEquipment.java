package models.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Sound equipment class
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class SoundEquipment extends PromotionalProduct {
    @XmlElement(name="powerConsumption")
    private double powerConsumption;

    @XmlElement(name="soundEquipmentType")
    private SoundEquipmentType soundEquipmentType;

    @Override
    public Product copy() {
        final SoundEquipment equipment = new SoundEquipment();

        equipment.setId(getId());
        equipment.setName(getName());
        equipment.setPrice(getOriginalPrice());
        equipment.setImagePath(getImagePath());
        equipment.setStockCount(getStockCount());
        equipment.setDescription(getDescription());
        equipment.setPowerConsumption(getPowerConsumption());
        equipment.setSoundEquipmentType(getSoundEquipmentType());
        equipment.setPromotion(getPromotion() != null ? getPromotion().copy() : null);

        return equipment;
    }

    @Override
    public boolean isValid(){
        return super.isValid() && powerConsumption >= 0 && soundEquipmentType != null;
    }

    /**
     * Gets sound equipment type
     * @return sound equipment type
     */
    public SoundEquipmentType getSoundEquipmentType() {
        return soundEquipmentType;
    }

    /**
     * Sets sound equipment type
     * @param soundEquipmentType sound equipment type
     */
    public void setSoundEquipmentType(final SoundEquipmentType soundEquipmentType) {
        this.soundEquipmentType = soundEquipmentType;
    }

    /**
     * Gets power consumption
     * @return power consumption
     */
    public double getPowerConsumption() {
        return powerConsumption;
    }

    /**
     * Sets power consumption
     * @param powerConsumption power consumption
     */
    public void setPowerConsumption(final double powerConsumption) {
        if(powerConsumption < 0){
            throw new IllegalArgumentException("powerConsumption");
        }

        this.powerConsumption = powerConsumption;
    }
}

