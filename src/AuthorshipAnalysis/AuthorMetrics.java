package AuthorshipAnalysis;

import java.util.HashMap;

public class AuthorMetrics {

    private String author = "";
    private Book book = new Book();

    /**
     * setBookAuthor
     *
     * @param bookAuthor:String
     */
    public void setBookAuthor(String bookAuthor) {
        book.setAuthor(bookAuthor);
    }

    /**
     * getBookTitile
     *
     * @return bookTitile:String
     */
    public String getBookTitile() {
        return book.getBookTitile();
    }

    /**
     * setBookTitile
     *
     * @param bookTitile:String
     */
    public void setBookTitile(String bookTitile) {
        book.setBookTitile(bookTitile);
    }

    /**
     * getAuthor
     *
     * @return bookAuthor:String
     */
    public String getAuthor() {
        return book.getAuthor();
    }

    /**
     * setAuthor
     *
     * @param value:String
     */
    public void setAuthor(String value) {
        this.book.setAuthor(value);
    }

    /**
     * getAvgSentenceLength
     *
     * @return averageSentenceLength :double
     */
    public double getAvgSentenceLength() {
        return book.averageSentenceLength();
    }

    /**
     * setAvgSentenceLength
     *
     * @param value :double
     */
    public void setAvgSentenceLength(double value) {
        book.setAvgSentenceLength(value);
    }

    /**
     * getWordToSentenceRatio
     *
     * @return ratioWordToSentenceLength :double
     */
    public double getWordToSentenceRatio() {
        return book.ratioWordToSentenceLength();
    }

    /**
     * setWordToSentenceRatio
     *
     * @param value :double
     */
    public void setWordToSentenceRatio(double value) {
        book.setWordToSentenceRatio(value);
    }

    /**
     * getAvgWordLength
     *
     * @return distributionOfWordLengths:HashMap<Integer, Double>
     */
    public HashMap<Integer, Double> getAvgWordLength() {
        return book.distributionOfWordLengths();
    }

    /**
     * setAvgWordLength
     *
     * @param value:HashMap<Integer, Double>
     */
    public void setAvgWordLength(HashMap<Integer, Double> value) {
        book.setAvgWordLength(value);
    }

    /**
     * getLetterFreq
     *
     * @return relativeLetterFrequency[]:Double
     */
    public double[] getLetterFreq() {
        return book.relativeLetterFrequency();
    }

    /**
     * setLetterFreq
     *
     * @param value :double[]
     */
    public void setLetterFreq(double[] value) {
        book.setLetterFreq(value);
    }

    /**
     * getLetterPairFreq
     *
     * @return relativeLetterPairFrequencies:HashMap<String, Double>
     */
    public HashMap<String, Double> getLetterPairFreq() {
        return book.relativeLetterPairFrequencies();
    }

    /**
     * setLetterPairFreq
     *
     * @param value:HashMap<String, Double>
     */
    public void setLetterPairFreq(HashMap<String, Double> value) {
        book.setLetterPairFreq(value);
    }

    /**
     * getVocabRichness
     *
     * @return vocabularyRichness() :double
     */
    public double getVocabRichness() {
        return book.vocabularyRichness();
    }

    /**
     * setVocabRichness
     *
     * @param value :double
     */
    public void setVocabRichness(double value) {
        book.setVocabRichness(value);
    }

    /**
     * getFreqNoun
     *
     * @return frequencyOfNounUsage() :double
     */
    public double getFreqNoun() {
        return book.frequencyOfNounUsage();
    }

    /**
     * setFreqNoun
     *
     * @param value :double
     */
    public void setFreqNoun(double value) {
        book.setFreqNoun(value);
    }

    /**
     * getFreqVerb
     *
     * @return frequencyOfVerbs() :double
     */
    public double getFreqVerb() {
        return book.frequencyOfVerbs();
    }

    /**
     * setFreqVerb
     *
     * @param value :double
     */
    public void setFreqVerb(double value) {
        book.setFreqVerb(value);
    }

    /**
     * getFreqAdjective
     *
     * @return frequencyOfAdjectiveUsage() :double
     */
    public double getFreqAdjective() {
        return book.frequencyOfAdjectiveUsage();
    }

    /**
     * setFreqAdjective
     *
     * @param value :double
     */
    public void setFreqAdjective(double value) {
        book.setFreqAdjective(value);
    }

    /**
     * getAdjectiveToNounRatio
     *
     * @return ratioAdjectivesToNounUsage() :double
     */
    public double getAdjectiveToNounRatio() {
        return book.ratioAdjectivesToNounUsage();
    }

    /**
     * setAdjectiveToNounRatio
     *
     * @param value :double
     */
    public void setAdjectiveToNounRatio(double value) {
        book.setAdjectiveToNounRatio(value);
    }

    /**
     * getLexicalDensity
     *
     * @return simpleLexicalDensity() :double
     */
    public double getLexicalDensity() {
        return book.simpleLexicalDensity();
    }

    /**
     * setLexicalDensity
     *
     * @param value:double
     */
    public void setLexicalDensity(double value) {
        book.setLexicalDensity(value);
    }

    /**
     * getWordLength
     *
     * @return avgWordLength:double
     */
    public double getWordLength() {
        return book.getWordLength();
    }

    /**
     * setWordLength
     *
     * @param value :double
     */
    public void setWordLength(double value) {
        book.setWordLength(value);
    }
}
