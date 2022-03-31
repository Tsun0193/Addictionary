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

    public static void findSimilarWord(String word) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        myTrie.reset();
        for(int i = 0; i < word.length(); ++i) {
            myTrie.traverseNextChar(word.charAt(i));
        }
        myTrie.findSimilar(res,myTrie.getLastCurNode());
        System.out.println("Similar word to " + word + ":");
        for (Integer re : res) {
            System.out.println(re + " : " + DictionaryManagement.selectWordWithId(re));
        }
    }
    public static void buildTrie() {
        String sql = "SELECT id, word FROM av";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                myTrie.addWord(rs.getString("word"),rs.getInt("id"));
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



    public static void updateWord(int id, String data){
        String sql = "UPDATE av SET html = ? "
                + "WHERE id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setString(1, data);
            preStatement.setInt(2, id);
            // update
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteWord(int id) {
        String sql = "DELETE FROM av WHERE id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setInt(1, id);
            preStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String selectWordWithId(int id) {
        String sql = "SELECT word FROM av WHERE id = ?";

        try (PreparedStatement preStatement = conn.prepareStatement(sql);) {
            preStatement.setInt(1, id);
            try(ResultSet rs = preStatement.executeQuery()) {
                return rs.getString("word");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static void insertWordToTable(int id, String word, String html,String description, String pronounce) {
        String sql = "INSERT INTO av(id,word,html,description,pronounce) VALUES(?,?,?,?,?)";

        try (PreparedStatement preStatement = conn.prepareStatement(sql)) {
            preStatement.setInt(1, id);
            preStatement.setString(2,word);
            preStatement.setString(3, html);
            preStatement.setString(4, description);
            preStatement.setString(5, pronounce);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection conn = null;
    private static Trie myTrie = new Trie();
}