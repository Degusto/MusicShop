package views;

import models.products.*;
import presenters.interfaces.NewProductView;
import presenters.interfaces.ProductView;
import presenters.interfaces.ViewFactory;

/**
 * ViewFactory implementation
 */
public class ViewFactoryImpl implements ViewFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Product> ProductView<T> getProductView(final Class<T> type) {
        if (type == Guitar.class) {
            return (ProductView<T>)new GuitarForm();
        }

        if (type == KeyboardInstrument.class) {
            return (ProductView<T>)new KeyboardInstrumentForm();
        }

        if (type == PercussionInstrument.class) {
            return (ProductView<T>)new PercussionInstrumentForm();
        }

        if (type == SoundEquipment.class) {
            return (ProductView<T>)new SoundEquipmentForm();
        }

        throw new IllegalArgumentException("given product type is not supported");
    }

    @Override
    public NewProductView getNewProductView() {
        return new NewProductForm();
    }
}
