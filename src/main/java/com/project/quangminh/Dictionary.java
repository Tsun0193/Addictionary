package com.project.quangminh;

import java.util.ArrayList;
import java.util.Collections;


public class Dictionary {
    public static ArrayList<Word> listDictionary = new ArrayList<Word>();
    public ArrayList<Word> getList() {
        return listDictionary;
    }

    public void setList(ArrayList<Word> l) {
        Dictionary.listDictionary = l;
    }

    public int getLength(){
        return listDictionary.size();
    }
    public void addWord(Word word){
        listDictionary.add(word);
    }
    public Word getWord(int index){
        return listDictionary.get(index);
    }

    public void setWord(int index,Word word){
        listDictionary.set(index,word);
    }

    public void removeWord(Word word){
        listDictionary.remove(word);
    }

    public void removeWord(String target,String meaning){
        for(int i=0;i<listDictionary.size();i++){
            if(target.toLowerCase().compareTo(listDictionary.get(i).getTargetWord().toLowerCase())==0){
                listDictionary.remove(i);
                break;
            }
        }
    }


    public boolean existed(Word word){
        for(int i = 0;i<listDictionary.size();i++){
            if(word.getTargetWord().toLowerCase().compareTo(listDictionary.get(i).getTargetWord().toLowerCase())==0){
                return true;
            }
        }
        return false;
    }
    public void sortDictionary(){
        Collections.sort(listDictionary);
    }
}
