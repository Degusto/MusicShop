package views;

import presenters.interfaces.DialogService;

import javax.swing.*;

/**
 * Implementation of DialogService
 */
public class DialogServiceImpl implements DialogService {
    @Override
    public void showInfo(final String info) {
        JOptionPane.showMessageDialog(null, info);
    }

    @Override
    public boolean showConfirmationDialog(final String message) {
        return JOptionPane.showConfirmDialog(null, message) == JOptionPane.YES_OPTION;
    }

    @Override
    public void showError(final String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
