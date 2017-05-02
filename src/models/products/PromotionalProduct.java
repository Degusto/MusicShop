package models.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Product with promotion
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PromotionalProduct extends Product {
    @XmlElement
    private Promotion promotion;

    /**
     * Get current promotion
     * @return promotion
     */
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * Sets current promotion
     * @param promotion promotion
     */
    public void setPromotion(final Promotion promotion) {
        if (promotion == null) {
            this.promotion = null;

            return;
        }

        this.promotion = promotion.copy();
    }

    @Override
    public boolean isValid() {
        return super.isValid() && promotion != null && promotion.isValid();
    }

    @Override
    public double getPrice() {
        double price = super.getPrice();

        if (promotion != null && promotion.isActive()) {
            price = price - price * (double) promotion.getDiscount() / 100D;
        }

        return Math.round(price * 100.0) / 100.0;
    }

    /**
     * Returns price without discount
     * @return price without discount
     */
    public double getOriginalPrice() {
        return super.getPrice();
    }
}
