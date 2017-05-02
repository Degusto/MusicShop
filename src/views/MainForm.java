package views;

import models.XmlSerializer;
import models.products.Product;
import models.services.Service;
import presenters.MainPresenter;
import presenters.PresenterFactory;
import presenters.interfaces.DialogService;
import presenters.interfaces.MainView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Shop view
 */
@SuppressWarnings("ClassWithTooManyFields")
public class MainForm extends JFrame implements MainView {
    private JPanel mainPanel;
    private JTable servicesTable;
    private JTable repairServiceTable;
    private JTextField searchTextField;
    private JButton searchButton;
    private JTable productsTable;
    private JButton importSettingsButton;
    private JButton exportSettingsButton;
    private JButton newProductButton;

    private List<Product> products;
    private final MainPresenter presenter;
    private final JFileChooser fileChooser;

    /**
     * Standard constructor
     */
    public MainForm() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML", "xml"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        final DialogService dialogService = new DialogServiceImpl();

        this.presenter = new MainPresenter(this, new PresenterFactory(new ViewFactoryImpl(), dialogService), dialogService, new XmlSerializer() {});

        super.add(mainPanel);
        super.setTitle("Music shop");

        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        servicesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        repairServiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addListeners();
    }

    private void addListeners() {
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                presenter.updateProducts(searchTextField.getText());
            }
        });

        newProductButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                presenter.addNewProduct();
            }
        });

        productsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int row = productsTable.rowAtPoint(e.getPoint());

                if (row != -1 && e.getClickCount() == 2) {
                    presenter.showProduct(products.get(row));
                }
            }
        });

        productsTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_DELETE && productsTable.getSelectedRow() != -1) {
                    presenter.removeProduct(products.get(productsTable.getSelectedRow()));
                }
            }
        });
        exportSettingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                if(fileChooser.showDialog(mainPanel, "Export") == JFileChooser.APPROVE_OPTION){
                    presenter.exportSettings(fileChooser.getSelectedFile().getPath());
                }
            }
        });
        importSettingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                super.mouseClicked(e);

                if(fileChooser.showDialog(mainPanel, "Import") == JFileChooser.APPROVE_OPTION){
                    presenter.importSettings(fileChooser.getSelectedFile().getPath());
                }
            }
        });
    }

    @Override
    public void setProducts(final List<Product> products) {
        this.products = products;

        final List<TableRecord> records = products.stream().map(p -> new TableRecord(p.getName(), p.getPrice())).collect(Collectors.toList());

        productsTable.setModel(new RecordsTableModel(records));
    }

    @Override
    public void setRepairServices(final List<Service> services) {
        final List<TableRecord> records = services.stream().map(p -> new TableRecord(p.getName(), p.getPrice())).collect(Collectors.toList());

        repairServiceTable.setModel(new RecordsTableModel(records));
    }

    @Override
    public void setServices(final List<Service> services) {
        final List<TableRecord> records = services.stream().map(p -> new TableRecord(p.getName(), p.getPrice())).collect(Collectors.toList());

        servicesTable.setModel(new RecordsTableModel(records));
    }
}
