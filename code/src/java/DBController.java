package com.giorgia.pokequiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBController {
    //connection to the database PokeDB
    private static final String URL = "jdbc:sqlite:C:\\Users\\utente\\IdeaProjects\\PokeQuiz\\PokeDB.db";
    public static Connection connect() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    //create the table (if it doesn't exist already) which will contain all the scores
    public static void createTableChart() {
        String sql = "CREATE TABLE IF NOT EXISTS CHART (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "NAME TEXT NOT NULL, " +
                     "SCORE INTEGER NOT NULL" +
                     ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class Gamer {
        private String name;
        private int score;

        public Gamer(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }

        @Override
        public String toString() {
            return name + " - " + score;
        }
    }

    //add a new gamer and score to the chart
    public void addGamerAndScore(String name, int score) {
        String sql = "INSERT INTO chart(name, score) VALUES(?, ?)";

        try (Connection c = connect();
             PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //get the chart with all the score
    public List<Gamer> getChart() {
        String sql = "SELECT name, score FROM chart ORDER BY score DESC";
        List<Gamer> chart = new ArrayList<>();

        try (Connection c = connect();
             PreparedStatement pstmt = c.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                chart.add(new Gamer(name, score));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return chart;
    }
}