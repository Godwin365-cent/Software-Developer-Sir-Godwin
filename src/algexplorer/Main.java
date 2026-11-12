package algexplorer;

import javax.swing.SwingUtilities;
import algexplorer.ui.MainFrame;

/**
 * Entry point for the Algorithm Explorer application.
 * Shows a catalog hub where each algorithm module can be opened.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
