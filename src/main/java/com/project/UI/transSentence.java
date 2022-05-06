package com.project.UI;

import com.project.dodung.DictionaryCommandline;

import javax.swing.*;
import java.awt.event.*;

public class transSentence extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelTitle;
    private JPanel panelTarget;
    private JPanel panelTrans;
    private JLabel labelTarget;
    private JLabel labelTrans;
    private JTextArea textAreaTargetSentence;
    private JTextArea textAreaTransSentence;
    private JButton buttonSpeechTarget;
    private JButton buttonSpeechTrans;

    public transSentence() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        textAreaTargetSentence.setText(DictionaryApplication.sentence);
        textAreaTransSentence.setText(DictionaryCommandline.sentenceTranslator(DictionaryApplication.sentence));

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonSpeechTarget.addActionListener(e -> DictionaryCommandline.speak(textAreaTargetSentence.getText()));
        buttonSpeechTrans.addActionListener(e -> DictionaryCommandline.speak(textAreaTransSentence.getText()));
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
        transSentence dialog = new transSentence();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
