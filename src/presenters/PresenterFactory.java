package presenters;

import models.products.*;
import presenters.interfaces.DialogService;
import presenters.interfaces.ViewFactory;
import utilities.Guard;

/**
 * Presenters factory
 */
public final class PresenterFactory {
    private final DialogService dialogService;
    private final ViewFactory productViewFactory;

    /**
     * Dependencies constructor
     * @param productViewFactory view factory
     * @param dialogService dialog service
     */
    public PresenterFactory(final ViewFactory productViewFactory, final DialogService dialogService) {
        Guard.notNull(productViewFactory, "productViewFactory");
        Guard.notNull(dialogService, "dialogService");

        this.dialogService = dialogService;
        this.productViewFactory = productViewFactory;
    }

    /**
     * Creates product presenter
     * @param type product type
     * @param <T> product type
     * @return presenter for given product type
     */
    @SuppressWarnings("unchecked")
    public <T extends Product> ProductPresenter<T> createProductPresenter(final Class<? extends Product> type) {
        if (type == Guitar.class) {
            return (ProductPresenter<T>) new ProductPresenter<>(productViewFactory.getProductView(Guitar.class), dialogService);
        }

        if (type == KeyboardInstrument.class) {
            return (ProductPresenter<T>)new ProductPresenter<>(productViewFactory.getProductView(KeyboardInstrument.class), dialogService);
        }

        if (type == PercussionInstrument.class) {
            return (ProductPresenter<T>)new ProductPresenter<>(productViewFactory.getProductView(PercussionInstrument.class), dialogService);
        }

        if (type == SoundEquipment.class) {
            return (ProductPresenter<T>)new ProductPresenter<>(productViewFactory.getProductView(SoundEquipment.class), dialogService);
        }

        throw new IllegalArgumentException("given product type is not supported");
    }

    /**
     * Creates new product presenter
     * @return new product presenter
     */
    public NewProductPresenter createNewProductPresenter(){
        return new NewProductPresenter(productViewFactory.getNewProductView());
    }
}
