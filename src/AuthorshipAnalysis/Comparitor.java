package AuthorshipAnalysis;

import java.util.ArrayList;
import java.util.List;

/*
 * This class compares all authors in the database to a book that is 
 * being passed in. The variable results is an arrayList of Result objects
 * that is sorted based on their overall score. The result in the 0 index
 * is the author with the lowest distance from the book being compared.
 */
public class Comparitor {

    private final SqlConnection sql;
    private final Book newBook;
    private final List<AuthorMetrics> authorList;
    private ArrayList<Results> results;

    /*
     * Constructor initializes the sql connection and the new book to 
     * be compared, then sets the authorList.
     */
    public Comparitor(Book b) {
        sql = new SqlConnection();
        authorList = new ArrayList<>();
        results = new ArrayList<>();
        this.newBook = b;
        getAuthorsAndMetrics();
    }

    public ArrayList<Results> getResults() {
        return this.results;
    }
    /*
     * Gets all the author metrics for the authors in the database, and adds
     * them to a list.
     */

    private void getAuthorsAndMetrics() {
        List<String> authors = sql.getListOfAuthors();
        for (int i = 0; i < authors.size(); i++) {
            String s = authors.get(i);
            AuthorMetrics am = sql.getAuthorMetrics(s);
            if (am != null) {
                authorList.add(am);
            }
        }
    }

    /*
     * Compare all the authors in the authorList List to the new book and sets
     * results to the proper arraylist that is sorted by score.
     */
    public void compareMetrics() {
        ArrayList<String> keyList = new ArrayList<>();
        for (int i = 0; i < authorList.size(); i++) {
            Results r = new Results();
            AuthorMetrics a = authorList.get(i);
            r.setDiffAvgWordLength(Math.abs(newBook.averageWordLength()
                    - a.getAvgWordLength()));
            r.setDiffAverageSentenceLength(Math.abs(newBook
                    .averageSentenceLength() - a.getAvgSentenceLength()));
            r.setDiffRatioWordToSentenceLength(Math.abs(newBook
                    .ratioWordToSentenceLength() - a.getWordToSentenceRatio()));
            r.setDiffFreqNoun(Math.abs(a.getFreqNoun()
                    - newBook.frequencyOfNounUsage()));
            r.setDiffFreqVerb(Math.abs(a.getFreqVerb()
                    - newBook.frequencyOfVerbUsage()));
            r.setDiffFreqAdjective(Math.abs(a.getFreqAdjective()
                    - newBook.frequencyOfAdjectiveUsage()));
            r.setDiffAdjectiveToNounRatio(Math.abs(a.getAdjectiveToNounRatio()
                    - newBook.ratioAdjectivesToNounUsage()));
            r.setDiffLexicalDensity(Math.abs(a.getLexicalDensity()
                    - newBook.simpleLexicalDensity()));
            // ------------------------------------------Method
            // block-----------------------------------------------------
            double sum = 0;
            for (int j = 0; j < 26; j++) {
                sum += Math.abs(newBook.relativeLetterFrequency()[j]
                        - a.getRelativeLetterFreq()[j]);
            }
            sum = sum / 26;
            r.setDiffRelativeLetterFrequency(sum);
            // -------------------------------------------Method
            // block-----------------------------------------------------
            sum = 0;
            int count = 0;
            // go through first hashmap
            for (String key : newBook.relativeLetterPairFrequencies().keySet()) {
                keyList.add(key);
                if (a.getRelativeLetterPairFrequencies().containsKey(key)) {
                    sum += Math.abs(newBook.relativeLetterPairFrequencies()
                            .get(key)
                            - a.getRelativeLetterPairFrequencies().get(key));
                    keyList.add(key);
                    count++;
                } else {
                    sum += newBook.relativeLetterPairFrequencies().get(key);
                    count++;
                }
            }
            // go through second hashmap if difference hasn't been calculated
            // yet
            for (String key : a.getRelativeLetterPairFrequencies().keySet()) {
                if (!keyList.contains(key)) {
                    if (newBook.relativeLetterPairFrequencies()
                            .containsKey(key)) {
                        sum += Math
                                .abs(newBook.relativeLetterPairFrequencies()
                                .get(key)
                                - a.getRelativeLetterPairFrequencies()
                                .get(key));
                        count++;
                    } else {
                        sum += a.getRelativeLetterPairFrequencies().get(key);
                        count++;
                    }
                }
            }
            sum = sum / count;
            r.setDiffRelativeLetterPairFrequencies(sum);
            r.setDiffVocabRichness(Math.abs(a.getVocabRichness()
                    - newBook.vocabularyRichness()));
            // ------------------------------------------Method
            // block-----------------------------------------------------
            ArrayList<Integer> keyList2 = new ArrayList<>();
            count = 0;
            sum = 0;
            // go through first hashmap
            for (int key : newBook.distributionOfWordLengths().keySet()) {
                keyList2.add(key);
                if (a.getDistributionOfWordLengths().containsKey(key)) {
                    sum += Math.abs(newBook.distributionOfWordLengths()
                            .get(key)
                            - a.getDistributionOfWordLengths().get(key));
                    keyList2.add(key);
                    count++;
                } else {
                    sum += newBook.distributionOfWordLengths().get(key);
                    count++;
                }
            }
            // go through second hashmap if difference hasn't been calculated
            // yet
            for (int key : a.getDistributionOfWordLengths().keySet()) {
                if (!keyList2.contains(key)) {
                    if (newBook.distributionOfWordLengths().containsKey(key)) {
                        sum += Math.abs(newBook.distributionOfWordLengths()
                                .get(key)
                                - a.getDistributionOfWordLengths().get(key));
                        count++;
                    } else {
                        sum += a.getDistributionOfWordLengths().get(key);
                        count++;
                    }
                }
            }
            sum = sum / count;
            r.setDiffDistributionOfWordLengths(sum);
            r.setScore();
            results.add(r);
        }

        // ----------------------------------Sort arrayList by score and return it-------------------------------------
        double min = Double.POSITIVE_INFINITY;
        int index = -1;
        ArrayList<Results> temp = new ArrayList<>();
        int size = results.size();
        while (size > 0) {
            for (int i = 0; i < size; i++) {
                if (results.get(i).getScore() < min) {
                    min = results.get(i).getScore();
                    index = i;
                }
            }
            results.remove(index);
            temp.add(results.get(index));
            size--;
        }
        this.results = temp;
    }

}
