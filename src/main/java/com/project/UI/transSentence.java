package com.project.UI;

import com.project.dictManagement.DictionaryCommandline;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
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
        Border b = new LineBorder(new Color(152,158,161), 1);
        textAreaTransSentence.setBorder(b);
        textAreaTargetSentence.setBorder(b);


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


}
