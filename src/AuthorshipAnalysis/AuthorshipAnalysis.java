package AuthorshipAnalysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Scott Dykstra
 * @version August 31,2013
 *
 */
public class AuthorshipAnalysis {

    public static Scanner keyboard = new Scanner(System.in);
    public static EpubUnZip unzip = new EpubUnZip();
    public static ArrayList<String> fileList;
    public static String bookWordList = null;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        HTMLParser parser = new HTMLParser();
        //  String zipFileName =  printMessageWithReturn("Please enter file name.\n");

        fileList = unzip.unZipEpub("MyFile.epub", "myUnZippedFile");
        // fileList = unzip.unZipEpub(zipFileName,"myUnZippedFile");

        // bookWordList = parser.HtmlParser(fileList);
        parser.saveParsedEBook(parser.HtmlParser(fileList), "MyFile.epub");
        //  printMessage(bookWordList);

    }//end main

    private static String printMessageWithReturn(String message) {
        System.out.printf(message);
        return keyboard.next();
    }

    private static void printMessage(String message) {
        System.out.printf(message);
    }
}//end AuthorshipAnalysis
