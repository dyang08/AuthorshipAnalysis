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
    public static String AuthorsName = null;
    public static String title = null;
    public static String fileName = null;    
    public static String bookWordList = null;

    public static void main(String[] args)throws FileNotFoundException, IOException {

        Book book = new Book(AuthorsName, title, fileName);


    }//end main

    private static String printMessageWithReturn(String message) {
        System.out.printf(message);
        return keyboard.next();
    }

    private static void printMessage(String message) {
        System.out.printf(message);
    }
    
    
}//end AuthorshipAnalysis
