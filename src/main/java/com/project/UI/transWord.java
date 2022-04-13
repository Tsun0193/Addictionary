package com.project.UI;

import javax.swing.*;
import java.awt.event.*;

import static com.project.UI.DictionaryApplication.transStr;

public class transWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonEdit;
    private JLabel transWordLabel;
    private JLabel inputWordLabel;
    private JLabel definitionLabel;
    private JTextArea transWordDefText;

    public transWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        String label = transStr;
        transWordLabel.setText("Translation: " + label);

        inputWordLabel.setText("/"+"NULL"+"/");

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
