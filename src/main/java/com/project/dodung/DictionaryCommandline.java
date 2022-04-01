package com.project.dodung;

import java.util.ArrayList;

/** Nếu thao tác với Dictionary thì thao tác thông qua DictionaryCommandline. */
public class DictionaryCommandline {

    /** Find similar word to provided word
     * @param word the word to find similar
     * @return an array of pair that contains word's id and word's name
     */
    public static ArrayList<Pair<Integer,String> > makeSuggestion(String word) {
        Word w = new Word(word);
        return DictionaryManagement.stringSimilarWords(w);
    }

    /** Get word content from database with id
     * @param id word's id
     * @return a pair with word and its content(html)
     */
    public static Pair<String,String> getExactWord(int id) {
        return DictionaryManagement.selectWordAndHtmlWithId(id);
    }

    /** Get word content from database with string word
     * @param word string word
     * @return a pair with word and its content(html)
     */
    public static Pair<String,String> getExactWord(String word) {
        Word w = new Word(word);
        return DictionaryManagement.selectWordAndHtmlWithId(w.getId());
    }

    /** Insert new word to database
     * @param word word to be inserted
     * @param html word's content(html)
     */
    public static void insertWord(String word,String html) {
        Word w = new Word(word,html,DictionaryManagement.getMaxWordId()+1);
        DictionaryManagement.insertWordToTable(w);
    }

    /** Delete word from database
     * @param id word's id
     */
    public static void deleteWord(int id) {
        Word w = new Word(DictionaryManagement.selectWordWithId(id),id);
        DictionaryManagement.deleteWord(w);
    }

}
