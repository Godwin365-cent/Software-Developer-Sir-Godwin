package algexplorer.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Simple placeholder panel for the Sorting module.
 * Shows a label and a back‑to‑hub button.
 */
public class SortingPanel extends JPanel {
    public SortingPanel(Consumer<String> navigate) {
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);
        JLabel label = new JLabel("Sorting Module – work in progress", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(UITheme.TEXT_COLOR);
        add(label, BorderLayout.CENTER);
        JButton back = new JButton("Back to Hub");
        back.addActionListener(e -> navigate.accept("HUB"));
        JPanel south = new JPanel();
        south.setBackground(UITheme.BACKGROUND_COLOR);
        south.add(back);
        add(south, BorderLayout.SOUTH);
    }
}
