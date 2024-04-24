package Ispirer.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PhoneBookSearch extends JFrame {

    private static final String DATA_FILE = "src/main/resources/phonebook.xml";
    private final JTextField searchField;
    private final JTextArea resultArea;

    public PhoneBookSearch() {
        setTitle("Phonebook Search");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel searchLabel = new JLabel("Enter surname:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        add(panel);
        add(new JScrollPane(resultArea), "South");

        searchButton.addActionListener(new SearchButtonActionListener());

        setVisible(true);
    }
    private class SearchButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchSurname = searchField.getText().trim().toLowerCase();
            if (!searchSurname.isEmpty()) {
                searchPhone(searchSurname);
            } else {
                resultArea.setText("Please enter a surname.");
            }
        }
    }

    private void searchPhone(String searchSurname) {
        try {
            File xmlFile = new File(DATA_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList entryList = doc.getElementsByTagName("entry");
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < entryList.getLength(); i++) {
                Node entryNode = entryList.item(i);
                if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element entryElement = (Element) entryNode;
                    String firstname = entryElement.getElementsByTagName("firstname").item(0).getTextContent();
                    String surname = entryElement.getElementsByTagName("surname").item(0).getTextContent();
                    String number = entryElement.getElementsByTagName("number").item(0).getTextContent();
                    if (surname.equalsIgnoreCase(searchSurname)) {
                        result
                                .append("Firstname: ").append(firstname).append('\n')
                                .append("Surname: ").append(surname).append('\n')
                                .append("Phone Number: ").append(number).append("\n");
                    }
                }
            }

            if (result.isEmpty()) {
                resultArea.setText("No phone number found for the entered surname.");
            } else {
                resultArea.setText(result.toString());
            }
        } catch (Exception e) {
            resultArea.setText("Error occurred while searching.");
        }
    }

    public static void main(String[] args) {
        new PhoneBookSearch();
    }
}