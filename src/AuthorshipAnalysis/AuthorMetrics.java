package AuthorshipAnalysis;

import java.util.HashMap;

public class AuthorMetrics {

    private String author;
    private Double avgWordLength;
    private Double avgSentenceLength;
    private Double wordToSentenceRatio;
    private Double vocabRichness;
    private Double freqNoun;
    private Double freqVerb;
    private Double freqAdjective;
    private Double adjectiveToNounRatio;
    private Double lexicalDensity;
    private HashMap<Integer, Double> distributionOfWordLengths;
    private Double[] relativeLetterFreq;
    private HashMap<String, Double> relativeLetterPairFrequencies;

    /**
     * setBookAuthor
     *
     * @param author:String
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * getAuthor
     *
     * @return bookAuthor:String
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * getAvgSentenceLength
     *
     * @return averageSentenceLength :double
     */
    public Double getAvgSentenceLength() {
        return this.avgSentenceLength;
    }

    /**
     * setAvgSentenceLength
     *
     * @param value :double
     */
    public void setAvgSentenceLength(Double value) {
        this.avgSentenceLength = value;
    }

    /**
     * getWordToSentenceRatio
     *
     * @return ratioWordToSentenceLength :double
     */
    public Double getWordToSentenceRatio() {
        return this.wordToSentenceRatio;
    }

    /**
     * setWordToSentenceRatio
     *
     * @param value :double
     */
    public void setWordToSentenceRatio(Double value) {
        this.wordToSentenceRatio=value;
    }

    /**
     * getDistributionOfWordLengths
     *
     * @return distributionOfWordLengths:HashMap<Integer, Double>
     */
    public HashMap<Integer, Double> getDistributionOfWordLengths() {
        return this.distributionOfWordLengths;
    }

    /**
     * setDistributionOfWordLengths
     *
     * @param value:HashMap<Integer, Double>
     */
    public void setDistributionOfWordLengths(HashMap<Integer, Double> value) {
        this.distributionOfWordLengths=value;
    }

    /**
     * getRelativeLetterFreq
     *
     * @return relativeLetterFrequency[]:Double
     */
    public Double[] getRelativeLetterFreq() {
        return this.relativeLetterFreq;
    }

    /**
     * setRelativeLetterFreq
     *
     * @param value :double[]
     */
    public void setRelativeLetterFreq(Double[] value) {
        this.relativeLetterFreq = value;
    }

    /**
     * getRelativeLetterPairFrequencies
     *
     * @return relativeLetterPairFrequencies:HashMap<String, Double>
     */
    public HashMap<String, Double> getRelativeLetterPairFrequencies() {
        return this.relativeLetterPairFrequencies;
    }

    /**
     * setRelativeLetterPairFrequencies
     *
     * @param value:HashMap<String, Double>
     */
    public void setRelativeLetterPairFrequencies(HashMap<String, Double> value) {
        this.relativeLetterPairFrequencies=value;
    }

    /**
     * getVocabRichness
     *
     * @return vocabularyRichness() :double
     */
    public Double getVocabRichness() {
        return this.vocabRichness;
    }

    /**
     * setVocabRichness
     *
     * @param value :double
     */
    public void setVocabRichness(Double value) {
        this.vocabRichness=value;
    }

    /**
     * getFreqNoun
     *
     * @return frequencyOfNounUsage() :double
     */
    public Double getFreqNoun() {
        return this.freqNoun;
    }

    /**
     * setFreqNoun
     *
     * @param value :double
     */
    public void setFreqNoun(Double value) {
        this.freqNoun=value;
    }

    /**
     * getFreqVerb
     *
     * @return frequencyOfVerbs() :double
     */
    public Double getFreqVerb() {
        return this.freqVerb;
    }

    /**
     * setFreqVerb
     *
     * @param value :double
     */
    public void setFreqVerb(Double value) {
        this.freqVerb=value;
    }

    /**
     * getFreqAdjective
     *
     * @return frequencyOfAdjectiveUsage() :double
     */
    public Double getFreqAdjective() {
        return this.freqAdjective;
    }

    /**
     * setFreqAdjective
     *
     * @param value :double
     */
    public void setFreqAdjective(Double value) {
        this.freqAdjective=value;
    }

    /**
     * getAdjectiveToNounRatio
     *
     * @return ratioAdjectivesToNounUsage() :double
     */
    public Double getAdjectiveToNounRatio() {
        return this.adjectiveToNounRatio;
    }

    /**
     * setAdjectiveToNounRatio
     *
     * @param value :double
     */
    public void setAdjectiveToNounRatio(Double value) {
        this.adjectiveToNounRatio=value;
    }

    /**
     * getLexicalDensity
     *
     * @return simpleLexicalDensity() :double
     */
    public Double getLexicalDensity() {
        return this.lexicalDensity;
    }

    /**
     * setLexicalDensity
     *
     * @param value:double
     */
    public void setLexicalDensity(Double value) {
        this.lexicalDensity=value;
    }

    /**
     * getAvgWordLength
     *
     * @return avgWordLength:double
     */
    public Double getAvgWordLength() {
        return this.avgWordLength;
    }

    /**
     * setAvgWordLength
     *
     * @param value :double
     */
    public void setAvgWordLength(Double value) {
        this.avgWordLength=value;
    }
}
