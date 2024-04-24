package Ispirer.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteSorterApp extends JFrame {
    private final static String INPUT_FILE = "src/main/resources/input.txt";
    private final static String OUTPUT_FILE = "src/main/resources/output.txt";
    private final static String INPUT_FILE_LABEL = "Input File:";
    private final static String OUTPUT_FILE_LABEL = "Output File:";
    private final static String APPLICATION_NAME = "Byte Sorter";
    private final static String SORT_BUTTON_NAME = "Sort";

    public ByteSorterApp() {
        setTitle(APPLICATION_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 1));

        JPanel inputFilePanel = generatePanelForTextFile(INPUT_FILE_LABEL, INPUT_FILE);
        JPanel outputFilePanel = generatePanelForTextFile(OUTPUT_FILE_LABEL, OUTPUT_FILE);

        JPanel sortButtonPanel = new JPanel();
        sortButtonPanel.setLayout(new FlowLayout());
        JButton sortButton = new JButton(SORT_BUTTON_NAME);
        sortButton.addActionListener(new SortButtonListener());
        sortButtonPanel.add(sortButton);

        add(inputFilePanel);
        add(outputFilePanel);
        add(sortButtonPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ByteSorterApp();
    }

    private JPanel generatePanelForTextFile(String labelName, String content) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel inputFileLabel = new JLabel(labelName);
        JTextField textField = new JTextField(20);
        panel.add(inputFileLabel);
        panel.add(textField);
        textField.setText(content);
        textField.setEditable(false);
        return panel;
    }

    private void sortBytes() throws IOException {
        try (FileInputStream fis = new FileInputStream(INPUT_FILE);
             FileOutputStream fos = new FileOutputStream(OUTPUT_FILE)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                Arrays.sort(buffer, 0, bytesRead);
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    private class SortButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                sortBytes();
                JOptionPane.showMessageDialog(null, "Sorting completed successfully");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error sorting bytes: " + ex.getMessage());
            }
        }
    }
}