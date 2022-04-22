package com.project.dodung;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.cloud.translate.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** Nếu thao tác với Dictionary thì thao tác thông qua DictionaryCommandline. */
public class DictionaryCommandline {

    public DictionaryCommandline() {
        DictionaryManagement.connect();
        DictionaryManagement.buildTrie();
    }
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
    // Cần check xem từ có tồn tại hay không
    /** Insert new word to database
     * @param word word to be inserted
     * @param html word's content(html)
     */
    public static boolean insertWord(String word,String html) {
        Word w = new Word(word,html,DictionaryManagement.getMaxWordId()+1);
        try{
            DictionaryManagement.insertWordToTable(w);
        }
        catch(existException e) {
            return false;
        }
        return true;
    }

    /** Delete word from database
     * @param id word's id
     */
    public static boolean deleteWord(int id) {
        Word w = new Word(DictionaryManagement.selectWordWithId(id),id);
        try{
            DictionaryManagement.deleteWord(w);
        }
        catch(nonExistException e) {
            return false;
        }
        return true;
    }

    public boolean checkConnection() {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String sentenceTranslator(String sentence) {
        if(sentence.isEmpty()) {
            return "";
        }
        Translate translator = TranslateOptions.getDefaultInstance().getService();
        Translation translation = translator.translate(
                sentence,
                Translate.TranslateOption.sourceLanguage(
                        translator.detect(sentence).getLanguage()),
                Translate.TranslateOption.targetLanguage("vi")
        );
        return translation.getTranslatedText();
    }

    public void speak(String sentence, String languageCode) throws Exception {
        try (TextToSpeechClient client = TextToSpeechClient.create()) {
            SynthesisInput input = SynthesisInput.newBuilder().setText(sentence).build();

            VoiceSelectionParams voice = VoiceSelectionParams
                    .newBuilder()
                    .setLanguageCode(languageCode)
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            AudioConfig audioConfig = AudioConfig
                    .newBuilder()
                    .setAudioEncoding(AudioEncoding.LINEAR16)
                    .build();

            SynthesizeSpeechResponse response = client
                    .synthesizeSpeech(input, voice, audioConfig);

            InputStream stream = new ByteArrayInputStream(response.getAudioContent().toByteArray());
            AudioInputStream sound = AudioSystem.getAudioInputStream(stream);

            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        }
    }

}
