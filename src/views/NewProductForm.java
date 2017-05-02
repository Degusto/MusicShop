package views;

import models.products.Guitar;
import models.products.KeyboardInstrument;
import models.products.PercussionInstrument;
import models.products.SoundEquipment;
import presenters.NewProductPresenter;
import presenters.interfaces.NewProductView;
import utilities.Guard;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

/**
 * New product form
 */
public class NewProductForm extends JFrame implements NewProductView{
    private JDialog dialog;
    private JPanel mainPanel;
    private JButton addButton;
    private JComboBox<String> productComboBox;
    private NewProductPresenter newProductPresenter;

    /**
     * Standard constructor
     */
    public NewProductForm(){
        super.add(mainPanel);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                if(productComboBox.getSelectedIndex() != -1){
                    switch(productComboBox.getSelectedItem().toString()){
                        case "Guitar":
                            newProductPresenter.addProduct(new Guitar());
                            break;
                        case "Keyboard instrument":
                            newProductPresenter.addProduct(new KeyboardInstrument());
                            break;
                        case "Percussion instrument":
                            newProductPresenter.addProduct(new PercussionInstrument());
                            break;
                        case "Sound equipment":
                            newProductPresenter.addProduct(new SoundEquipment());
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void showView() {
        productComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Guitar", "Keyboard instrument", "Percussion instrument", "Sound equipment"}));

        dialog = new JDialog((JFrame)this.getParent(), "Add new product", true);
        dialog.getContentPane().add(mainPanel);
        dialog.setSize(300,180);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(mainPanel);
        dialog.pack();
        dialog.setVisible(true);
    }

    @Override
    public void setPresenter(final NewProductPresenter newProductPresenter) {
        Guard.notNull(newProductPresenter, "newProductPresenter");

        this.newProductPresenter = newProductPresenter;
    }

    @Override
    public void close() {
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
        super.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
