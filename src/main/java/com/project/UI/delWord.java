package com.project.UI;

import com.project.dodung.DictionaryManagement;
import com.project.dodung.Word;

import javax.swing.*;
import java.awt.event.*;

public class delWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonApply;
    private JButton buttonCancel;
    private JPanel YN;
    private JLabel delWordLabel;
    private JPanel contextPanel;
    private JLabel input;
    private JTextField delWordText;
    private JPanel suggestionPanel;
    private JLabel suggestionLabel;

    public delWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonApply);


        buttonApply.addActionListener(new ActionListener() {
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




        delWordText.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                Word w = new Word(delWordText.getText());

                System.out.println(w.getWord());
                System.out.println(DictionaryManagement.stringSimilarWord(w));
                System.out.println("*");
                suggestionLabel.setText("Suggestion: \n" + DictionaryManagement.stringSimilarWord(w));
            }
        });



        /*
        delWordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Word w = new Word(delWordText.getText());

                System.out.println(w.getWord());
                System.out.println(DictionaryManagement.stringSimilarWord(w));
                System.out.println("*");
                suggestionLabel.setText("Suggestion: \n" + DictionaryManagement.stringSimilarWord(w));
            }
        });

         */
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
        delWord dialog = new delWord();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
