package GUI;

import Core.Tree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainGUI extends JDialog {
    private JPanel contentPane;
    private JTextField filePathTextField;
    private JButton dictionaryFileButton;
    private JButton loadFileButton;
    private JButton clearFileButton;
    private JTextField wordTextField;
    private JButton insertButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton printSizeButton;
    private JTextArea textArea1;


    private File fileForOperations = null;
    private final StringBuilder sb = new StringBuilder();
    private Tree t = new Tree();

    private MainGUI() {
        setModal(true);
        setContentPane(contentPane);
        filePathTextField.setEnabled(false);
        filePathTextField.setText("No file selected.");
        this.initComponents();
    }

    private static void setUIFlavour() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileForOperations = fileChooser.getSelectedFile();
                    filePathTextField.setText(fileForOperations.getAbsolutePath());
                }
            }
        });

        dictionaryFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                }
            }
        });

        clearFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileForOperations = null;
                filePathTextField.setText("No file selected.");
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null) {

                    String word = wordTextField.getText();
                    sb.append(word + ' ');
                    sb.append(t.insertWord(word) + "\n");

                } else {
                    try {
                        boolean[] inserted = t.insertWords(fileForOperations);
                        List<String> lines = Files.readAllLines(Paths.get(fileForOperations.getPath()));
                        int i = 0;
                        for (String s : lines) {
                            sb.append(s + ' ');
                            sb.append(inserted[i] + "\n");

                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                textArea1.setText(sb.toString());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null) {
                    String word = wordTextField.getText();
                    sb.append(word + ' ');
                    try {
                        sb.append(t.searchWord(word) + "\n");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        boolean[] founded = t.searchWords(fileForOperations);
                        List<String> lines = Files.readAllLines(Paths.get(fileForOperations.getPath()));
                        int i = 0;
                        for (String s : lines) {
                            sb.append(s + ' ');
                            sb.append(founded[i] + "\n");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                textArea1.setText(sb.toString());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null) {
                    String word = wordTextField.getText();
                    sb.append(word + ' ');
                    try {
                        sb.append(t.deleteWord(word) + "\n");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                } else {
                    try {
                        boolean[] deleted = t.deleteWords(fileForOperations);
                        List<String> lines = Files.readAllLines(Paths.get(fileForOperations.getPath()));
                        int i = 0;
                        for (String s : lines) {
                            sb.append(s + ' ');
                            sb.append(deleted[i] + "\n");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                textArea1.setText(sb.toString());
            }
        });

        printSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sb.append("Size of Dictionary : " + t.getSize() + "\n");
                sb.append("Height of Tree : " + t.getHeight() + "\n");

                textArea1.setText(sb.toString());

            }
        });
    }

    public static void main(String[] args) {
        setUIFlavour();
        JFrame frame = new JFrame("AVL Dictionary");
        frame.setContentPane(new MainGUI().contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
