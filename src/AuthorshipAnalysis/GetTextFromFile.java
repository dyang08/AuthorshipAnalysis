package AuthorshipAnalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott
 * @version September 2, 2013
 */
public class GetTextFromFile {

    private static final EpubUnZip unzip = new EpubUnZip();
    private static ArrayList<String> fileList;
    private static final String bookWordList = null;

    public String getString(String fileName) throws FileNotFoundException, IOException {

        int size = fileName.length();
        String bookString = null;
        
        CharSequence temptxt = fileName.subSequence(size - 3, size);
        CharSequence tempEpub = fileName.subSequence(size - 4, size);

        if (tempEpub.equals("epub") || temptxt.equals("zip")) {

            bookString = getStringFromEpub(fileName);
        } else if (temptxt.equals("txt")) {
            bookString = getTextString(fileName);
        }

        return bookString;

    }

    private String getStringFromEpub(String filename){
        try {
            String saveUnZippedFile = "myUnZippedFile";
            fileList = unzip.unZipEpub(filename, saveUnZippedFile);
            HTMLParser parser = new HTMLParser();

            return parser.HtmlParser(fileList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GetTextFromFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetTextFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * getTextString take text file name and returns a string, has IOException
     *
     * @param fileName
     * @return fileString
     */
    private String getTextString(String fileName) {
        String fileString = null;

        try {
            StringTokenizer reader;
            File file = new File(fileName);

            FileInputStream fileInput = null;
            try {
                fileInput = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            byte buffer[] = new byte[(int) file.length()];
            fileInput.read(buffer, 0, (int) file.length());
            String stringOfBuffer = new String(buffer);
            reader = new StringTokenizer(stringOfBuffer);



            while (reader.hasMoreTokens()) {
                fileString += reader.nextToken() + " ";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileString;
    }//end getTextString
}//end GetTextFromFile
