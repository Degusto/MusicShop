package views;

import models.products.Promotion;
import models.products.SoundEquipment;
import models.products.SoundEquipmentType;
import utilities.DateUtilities;
import utilities.DoubleUtilities;
import utilities.IntegerUtilities;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Form for any sound equipment product
 */
@SuppressWarnings("ClassWithTooManyFields")
public class SoundEquipmentForm extends PromotionalProductForm<SoundEquipment>{
    private JPanel mainPanel;
    private JLabel imageLabel;
    private JButton saveButton;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField discountTextField;
    private JTextArea descriptionTextArea;
    private JTextField imagePathTextField;
    private JComboBox<SoundEquipmentType> equipmentTypeComboBox;
    private JTextField endPromotionTextField;
    private JTextField startPromotionTextField;
    private JTextField priceWithDiscountTextField;
    private JTextField powerConsumptionTextField;
    private JTextField stockCountTextField;

    /**
     * Creates form
     */
    public SoundEquipmentForm(){
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
        final Double powerConsumption = DoubleUtilities.tryParse(powerConsumptionTextField.getText());

        if (price == null || stockCount == null || powerConsumption == null) {
            showMessageDialog(null, "Type correct numbers!");

            return;
        }

        final SoundEquipment soundEquipment = getProduct();
        final Promotion promotion = super.createPromotion(startPromotionTextField.getText(), endPromotionTextField.getText(), discountTextField.getText());

        if (promotion != null && !promotion.isValid()) {
            showMessageDialog(null, "Promotion's fields are incorrect!", "Error", ERROR_MESSAGE);

            return;
        }

        soundEquipment.setPrice(price);
        soundEquipment.setPromotion(promotion);
        soundEquipment.setStockCount(stockCount);
        soundEquipment.setName(nameTextField.getText());
        soundEquipment.setPowerConsumption(powerConsumption);
        soundEquipment.setImagePath(imagePathTextField.getText());
        soundEquipment.setDescription(descriptionTextArea.getText());
        soundEquipment.setSoundEquipmentType((SoundEquipmentType) equipmentTypeComboBox.getSelectedItem());

        getPresenter().save(soundEquipment);
    }

    @Override
    protected void initializeFields() {
        final SoundEquipment soundEquipment = getProduct();

        super.setProductImage(imageLabel, soundEquipment);

        equipmentTypeComboBox.setModel(new DefaultComboBoxModel<>(SoundEquipmentType.values()));

        nameTextField.setText(soundEquipment.getName());
        imagePathTextField.setText(soundEquipment.getImagePath());
        descriptionTextArea.setText(soundEquipment.getDescription());
        stockCountTextField.setText(Integer.toString(soundEquipment.getStockCount()));
        powerConsumptionTextField.setText(Double.toString(soundEquipment.getPowerConsumption()));
        priceWithDiscountTextField.setText(Double.toString(soundEquipment.getPrice()));
        priceTextField.setText(Double.toString(soundEquipment.getOriginalPrice()));
        equipmentTypeComboBox.setSelectedItem(soundEquipment.getSoundEquipmentType());

        final Promotion promotion = soundEquipment.getPromotion();

        if (promotion != null) {
            startPromotionTextField.setText(DateUtilities.format(promotion.getStart()));
            endPromotionTextField.setText(DateUtilities.format(promotion.getEnd()));
            discountTextField.setText(Integer.toString(promotion.getDiscount()));
        }
    }
}
