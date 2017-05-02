package views;

import models.products.Guitar;
import models.products.GuitarType;
import utilities.DoubleUtilities;
import utilities.IntegerUtilities;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Guitar edit form
 */
@SuppressWarnings("ClassWithTooManyFields")
public final class GuitarForm extends ProductForm<Guitar> {
    private JPanel mainPanel;
    private JLabel imageLabel;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JButton saveButton;
    private JTextField imagePathTextField;
    private JTextArea descriptionTextArea;
    private JTextField stringCountTextField;
    private JTextField pickupCountTextField;
    private JTextField fretCountTextField;
    private JComboBox<GuitarType> guitarTypeComboBox;
    private JTextField stockCountTextField;

    /**
     * Standard constructor
     */
    public GuitarForm() {
        setSaveButton(saveButton);

        super.setMainPanel(mainPanel);
    }

    @Override
    protected void saveProduct() {
        final Double price = DoubleUtilities.tryParse(priceTextField.getText());
        final Integer fretCount = IntegerUtilities.tryParse(fretCountTextField.getText());
        final Integer stringCount = IntegerUtilities.tryParse(stringCountTextField.getText());
        final Integer pickupCount = IntegerUtilities.tryParse(pickupCountTextField.getText());
        final Integer stockCount = IntegerUtilities.tryParse(stockCountTextField.getText());

        if (price == null || fretCount == null || stringCount == null || pickupCount == null || stockCount == null) {
            showMessageDialog(null, "Type correct numbers!");

            return;
        }

        final Guitar guitar = getProduct();

        guitar.setName(nameTextField.getText());
        guitar.setPrice(price);
        guitar.setPickupCount(pickupCount);
        guitar.setStringCount(stringCount);
        guitar.setFretCount(fretCount);
        guitar.setStockCount(stockCount);
        guitar.setImagePath(imagePathTextField.getText());
        guitar.setDescription(descriptionTextArea.getText());
        guitar.setGuitarType((GuitarType) guitarTypeComboBox.getSelectedItem());

        getPresenter().save(guitar);
    }

    @Override
    protected void initializeFields() {
        final Guitar guitar = getProduct();

        super.setProductImage(imageLabel, guitar);

        guitarTypeComboBox.setModel(new DefaultComboBoxModel<>(GuitarType.values()));

        nameTextField.setText(guitar.getName());
        stockCountTextField.setText(Integer.toString(guitar.getStockCount()));
        imagePathTextField.setText(guitar.getImagePath());
        priceTextField.setText(Double.toString(guitar.getPrice()));
        descriptionTextArea.setText(guitar.getDescription());
        stringCountTextField.setText(Integer.toString(guitar.getStringCount()));
        fretCountTextField.setText(Integer.toString(guitar.getFretCount()));
        pickupCountTextField.setText(Integer.toString(guitar.getPickupCount()));
        guitarTypeComboBox.setSelectedItem(guitar.getGuitarType());
    }
}
