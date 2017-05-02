package presenters;

import models.Shop;
import models.XmlSerializer;
import models.products.Product;
import presenters.interfaces.DialogService;
import presenters.interfaces.MainView;
import utilities.Guard;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Presenter which controls shop view
 */
public final class MainPresenter {
    private Shop shop;
    private String productFilter = "";

    private final MainView view;
    private final XmlSerializer serializer;
    private final DialogService dialogService;
    private final PresenterFactory presenterFactory;

    /**
     * Constructor with required dependencies
     * @param view shop view
     * @param presenterFactory presenter factory
     * @param dialogService dialog service
     * @param serializer xml serializer
     */
    public MainPresenter(final MainView view, final PresenterFactory presenterFactory, final DialogService dialogService, final XmlSerializer serializer) {
        Guard.notNull(view, "view");
        Guard.notNull(dialogService, "dialogService");
        Guard.notNull(presenterFactory, "presenterFactory");
        Guard.notNull(serializer, "serializer");

        this.view = view;
        this.serializer = serializer;
        this.dialogService = dialogService;
        this.presenterFactory = presenterFactory;

        this.shop = new Shop();

        if(new File("shop.xml").exists()){
            importSettings("shop.xml");
        }

        this.view.setRepairServices(shop.getRepairService().getServices());
        this.view.setServices(shop.getServices());
    }

    private List<Product> getProducts() {
        return shop.getProducts()
                .stream()
                .filter(product -> product.getName().toLowerCase().contains(productFilter))
                .map(Product::copy)
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Shows product's edit form
     * @param product product to edit
     */
    public void showProduct(final Product product){
        showProduct(product, false);
    }

    private void showProduct(Product product, final boolean newProduct) {
        if (product == null) {
            return;
        }

        final ProductPresenter<Product> productPresenter = presenterFactory.createProductPresenter(product.getClass());

        final String productId = product.getId();

        product = shop.getProduct(productId);

        productPresenter.addUpdateListener(this::updateProduct);
        productPresenter.show(product, newProduct);
    }

    private void updateProduct(final Product product) {
        shop.removeProduct(product.getId());
        shop.addProduct(product);

        updateProducts(this.productFilter);
    }

    /**
     * Adds new product and open its edit form
     */
    public void addNewProduct(){
        final NewProductPresenter presenter = presenterFactory.createNewProductPresenter();

        presenter.addNewProductListener(product -> {
            shop.addProduct(product);

            updateProducts(productFilter);

            showProduct(product.copy(), true);
        });

        presenter.show();
    }

    /**
     * Removes product from shop
     * @param product product to remove
     */
    public void removeProduct(final Product product) {
        final String message = "Do you want to delete this product(" + product.getName() + ")?";

        if (dialogService.showConfirmationDialog(message)) {
            shop.removeProduct(product.getId());

            updateProducts(productFilter);
        }
    }

    /**
     * Filters product by product's name
     * @param productFilter product name filter
     */
    public void updateProducts(final String productFilter) {
        this.productFilter = productFilter.toLowerCase();

        this.view.setProducts(getProducts());
    }

    /**
     * Exports products to file
     * @param path path to destination XML file
     */
    public void exportSettings(final String path){
        try {
            serializer.serialize(path, shop);
        } catch (final JAXBException e) {
            dialogService.showError(e.getMessage());
        }
    }

    /**
     * Imports products from XML file
     * @param path path to XML file
     */
    public void importSettings(final String path){
        try {
            this.shop = serializer.deserialize(path, Shop.class);

            updateProducts(this.productFilter);
        } catch (final JAXBException e) {
            dialogService.showError(e.getMessage());
        }
    }
}
