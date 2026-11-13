package algexplorer.ui;

import algexplorer.ui.components.HubCard;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Hub panel that shows a grid of HubCard components for each algorithm/module.
 * Includes an "Exit App" button at the bottom.
 */
public class HubPanel extends JPanel {
    private final Consumer<String> navigate;
    private final JButton exitButton = new JButton("Exit App");

    public HubPanel(Consumer<String> navigate) {
        this.navigate = navigate;
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);
        add(createHeader(), BorderLayout.NORTH);
        add(createCardsGrid(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JComponent createHeader() {
        JLabel title = new JLabel("Algorithm Explorer – Catalog Hub", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(UITheme.TEXT_COLOR);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        return title;
    }

    private JComponent createCardsGrid() {
        JPanel grid = new JPanel(new GridLayout(0, 2, 20, 20));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        grid.setBackground(UITheme.BACKGROUND_COLOR);

        List<CardInfo> cards = new ArrayList<>();
        cards.add(new CardInfo("Sorting", "Explore classic sorting algorithms", "SORTING"));
        cards.add(new CardInfo("Recursion", "Dive into recursive problem solving", "RECURSION"));
        cards.add(new CardInfo("Data Structures", "Learn about stacks, queues, trees…", "STRUCTURES"));
        cards.add(new CardInfo("Benchmark", "Measure performance of implementations", "BENCHMARK"));

        for (CardInfo info : cards) {
            HubCard card = new HubCard(info.title, info.description, e -> navigate.accept(info.target));
            grid.add(card);
        }
        return new JScrollPane(grid);
    }

    private JComponent createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(UITheme.BACKGROUND_COLOR);
        exitButton.addActionListener(e -> System.exit(0));
        footer.add(exitButton);
        return footer;
    }

    private static class CardInfo {
        final String title;
        final String description;
        final String target;
        CardInfo(String title, String description, String target) {
            this.title = title;
            this.description = description;
            this.target = target;
        }
    }
}
