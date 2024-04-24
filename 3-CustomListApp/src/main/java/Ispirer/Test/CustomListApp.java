package Ispirer.Test;

import Ispirer.Test.list.CustomList;

import javax.swing.*;
import java.awt.*;

public class CustomListApp extends JFrame {
    private final CustomList<String> customList;
    private final DefaultListModel<String> listModel;

    public CustomListApp() {
        setTitle("Custom List App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        customList = new CustomList<>();

        JPanel panel = new JPanel(new BorderLayout());
        JButton addButton = new JButton("Add Item");
        listModel = new DefaultListModel<>();
        JList<String> jList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(jList);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.SOUTH);

        add(panel);

        addButton.addActionListener(e -> {
            String newItem = "Item " + (customList.size() + 1);
            customList.add(newItem);
        });

        customList.addListChangedListener(event -> updateUI());
        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomListApp();
    }

    private void updateUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                listModel.clear();
                for (int i = 0; i < customList.size(); i++) {
                    listModel.addElement(customList.get(i));
                }
            }
        });
    }
}