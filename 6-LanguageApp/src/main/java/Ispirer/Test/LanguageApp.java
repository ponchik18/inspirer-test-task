package Ispirer.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LanguageApp extends JFrame {
    private ResourceBundle messages = ResourceBundle.getBundle("messages");
    private final JLabel label;
    private final JButton button;

    public LanguageApp() {
        setTitle("Language App");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel(messages.getString("label.text"));
        button = new JButton(messages.getString("button.text"));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(label, BorderLayout.CENTER);
        getContentPane().add(button, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu languageMenu = new JMenu("Language");
        menuBar.add(languageMenu);

        JMenuItem englishMenuItem = new JMenuItem("English");
        englishMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messages = ResourceBundle.getBundle("messages", Locale.ENGLISH);
                setLanguage();
            }
        });
        languageMenu.add(englishMenuItem);

        JMenuItem russianMenuItem = new JMenuItem("Русский");
        russianMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messages = ResourceBundle.getBundle("messages", new Locale("ru", "RU"));
                setLanguage();
            }
        });
        languageMenu.add(russianMenuItem);

        setVisible(true);
    }

    private void setLanguage() {
        label.setText(messages.getString("label.text"));
        button.setText(messages.getString("button.text"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LanguageApp();
            }
        });
    }
}
