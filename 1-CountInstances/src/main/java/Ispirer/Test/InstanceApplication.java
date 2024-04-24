package Ispirer.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class InstanceApplication {
    private static final String LABEL_TEXT = "Number of instances: %d";
    private static final String FRAME_NAME = "Instance Counter-%d";
    private static final String BUTTON_TEXT = "Create new class";
    private static final List<InstanceApplication> instancesList = new ArrayList<>();
    private static int instanceCount = 0;
    private final JLabel instanceLabel;


    public InstanceApplication() {
        JFrame frame = new JFrame(generateFrameText());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.addWindowListener(new DeleteInstanceWindowAdapter(this));
        addInstance(this);
        frame.setLayout(new FlowLayout());

        instanceLabel = new JLabel(generateTextForLabel());
        frame.add(instanceLabel);

        JButton openNewButton = new JButton(BUTTON_TEXT);
        openNewButton.addActionListener(new CreateNewInstanceListener());
        frame.add(openNewButton);
        frame.setVisible(true);
    }

    private static void incrementInstanceCount() {
        instanceCount++;
    }

    private static void decrementInstanceCount() {
        if (instanceCount > 0) {
            instanceCount--;
        }
    }

    public static void addInstance(InstanceApplication instance) {
        instancesList.add(instance);
        incrementInstanceCount();
    }

    public static void removeInstance(InstanceApplication instance) {
        instancesList.remove(instance);
        decrementInstanceCount();
    }

    public static void main(String[] args) {
        new InstanceApplication();
        while (instanceCount!=0);
    }

    private static void updateLabelForAllInstance() {
        for (InstanceApplication instance : instancesList) {
            instance.updateLabel();
        }
    }

    private void updateLabel() {
        instanceLabel.setText(generateTextForLabel());
    }

    private String generateTextForLabel() {
        return String.format(LABEL_TEXT, instanceCount);
    }

    private String generateFrameText() {
        return String.format(FRAME_NAME, instanceCount + 1);
    }

    private class CreateNewInstanceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            instanceLabel.setText(generateTextForLabel());
            new InstanceApplication();
            updateLabelForAllInstance();
        }
    }

    private class DeleteInstanceWindowAdapter extends WindowAdapter {
        private final InstanceApplication instance;

        public DeleteInstanceWindowAdapter(InstanceApplication instance) {
            this.instance = instance;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            if(instanceCount == 1) {
                System.exit(0);
            }
            removeInstance(instance);
            updateLabelForAllInstance();
        }
    }

}