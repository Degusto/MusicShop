package presenters;

import models.products.Product;
import presenters.interfaces.DialogService;
import presenters.interfaces.ProductView;
import utilities.Guard;

/**
 * Product's presenter which control product's view
 * @param <T> product type
 */
public class ProductPresenter<T extends Product> {
    private ProductUpdateListener updateListener;

    private final ProductView<T> view;
    private final DialogService dialogService;

    /**
     * Constructor for product presenter
     * @param view view instance
     * @param dialogService dialog service
     */
    public ProductPresenter(final ProductView<T> view, final DialogService dialogService) {
        Guard.notNull(view, "view");
        Guard.notNull(dialogService, "dialogService");

        this.view = view;
        this.dialogService = dialogService;

        this.view.setPresenter(this);
    }

    /**
     * Shows product's view
     * @param product product to show
     * @param newProduct indicates whether view is in new/edit mode
     */
    public void show(final T product, final boolean newProduct) {
        Guard.notNull(product, "product");

        //noinspection unchecked
        view.showProduct((T)product.copy(), newProduct);
    }

    /**
     * Saves product
     * @param product product to save
     */
    public void save(final T product){
        Guard.notNull(product, "product");

        if(!dialogService.showConfirmationDialog("Do you want to save a product?")){
            return;
        }

        if(!product.isValid()){
            dialogService.showInfo("Fill fields with correct data.");

            return;
        }

        if(this.updateListener != null){
            updateListener.update(product);

            view.close();
        }
    }

    /**
     * Adds listener for update
     * @param updateable listener
     */
    public void addUpdateListener(final ProductUpdateListener updateable) {
        this.updateListener = updateable;
    }
}
