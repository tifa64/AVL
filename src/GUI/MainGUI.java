package GUI;

import Core.Tree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

    private void initComponents(){
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
                if (fileForOperations == null){
                        String word = wordTextField.getText();
                        sb.append(word + ' ');
                        sb.append(t.insertWord(word));

                    } else {
                        boolean inserted = t.insertWords(fileForOperations);
                    }


            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null){
                    String word = wordTextField.getText();
                    // call function to search a single word from text field
                } else {
                    // batch search from file
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileForOperations == null){
                    String word = wordTextField.getText();
                    // call function to delete a single word from text field
                } else {
                    // batch delete from file
                }
            }
        });

        printSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
