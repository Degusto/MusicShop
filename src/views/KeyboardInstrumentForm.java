package views;

import models.products.KeyboardInstrument;
import models.products.KeyboardInstrumentType;
import models.products.Promotion;
import utilities.DateUtilities;
import utilities.DoubleUtilities;
import utilities.IntegerUtilities;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Keyboard instrument edit form
 */
@SuppressWarnings("ClassWithTooManyFields")
public class KeyboardInstrumentForm extends PromotionalProductForm<KeyboardInstrument> {
    private JPanel mainPanel;
    private JLabel imageLabel;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField imagePathTextField;
    private JTextField keyCountTextField;
    private JButton saveButton;
    private JTextArea descriptionTextArea;
    private JComboBox<KeyboardInstrumentType> instrumentTypeComboBox;
    private JTextField priceWithDiscountTextField;
    private JTextField startPromotionTextField;
    private JTextField endPromotionTextField;
    private JTextField discountTextField;
    private JTextField stockCountTextField;

    /**
     * Standard constructor
     */
    public KeyboardInstrumentForm() {
        setSaveButton(saveButton);
        setPriceField(priceTextField);
        setDiscountField(discountTextField);
        setPriceWithDiscountField(priceWithDiscountTextField);

        super.setMainPanel(mainPanel);
    }

    @Override
    protected void saveProduct() {
        final Double price = DoubleUtilities.tryParse(priceTextField.getText());
        final Integer keyCount = IntegerUtilities.tryParse(keyCountTextField.getText());
        final Integer stockCount = IntegerUtilities.tryParse(stockCountTextField.getText());

        if (price == null || keyCount == null || stockCount == null) {
            showMessageDialog(null, "Type correct numbers!");

            return;
        }

        final KeyboardInstrument instrument = getProduct();
        final Promotion promotion = super.createPromotion(startPromotionTextField.getText(), endPromotionTextField.getText(), discountTextField.getText());

        if (promotion != null && !promotion.isValid()) {
            showMessageDialog(null, "Promotion's fields are incorrect!", "Error", ERROR_MESSAGE);

            return;
        }

        instrument.setPromotion(promotion);
        instrument.setName(nameTextField.getText());
        instrument.setPrice(price);
        instrument.setStockCount(stockCount);
        instrument.setDescription(descriptionTextArea.getText());
        instrument.setImagePath(imagePathTextField.getText());
        instrument.setKeyCount(keyCount);
        instrument.setKeyboardInstrumentType((KeyboardInstrumentType) instrumentTypeComboBox.getSelectedItem());

        getPresenter().save(instrument);
    }

    @Override
    protected void initializeFields() {
        final KeyboardInstrument instrument = getProduct();

        super.setProductImage(imageLabel, instrument);

        instrumentTypeComboBox.setModel(new DefaultComboBoxModel<>(KeyboardInstrumentType.values()));

        nameTextField.setText(instrument.getName());
        imagePathTextField.setText(instrument.getImagePath());
        descriptionTextArea.setText(instrument.getDescription());
        keyCountTextField.setText(Integer.toString(instrument.getKeyCount()));
        priceWithDiscountTextField.setText(Double.toString(instrument.getPrice()));
        priceTextField.setText(Double.toString(instrument.getOriginalPrice()));
        stockCountTextField.setText(Integer.toString(instrument.getStockCount()));
        instrumentTypeComboBox.setSelectedItem(instrument.getKeyboardInstrumentType());

        final Promotion promotion = instrument.getPromotion();

        if (promotion != null) {
            startPromotionTextField.setText(DateUtilities.format(promotion.getStart()));
            endPromotionTextField.setText(DateUtilities.format(promotion.getEnd()));
            discountTextField.setText(Integer.toString(promotion.getDiscount()));
        }
    }
}
