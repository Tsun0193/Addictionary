package com.project.UI;

import com.project.dodung.*;


import javax.swing.*;
import java.awt.event.*;

public class completeWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelEdit;
    private JPanel panelEdit;
    private JPanel panelEditPronounce;
    private JLabel labelTargetWord;
    private JPanel panelEditDefinition;
    private JLabel labelPronounce;
    private JLabel labelEditDefinition;
    private JEditorPane editorPaneEditPronounce;
    private JEditorPane editorPaneEditDefinition;
    private JLabel labelTarget;
    private JLabel labelWord;

    public completeWord() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        labelWord.setText(DictionaryApplication.editStr);

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
        DictionaryCommandline.deleteWord(DictionaryManagement.myTrie.findWordId(labelWord.getText()));
        DictionaryCommandline.insertWord(labelWord.getText(),
                editorPaneEditPronounce.getText(),editorPaneEditDefinition.getText());
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
