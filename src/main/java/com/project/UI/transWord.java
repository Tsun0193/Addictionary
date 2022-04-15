package com.project.UI;

import com.project.dodung.DictionaryManagement;
import com.project.dodung.Word;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class transWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonEdit;
    private JLabel transWordLabel;
    private JLabel pronounLabel;
    private JPanel buttonPanel;
    private JPanel definitionPanel;
    private JLabel definitionLabel;
    private JTextPane definitionTextPane;
    private JScrollBar definitionScrollBar;
    private JLabel inputWordLabel;


    public transWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onEdit() {
        // add your code here if necessary
        DictionaryApplication.editStr = inputWordLabel.getText();
        completeWord dialog = new completeWord();
        dialog.pack();
        dialog.setVisible(true);
        dispose();
    }

    private void setDefinitionText() {
        definitionTextPane.setContentType("text/html");
        String label = DictionaryApplication.transStr;
        transWordLabel.setText("Translation: " + label);
        Word word = new Word(label);
        String s = DictionaryManagement.selectWordHtmlWithId(DictionaryManagement.getWordId(word));
        String pronounciation = "";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') {
                count++;
            }
            if (count == 2) {
                pronounciation += s.charAt(i);
            } else if (count == 3) {
                pronounciation += s.charAt(i);
                break;
            }
        }
        pronounLabel.setText(pronounciation);

        System.out.println(s);
        definitionTextPane.setText(DictionaryManagement.selectWordHtmlWithId(DictionaryManagement.getWordId(word)));
    }

    public static void main(String[] args) {
        transWord dialog = new transWord();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
