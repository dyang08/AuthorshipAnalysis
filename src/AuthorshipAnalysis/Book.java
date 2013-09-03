import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.HashMap;
public class Book {

	String bookText;

	int numNouns;
	int numWords;
	int numAdjectives;
	int numVerbs;
	int numSentences;

	double avgWordLength = 0;
	double averageSentenceLength = 0;
	double[] relativeLetterFrequency = null;
	HashMap<String, Integer> relativeLetterPairFrequencies;

	StringTokenizer st;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Book book = new Book("abcd");
		
		book.relativeLetterPairFrequencies();
	
		System.out.println();

	}

	Book() {

	}

	Book(String text) {

		this.bookText = text;

	}

	public double averageWordLength() {

		if (avgWordLength != 0) {
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

	public double averageSentenceLength() {

		if (averageSentenceLength != 0) {
			return this.averageSentenceLength;
		} else {

			st = new StringTokenizer(bookText, ".!?");

			double sentenceTotal = 0;
			double cumulativeSum = 0;

			while (st.hasMoreTokens()) {

				String sentence = st.nextToken().trim();

				cumulativeSum += sentence.length() + 1;

				sentenceTotal++;

			}

			this.averageSentenceLength = cumulativeSum / sentenceTotal;

			return this.averageSentenceLength;

			// Get the count of sentenceLength
		}
	}

	public double ratioWordToSentenceLength() {

		return averageWordLength() / averageSentenceLength();
	}

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
			
			while(st.hasMoreTokens()) {
				
				String word = st.nextToken();
				
				for (int i = 0; i < word.length(); i++) {
					
					int charValue = Character.getNumericValue(word.charAt(i));
					
					if(charValue >= 10 && charValue <= 35) {
						
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

	public HashMap<String, Integer>  relativeLetterPairFrequencies() {		
		
		if (relativeLetterPairFrequencies != null) {
			return this.relativeLetterPairFrequencies;
		} else {
			
			relativeLetterPairFrequencies = new HashMap<>();

			double cumulativeSum = 0;

			st = new StringTokenizer(bookText);

			while(st.hasMoreTokens()) {

				String word = st.nextToken();

				for (int i = 0; i < word.length()-1; i++) {

					int firstCharValue = Character.getNumericValue(word.charAt(i));
					int secondCharValue = Character.getNumericValue(word.charAt(i+1));

					if((firstCharValue >= 10 && firstCharValue <= 35)&&(secondCharValue >= 10 && secondCharValue <= 35)) {

						String key = word.substring(i,i+2);

						System.out.println(key);

						if(relativeLetterPairFrequencies.containsKey(key)) {

							int tempVal = relativeLetterPairFrequencies.get(key);
							tempVal++;

							relativeLetterPairFrequencies.put(key, tempVal);
						} else {
							relativeLetterPairFrequencies.put(key, 1);
						}


					}
				}
			}


		}

		return null;
	}



	public double vocabularyRichness(String text) {
		return 0;

		// # OF unique words / total number of words
	}

	// Lower Metrics

	public void distributionOfWordLengths(String text) {

		// TODO
		// NEED MORE INFO
	}

	public double frequencyOfNounUsage(int numNouns, int numWords) {

		// nouns / words

		return 0;
	}

	public double frequencyOfVerbs(int numVerbs, int numNouns) {

		// verbs / words

		return 0;
	}

	public double frequencyOfAdjectiveUsage(int numAdjectives, int numWords) {

		// adjectives / words

		return 0;
	}

	public double ratioAdjectivesToNounUsage(int numAdjectives, int numNouns) {

		// adjectives / nouns

		return 0;
	}

	public double simpleLexicalDensity(int numAdjectivs, int numWords) {

		// nouns+verbs+adjectives / total words

		return 0;
	}

	// HELPER METHODS

	public double countOfWordLength(String text) {

		return 0;

	}

	public double countOfSentenceLength(String text) {
		return 0;

	}

}
