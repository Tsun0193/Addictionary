package com.project.quangminh;

import com.project.dodung.DictionaryManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryApplication extends JFrame {
    private JPanel mainPanel;
    private JTextField searchWordTextField;
    private JLabel searchWordLabel;
    private JLabel similarWordLabel;
    private JButton similarWordButton;

    public DictionaryApplication(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        similarWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void main(String[] args){
        JFrame appFrame = new DictionaryApplication("Dictionary pro vjp");
        appFrame.setVisible(true);
    }
}

