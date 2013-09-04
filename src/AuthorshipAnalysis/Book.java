package AuthorshipAnalysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import rita.RiLexicon;

/**
 *
 * @author Matt Henry, Scott Dykstra
 * @version September 3, 2013
 *
 */
public final class Book {
    // need to work out a distance formula to do the metrics for comparing 
    // authors 

    private String bookAuthor;
    private String bookTitile;
    private String bookText;
    private int numNouns;
    private int numAdjectives;
    private int numVerbs;
    private int totalWords;
    private double avgWordLength = -1;
    private double averageSentenceLength = -1;
    private double ratioWordToSentenceLength = -1;
    private double vocabRichness = -1;
    private double freqNoun = -1;
    private double freqVerb = -1;
    private double freqAdjective = -1;
    private double adjectiveToNounRatio = -1;
    private double lexicalDensity = -1;
    private double[] relativeLetterFrequency = null;
    private HashMap<String, Double> relativeLetterPairFrequencies;
    private HashMap<Integer, Double> distributionOfWordLengths = null;
    private HashMap<Integer, Double> wordLength = null;
    private StringTokenizer st;
    private getTextFromFile textFile = new getTextFromFile();

    Book() {

        bookAuthor = null;
        bookTitile = null;
        bookText = null;
        numNouns = 0;
        numAdjectives = 0;
        numVerbs = 0;
        totalWords = 0;
        avgWordLength = -1;
        averageSentenceLength = -1;
        ratioWordToSentenceLength = -1;
        relativeLetterFrequency = null;
        relativeLetterPairFrequencies = null;
        distributionOfWordLengths = null;
        vocabRichness = -1;
        freqNoun = -1;
        freqVerb = -1;
        freqAdjective = -1;
        adjectiveToNounRatio = -1;
        lexicalDensity = -1;
        wordLength = null;
    }

    Book(String booktext) {

        bookAuthor = null;
        bookTitile = null;
        bookText = booktext;
        numNouns = 0;
        numAdjectives = 0;
        numVerbs = 0;
        totalWords = 0;
        avgWordLength = -1;
        averageSentenceLength = -1;
        ratioWordToSentenceLength = -1;
        relativeLetterFrequency = null;
        relativeLetterPairFrequencies = null;
        distributionOfWordLengths = null;
        vocabRichness = -1;
        freqNoun = -1;
        freqVerb = -1;
        freqAdjective = -1;
        adjectiveToNounRatio = -1;
        lexicalDensity = -1;
        wordLength = null;
    }

    Book(String author, String title, String fileName) throws FileNotFoundException, IOException {
        bookAuthor = author;
        bookTitile = title;
        bookText = textFile.getString(fileName);
        numNouns = 0;
        numAdjectives = 0;
        numVerbs = 0;
        totalWords = 0;
        avgWordLength = averageWordLength();
        averageSentenceLength = averageSentenceLength();
        ratioWordToSentenceLength = ratioWordToSentenceLength();
        relativeLetterFrequency = null;
        relativeLetterPairFrequencies = relativeLetterPairFrequencies();
        distributionOfWordLengths = distributionOfWordLengths();
        vocabRichness = vocabularyRichness();
        freqNoun = frequencyOfNounUsage();
        freqVerb = frequencyOfVerbs();
        freqAdjective = frequencyOfAdjectiveUsage();
        adjectiveToNounRatio = ratioAdjectivesToNounUsage();
        lexicalDensity = simpleLexicalDensity();
        wordLength = distributionOfWordLengths();
    }

    /**
     * averageWordLength gets average word length form bookText if that value is
     * not already set
     *
     * @return avgWordLength :double
     */
    public double averageWordLength() {

        if (avgWordLength != -1) {
            return avgWordLength;
        } else {

            double cumulativeSum = 0;
            double wordTotal = 0;

            st = new StringTokenizer(bookText);

            while (st.hasMoreTokens()) {

                String word = st.nextToken();

                cumulativeSum += word.length();

                wordTotal++;
            }

            this.avgWordLength = cumulativeSum / wordTotal;

            return this.avgWordLength;

        }
    }

    /**
     * averageSentenceLength gets average sentence length from bookText if that
     * value is not already set
     *
     * @return averageSentenceLength :double
     */
    public double averageSentenceLength() {

        if (averageSentenceLength != -1) {
            return this.averageSentenceLength;
        } else {

            BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);

            iterator.setText(bookText);
            int start = iterator.first();

            double sentenceTotal = 0;
            double cumulativeSum = 0;

            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {

                String sentence = bookText.substring(start, end);

                cumulativeSum += sentence.length();

                sentenceTotal++;

            }

            this.averageSentenceLength = cumulativeSum / sentenceTotal;

            return this.averageSentenceLength;

        }
    }

    /**
     * ratioWordToSentenceLength uses averageWordLength() divided by
     * averageSentenceLength() to calculate the ratio of words to number of
     * sentence length
     *
     * @return ratioWordToSentenceLength :double
     */
    public double ratioWordToSentenceLength() {

        if (ratioWordToSentenceLength != -1) {
            return this.ratioWordToSentenceLength;
        } else {
            ratioWordToSentenceLength = averageWordLength() / averageSentenceLength();
            return this.ratioWordToSentenceLength;
        }

    }

    /**
     * relativeLetterFrequency() looks for letter values and counts the number
     * of time each letter is used in the text and returns that letters average
     * compared to the total number of letters used.
     *
     * @return frequencies :double[]
     */
    public double[] relativeLetterFrequency() {

        double[] frequencies;

        if (relativeLetterFrequency != null) {
            return this.relativeLetterFrequency;
        } else {

            double cumulativeSum = 0;
            frequencies = new double[26];

            for (int i = 0; i < frequencies.length; i++) {
                frequencies[i] = 0;
            }

            st = new StringTokenizer(bookText);

            while (st.hasMoreTokens()) {

                String word = st.nextToken();

                for (int i = 0; i < word.length(); i++) {

                    int charValue = Character.getNumericValue(word.charAt(i)) - 10;

                    if (charValue >= 10 && charValue <= 35) {

                        frequencies[charValue]++;
                        cumulativeSum++;
                    }
                }
            }

            for (int i = 0; i < frequencies.length; i++) {
                frequencies[i] = frequencies[i] / cumulativeSum;
            }

        }

        return frequencies;
    }

    /**
     * relativeLetterPairFrequencies look through the text string and adds
     * letter pairs to a HashMap then counts the number of times that letter
     * pair appears in the text string
     *
     * @return relativeLetterPairFrequencies :HashMap<String, Double>
     */
    public HashMap<String, Double> relativeLetterPairFrequencies() {

        if (relativeLetterPairFrequencies != null) {
            return this.relativeLetterPairFrequencies;
        } else {

            relativeLetterPairFrequencies = new HashMap<>();

            double cumulativeSum = 0;

            st = new StringTokenizer(bookText);

            while (st.hasMoreTokens()) {

                String word = st.nextToken();

                for (int i = 0; i < word.length() - 1; i++) {

                    int firstCharValue = Character.getNumericValue(word.charAt(i));
                    int secondCharValue = Character.getNumericValue(word.charAt(i + 1));

                    if ((firstCharValue >= 10 && firstCharValue <= 35)
                            && (secondCharValue >= 10 && secondCharValue <= 35)) {

                        String key = word.substring(i, i + 2);

                        if (relativeLetterPairFrequencies.containsKey(key)) {

                            Double tempVal = relativeLetterPairFrequencies.get(key);
                            tempVal++;

                            relativeLetterPairFrequencies.put(key, tempVal);
                            cumulativeSum++;
                        } else {
                            relativeLetterPairFrequencies.put(key, 1.0);
                            cumulativeSum++;
                        }
                    }
                }
            }

            for (String key : relativeLetterPairFrequencies.keySet()) {

                Double num = relativeLetterPairFrequencies.get(key);

                Double freq = num / cumulativeSum;

                relativeLetterPairFrequencies.put(key, freq);
            }
        }
        return relativeLetterPairFrequencies;
    }

    /**
     * vocabularyRichness counts the number of words that appear once in the
     * text the divide that by the total number of word giving the vocabulary
     * richness for the text.
     *
     * @return vocabRichness :double
     */
    public double vocabularyRichness() {

        HashMap<String, Integer> words = new HashMap<>();
        st = new StringTokenizer(bookText);
        int total = 0;

        if (vocabRichness != -1) {
            return this.vocabRichness;
        } else {

            while (st.hasMoreTokens()) {

                String word = st.nextToken();
                total++;

                if (words.containsKey(word)) {

                    int val = words.get(word);

                    val++;

                    words.put(word, val);

                } else {

                    words.put(word, 1);

                }

            }

            int uniqueWords = 0;

            for (String key : words.keySet()) {

                if (words.get(key) == 1) {

                    uniqueWords++;
                }
            }
            this.vocabRichness = (double) uniqueWords / (double) total;
        }
        return this.vocabRichness;
    }

    // Lower Metrics 
    /**
     * distributionOfWordLengths puts word into HashMap and counts the number of
     * time each word appears in the text string then divides by the total
     * number of words
     *
     * @return distributionOfWordLengths :HashMap<Integer, Double>
     */
    public HashMap<Integer, Double> distributionOfWordLengths() {

        st = new StringTokenizer(bookText);

        if (distributionOfWordLengths != null) {
            return this.distributionOfWordLengths;
        } else {

            totalWords = 0;

            distributionOfWordLengths = new HashMap<>();

            while (st.hasMoreTokens()) {

                totalWords++;

                String word = st.nextToken();

                if (distributionOfWordLengths.containsKey(word.length())) {

                    distributionOfWordLengths.put(word.length(),
                            distributionOfWordLengths.get(word.length()) + 1);

                } else {

                    distributionOfWordLengths.put(word.length(), 1.0);

                }

            }

            for (Integer key : distributionOfWordLengths.keySet()) {

                double val = distributionOfWordLengths.get(key);

                double dist = val / totalWords;

                distributionOfWordLengths.put(key, dist);
            }

        }

        return this.distributionOfWordLengths;
    }

    /**
     * frequencyOfNounUsage uses a tool to look for nouns from the text string
     * and compare them against a dictionary. Then counts the number of nouns
     * and the number of total words then finds the average of nouns to total
     * words.
     *
     * @return freqNoun :double
     */
    public double frequencyOfNounUsage() {

        if (freqNoun != -1) {
            return this.freqNoun;
        } else {

            RiLexicon r = new RiLexicon();

            st = new StringTokenizer(bookText);

            totalWords = 0;

            while (st.hasMoreTokens()) {

                totalWords++;

                String word = st.nextToken();

                if (r.isNoun(word)) {

                    numNouns++;

                }

            }

            this.freqNoun = (double) numNouns / (double) totalWords;

            return this.freqNoun;

        }
    }

    /**
     * frequencyOfVerbs uses a tool to look for verbs from the text string and
     * compare them against a dictionary. Then counts the number of verbs and
     * the number of total words then finds the average of verbs to total words.
     *
     * @return freqVerb :double
     */
    public double frequencyOfVerbs() {

        if (freqVerb != -1) {
            return this.freqVerb;
        } else {

            RiLexicon r = new RiLexicon();

            st = new StringTokenizer(bookText);

            totalWords = 0;

            while (st.hasMoreTokens()) {

                totalWords++;

                String word = st.nextToken();

                if (r.isVerb(word)) {

                    numVerbs++;
                }
            }

            this.freqVerb = (double) numVerbs / (double) totalWords;

            return this.freqVerb;

        }
    }

    /**
     * frequencyOfAdjectiveUsage uses a tool to look for adjective from the text
     * string and compare them against a dictionary. Then counts the number of
     * adjective and the number of total words then finds the average of
     * adjective to total words.
     *
     * @return freqAdjective :double
     */
    public double frequencyOfAdjectiveUsage() {

        if (freqAdjective != -1) {
            return this.freqAdjective;
        } else {

            RiLexicon r = new RiLexicon();

            st = new StringTokenizer(bookText);

            totalWords = 0;

            while (st.hasMoreTokens()) {

                totalWords++;

                String word = st.nextToken();

                if (r.isAdjective(word)) {

                    numAdjectives++;
                }
            }

            this.freqAdjective = (double) numAdjectives / (double) totalWords;

            return this.freqAdjective;

        }
    }

    /**
     * ratioAdjectivesToNounUsage finds the average adjectives to noun ratio.
     *
     * @return adjectiveToNounRatio :double
     */
    public double ratioAdjectivesToNounUsage() {

        if (adjectiveToNounRatio != -1) {
            return this.adjectiveToNounRatio;
        } else {
            if (numNouns != 0) {
                this.adjectiveToNounRatio = numAdjectives / numNouns;
            } else {
                this.adjectiveToNounRatio = 0;
            }
        }
        return this.adjectiveToNounRatio;
    }

    /**
     * simpleLexicalDensity takes the values form the total number of nouns,
     * verbs and adjectives. Then divides that by the total number of words.
     *
     * @return lexicalDensity :double
     */
    public double simpleLexicalDensity() {

        if (lexicalDensity != -1) {
            return this.lexicalDensity;
        } else {

            this.lexicalDensity = (numNouns + numVerbs + numAdjectives)
                    / totalWords;

        }
        return this.lexicalDensity;
    }

    // *******************Getters and Setters************************* 
    /**
     * setBookTitile
     *
     * @param bookTitile :String
     */
    public void setBookTitile(String bookTitile) {
        this.bookTitile = bookTitile;
    }

    /**
     * getBookTitile
     *
     * @return bookTitile :String
     */
    public String getBookTitile() {
        return this.bookTitile;
    }

    /**
     * getAuthor
     *
     * @return bookAuthor :String
     */
    public String getAuthor() {
        return this.bookAuthor;
    }

    /**
     * setBookAuthor
     *
     * @param bookAuthor:String
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * setAuthor
     *
     * @param value:String
     */
    public void setAuthor(String value) {
        this.bookAuthor = value;
    }

    /**
     * setAvgSentenceLength
     *
     * @param value:double
     */
    public void setAvgSentenceLength(double value) {
        this.averageSentenceLength = value;
    }

    /**
     * setWordToSentenceRatio
     *
     * @param value :double
     */
    public void setWordToSentenceRatio(double value) {
        this.ratioWordToSentenceLength = value;
    }

    /**
     * setAvgWordLength
     *
     * @param value :HashMap<Integer, Double>
     */
    public void setAvgWordLength(HashMap<Integer, Double> value) {
        this.distributionOfWordLengths = value;
    }

    /**
     * setLetterFreq
     *
     * @param value:double[]
     */
    public void setLetterFreq(double[] value) {
        this.relativeLetterFrequency = value;
    }

    /**
     * setLetterPairFreq
     *
     * @param value :HashMap<String, Double>
     */
    public void setLetterPairFreq(HashMap<String, Double> value) {
        this.relativeLetterPairFrequencies = value;
    }

    /**
     * setVocabRichness
     *
     * @param value:double
     */
    public void setVocabRichness(double value) {
        this.vocabRichness = value;
    }

    /**
     * setFreqNoun
     *
     * @param value:double
     */
    public void setFreqNoun(double value) {
        this.freqNoun = value;
    }

    /**
     * setFreqVerb
     *
     * @param value:double
     */
    public void setFreqVerb(double value) {
        this.freqVerb = value;
    }

    /**
     * setFreqAdjective
     *
     * @param value:double
     */
    public void setFreqAdjective(double value) {
        this.freqAdjective = value;
    }

    /**
     * setAdjectiveToNounRatio
     *
     * @param value:double
     */
    public void setAdjectiveToNounRatio(double value) {
        this.adjectiveToNounRatio = value;
    }

    /**
     * setLexicalDensity
     *
     * @param value:double
     */
    public void setLexicalDensity(double value) {
        this.lexicalDensity = value;
    }

    /**
     * getWordLength
     *
     * @return avgWordLength:double
     */
    public double getWordLength() {
        return this.avgWordLength;
    }

    /**
     * setWordLength
     *
     * @param value :double
     */
    public void setWordLength(double value) {
        this.avgWordLength = value;
    }
}//end Book Class