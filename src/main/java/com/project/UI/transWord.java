package com.project.UI;

import com.project.dodung.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class transWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonEdit;
    private JPanel buttonPanel;
    private JPanel definitionPanel;
    private JTextPane textPaneDefinition;
    private JLabel labelTitle;
    private JButton buttonSpeech;
    private JLabel labelTarget;
    private JLabel labelWord;
    private JPanel panelTarget;


    public transWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        labelWord.setText(DictionaryApplication.transStr);
        setDefinitionText();

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

        buttonSpeech.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DictionaryCommandline.speak(labelWord.getText());
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onEdit() {
        // add your code here if necessary
        DictionaryApplication.editStr = DictionaryApplication.transStr;
        DictionaryApplication.editDefinition = textPaneDefinition.getText();
        System.out.println(DictionaryApplication.editDefinition);
        completeWord dialog = new completeWord();
        dialog.pack();
        dialog.setVisible(true);
        dispose();
    }


    private void setDefinitionText() {
        textPaneDefinition.setContentType("text/html");
        Word word = new Word(labelWord.getText());
        String s = DictionaryManagement.selectWordHtmlWithId(DictionaryManagement.getWordId(word));
        textPaneDefinition.setText(DictionaryManagement.selectWordHtmlWithId(DictionaryManagement.getWordId(word)));
    }


    public static void main(String[] args) {
        transWord dialog = new transWord();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
