package AuthorshipAnalysis;

import java.util.HashMap;

public class AuthorMetrics {

    private String author = "";
  
    private Book book = new Book();

    /**
     *
     * @param bookAuthor
     */
    public void setBookAuthor(String bookAuthor) {
        book.setAuthor(bookAuthor);
    }

    /**
     *
     * @return bookTitile
     */
    public String getBookTitile() {
        return book.getBookTitile();
    }

    /**
     *
     * @param bookTitile
     */
    public void setBookTitile(String bookTitile) {
        book.setBookTitile(bookTitile);
    }

    /**
     *
     * @return bookAuthor
     */
    public String getAuthor() {
        return book.getAuthor();
    }

    /**
     *
     * @param value
     */
    public void setAuthor(String value) {
        this.book.setAuthor(value);
    }

    /**
     *
     * @return averageSentenceLength
     */
    public double getAvgSentenceLength() {
        return book.averageSentenceLength();
    }

    /**
     *
     * @param value
     */
    public void setAvgSentenceLength(double value) {
        book.setAvgSentenceLength(value);
    }

    /**
     *
     * @return ratioWordToSentenceLength
     */
    public double getWordToSentenceRatio() {
        return book.ratioWordToSentenceLength();
    }

    /**
     *
     * @param value
     */
    public void setWordToSentenceRatio(double value) {
        book.setWordToSentenceRatio(value);
    }

    /**
     *
     * @return distributionOfWordLengths
     */
    public HashMap<Integer, Double> getAvgWordLength() {
        return book.distributionOfWordLengths();
    }

    /**
     *
     * @param value
     */
    public void setAvgWordLength(HashMap<Integer, Double> value) {
        book.setAvgWordLength(value);
    }

    /**
     *
     * @return relativeLetterFrequency[]:Double
     */
    public double[] getLetterFreq() {
        return book.relativeLetterFrequency();
    }

    /**
     *
     * @param value
     */
    public void setLetterFreq(double[] value) {
        book.setLetterFreq(value);
    }

    /**
     *
     * @return relativeLetterPairFrequencies
     */
    public HashMap<String, Double> getLetterPairFreq() {
        return book.relativeLetterPairFrequencies();
    }

    /**
     *
     * @param value
     */
    public void setLetterPairFreq(HashMap<String, Double> value) {
        book.setLetterPairFreq(value);
    }

    /**
     *
     * @return vocabularyRichness()
     */
    public double getVocabRichness() {
        return book.vocabularyRichness();
    }

    /**
     *
     * @param value
     */
    public void setVocabRichness(double value) {
        book.setVocabRichness(value);
    }

    /**
     *
     * @return frequencyOfNounUsage()
     */
    public double getFreqNoun() {
        return book.frequencyOfNounUsage();
    }

    /**
     *
     * @param value
     */
    public void setFreqNoun(double value) {
      book.setFreqNoun(value);
    }

    /**
     *
     * @return frequencyOfVerbs()
     */
    public double getFreqVerb() {
        return book.frequencyOfVerbs();
    }

    /**
     *
     * @param value
     */
    public void setFreqVerb(double value) {
        book.setFreqVerb(value);
    }

    /**
     *
     * @return frequencyOfAdjectiveUsage
     */
    public double getFreqAdjective() {
        return book.frequencyOfAdjectiveUsage();
    }

    /**
     *
     * @param value
     */
    public void setFreqAdjective(double value) {
        book.setFreqAdjective(value);
    }

    /**
     *
     * @return ratioAdjectivesToNounUsage()
     */
    public double getAdjectiveToNounRatio() {
        return book.ratioAdjectivesToNounUsage();
    }

    /**
     *
     * @param value
     */
    public void setAdjectiveToNounRatio(double value) {
       book.setAdjectiveToNounRatio(value);
    }

    /**
     *
     * @return simpleLexicalDensity()
     */
    public double getLexicalDensity() {
        return book.simpleLexicalDensity();
    }

    /**
     *
     * @param value
     */
    public void setLexicalDensity(double value) {
      book.setLexicalDensity(value);
    }

    public double getWordLength() {
        return book.getWordLength();
    }

    public void setWordLength(double value) {
        book.setWordLength(value);
    }
}
