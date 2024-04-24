package Ispirer.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComplexNumberCalculator extends JFrame {
    private final JTextField realPartField1, imaginaryPartField1, realPartField2, imaginaryPartField2;
    private final JButton addButton, subtractButton, multiplyButton;
    private final JTextArea resultArea;

    public ComplexNumberCalculator() {
        setTitle("Complex Number Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5));

        realPartField1 = new JTextField(5);
        imaginaryPartField1 = new JTextField(5);
        realPartField2 = new JTextField(5);
        imaginaryPartField2 = new JTextField(5);

        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(new JLabel("Complex Number 1"));
        add(new JLabel("Complex Number 2"));
        add(new JLabel("Real Part:"));
        add(realPartField1);
        add(realPartField2);
        add(new JLabel("Imaginary Part:"));
        add(imaginaryPartField1);
        add(imaginaryPartField2);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);

        addButton.addActionListener(new OperationButtonListener());
        subtractButton.addActionListener(new OperationButtonListener());
        multiplyButton.addActionListener(new OperationButtonListener());

        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double realPart1 = Double.parseDouble(realPartField1.getText());
                double imaginaryPart1 = Double.parseDouble(imaginaryPartField1.getText());
                double realPart2 = Double.parseDouble(realPartField2.getText());
                double imaginaryPart2 = Double.parseDouble(imaginaryPartField2.getText());


                ComplexNumber num1 = new ComplexNumber(realPart1, imaginaryPart1);
                ComplexNumber num2 = new ComplexNumber(realPart2, imaginaryPart2);


                if (e.getSource() == addButton) {
                    ComplexNumber sum = num1.add(num2);
                    resultArea.setText("Sum: " + sum);
                } else if (e.getSource() == subtractButton) {
                    ComplexNumber difference = num1.subtract(num2);
                    resultArea.setText("Difference: " + difference);
                } else if (e.getSource() == multiplyButton) {
                    ComplexNumber product = num1.multiply(num2);
                    resultArea.setText("Product: " + product);
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter valid numbers.");
            }
        }
    }

    public static void main(String[] args) {
        new ComplexNumberCalculator();
    }
}
