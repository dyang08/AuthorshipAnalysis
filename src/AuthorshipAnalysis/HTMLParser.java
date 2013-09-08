package AuthorshipAnalysis;

import java.io.*;
import java.util.ArrayList;
import org.jsoup.Jsoup;

/**
 * @author Scott Dykstra
 * @version August 31,2013
 *
 * code for extractText method from
 * http://stackoverflow.com/questions/9631477/retrieve-text-from-html-file-in-java
 */
public class HTMLParser {

    public String HtmlParser(ArrayList<String> fileName) throws FileNotFoundException, IOException {

        String fileString = null;
        for (int i = 0; i < fileName.size(); i++) {

            String file = "myUnZippedFile//" + fileName.get(i);
            FileReader reader = new FileReader(file);
            fileString += extractText(reader);
        }
        return fileString;

    }//end main

    public String extractText(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String textOnly = Jsoup.parse(sb.toString()).text();
        return textOnly;
    }//end extractText

    public void saveParsedEBook(String parsedBook, String bookTitles) {
     
            bookTitles +=".txt";
       
       PrintWriter writer = null;

        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(bookTitles)));
            for(int i=0;i< parsedBook.split(" ").length  ;i++)

            writer.write(parsedBook);

        } catch (IOException ex) {
            System.err.print(ex);
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
        System.out.print("Book done");
    }//end saveGraph
}