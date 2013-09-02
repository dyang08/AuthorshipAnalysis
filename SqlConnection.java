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

    public void createTableBookMetrics() {
        Statement stat = null;
        String sql = "CREATE TABLE `book_metrics` (\n"
                + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                + "  `name` varchar(45) DEFAULT NULL,\n"
                + "  `average_word_length` double DEFAULT NULL,\n"
                + "  `average_sentence_legth` double DEFAULT NULL,\n"
                + "  `ratio_word_sentence` double DEFAULT NULL,\n"
                + "  `rel_letter_freq` double DEFAULT NULL,\n"
                + "  `rel_letter_pair_freq` double DEFAULT NULL,\n"
                + "  `vocab_richness` double DEFAULT NULL,\n"
                + "  `freq_noun` double DEFAULT NULL,\n"
                + "  `freq_verb` double DEFAULT NULL,\n"
                + "  `freq_adjective` double DEFAULT NULL,\n"
                + "  `ratio_adjective_noun` double DEFAULT NULL,\n"
                + "  `simple_lexical_density` double DEFAULT NULL,\n"
                + "  `distribution_word_length_id` int(11) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`id`),\n"
                + "  UNIQUE KEY `id_UNIQUE` (`id`)\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;";
        if (conn != null) {
            try {
                stat = conn.createStatement();
            } catch (SQLException e) {
                System.out.println("Connection Couldnt be Obtained");
            }

            if (stat != null) {
                try {
                    stat.executeUpdate(sql);
                } catch (SQLException e) {
                    System.out.println("Table already Exists");
                }
            }
        }
    }

    public void createTableDistributionWordLength() {
        Statement stat = null;
        String sql = "CREATE TABLE `distribution_word_length` (\n"
                + "  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,\n"
                + "  `one_letter` double DEFAULT NULL,\n"
                + "  `two_letter` double DEFAULT NULL,\n"
                + "  `three_letter` double DEFAULT NULL,\n"
                + "  `four_letter` double DEFAULT NULL,\n"
                + "  `five_letter` double DEFAULT NULL,\n"
                + "  PRIMARY KEY (`id`)\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        if (conn != null) {
            try {
                stat = conn.createStatement();
            } catch (SQLException e) {
                System.out.println("Connection Couldnt be Obtained");
            }

            if (stat != null) {
                try {
                    stat.executeUpdate(sql);
                } catch (SQLException e) {
                    System.out.println("Table already Exists");
                }
            }
        }
    }

    public void addBookMetrics(String word) {
        PreparedStatement pstat = null;
        String sql = "INSERT INTO book_metrics VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        if (conn != null) {
            try {
                pstat.setInt(1, 0);
                pstat.setString(2, /*bookname*/);
                pstat.setString(3, /*author*/);
                pstat.setDouble(4, /*averageWordLength*/);
                pstat.setDouble(5, /*averageSentenceLegth*/);
                pstat.setDouble(6, /*ratioWordSentence*/);
                pstat.setDouble(7, /*relLetterFreq*/
                pstat.setDouble(8, /*relLetterPairFreq*/);
                pstat.setDouble(9, /*vocabRichness*/);
                pstat.setDouble(10, /*freqNoun*/);
                pstat.setDouble(11, /*freqVerb*/);
                pstat.setDouble(12, /*freqAdjective*/);
                pstat.setDouble(13, /*ratioAdjectiveNoun*/);
                pstat.setDouble(14, /*simpleLexicalDensity*/);
                pstat.setInt(15, /*idOfDistributionWordLength*/);
                pstat = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Connection Couldnt be Obtained");
            }
        }

        if (pstat != null) {
            try {
                pstat.setString(1, word);
                pstat.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Insertion of the entry was unsuccessful");
            }
        }
    }
    
    public void updateBookMetrics(String word) {
        PreparedStatement pstat = null;
        String sql = "UPDATE book_metrics b SET  b.name = ?, b.author = ?, "
                + "b.average_word_length = ?, b.average_sentence_legth = ?, "
                + "b.ratio_word_sentence = ?, b.rel_letter_freq = ?, "
                + "b.rel_letter_pair_freq = ?, b.vocab_richness = ?, "
                + "b.freq_noun = ?, b.freq_verb = ?, b.freq_adjective = ?, "
                + "b.ratio_adjective_noun = ?, b.simple_lexical_density = ?, "
                + "b.distribution_word_length_id = ? WHERE b.id = ?;";

        if (conn != null) {
            try {
                pstat.setString(1, /*bookname*/);
                pstat.setString(2, /*author*/);
                pstat.setDouble(3, /*averageWordLength*/);
                pstat.setDouble(4, /*averageSentenceLegth*/);
                pstat.setDouble(5, /*ratioWordSentence*/);
                pstat.setDouble(6, /*relLetterFreq*/
                pstat.setDouble(7, /*relLetterPairFreq*/);
                pstat.setDouble(8, /*vocabRichness*/);
                pstat.setDouble(9, /*freqNoun*/);
                pstat.setDouble(10, /*freqVerb*/);
                pstat.setDouble(11, /*freqAdjective*/);
                pstat.setDouble(12, /*ratioAdjectiveNoun*/);
                pstat.setDouble(13, /*simpleLexicalDensity*/);
                pstat.setInt(14, /*idOfDistributionWordLength*/);
                pstat.setInt(15, /*metricId*/);
                pstat = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Connection Couldnt be Obtained");
            }
        }

        if (pstat != null) {
            try {
                pstat.setString(1, word);
                pstat.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Insertion of the entry was unsuccessful");
            }
        }
    }

    public void getBookMetrics(String author) {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT AVG(b.average_word_length), "
                + "AVG(b.average_sentence_length), AVG(b.ratio_word_sentence), "
                + "AVG(b.rel_letter_freq), AVG(b.rel_letter_pair_freq), "
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
                e.printStackTrace();
            }
        }
        
        if(rs != null){
            try {
                double averageWordLegth = rs.getDouble(1);
                double averageSentenceLegth = rs.getDouble(2);
                double ratioWordSentence = rs.getDouble(3);
                double relLetterFreq = rs.getDouble(4);
                double relLetterPairFreq = rs.getDouble(5);
                double vocabRichness = rs.getDouble(6);
                double freqNoun = rs.getDouble(7);
                double freqVerb = rs.getDouble(8);
                double freqAdjective = rs.getDouble(9);
                double ratioAdjectiveNoun = rs.getDouble(10);
                double simpleLexicalDensity = rs.getDouble(11);
            } catch (SQLException ex) {
                Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
