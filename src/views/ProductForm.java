package views;

import models.products.Product;
import presenters.ProductPresenter;
import presenters.interfaces.ProductView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Base class for product's form
 * @param <T> Product Type
 */
public abstract class ProductForm<T extends Product> extends JFrame implements ProductView<T> {
    private T product;
    private ProductPresenter<T> presenter;
    private JDialog dialog;
    private boolean skipImage;

    @Override
    public void setPresenter(final ProductPresenter<T> productPresenter) {
        this.presenter = productPresenter;
    }

    @Override
    public void showProduct(final T product, final boolean newProduct) {
        this.product = product;
        this.skipImage = newProduct;

        initializeFields();

        dialog.setLocationRelativeTo(null);
        dialog.setTitle(product.getName());
        dialog.setVisible(true);
    }

    /**
     * Sets main panel and main panel's properties
     * @param mainPanel form's main panel
     */
    void setMainPanel(final JPanel mainPanel){
        dialog = new JDialog((JFrame)this.getParent());
        dialog.getContentPane().add(mainPanel);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(mainPanel);
        dialog.setModal(true);
        dialog.pack();
        dialog.setSize(1000,768);
    }

    @Override
    public void close() {
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
        super.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Method which is invoked when user wants to save product
     */
    protected abstract void saveProduct();

    /**
     * Initialize GUI fields before form is displayed
     */
    protected abstract void initializeFields();

    /**
     * Adds listener for mouse click on save button
     * @param saveButton save button
     */
    void setSaveButton(final JButton saveButton) {
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                if (SwingUtilities.isLeftMouseButton(e)) {
                    saveProduct();
                }
            }
        });
    }

    /**
     * Display product's image, if product has just been added then image is not displayed because path is empty
     * @param imageLabel label which display image
     * @param product product
     */
    void setProductImage(final JLabel imageLabel, final T product) {
        if(skipImage){
            return;
        }

        try {
            ImageIcon productImage = new ImageIcon(product.getImage());

            productImage = new ImageIcon(productImage.getImage().getScaledInstance(640,360, Image.SCALE_SMOOTH));

            imageLabel.setIcon(productImage);
        } catch (final IOException e) {
            showMessageDialog(this, "Image not found: " + product.getImagePath());
        }
    }

    /**
     * Returns presenter instance
     * @return presenter instance
     */
    ProductPresenter<T> getPresenter() {
        return this.presenter;
    }

    /**
     * Returns product instance
     * @return product instance
     */
    T getProduct() {
        return this.product;
    }
}
