package algexplorer.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window. It holds a sidebar for navigation and a central area
 * that swaps between the hub and individual module panels.
 */
public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    public MainFrame() {
        setTitle("Algorithm Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 640);
        setLocationRelativeTo(null);

        // Sidebar with navigation buttons
        ModernSidebar sidebar = new ModernSidebar(this::showPanel);

        // Add the hub and placeholder modules to the content panel
        contentPanel.add(new HubPanel(this::showPanel), "HUB");
        contentPanel.add(new SortingPanel(this::showPanel), "SORTING");
        contentPanel.add(new RecursionPanel(this::showPanel), "RECURSION");
        contentPanel.add(new StructuresPanel(this::showPanel), "STRUCTURES");
        contentPanel.add(new BenchmarkPanel(this::showPanel), "BENCHMARK");

        // Layout: sidebar on the left, content on the right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, contentPanel);
        splitPane.setDividerLocation(200);
        splitPane.setResizeWeight(0.0);
        getContentPane().add(splitPane);

        // Show the hub on startup
        showPanel("HUB");
    }

    /**
     * Switches the central panel to the named card.
     */
    private void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }
}
