package AuthorshipAnalysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        this.avgSentenceLength = convertValue(value);
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
        this.wordToSentenceRatio = convertValue(value);
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
        HashMap<Integer, Double> temp = new HashMap<>();
        for (Integer length : value.keySet()) {
            double dist = convertValue(value.get(length));
            if (dist > 0) {
                temp.put(length, dist);
            } 
        }
        
        this.distributionOfWordLengths = temp;
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
        for (int i = 0; i < value.length; i++) {
            Double double1 = value[i];
            if(double1 !=null){
            value[i] = convertValue(double1);
            }
        }
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
        HashMap<String, Double> temp = new HashMap<>();
        for (String pair : value.keySet()) {
            double freq = convertValue(value.get(pair));
            if (freq > 0) {
                temp.put(pair, freq);
            } 
        }
        this.relativeLetterPairFrequencies = temp;
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
        this.vocabRichness = convertValue(value);
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
        this.freqNoun = convertValue(value);
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
        this.freqVerb = convertValue(value);
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
        this.freqAdjective = convertValue(value);
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
        this.adjectiveToNounRatio = convertValue(value);
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
        this.lexicalDensity = convertValue(value);
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
        this.avgWordLength = convertValue(value);
    }

    /**
     * Converts a double to value of two decimal places
     *
     * @param value original double value
     * @return converted double value
     */
    private Double convertValue(Double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.multiply(BigDecimal.valueOf(100));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        bd = bd.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        value = bd.doubleValue();
        return value;
    }
}
