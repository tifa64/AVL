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
    private JTextArea outputTextArea;


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
        dictionaryFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        boolean[] inserted = t.insertWords(selectedFile);
                        List<String> lines = null;
                        lines = Files.readAllLines(Paths.get(selectedFile.getPath()));
                        int i = 0;
                        for (String s : lines) {
                            sb.append(s + ' ');
                            String condition = inserted[i] ? "inserted succesfully" : "is duplicate";
                            sb.append(condition + "\n");
                            i++;
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileForOperations = fileChooser.getSelectedFile();
                    filePathTextField.setText(fileForOperations.getAbsolutePath());
                    wordTextField.setEnabled(false);
                    wordTextField.setText("File is chosen for batch actions. Clear file to make an operation on a single word.");
                }
            }
        });

        clearFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileForOperations = null;
                filePathTextField.setText("No file selected.");
                wordTextField.setEnabled(true);
                wordTextField.setText("");
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null) {
                    String word = wordTextField.getText();
                    sb.append(word + ' ');
                    String condition = t.insertWord(word) ? "inserted succesfully" : "is duplicate";
                    sb.append(condition + "\n");
                    wordTextField.setText("");
                } else {
                    try {
                        boolean[] inserted = t.insertWords(fileForOperations);
                        List<String> lines = Files.readAllLines(Paths.get(fileForOperations.getPath()));
                        int i = 0;
                        for (String s : lines) {
                            sb.append(s + ' ');
                            String condition = inserted[i] ? "inserted succesfully" : "is duplicate";
                            sb.append(condition + "\n");
                            i++;
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                outputTextArea.setText(sb.toString());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null) {
                    String word = wordTextField.getText();
                    try {
                        String condition = t.searchWord(word) ? "found" : "not found";
                        sb.append(word + ' ' + condition + "\n");
                        wordTextField.setText("");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        boolean[] found = t.searchWords(fileForOperations);
                        List<String> lines = Files.readAllLines(Paths.get(fileForOperations.getPath()));
                        int i = 0;
                        for (String s : lines) {
                            sb.append(s + ' ');
                            String condition = found[i] ? "found" : "not found";
                            sb.append(condition + "\n");
                            i++;
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        sb.append("Tree is empty.");
                    }
                }
                outputTextArea.setText(sb.toString());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null) {
                    String word = wordTextField.getText();
                    try {
                        String condition = t.deleteWord(word) ? "deleted" : "not found";
                        sb.append(word + ' ');
                        sb.append(condition + "\n");
                        wordTextField.setText("");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        sb.append("Tree is empty.");
                    }
                } else {
                    try {
                        boolean[] deleted = t.deleteWords(fileForOperations);
                        List<String> lines = Files.readAllLines(Paths.get(fileForOperations.getPath()));
                        int i = 0;
                        for (String s : lines) {
                            sb.append(s + ' ');
                            String condition = deleted[i] ? "deleted" : "not found";
                            sb.append(condition + "\n");
                            i++;
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                outputTextArea.setText(sb.toString());
            }
        });

        printSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sb.append("Size of Dictionary : " + t.getSize() + "\n");
                sb.append("Height of Tree : " + t.getHeight() + "\n");

                outputTextArea.setText(sb.toString());

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
