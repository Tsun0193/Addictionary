package com.project.dodung;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DictionaryManagement {
    /**
     * Connect to a sample database
     */
    public static void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:dict_hh.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static boolean internetConnection() {
        boolean flag = true;
        try {
            String url = "www.google.com";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to Internet has been established.");
        } catch (Exception e) {
            flag = false;
            System.out.println(e.getMessage());
        }
        return flag;
    }

    /**
     * Thử lại đi ;-; không chắc lắm đâu.
     */
    public static ArrayList<Pair<Integer,String> > stringSimilarWords(Word w) {
        ArrayList<Pair<Integer,String> > res = new ArrayList<>();
        myTrie.reset();
        String word = w.getWord();
        int lastPosOnTrie = myTrie.traverseNonInsert(word);
        if(lastPosOnTrie == 0 && word.length() > 0) {
            return res;
        }
        ArrayList<Integer> ListId = new ArrayList<>();
        myTrie.findSimilar(ListId,lastPosOnTrie);
        Pair<Integer,String> toAdd = new Pair<>();
        System.out.println("Similar word to " + word + ":");
        for (Integer re : ListId) {
            toAdd.setKey(re);
            toAdd.setValue(DictionaryManagement.selectWordWithId(re));
            res.add(toAdd);
            System.out.println(DictionaryManagement.selectWordWithId(re));
        }
        return res;
    }

    public static void buildTrie() {
        String sql = "SELECT id, word FROM av";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                myTrie.addWord(Word.normalizeWord(rs.getString("word")),rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAll(){
        String sql = "SELECT id, word FROM av";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("word"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void updateWord(Word w){
        String sql = "UPDATE av SET html = ? "
                + "WHERE id = ?";
        if(w.getId() == 0) return;
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setString(1, w.getHtml());
            preStatement.setInt(2, w.getId());
            // update
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteWord(Word w) {
        String sql = "DELETE FROM av WHERE id = ?";
        if(w.getId() == 0) return;
        // Delete on trie
        myTrie.deleteWord(w.getWord());
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setInt(1, w.getId());
            preStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String selectWordWithId(int id) {
        String sql = "SELECT word FROM av WHERE id = ?";
        if(id == 0) return "";
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setInt(1, id);
            try(ResultSet rs = preStatement.executeQuery()) {
                return Word.normalizeWord(rs.getString("word"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static String selectWordHtmlWithId(int id) {
        String sql = "SELECT html FROM av WHERE id = ?";
        if(id == 0) return "";
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setInt(1, id);
            try(ResultSet rs = preStatement.executeQuery()) {
                return rs.getString("word");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static Pair<String,String> selectWordAndHtmlWithId(int id) {
        String sql = "SELECT word,html FROM av WHERE id = ?";
        Pair<String,String> ret = new Pair<>();
        if(id == 0) return ret;
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setInt(1, id);
            try(ResultSet rs = preStatement.executeQuery()) {
                ret.setKey(Word.normalizeWord(rs.getString("word")));
                ret.setValue(rs.getString("html"));
                return ret;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public static void insertWordToTable(Word w) {
        String sql = "INSERT INTO av(id,word,html) VALUES(?,?,?)";
        ++maxWordId;
        myTrie.addWord(w.getWord(),w.getId());
        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setInt(1, w.getId());
            preStatement.setString(2,w.getWord());
            preStatement.setString(3, w.getHtml());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getWordId(Word w) {
        return myTrie.findWordId(w.getWord());
    }

    public static int getMaxWordId() {
        return maxWordId;
    }
    public static Connection conn = null;
    private static Trie myTrie = new Trie();
    /** Sẽ sửa lại nếu có sort word khi tắt chương trình. */
    private static int maxWordId = 108854;
}