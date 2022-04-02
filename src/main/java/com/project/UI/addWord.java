package com.project.quangminh;
import com.project.dodung.DictionaryManagement;
import com.project.dodung.*;
import javax.swing.*;
import java.awt.event.*;

public class addWord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField newPronounTextfield;
    private JTextField newWordTextField;
    private JTextField newDefTextField;
    private JPanel newWordPanel;
    private JLabel newWordLabel;
    private JPanel newPronounPanel;
    private JLabel newPronounLabel;
    private JPanel newDefPanel;
    private JLabel newDefLabel;

    public addWord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        Word w = new Word(newWordTextField.getText(),1);
        try{
            System.out.println(w.getWord());
            DictionaryManagement.insertWordToTable(w);
        }
        catch (existException e){
            JOptionPane.showMessageDialog(contentPane,e.getMessage());
        }
        //DictionaryManagement.insertWordToTable();
        dispose();
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
