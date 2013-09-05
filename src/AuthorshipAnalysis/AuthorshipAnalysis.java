package AuthorshipAnalysis;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Scott Dykstra
 * @version September 2,2013
 *
 */
public class AuthorshipAnalysis {

    public static Scanner keyboard = new Scanner(System.in);
    public static String authorsName = null;
    public static String title = null;
    public static String fileName = null;
    public static String bookWordList = null;

    public static void main(String[] args)throws FileNotFoundException, IOException {

        Book book = new Book(authorsName, title, fileName);
        
        SqlConnection sqlConnection = new SqlConnection();
        
        
        AuthorMetrics a = sqlConnection.getAuthorMetrics(authorsName);
        AuthorMetrics b = a;
    }//end main

    private static String printMessageWithReturn(String message) {
        System.out.printf(message);
        return keyboard.next();
    }

    private static void printMessage(String message) {
        System.out.printf(message);
    }
    
    
}//end AuthorshipAnalysis
