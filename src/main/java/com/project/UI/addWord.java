package com.project.UI;
import com.project.dodung.*;
import javax.swing.*;
import java.awt.event.*;

public class addWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldAddPronounce;
    private JTextField textFieldAddWord;
    private JPanel panelWord;
    private JLabel labelWord;
    private JPanel panelPronounce;
    private JLabel labelPronounce;
    private JPanel panelNewDefinition;
    private JLabel labelDefinition;
    private JTextField textFieldAddDefinition;
    private JPanel panelWordPronounce;

    public addWord() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);

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

    }

    private void onOK() {
        int confirm = JOptionPane.showConfirmDialog(contentPane,"This action will make the database change!\n" +
                "Are you sure about adding this word to dictionary?","Confirm",JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            if (!DictionaryCommandline.insertWord(textFieldAddWord.getText(), textFieldAddPronounce.getText()
                    , textFieldAddDefinition.getText())) {
                JOptionPane.showMessageDialog(contentPane,
                        "Word\"" + textFieldAddWord.getText() + "\"" + "is existed!");
            } else {
                JOptionPane.showMessageDialog(contentPane,
                        "Word\"" + textFieldAddWord.getText() + "\"" + "was added successfully!");
            }
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    public static void main(String[] args) {
        addWord dialog = new addWord();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
