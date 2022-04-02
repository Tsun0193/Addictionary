package com.project.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton translWordButton;
    private JLabel transWordLabel;

    private JPanel transSentencePanel;
    private JButton transSentenceButton;
    private JLabel transSentenceLabel;

    private JPanel addWordPanel;
    private JLabel addWordLabel;
    private JButton addWordButton;

    private JPanel delWordPanel;
    private JLabel delWordLabel;
    private JButton delWordButton;

    /**
     * khởi chạy (cũng không hiểu mớ này lắm)
     * @param title
     */
    public DictionaryApplication(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(appPanel);
        this.pack();

        /**
         * click addWordButton
         */
        addWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWord dialog = new addWord();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new DictionaryApplication("Dictionary pro vjp");
        frame.setVisible(true);
    }
}
