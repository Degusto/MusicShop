package models.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * Promotion class
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class Promotion {
    @XmlElement
    private Date start;

    @XmlElement
    private Date end;

    @XmlElement
    private int discount;

    /**
     * Returns promotion copy
     * @return copied promotion
     */
    public Promotion copy() {
        final Promotion promotion = new Promotion();

        promotion.setStart(getStart());
        promotion.setEnd(getEnd());
        promotion.setDiscount(getDiscount());

        return promotion;
    }

    /**
     * Checks if promotion is valid
     * @return promotion state
     */
    public boolean isValid() {
        return start != null && end != null
                && start.before(end) && end.after(start)
                && discount >= 5 && discount <= 50;

    }

    /**
     * Checks if promotion is active
     * @return whether promotion is active
     */
    public boolean isActive() {
        final Date currentDate = new Date();

        return isValid() && currentDate.after(start) && currentDate.before(end);
    }

    /**
     * Gets promotion's discount
     * @return discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets promotion's discount
     * @param discount promotion's discount
     */
    public void setDiscount(final int discount) {
        this.discount = discount;
    }

    /**
     * Returns promotion's end date
     * @return promotion's end date
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Sets promotion's end date
     * @param end end date
     */
    public void setEnd(final Date end) {
        this.end = end;
    }

    /**
     * Returns promotion's start date
     * @return promotion's start date
     */
    public Date getStart() {
        return start;
    }

    /**
     * Sets promotion's start date
     * @param start start date
     */
    public void setStart(final Date start) {
        this.start = start;
    }
}
