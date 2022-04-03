package com.project.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

/**
 * cửa sổ chính có 4 mục: transWord,transSentence,addWord và delWord.
 */


public class DictionaryApplication extends JFrame {

    /**
     * panel chính và nhãn ghi tên app
     */
    private JPanel appPanel;
    private JLabel appLabel;
    /**
     * 4 panel con.
     */
    private JPanel transWordPanel;
    private JButton transWordButton;
    private JLabel transWordLabel;

    private JPanel addWordPanel;
    private JLabel addWordLabel;
    private JButton addWordButton;

    private JPanel delWordPanel;
    private JLabel delWordLabel;
    private JButton delWordButton;
    private JPanel transSentencePanel;
    private JLabel transSentenceLabel;
    private JButton transSentenceButton;
    private JTextField transWordText;
    private JTextField textField2;
    /**
     * Input from Translate Word Area.
     */
    public static String str;

    /**
     * khởi chạy (cũng không hiểu mớ này lắm)
     * @param title String
     */
    public DictionaryApplication(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(appPanel);
        this.pack();

        addWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWord dialog = new addWord();
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        delWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delWord dialog = new delWord();
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        transWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = transWordText.getText();
                transWord dialog = new transWord();
                dialog.pack();
                dialog.setVisible(true);
            }
        });

    }

    public static String getStr() {
        return str;
    }

    public static void main(String[] args){
        JFrame frame = new DictionaryApplication("Dictionary pro vjp");
        frame.setVisible(true);
    }

}
