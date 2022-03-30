package com.project.quangminh;

public class Word implements Comparable<Word> {
    private String targetWord;
    private String meaningWord;

    public Word() {

    }

    public Word(String targetWord, String meaningWord) {
        this.targetWord = targetWord;
        this.meaningWord = meaningWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public String getMeaningWord() {
        return meaningWord;
    }

    public void setMeaningWord(String meaningWord) {
        this.meaningWord = meaningWord;
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    @Override
    public int compareTo(Word o) {
        return this.getTargetWord().compareTo(o.getTargetWord());
    }
}
