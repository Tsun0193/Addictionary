package com.project.duydang;

import com.project.dodung.DictionaryManagement;

import javax.swing.*;
import java.awt.*;

public class DictionaryApplication {
    private DictionaryManagement manager = new DictionaryManagement();
    private final JFrame Frame = new JFrame();
    private final JDialog addWord = new JDialog(Frame, "Add word:", true);
    private final JDialog delWord = new JDialog(Frame, "Delete Word?", true);
    private final JDialog editWord = new JDialog(Frame, "Edit Word:", true);
    private final JDialog translateSentence = new JDialog(Frame, "Translate", true);

    private final JPanel Box = new JPanel(new GridBagLayout());
    private final JPanel functionBox = new JPanel(new GridLayout(1, 0, 5, 0));
    private final JPanel definitionBox = new JPanel(new GridLayout(1, 0, 5, 0));
    private final JPanel givenWord = new JPanel(new GridLayout(0, 2));
    private final JPanel searchingArea = new JPanel();
    private final JPanel definitionArea = new JPanel();
    private final JPanel searchingBoxArea = new JPanel();
    private final JPanel suggestArea = new JPanel();
    private final JPanel sentenceInputArea = new JPanel();
    private final JPanel translatedOutputArea = new JPanel();
    private final JPanel viPanel = new JPanel();
    private final JPanel enPanel = new JPanel();

    private final JButton addB = new JButton("Add word:");
    private final JButton transB = new JButton("Translate:");
    private final JButton sentenceTransB = new JButton("Translate this Sentence.");
    private final JButton deleteB = new JButton("Delete.");
    private final JButton editB = new JButton();
    private final JButton viPronunciationB = new JButton();
    private final JButton enPronunciationB = new JButton();

    private final JLabel editTarget = new JLabel("Word: ");

    JTextField wordHere = new JTextField("Word here");
    JTextArea definitionHere = new JTextArea("Definition here");
    JTextArea sentenceHere = new JTextArea("Sentence here");
    JTextArea translatedHere = new JTextArea("Translated sentence here");
    JList<String> Strings = new JList<>();
    JScrollPane stringsScroll = new JScrollPane(Strings);
    JScrollPane definitionsScroll = new JScrollPane(definitionHere);

    public DictionaryApplication(DictionaryManagement manager) {
        this.manager = manager;
    }

    public DictionaryApplication() {
    }

    public void borderDesign(JTextArea area) {
        area.setBorder(BorderFactory.createLineBorder(new Color(25, 25, 25)));
    }

    public void buttonDesign(JButton button) {
        button.setBackground(new Color(128, 128, 128));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public void windowFill(GridBagConstraints gbc, int tx, int ty, int dx, int dy) {
        gbc.gridx = tx;
        gbc.gridy = ty;
        gbc.weightx = dx;
        gbc.weighty = dy;
    }

    public void action() {
        addB.addActionListener(e -> addWord.setVisible(true));

        deleteB.addActionListener(e -> {
            if (Strings.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(Frame, "Fuck you idiot.");
            } else {
                delWord.setVisible(true);
            }
        });

        editB.addActionListener(e -> {
            if (Strings.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(Frame, "Hah, nice try stupid.");
            } else {
                editTarget.setText(" " + Strings.getSelectedValue());
                editWord.setVisible(true);
            }
        });

        transB.addActionListener(e -> {
            if (!DictionaryManagement.internetConnection()) {
                JOptionPane.showMessageDialog(Frame, "Failed to connect to the Internet.");
            } else {
                // ti nua bo m code sau.
                translatedHere.setText(" " + " ");
            }
        });

        sentenceTransB.addActionListener(e -> translateSentence.setVisible(true));
    }
    public void initAddWordDialog(){

    }
}
