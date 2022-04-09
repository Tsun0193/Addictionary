package com.project.UI;

import com.project.dodung.DictionaryManagement;

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
    private JList suggestionList;

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
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultListModel<String>listModel=new DefaultListModel<>();
                String s= delWordText.getText();
                listModel.addAll(DictionaryManagement.stringSimilarWord(s));
                suggestionList.setModel(listModel);
            }
        });
        suggestionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                delWordText.setText(suggestionList.getSelectedValue().toString());
            }
        });

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
