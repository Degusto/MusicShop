package presenters.interfaces;

/**
 * Interface for dialog service
 */
public interface DialogService {
    /**
     * Shows styled information message to user
     * @param info message to show
     */
    @SuppressWarnings("SameParameterValue")
    void showInfo(String info);

    /**
     * Shows confirmation dialog
     * @param message question to user
     * @return true: OK, false: CANCEL
     */
    boolean showConfirmationDialog(String message);

    /**
     * Shows styled error message
     * @param message error message
     */
    void showError(String message);
}
