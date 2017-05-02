package presenters.interfaces;

import presenters.NewProductPresenter;

/**
 * New product view interface
 */
public interface NewProductView {
    /**
     * Shows view
     */
    void showView();

    /**
     * Sets presenter for view
     * @param newProductPresenter presenter
     */
    void setPresenter(NewProductPresenter newProductPresenter);

    /**
     * Closes view
     */
    void close();
}
