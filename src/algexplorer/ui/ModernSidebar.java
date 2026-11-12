package algexplorer.ui;

import java.awt.*;
import javax.swing.*;
import java.util.function.Consumer;

/**
 * Simple sidebar with navigation buttons.
 * Calls the provided Consumer<String> with the target panel name.
 */
public class ModernSidebar extends JPanel {
    private final Consumer<String> navigate;

    public ModernSidebar(Consumer<String> navigate) {
        this.navigate = navigate;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(UITheme.SIDEBAR_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(createButton("Hub", "HUB"));
        add(Box.createVerticalStrut(10));
        add(createButton("Sorting", "SORTING"));
        add(Box.createVerticalStrut(5));
        add(createButton("Recursion", "RECURSION"));
        add(Box.createVerticalStrut(5));
        add(createButton("Data Structures", "STRUCTURES"));
        add(Box.createVerticalStrut(5));
        add(createButton("Benchmark", "BENCHMARK"));
    }

    private JButton createButton(String label, String target) {
        JButton btn = new JButton(label);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setFocusPainted(false);
        btn.setBackground(UITheme.SIDEBAR_BUTTON_COLOR);
        btn.setForeground(UITheme.TEXT_COLOR);
        btn.addActionListener(e -> navigate.accept(target));
        return btn;
    }
}
