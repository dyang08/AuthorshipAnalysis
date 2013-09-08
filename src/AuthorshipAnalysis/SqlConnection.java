/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthorshipAnalysis;

/**
 *
 * @author Hector
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SqlConnection {

    Connection conn;

    public SqlConnection() {
        this.getConn();
    }

    public Connection getConn() {
        try {
            conn = getConnection();

        } catch (SQLException e) {
            System.out.println("Connection Couldnt be Obtained");
        }
        return conn;
    }

    public static Connection getConnection() throws SQLException {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://mysqldb.civxtqnqsx7j.us-west-2.rds.amazonaws.com:3306/mysqldb";
        String username = "hector";
        String password = "oklarican";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DriverManager.getConnection(url, username, password);
    }

    public void addBookMetrics(Book book) {
        PreparedStatement pstat = null;
        String sql = "INSERT INTO book_metrics (id, title, author, "
                + "average_word_length, average_sentence_length, "
                + "ratio_word_sentence, vocab_richness, freq_noun, "
                + "freq_verb, freq_adjective, ratio_adjective_noun, "
                + "simple_lexical_density) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        if (conn != null) {
            try {
                pstat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstat.setInt(1, 0);
                pstat.setString(2, book.getBookTitle());
                pstat.setString(3, book.getAuthor());
                pstat.setDouble(4, book.averageWordLength());
                pstat.setDouble(5, book.averageSentenceLength());
                pstat.setDouble(6, book.ratioWordToSentenceLength());
                pstat.setDouble(7, book.vocabularyRichness());
                pstat.setDouble(8, book.frequencyOfNounUsage());
                pstat.setDouble(9, book.frequencyOfVerbUsage());
                pstat.setDouble(10, book.frequencyOfAdjectiveUsage());
                pstat.setDouble(11, book.ratioAdjectivesToNounUsage());
                pstat.setDouble(12, book.simpleLexicalDensity());

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (pstat != null) {
            try {
                pstat.executeUpdate();
                ResultSet rs = pstat.getGeneratedKeys();
                rs.next();
                book.setId(rs.getInt(1));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        addRelativeLetterFrequency(book.getId(), book.relativeLetterFrequency());
        addRelativeLetterPairFrequency(book.getId(), book.relativeLetterPairFrequencies());
        addDistributionWordLength(book.getId(), book.distributionOfWordLengths());
    }

    private void addRelativeLetterFrequency(int id, double[] relativeLetterFrequency) {
        PreparedStatement pstat = null;
        String sql = "INSERT INTO relative_letter_freq (book_id, letter_number, "
                + "freq) "
                + "VALUES(?, ?, ?);";
        for (int i = 0; i < relativeLetterFrequency.length; i++) {
            if (conn != null) {
                try {
                    pstat = conn.prepareStatement(sql);
                    pstat.setInt(1, id);
                    pstat.setInt(2, i);
                    pstat.setDouble(3, relativeLetterFrequency[i]);

                } catch (SQLException e) {
                    System.out.println("Connection Couldnt be Obtained");
                }
            }

            if (pstat != null) {
                try {
                    pstat.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void addRelativeLetterPairFrequency(int id, HashMap<String, Double> relativeLetterPairFrequencies) {
        PreparedStatement pstat = null;
        String sql = "INSERT INTO relative_letter_pair_freq (book_id, "
                + "letter_pair, freq) "
                + "VALUES(?, ?, ?);";
        for (String pair : relativeLetterPairFrequencies.keySet()) {
            if (conn != null) {
                try {
                    pstat = conn.prepareStatement(sql);
                    pstat.setInt(1, id);
                    pstat.setString(2, pair);
                    pstat.setDouble(3, relativeLetterPairFrequencies.get(pair));

                } catch (SQLException e) {
                    System.out.println("Connection Couldnt be Obtained");
                }
            }

            if (pstat != null) {
                try {
                    pstat.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void addDistributionWordLength(int id, HashMap<Integer, Double> distributionOfWordLengths) {
        PreparedStatement pstat = null;
        String sql = "INSERT INTO distribution_word_length (book_id, length, "
                + "distribution) "
                + "VALUES(?, ?, ?);";
        for (Integer length : distributionOfWordLengths.keySet()) {
            if (conn != null) {
                try {
                    pstat = conn.prepareStatement(sql);
                    pstat.setInt(1, id);
                    pstat.setInt(2, length);
                    pstat.setDouble(3, distributionOfWordLengths.get(length));

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (pstat != null) {
                try {
                    pstat.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public List<String> getListOfAuthors() {
        List<String> authors = new ArrayList<>();
        PreparedStatement pstat = null;
        ResultSet rs;
        String sql = "SELECT DISTINCT author FROM book_metrics;";
        if (conn != null) {
            try {
                pstat = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Connection Couldnt be Obtained");
            }
        }

        if (pstat != null) {
            try {
                rs = pstat.executeQuery();
                while (rs.next()) {
                    String value = rs.getString(1);
                    authors.add(value);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return authors;
    }

    public AuthorMetrics getAuthorMetrics(String author) {
        AuthorMetrics authorMetrics = new AuthorMetrics();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT AVG(b.average_word_length), "
                + "AVG(b.average_sentence_length), AVG(b.ratio_word_sentence), "
                + "AVG(b.vocab_richness), AVG(b.freq_noun), AVG(b.freq_verb), "
                + "AVG(b.freq_adjective), AVG(b.ratio_adjective_noun), "
                + "AVG(b.simple_lexical_density) FROM book_metrics b "
                + "WHERE author = ?;";
        if (conn != null) {
            try {
                pstat = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Connection Couldnt be Obtained");
            }
        }

        if (pstat != null) {
            try {
                pstat.setString(1, author);
                rs = pstat.executeQuery();
                rs.next();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (rs != null) {
            try {
                authorMetrics.setAvgWordLength(rs.getDouble(1));
                authorMetrics.setAvgSentenceLength(rs.getDouble(2));
                authorMetrics.setWordToSentenceRatio(rs.getDouble(3));
                authorMetrics.setVocabRichness(rs.getDouble(4));
                authorMetrics.setFreqNoun(rs.getDouble(5));
                authorMetrics.setFreqVerb(rs.getDouble(6));
                authorMetrics.setFreqAdjective(rs.getDouble(7));
                authorMetrics.setAdjectiveToNounRatio(rs.getDouble(8));
                authorMetrics.setLexicalDensity(rs.getDouble(9));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        authorMetrics.setRelativeLetterFreq(getRelativeLetterFrequency(author));
        authorMetrics.setRelativeLetterPairFrequencies(getRelativeLetterPairFrequency(author));
        authorMetrics.setDistributionOfWordLengths(getDistributionWordLength(author));
        return authorMetrics;
    }

    private Double[] getRelativeLetterFrequency(String author) {
        Double[] relativeLetterFrequency = new Double[26];
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT r.letter_number, AVG(r.freq) "
                + "FROM relative_letter_freq r "
                + "WHERE book_id in "
                + "(SELECT id FROM book_metrics WHERE author = ?) "
                + "GROUP BY r.letter_number;";
        if (conn != null) {
            try {
                pstat = conn.prepareStatement(sql);
                pstat.setString(1, author);
            } catch (SQLException e) {
                System.out.println("Connection Couldnt be Obtained");
            }
        }

        if (pstat != null) {
            try {
                pstat.setString(1, author);
                rs = pstat.executeQuery();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (rs != null) {
            try {
                while (rs.next()) {
                    Integer letterNumber = rs.getInt(1);
                    Double avgFreq = rs.getDouble(2);
                    relativeLetterFrequency[letterNumber] = avgFreq;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return relativeLetterFrequency;
    }

    private HashMap<Integer, Double> getDistributionWordLength(String author) {
        HashMap<Integer, Double> distributionWordLength = new HashMap();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT d.length, AVG(d.distribution) "
                + "FROM distribution_word_length d "
                + "WHERE book_id in "
                + "(SELECT id FROM book_metrics WHERE author = ?) "
                + "GROUP BY d.length;";
        if (conn != null) {
            try {
                pstat = conn.prepareStatement(sql);
                pstat.setString(1, author);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (pstat != null) {
            try {
                pstat.setString(1, author);
                rs = pstat.executeQuery();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (rs != null) {
            try {
                while (rs.next()) {
                    Integer length = rs.getInt(1);
                    Double averagerDistribution = rs.getDouble(2);
                    distributionWordLength.put(length, averagerDistribution);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return distributionWordLength;
    }

    private HashMap<String, Double> getRelativeLetterPairFrequency(String author) {
        HashMap<String, Double> relativeLetterPairFrequency = new HashMap();
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT r.letter_pair, AVG(r.freq) "
                + "FROM relative_letter_pair_freq r "
                + "WHERE book_id in "
                + "(SELECT id FROM book_metrics WHERE author = ?) "
                + "GROUP BY r.letter_pair;";
        if (conn != null) {
            try {
                pstat = conn.prepareStatement(sql);
                pstat.setString(1, author);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (pstat != null) {
            try {
                pstat.setString(1, author);
                rs = pstat.executeQuery();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (rs != null) {
            try {
                while (rs.next()) {
                    String pair = rs.getString(1);
                    Double avgFreq = rs.getDouble(2);
                    relativeLetterPairFrequency.put(pair, avgFreq);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return relativeLetterPairFrequency;
    }

}
