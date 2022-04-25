package com.project.UI;

import com.project.dodung.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

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
    private JTextField transSentenceText;
    private JButton editWordButton;
    private JList suggestionTransWordList;
    private JTextField warningTextField;
    private JPanel editWordPanel;
    /**
     * Input from Different Area.
     */
    public static String addStr;
    public static String addPro;
    public static String addDef;
    public static String delStr;
    public static String editStr;
    public static String transStr;
    public static String sentence;

    /**
     * khởi chạy (cũng không hiểu mớ này lắm).
     * @param title String
     */
    public DictionaryApplication(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(appPanel);
        this.pack();

        DictionaryManagement.connect();
        DictionaryManagement.buildTrie();

        addWordButton.addActionListener(e -> {
            addWord dialog = new addWord();
            dialog.pack();
            dialog.setVisible(true);
        });

        delWordButton.addActionListener(e -> {
            delWord dialog = new delWord();
            dialog.pack();
            dialog.setVisible(true);
        });

        transWordButton.addActionListener(e -> {
            transStr = transWordText.getText();
            Word w = new Word(transStr);
            if (w.getId() == 0 || Objects.equals(w.getWord(), "")){
                JOptionPane.showMessageDialog(appPanel,"This word is non-existed in dictionary");
            }else {
                transWord dialog = new transWord();
                dialog.pack();
                dialog.setVisible(true);
            }

        });


        transSentenceButton.addActionListener(e -> {
            sentence = transSentenceText.getText();
            transSentence dialog = new transSentence();
            dialog.pack();
            dialog.setVisible(true);
        });
        editWordButton.addActionListener(e -> {
            editWord dialog = new editWord();
            dialog.pack();
            dialog.setVisible(true);
        });
        transWordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                DefaultListModel<String>listModel=new DefaultListModel<>();
                String s= transWordText.getText();
                listModel.addAll(DictionaryManagement.stringSimilarWord(s));
                suggestionTransWordList.setModel(listModel);
            }
        });

        suggestionTransWordList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                transWordText.setText(suggestionTransWordList.getSelectedValue().toString());
            }
        });
    }

    public static String getTransStr() {
        return transStr;
    }

    public static String getSentence() {
        return sentence;
    }

    public static void main(String[] args){
        JFrame frame = new DictionaryApplication("Dictionary pro vjp");
        frame.setVisible(true);
    }

}
