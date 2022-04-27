package com.project.dodung;

import com.project.main.Main;


public class DoDungMain extends Main {

    @Override
    public void run(){

    }
    public static void main(String args[]) {
        DictionaryManagement.connect();
        DictionaryManagement.buildTrie();
        String a = DictionaryCommandline.sentenceTranslator("how are you");
        System.out.println("how are you ->" + a);
        DictionaryCommandline.speak("how are you");
    }
}
