package views;

import models.products.PercussionInstrument;
import models.products.PercussionInstrumentType;
import models.products.Promotion;
import utilities.DateUtilities;
import utilities.DoubleUtilities;
import utilities.IntegerUtilities;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Percussion instrument form
 */
@SuppressWarnings("ClassWithTooManyFields")
public class PercussionInstrumentForm  extends PromotionalProductForm<PercussionInstrument> {
    private JPanel mainPanel;
    private JLabel imageLabel;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField imagePathTextField;
    private JButton saveButton;
    private JTextArea descriptionTextArea;
    private JComboBox<PercussionInstrumentType> instrumentTypeComboBox;
    private JTextField priceWithDiscountTextField;
    private JTextField startPromotionTextField;
    private JTextField endPromotionTextField;
    private JTextField discountTextField;
    private JCheckBox soundHeightDefinedCheckBox;
    private JTextField stockCountTextField;

    /**
     * Standard form constructor
     */
    public PercussionInstrumentForm() {
        setSaveButton(saveButton);
        setPriceField(priceTextField);
        setDiscountField(discountTextField);
        setPriceWithDiscountField(priceWithDiscountTextField);

        super.setMainPanel(mainPanel);
    }

    @Override
    protected void saveProduct() {
        final Double price = DoubleUtilities.tryParse(priceTextField.getText());
        final Integer stockCount = IntegerUtilities.tryParse(stockCountTextField.getText());

        if (price == null || stockCount == null) {
            showMessageDialog(null, "Type correct numbers!");

            return;
        }

        final PercussionInstrument instrument = getProduct();
        final Promotion promotion = super.createPromotion(startPromotionTextField.getText(), endPromotionTextField.getText(), discountTextField.getText());

        if (promotion != null && !promotion.isValid()) {
            showMessageDialog(null, "Promotion's fields are incorrect!", "Error", ERROR_MESSAGE);

            return;
        }

        instrument.setPrice(price);
        instrument.setPromotion(promotion);
        instrument.setName(nameTextField.getText());
        instrument.setStockCount(stockCount);
        instrument.setSoundHeightDefined(soundHeightDefinedCheckBox.isSelected());
        instrument.setImagePath(imagePathTextField.getText());
        instrument.setDescription(descriptionTextArea.getText());
        instrument.setPercussionInstrumentType((PercussionInstrumentType) instrumentTypeComboBox.getSelectedItem());

        getPresenter().save(instrument);
    }

    @Override
    protected void initializeFields() {
        final PercussionInstrument instrument = getProduct();

        super.setProductImage(imageLabel, instrument);

        instrumentTypeComboBox.setModel(new DefaultComboBoxModel<>(PercussionInstrumentType.values()));

        nameTextField.setText(instrument.getName());
        imagePathTextField.setText(instrument.getImagePath());
        descriptionTextArea.setText(instrument.getDescription());
        stockCountTextField.setText(Integer.toString(instrument.getStockCount()));
        soundHeightDefinedCheckBox.setSelected(instrument.getSoundHeightDefined());
        priceWithDiscountTextField.setText(Double.toString(instrument.getPrice()));
        priceTextField.setText(Double.toString(instrument.getOriginalPrice()));
        instrumentTypeComboBox.setSelectedItem(instrument.getPercussionInstrumentType());

        final Promotion promotion = instrument.getPromotion();

        if (promotion != null) {
            startPromotionTextField.setText(DateUtilities.format(promotion.getStart()));
            endPromotionTextField.setText(DateUtilities.format(promotion.getEnd()));
            discountTextField.setText(Integer.toString(promotion.getDiscount()));
        }
    }
}
