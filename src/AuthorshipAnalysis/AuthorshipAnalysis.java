package AuthorshipAnalysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Scott Dykstra
 * @version September 2,2013
 *
 */
public class AuthorshipAnalysis {

    public static String authorsName = null;
    public static String title = null;
    public static String fileName = null;
    public static String bookWordList = null;
    public static SqlConnection sqlConnection;

    public static void main(String[] args) throws FileNotFoundException, IOException {

       // Book book = new Book(authorsName, title, fileName);

        sqlConnection = new SqlConnection();

        List<String> a = sqlConnection.getListOfAuthors();
        List<String> b = a;
    }//end main

//    public String[] getAuthors() {
//        return sqlConnection.getListOfAuthors();
//    }
//
//    public AuthorMetrics getAuthorMetrics(String author) {
//        return sqlConnection.getAuthorMetrics(author);
//    }
//
//    public Map<String, Map<String, String>> getNewBookMetrics(Book unknownBook) {
//    }

}//end AuthorshipAnalysis
