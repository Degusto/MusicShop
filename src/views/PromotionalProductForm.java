package views;

import models.products.Product;
import models.products.Promotion;
import utilities.DateUtilities;
import utilities.DoubleUtilities;
import utilities.IntegerUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.ParseException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Base class for product with promotion
 * @param <T> product type
 */
abstract class PromotionalProductForm<T extends Product> extends ProductForm<T> {
    private JTextField priceField;
    private JTextField discountField;
    private JTextField priceWithDiscountField;

    /**
     * Sets price text field
     * @param priceField price text field
     */
    void setPriceField(final JTextField priceField) {
        this.priceField = priceField;
    }

    /**
     * Sets discount field
     * @param discountField discount text field
     */
    void setDiscountField(final JTextField discountField) {
        this.discountField = discountField;

        if (discountField == null) {
            return;
        }

        discountField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                showPricePreview();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                showPricePreview();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                showPricePreview();
            }
        });
    }

    /**
     * Shows price with discount
     */
    private void showPricePreview() {
        if (discountField == null || priceField == null) {
            return;
        }

        final Integer discount = IntegerUtilities.tryParse(discountField.getText());
        final Double price = DoubleUtilities.tryParse(priceField.getText());

        if (price == null || discount == null || discount < 5 || discount > 50) {
            priceWithDiscountField.setText("");
        } else {
            final double priceWithDiscount = price - (price * (double)discount / 100D);

            priceWithDiscountField.setText(Double.toString(Math.round(priceWithDiscount * 100.0) / 100.0));
        }

    }

    /**
     * Sets price with discount filed
     * @param priceWithDiscountField price with discount text field
     */
    void setPriceWithDiscountField(final JTextField priceWithDiscountField) {
        this.priceWithDiscountField = priceWithDiscountField;
    }

    /**
     * Creates promotion
     * @param startDate start date
     * @param endDate end date
     * @param discount discount
     * @return promotion
     */
    Promotion createPromotion(final String startDate, final String endDate, final String discount) {
        if (startDate.length() == 0 && endDate.length() == 0 && discount.length() == 0) {
            return null;
        }

        final Promotion promotion = new Promotion();

        try {
            promotion.setStart(DateUtilities.parse(startDate));
            promotion.setEnd(DateUtilities.parse(endDate));
            promotion.setDiscount(Integer.parseInt(discount));
        } catch (final ParseException e) {
            showMessageDialog(null, "Dates must be in format YYYY-MM-DD", "Error", ERROR_MESSAGE);

        } catch (final NumberFormatException e) {
            showMessageDialog(null, "Discount must be between 5 and 50", "Error", ERROR_MESSAGE);

        }

        return promotion;
    }
}