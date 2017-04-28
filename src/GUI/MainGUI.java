package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainGUI() {
        setModal(true);
        setContentPane(contentPane);
        filePathTextField.setEnabled(false);
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
        dictionaryFileButton.addActionListener(new ActionListener() {
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
