package com.project.dodung;

import com.project.main.Main;

import java.util.ArrayList;

public class DoDungMain extends Main {

    @Override
    public void run(){
        DictionaryManagement.connect();
        DictionaryManagement.buildTrie();
        DictionaryManagement.findSimilarWord("aban");
        DictionaryManagement.findSimilarWord("cut");
    }

}
