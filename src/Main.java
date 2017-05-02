import views.MainForm;

import javax.swing.*;

import static javax.swing.SwingUtilities.*;

/**
 * Application start class
 */
final class Main {

    /**
     * Application bootstrapping function
     * @param args arguments from command line
     */
    public static void main(final String[] args) {
        invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (final Exception e) {
            // No Nimbus, so style is default.
        }

        final MainForm window = new MainForm();

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1000,600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
