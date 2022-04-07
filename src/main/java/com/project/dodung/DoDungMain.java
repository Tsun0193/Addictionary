package com.project.dodung;

import com.project.main.Main;

import java.util.ArrayList;

public class DoDungMain extends Main {

    @Override
    public void run(){
        DictionaryManagement.connect();
        DictionaryManagement.buildTrie();
        Word w = new Word("aban");
        Word w1 = new Word("cut");
        System.out.println(w.getId());
        System.out.println(w1.getId());
    }
}
