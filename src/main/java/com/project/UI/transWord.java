package com.project.UI;

import com.project.dictManagement.*;

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
    private String targetWord;


    public transWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        targetWord =  DictionaryApplication.transStr;
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
                DictionaryCommandline.speak(targetWord);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onEdit() {
        // add your code here if necessary
        DictionaryApplication.editStr = targetWord;
        DictionaryApplication.editDefinition = textPaneDefinition.getText();
        System.out.println(DictionaryApplication.editDefinition);
        completeWord dialog = new completeWord();
        dialog.pack();
        dialog.setVisible(true);
        dispose();
    }


    private void setDefinitionText() {
        textPaneDefinition.setContentType("text/html");
        Pair<String,String> wordAndHtml = DictionaryCommandline.getExactWord(DictionaryApplication.transStr);
        textPaneDefinition.setText(wordAndHtml.getValue());
    }


}
