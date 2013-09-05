/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthorshipAnalysis;

import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Scott
 */
public class BookTest {

    public BookTest() {
    }

  
    public static  String authorName ="Arthur C. Clarke";
      public static   String bookTitle = "Cradle";
     public static    String bookText = "The emerald water smashes against the dark volcanic "
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
        
    public static   Book book = new Book(bookText);
        
 

 

    @Before
    public void setUp() {
    }

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
     * Test of relativeLetterFrequency method, of class Book.
   // need to think on this test can't test double array///////////////////////////////////
    @Test
    public void testRelativeLetterFrequency() {
        System.out.println("relativeLetterFrequency");
      Book instance = new Book("a");
     
        double[] expResult = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double[] result = book.relativeLetterFrequency();
        assertArrayEquals(expResult, result);
  
    }
  */
    /**
     * Test of relativeLetterPairFrequencies method, of class Book.
     */
    @Test
    public void testRelativeLetterPairFrequencies() {
        System.out.println("relativeLetterPairFrequencies");
      
        int expResult = 167;
        HashMap result = book.relativeLetterPairFrequencies();
        assertEquals(expResult, result.size());
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
       
        double expResult = 12.0;
        HashMap result = book.distributionOfWordLengths();
        assertEquals(expResult, result.size(),0.0);
   
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
    
    @Test
    public void testSimpleLexicalDensity() {
        System.out.println("simpleLexicalDensity");
       
        double expResult = 0.0;
        double result = book.simpleLexicalDensity();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setBookTitile method, of class Book.
    
    @Test
    public void testSetBookTitile() {
        System.out.println("setBookTitile");
        String bookTitile = "";
     
        book.setBookTitile(bookTitile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of getBookTitile method, of class Book.
    
    @Test
    public void testGetBookTitile() {
        System.out.println("getBookTitile");
       
        String expResult = "";
        String result = book.getBookTitile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of getAuthor method, of class Book.
   
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
       
        String expResult = "";
        String result = book.getAuthor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  */
    /**
     * Test of setBookAuthor method, of class Book.
   
    @Test
    public void testSetBookAuthor() {
        System.out.println("setBookAuthor");
        String bookAuthor = "";
      
        book.setBookAuthor(bookAuthor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  */
    /**
     * Test of setAuthor method, of class Book.
   
    @Test
    public void testSetAuthor() {
        System.out.println("setAuthor");
        String value = "";
      
        book.setAuthor(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  */
    /**
     * Test of setAvgSentenceLength method, of class Book.
    
    @Test
    public void testSetAvgSentenceLength() {
        System.out.println("setAvgSentenceLength");
        double value = 0.0;
     
        book.setAvgSentenceLength(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setWordToSentenceRatio method, of class Book.
   
    @Test
    public void testSetWordToSentenceRatio() {
        System.out.println("setWordToSentenceRatio");
        double value = 0.0;
      
        book.setWordToSentenceRatio(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  */
    /**
     * Test of setAvgWordLength method, of class Book.
    
    @Test
    public void testSetAvgWordLength() {
        System.out.println("setAvgWordLength");
        HashMap<Integer, Double> value = null;
      
        book.setAvgWordLength(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setLetterFreq method, of class Book.
    
    @Test
    public void testSetLetterFreq() {
        System.out.println("setLetterFreq");
        double[] value = null;
      
        book.setLetterFreq(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setLetterPairFreq method, of class Book.
    
    @Test
    public void testSetLetterPairFreq() {
        System.out.println("setLetterPairFreq");
        HashMap<String, Double> value = null;
       
        book.setLetterPairFreq(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setVocabRichness method, of class Book.
    
    @Test
    public void testSetVocabRichness() {
        System.out.println("setVocabRichness");
        double value = 0.0;
       
        book.setVocabRichness(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setFreqNoun method, of class Book.
    
    @Test
    public void testSetFreqNoun() {
        System.out.println("setFreqNoun");
        double value = 0.0;
       
        book.setFreqNoun(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setFreqVerb method, of class Book.
    
    @Test
    public void testSetFreqVerb() {
        System.out.println("setFreqVerb");
        double value = 0.0;
       
        book.setFreqVerb(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setFreqAdjective method, of class Book.
    
    @Test
    public void testSetFreqAdjective() {
        System.out.println("setFreqAdjective");
        double value = 0.0;
      
        book.setFreqAdjective(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setAdjectiveToNounRatio method, of class Book.
    
    @Test
    public void testSetAdjectiveToNounRatio() {
        System.out.println("setAdjectiveToNounRatio");
        double value = 0.0;
       
        book.setAdjectiveToNounRatio(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 */
    /**
     * Test of setLexicalDensity method, of class Book.
     
    @Test
    public void testSetLexicalDensity() {
        System.out.println("setLexicalDensity");
        double value = 0.0;
      
        book.setLexicalDensity(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of getWordLength method, of class Book.
     
    @Test
    public void testGetWordLength() {
        System.out.println("getWordLength");
       
        double expResult = 0.0;
        double result = book.getWordLength();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of setWordLength method, of class Book.
    
    @Test
    public void testSetWordLength() {
        System.out.println("setWordLength");
        double value = 0.0;
       
        book.setWordLength(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
