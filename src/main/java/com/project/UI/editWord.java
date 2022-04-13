package com.project.UI;

import com.project.dodung.DictionaryManagement;

import javax.swing.*;
import java.awt.event.*;


public class editWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel editWordLabel;
    private JTextField editWordText;
    private JLabel editTextLabel;
    private JList suggestionEditList;

    //public String editStr;

    public editWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        editWordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);editWordText.getText();
                DefaultListModel<String>listModel = new DefaultListModel<>();
                String s = editWordText.getText();
                listModel.addAll(DictionaryManagement.stringSimilarWord(s));
                suggestionEditList.setModel(listModel);
            }
        });

        suggestionEditList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                editWordText.setText(suggestionEditList.getSelectedValue().toString());
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
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        DictionaryApplication.editStr=editWordText.getText();
        completeWord dialog = new completeWord();
        dialog.pack();
        dialog.setVisible(true);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        editWord dialog = new editWord();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
