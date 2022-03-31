package com.project.duydang;

import com.project.dodung.DictionaryManagement;
import com.project.dodung.dictionaryInput;
import com.project.main.Main;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class DuyDangMain extends Main {
    /**
     * Declaration variables.
     */
    private DictionaryManagement manager = new DictionaryManagement();
    private final JFrame Frame = new JFrame();
    private final JDialog addWord = new JDialog(Frame, "Add word:", true);
    private final JDialog delWord = new JDialog(Frame, "Delete Word?", true);
    private final JDialog editWord = new JDialog(Frame, "Edit Word:", true);
    private final JDialog translateSentence = new JDialog(Frame, "Translate", true);

    private final JPanel Box = new JPanel(new GridBagLayout());
    private final JPanel functionBox = new JPanel(new GridLayout(1,0,5,0));
    private final JPanel definitionBox = new JPanel(new GridLayout(1,0,5,0));
    private final JPanel givenWord = new JPanel(new GridLayout(0,2));
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

    public DuyDangMain(DictionaryManagement manager) {
        this.manager = manager;
    }


    @Override
    public void run() {

    }
}
