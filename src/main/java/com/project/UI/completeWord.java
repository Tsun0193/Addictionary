package com.project.UI;

import javax.swing.*;
import java.awt.event.*;
import com.project.UI.editWord;
import com.project.UI.DictionaryApplication;

public class completeWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel completeLabel;
    private JLabel targetEditWord;
    private JLabel pronounceEditWord;
    private JTextField editProText;
    private JLabel defEditWord;
    private JTextField editDefText;
    private JLabel targetEditText;

    public static String inputEditPro;
    public static String inputEditDef;

    public completeWord() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        targetEditWord.setText("Target: "+DictionaryApplication.editStr);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        completeWord dialog = new completeWord();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
