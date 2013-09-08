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

    private int id;
    private String bookAuthor;
    private String bookTitle;
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
    private double[] relativeLetterFrequency;
    private HashMap<String, Double> relativeLetterPairFrequencies;
    private HashMap<Integer, Double> distributionOfWordLengths;
    private HashMap<Integer, Double> wordLength;
    private StringTokenizer st;
    private GetTextFromFile textFile = new GetTextFromFile();

    Book(String booktext) {

        bookAuthor = null;
        bookTitle = null;
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
        bookTitle = title;
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
        freqVerb = frequencyOfVerbUsage();
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
     * @return  return averageSentenceLength :double
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
     * @return  return  ratioWordToSentenceLength :double
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
     * @return  return  frequencies :double[]
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

                    if (charValue >= 0 && charValue <= 26) {

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
     * @return  return  relativeLetterPairFrequencies :HashMap<String, Double>
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

                        if (relativeLetterPairFrequencies.containsKey(key.toLowerCase())) {

                            Double tempVal = relativeLetterPairFrequencies.get(key.toLowerCase());
                            tempVal++;

                            relativeLetterPairFrequencies.put(key.toLowerCase(), tempVal);
                            cumulativeSum++;
                        } else {
                            relativeLetterPairFrequencies.put(key.toLowerCase(), 1.0);
                            cumulativeSum++;
                        }
                    }
                }
            }

            for (String ketSet : relativeLetterPairFrequencies.keySet()) {

                Double num = relativeLetterPairFrequencies.get(ketSet);

                Double freq = num / cumulativeSum;

                relativeLetterPairFrequencies.put(ketSet, freq);
            }
        }
        return relativeLetterPairFrequencies;
    }

    /**
     * vocabularyRichness counts the number of words that appear once in the
     * text the divide that by the total number of word giving the vocabulary
     * richness for the text.
     *
     * @return  return  vocabRichness :double
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
     * @return  return  distributionOfWordLengths :HashMap<Integer, Double>
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
                            distributionOfWordLengths.get(word.length()) + 1.0);

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
     * @return  return  freqNoun :double
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
     * @return  return  freqVerb :double
     */
    public double frequencyOfVerbUsage() {

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
     * @return  return  freqAdjective :double
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
     * @return  return  adjectiveToNounRatio :double
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
     * @return  return  lexicalDensity :double
     */
    public double simpleLexicalDensity() {

        if (lexicalDensity != -1) {
            return this.lexicalDensity;
        } else {
            this.lexicalDensity = ((double)numNouns +(double) numVerbs +(double) numAdjectives) / this.totalWords;

        }
        return this.lexicalDensity;
    }

    // *******************Getters ************************* 
    /**
     * getBookTitle
     *
     * @return  return  bookTitle :String
     */
    public String getBookTitle() {
        return this.bookTitle;
    }

    /**
     * getAuthor
     *
     * @return  return  bookAuthor :String
     */
    public String getAuthor() {
        return this.bookAuthor;
    }

    /**
     * getWordLength
     *
     * @return  return  avgWordLength:double
     */
    public double getWordLength() {
        return this.avgWordLength;
    }

    /**
     * get the id
     *
     * @return  return  id
     */
    public int getId() {
        return this.id;
    }

    /**
     * setId
     *
     * @param aInt
     */
    void setId(int aInt) {
        this.id = aInt;
    }
}//end Book Class