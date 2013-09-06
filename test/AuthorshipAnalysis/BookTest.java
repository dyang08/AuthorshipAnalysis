/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthorshipAnalysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Scott
 */
public class BookTest {

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public BookTest()  {
    }
    public static String authorName = "Arthur C. Clarke";
    public static String bookTitle = "Cradle";
    public static String bookText = "The emerald water smashes against the dark volcanic "
            + "cliffs.  Fine white spray hovers over the harsh rock "
            + "creating a misty veil that glimmers in the fading light. "
            + " In the distance, two yellow suns set simultaneously, "
            + "separated by about forty degrees as they disappear together"
            + " below the horizon.  Across the blue-black sky, on the "
            + "opposite side of the isthmus that slopes gently downward "
            + "from the volcanic cliffs to another ocean, a pair of full"
            + " moons rise as the two suns vanish.  Their twin moonlight, "
            + "although much weaker than the shine of the disappearing "
            + "suns, is still strong enough to create dancing moon shadows"
            + " on the ocean beneath the rocky overhang.";
    public static Book book = new Book(bookText);

    /**
     * Test of averageWordLength method, of class Book.
     */
    @Test
    public void testAverageWordLength() {
        System.out.println("averageWordLength");

        double expResult = 5.018348623853211;
        double result = book.averageWordLength();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of averageSentenceLength method, of class Book.
     */
    @Test
    public void testAverageSentenceLength() {
        System.out.println("averageSentenceLength");

        double expResult = 131.8;
        double result = book.averageSentenceLength();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of ratioWordToSentenceLength method, of class Book.
     */
    @Test
    public void testRatioWordToSentenceLength() {
        System.out.println("ratioWordToSentenceLength");

        double expResult = 0.038075482730297504;
        double result = book.ratioWordToSentenceLength();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of relativeLetterFrequency method, of class Book. // need to think
     * on this test can't test double array///////////////////////////////////
     *
     * @Test public void testRelativeLetterFrequency() {
     * System.out.println("relativeLetterFrequency"); Book instance = new
     * Book("a");
     *
     * double[] expResult =
     * {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; double[] result =
     * book.relativeLetterFrequency(); assertArrayEquals(expResult, result);
     *
     * }
     */
    
    
    /**
     * Test of relativeLetterPairFrequencies method, of class Book.
     */
    @Test
    public void testRelativeLetterPairFrequencies() {
        System.out.println("relativeLetterPairFrequencies");

        HashMap<String, Integer> expResult = new HashMap();
        Book iBook = new Book("ntsno genw  ga  ");
        String[] stringSet = {"nt", "ts", "sn", "no", "ge", "en", "nw", "ga"};

        for (int i = 0; i < stringSet.length; i++) {

            expResult.put(stringSet[i], i);
        }
        HashMap result = iBook.relativeLetterPairFrequencies();
        assertEquals(expResult.keySet(), result.keySet());
    }

    /**
     * Test of vocabularyRichness method, of class Book.
     */
    @Test
    public void testVocabularyRichness() {
        System.out.println("vocabularyRichness");

        double expResult = 0.6972477064220184;
        double result = book.vocabularyRichness();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of distributionOfWordLengths method, of class Book.
     */
    @Test
    public void testDistributionOfWordLengths() {
        System.out.println("distributionOfWordLengths");
int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15};
double [] value = {0.01834862385321101, 0.11926605504587157,0.1651376146788991,
    0.1834862385321101, 0.13761467889908258, 0.11926605504587157, 
    0.10091743119266056, 0.08256880733944955, 0.03669724770642202,
    0.01834862385321101, 0.009174311926605505, 0.009174311926605505};
        HashMap<Integer, Double> expResult = new HashMap();
        HashMap result = book.distributionOfWordLengths();
        for(int i=0; i<keys.length;i++){
        expResult.put(keys[i],value[i]);
        
    }
        
        
        assertEquals(expResult, result);

    }

    /**
     * Test of frequencyOfNounUsage method, of class Book.
     */
    @Test
    public void testFrequencyOfNounUsage() {
        System.out.println("frequencyOfNounUsage");

        double expResult = 0.41284403669724773;
        double result = book.frequencyOfNounUsage();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of frequencyOfVerbs method, of class Book.
     */
    @Test
    public void testFrequencyOfVerbs() {
        System.out.println("frequencyOfVerbs");

        double expResult = 0.23853211009174313;
        double result = book.frequencyOfVerbs();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of frequencyOfAdjectiveUsage method, of class Book.
     */
    @Test
    public void testFrequencyOfAdjectiveUsage() {
        System.out.println("frequencyOfAdjectiveUsage");

        double expResult = 0.29357798165137616;
        double result = book.frequencyOfAdjectiveUsage();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of ratioAdjectivesToNounUsage method, of class Book.
     */
    @Test
    public void testRatioAdjectivesToNounUsage() {
        System.out.println("ratioAdjectivesToNounUsage");

        double expResult = 0.0;
        double result = book.ratioAdjectivesToNounUsage();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of simpleLexicalDensity method, of class Book.
     */
    @Test
    public void testSimpleLexicalDensity() {
        System.out.println("simpleLexicalDensity");

        double expResult = 0.41284403669724773;
        double result = book.simpleLexicalDensity();
        System.out.print(result);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getBookTitile method, of class Book.
    
    @Test
    public void testGetBookTitile() throws FileNotFoundException, IOException {
        System.out.println("getBookTitile");
       Book lbook = new Book(authorName, bookTitle, bookText);
        String expResult = "Cradle";
        String result = book.getBookTitle();
        assertEquals(expResult, result);
        
    }
    *  */
    /**
     * Test of getAuthor method, of class Book.
     *
     * @Test public void testGetAuthor() { System.out.println("getAuthor");
     *
     * String expResult = ""; String result = book.getAuthor();
     * assertEquals(expResult, result); // TODO review the generated test code
     * and remove the default call to fail. fail("The test case is a
     * prototype."); }
     *
     * /**
     * Test of getWordLength method, of class Book.
     *
     * @Test public void testGetWordLength() {
     * System.out.println("getWordLength");
     *
     * double expResult = 0.0; double result = book.getWordLength();
     * assertEquals(expResult, result, 0.0); // TODO review the generated test
     * code and remove the default call to fail. fail("The test case is a
     * prototype."); }
     */
}
