package algexplorer.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window. It holds a sidebar for navigation and a central area
 * that swaps between the hub and individual module panels.
 */
import algexplorer.ui.UITheme;
import algexplorer.ui.StructuresPanel;
import algexplorer.ui.BenchmarkPanel;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    public MainFrame() {
        setTitle("Algorithm Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use screen bounds that EXCLUDE the Windows taskbar
        Rectangle usable = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int w = Math.min(960, usable.width);
        int h = Math.min(640, usable.height);
        setSize(w, h);
        // Centre within the usable area (not the full screen, so we stay above the taskbar)
        setLocation(usable.x + (usable.width - w) / 2, usable.y + (usable.height - h) / 2);

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
