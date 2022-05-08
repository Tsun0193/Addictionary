package com.project.UI;

import com.project.dictManagement.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
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
    private JButton editWordButton;
    private JList<String> suggestionTransWordList;
    private JPanel editWordPanel;
    private JEditorPane transSentenceEditorPane;
    private JLabel signatureField;
    private JLabel signatureTeam;
    /**
     * Input from Different Area.
     */
    public static String addStr;
    public static String addPro;
    public static String addDef;
    public static String delStr;
    public static String editStr;
    public static String editPronounce = "";
    public static String editDefinition = "";
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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DictionaryCommandline.close();
                dispose();
            }
        });

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

        Border b = new LineBorder(new Color(152, 158, 161), 1);
        transSentenceEditorPane.setBorder(b);

        transSentenceButton.addActionListener(e -> {
            sentence = transSentenceEditorPane.getText();
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
                transWordText.setText(suggestionTransWordList.getSelectedValue());
            }
        });
    }

    public static String getTransStr() {
        return transStr;
    }

    public static String getSentence() {
        return sentence;
    }

    public static void runApplication(){
        JFrame frame = new DictionaryApplication("Dictionary pro vjp");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
