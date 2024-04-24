package Ispirer.Test;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;

public class DirectoryTree extends JFrame {
    private final JTree tree;

    public DirectoryTree() {
        setTitle("Directory Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JButton chooseButton = new JButton("Choose Directory");
        chooseButton.addActionListener(e -> chooseDirectory());

        tree = new JTree();

        JScrollPane scrollPane = new JScrollPane(tree);
        getContentPane().add(chooseButton, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void chooseDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String directoryPath = fileChooser.getSelectedFile().getAbsolutePath();
            updateTree(directoryPath);
        }
    }

    private void updateTree(String directoryPath) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(directoryPath);
        buildTree(new File(directoryPath), root);
        tree.setModel(new DefaultTreeModel(root));
    }

    private void buildTree(File file, DefaultMutableTreeNode parentNode) {
        if (file.isDirectory()) {
            DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(file.getName());
            parentNode.add(dirNode);
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    buildTree(f, dirNode);
                }
            }
        } else {
            parentNode.add(new DefaultMutableTreeNode(file.getName()));
        }
    }

    public static void main(String[] args) {
        new DirectoryTree();
    }
}
