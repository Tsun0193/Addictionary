package com.project.UI;

import javax.swing.*;
import java.awt.event.*;

public class transWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonEdit;
    private JLabel transWordLabel;
    private JLabel inputWordLabel;
    private JLabel definitionLabel;
    private JTextField transWordTranslation;

    public transWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEdit();
            }
        });

        // call onEdit() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onEdit();
            }
        });

        // call onEdit() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEdit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        inputWordLabel.setText(DictionaryApplication.getTransStr());

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onEdit() {
        // add your code here if necessary
        DictionaryApplication.editStr=inputWordLabel.getText();
        completeWord dialog = new completeWord();
        dialog.pack();
        dialog.setVisible(true);
        dispose();
    }




    public static void main(String[] args) {
        transWord dialog = new transWord();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
