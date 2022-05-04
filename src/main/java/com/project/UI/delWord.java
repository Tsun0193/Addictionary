package com.project.UI;

import com.project.dodung.*;
import javax.swing.*;
import java.awt.event.*;

public class delWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonApply;
    private JButton buttonCancel;
    private JLabel labelDeleteWord;
    private JPanel panelContext;
    private JLabel input;
    private JTextField delWordText;
    private JPanel panelSuggestion;
    private JLabel labelSuggestion;
    private JList<String> listSuggestion;

    public delWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonApply);

        buttonApply.addActionListener(e -> onOK());

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

        delWordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultListModel<String>listModel=new DefaultListModel<>();
                String s= delWordText.getText();
                listModel.addAll(DictionaryManagement.stringSimilarWord(s));
                listSuggestion.setModel(listModel);
            }
        });

        listSuggestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                delWordText.setText(listSuggestion.getSelectedValue());
            }
        });

    }

    private void onOK() {
        JOptionPane.showConfirmDialog(contentPane,"Are you sure about deleting this word?");
        if(!DictionaryCommandline.deleteWord(DictionaryManagement.myTrie.findWordId(delWordText.getText()))){
            JOptionPane.showMessageDialog(contentPane,"Word\"" + delWordText.getText() + "\"" + "is existed!");
        }
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
