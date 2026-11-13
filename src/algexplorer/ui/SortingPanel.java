package algexplorer.ui;

import algexplorer.sorting.SortAlgorithms;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Panel that lets the user pick a sorting algorithm, enter numbers, run the sort
 * and see the sorted result. Includes a "Back to Hub" button and respects the
 * user‑specified data size (1‑100 elements).
 */
public class SortingPanel extends JPanel {
    private final Consumer<String> navigate;
    private final JTextField inputField = new JTextField(30);
    private final JComboBox<String> algorithmBox = new JComboBox<>(new String[]{"Bubble Sort", "Selection Sort", "Insertion Sort"});
    private final JTextArea outputArea = new JTextArea(8, 30);

    public SortingPanel(Consumer<String> navigate) {
        this.navigate = navigate;
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);

        // Header
        JLabel header = new JLabel("Sorting Algorithms", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        header.setForeground(UITheme.TEXT_COLOR);
        header.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        add(header, BorderLayout.NORTH);

        // Center form
        JPanel form = new JPanel();
        form.setBackground(UITheme.BACKGROUND_COLOR);
        form.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);
        gc.gridx = 0; gc.gridy = 0; gc.anchor = GridBagConstraints.EAST;
        form.add(new JLabel("Algorithm:"), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.WEST;
        form.add(algorithmBox, gc);
        gc.gridx = 0; gc.gridy++;
        gc.anchor = GridBagConstraints.EAST;
        form.add(new JLabel("Numbers (comma‑separated):"), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.WEST;
        inputField.setToolTipText("Enter 1‑100 integers separated by commas");
        form.add(inputField, gc);
        gc.gridx = 0; gc.gridy++;
        gc.gridwidth = 2; gc.anchor = GridBagConstraints.CENTER;
        JButton runBtn = new JButton("Run Sort");
        runBtn.addActionListener(e -> runSort());
        form.add(runBtn, gc);
        gc.gridy++; // output area
        outputArea.setEditable(false);
        outputArea.setBackground(UITheme.PRIMARY_COLOR);
        outputArea.setForeground(UITheme.TEXT_COLOR);
        JScrollPane scroll = new JScrollPane(outputArea);
        form.add(scroll, gc);
        add(form, BorderLayout.CENTER);

        // Footer with navigation
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(UITheme.BACKGROUND_COLOR);
        JButton back = new JButton("Back to Hub");
        back.addActionListener(e -> navigate.accept("HUB"));
        footer.add(back);
        add(footer, BorderLayout.SOUTH);
    }

    private void runSort() {
        String raw = inputField.getText().trim();
        if (raw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter numbers.");
            return;
        }
        try {
            int[] nums = IntStream.of(parseNumbers(raw)).toArray();
            if (nums.length > 100) {
                JOptionPane.showMessageDialog(this, "Maximum 100 numbers allowed.");
                return;
            }
            int[] sorted;
            switch (algorithmBox.getSelectedItem().toString()) {
                case "Selection Sort":
                    sorted = SortAlgorithms.selectionSort(nums.clone());
                    break;
                case "Insertion Sort":
                    sorted = SortAlgorithms.insertionSort(nums.clone());
                    break;
                default:
                    sorted = SortAlgorithms.bubbleSort(nums.clone());
            }
            outputArea.setText("Sorted: " + IntStream.of(sorted)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(", ")));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format. Use commas between integers.");
        }
    }

    /** Parses comma‑separated integers, ignores empty tokens. */
    private int[] parseNumbers(String s) {
        return java.util.Arrays.stream(s.split(","))
                .map(String::trim)
                .filter(tok -> !tok.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
