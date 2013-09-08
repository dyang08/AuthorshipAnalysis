package AuthorshipAnalysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final String bookText;
    private double numNouns;
    private double numAdjectives;
    private double numVerbs;
    private double totalWords;
    private double avgWordLength = -1;
    private double averageSentenceLength = -1;
    private double ratioWordToSentenceLength = -1;
    private double vocabRichness = -1;
    private double freqNoun = -1;
    private double freqVerb = -1;
    private double freqAdjective = -1;
    private double adjectiveToNounRatio = -1;
    private double lexicalDensity = -1;
    private final double[] relativeLetterFrequency;
    private HashMap<String, Double> relativeLetterPairFrequencies;
    private HashMap<Integer, Double> distributionOfWordLengths;
    private final HashMap<Integer, Double> wordLength;
    private StringTokenizer st;
    private final GetTextFromFile textFile = new GetTextFromFile();

    Book(String booktext) {

        bookAuthor = null;
        bookTitle = null;
        bookText = booktext;
        numNouns = 0;
        numAdjectives = 0;
        numVerbs = 0;
        totalWords = 0;
        avgWordLength = averageWordLength();
        averageSentenceLength = averageSentenceLength();
        ratioWordToSentenceLength = ratioWordToSentenceLength();
        relativeLetterFrequency = relativeLetterFrequency();
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
        relativeLetterFrequency = relativeLetterFrequency();
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

            return convertValue(this.avgWordLength);

        }
    }

    /**
     * averageSentenceLength gets average sentence length from bookText if that
     * value is not already set
     *
     * @return return averageSentenceLength :double
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

            return convertValue(this.averageSentenceLength);

        }
    }

    /**
     * ratioWordToSentenceLength uses averageWordLength() divided by
     * averageSentenceLength() to calculate the ratio of words to number of
     * sentence length
     *
     * @return return ratioWordToSentenceLength :double
     */
    public double ratioWordToSentenceLength() {

        if (ratioWordToSentenceLength != -1) {
            return convertValue(this.ratioWordToSentenceLength);
        } else {
            ratioWordToSentenceLength = averageWordLength() / averageSentenceLength();
            return convertValue(this.ratioWordToSentenceLength);
        }

    }

    /**
     * relativeLetterFrequency() looks for letter values and counts the number
     * of time each letter is used in the text and returns that letters average
     * compared to the total number of letters used.
     *
     * @return return frequencies :double[]
     */
    public double[] relativeLetterFrequency() {
        double cumulativeSum = 0;
        double[] frequencies;

        if (relativeLetterFrequency != null) {
            return this.relativeLetterFrequency;
        } else {

            frequencies = new double[26];

            for (int i = 0; i < frequencies.length; i++) {
                frequencies[i] = 0;
            }

            st = new StringTokenizer(bookText);

            while (st.hasMoreTokens()) {

                String word = st.nextToken(" ");
                cumulativeSum += word.length();
                for (int i = 0; i < word.length(); i++) {

                    int charValue = Character.getNumericValue(word.charAt(i)) - 10;

                    if (charValue >= 0 && charValue <= 26) {

                        frequencies[charValue] = frequencies[charValue] + 1;

                    }
                }
            }

            for (int i = 0; i < frequencies.length; i++) {
                frequencies[i] = convertValue(frequencies[i] / cumulativeSum);
            }

        }

        return frequencies;
    }

    /**
     * relativeLetterPairFrequencies look through the text string and adds
     * letter pairs to a HashMap then counts the number of times that letter
     * pair appears in the text string
     *
     * @return return relativeLetterPairFrequencies :HashMap<String, Double>
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
            HashMap<String, Double> temp = new HashMap<>();
            for (String keySet : relativeLetterPairFrequencies.keySet()) {

                Double num = relativeLetterPairFrequencies.get(keySet);

                Double freq = convertValue(num / cumulativeSum);
                if (freq > 0) {
                    temp.put(keySet, freq);
                } 
            }
            relativeLetterPairFrequencies = temp;
        }
//        return sortHashMapByValuesD(relativeLetterPairFrequencies);
        return relativeLetterPairFrequencies;
    }

    /**
     * vocabularyRichness counts the number of words that appear once in the
     * text the divide that by the total number of word giving the vocabulary
     * richness for the text.
     *
     * @return return vocabRichness :double
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
        return convertValue(this.vocabRichness);
    }

    // Lower Metrics 
    /**
     * distributionOfWordLengths puts word into HashMap and counts the number of
     * time each word appears in the text string then divides by the total
     * number of words
     *
     * @return return distributionOfWordLengths :HashMap<Integer, Double>
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
            HashMap<Integer, Double> temp = new HashMap<>();
            for (Integer key : distributionOfWordLengths.keySet()) {

                double val = distributionOfWordLengths.get(key);

                double dist = convertValue(val / totalWords);
                if (dist > 0) {
                    temp.put(key, dist);
                } 
            }
            distributionOfWordLengths = temp;
        }

//        return sortHashMapByValuesD(this.distributionOfWordLengths);
        return this.distributionOfWordLengths;
    }

    /**
     * frequencyOfNounUsage uses a tool to look for nouns from the text string
     * and compare them against a dictionary. Then counts the number of nouns
     * and the number of total words then finds the average of nouns to total
     * words.
     *
     * @return return freqNoun :double
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

            return convertValue(this.freqNoun);

        }
    }

    /**
     * frequencyOfVerbs uses a tool to look for verbs from the text string and
     * compare them against a dictionary. Then counts the number of verbs and
     * the number of total words then finds the average of verbs to total words.
     *
     * @return return freqVerb :double
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

            return convertValue(this.freqVerb);

        }
    }

    /**
     * frequencyOfAdjectiveUsage uses a tool to look for adjective from the text
     * string and compare them against a dictionary. Then counts the number of
     * adjective and the number of total words then finds the average of
     * adjective to total words.
     *
     * @return return freqAdjective :double
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

            return convertValue(this.freqAdjective);

        }
    }

    /**
     * ratioAdjectivesToNounUsage finds the average adjectives to noun ratio.
     *
     * @return return adjectiveToNounRatio :double
     */
    public double ratioAdjectivesToNounUsage() {

        if (adjectiveToNounRatio != -1) {
            return this.adjectiveToNounRatio;
        } else {
            if (numNouns != 0) {
                this.adjectiveToNounRatio = (numAdjectives / numNouns);
            } else {
                this.adjectiveToNounRatio = 0;
            }
        }
        return convertValue(this.adjectiveToNounRatio);
    }

    /**
     * simpleLexicalDensity takes the values form the total number of nouns,
     * verbs and adjectives. Then divides that by the total number of words.
     *
     * @return return lexicalDensity :double
     */
    public double simpleLexicalDensity() {

        if (lexicalDensity != -1) {
            return this.lexicalDensity;
        } else {
            this.lexicalDensity = ((double) numNouns + (double) numVerbs + (double) numAdjectives) / this.totalWords;

        }
        return convertValue(this.lexicalDensity);
    }

    // *******************Getters and Setters************************* 
    /**
     * getBookTitle
     *
     * @return return bookTitle :String
     */
    public String getBookTitle() {
        return this.bookTitle;
    }

    /**
     * getAuthor
     *
     * @return return bookAuthor :String
     */
    public String getAuthor() {
        return this.bookAuthor;
    }

    /**
     * Set the title
     *
     * @param title
     */
    public void setBookTitle(String title) {
        this.bookTitle = title;
    }

    /**
     * Set the author
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.bookAuthor = author;
    }

    /**
     * getWordLength
     *
     * @return return avgWordLength:double
     */
    public double getWordLength() {
        return this.avgWordLength;
    }

    /**
     * get the id
     *
     * @return return id
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

    //This is of possible use. It can sort the hashmaps by value
//    public HashMap sortHashMapByValuesD(HashMap passedMap) {
//        List mapKeys = new ArrayList(passedMap.keySet());
//        List mapValues = new ArrayList(passedMap.values());
//        Collections.sort(mapValues);
//        Collections.sort(mapKeys);
//
//        HashMap sortedMap
//                = new LinkedHashMap();
//
//        Iterator valueIt = mapValues.iterator();
//        while (valueIt.hasNext()) {
//            Object val = valueIt.next();
//            Iterator keyIt = mapKeys.iterator();
//
//            while (keyIt.hasNext()) {
//                Object key = keyIt.next();
//                String comp1 = passedMap.get(key).toString();
//                String comp2 = val.toString();
//
//                if (comp1.equals(comp2)) {
//                    passedMap.remove(key);
//                    mapKeys.remove(key);
//                    sortedMap.put(key, (Double) val);
//                    break;
//                }
//
//            }
//
//        }
//        return sortedMap;
//    }
}//end Book Class
