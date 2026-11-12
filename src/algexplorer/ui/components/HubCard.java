package algexplorer.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A simple card component used on the hub screen. It displays a title and an optional
 * description and fires the provided ActionListener when clicked.
 */
public class HubCard extends JPanel {
    private final JButton button;

    public HubCard(String title, String description, ActionListener onClick) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(UITheme.SECONDARY_COLOR, 2, true));
        setBackground(UITheme.PRIMARY_COLOR);

        button = new JButton("<html><b>" + title + "</b><br/>" + description + "</html>");
        button.setFocusPainted(false);
        button.setBackground(UITheme.PRIMARY_COLOR);
        button.setForeground(UITheme.TEXT_COLOR);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(onClick);
        add(button, BorderLayout.CENTER);
    }
}
