import java.util.HashMap;


public class Results {


	private String bookAuthor;
	private String bookTitle;
	private double diffNumNouns;
	private double diffNumAdjectives;
	private double diffNumVerbs;
	private double diffTotalWords;
	private double diffAvgWordLength = -1;
	private double diffAverageSentenceLength = -1;
	private double diffRatioWordToSentenceLength = -1;
	private double diffVocabRichness = -1;
	private double diffFreqNoun = -1;
	private double diffFreqVerb = -1;
	private double diffFreqAdjective = -1;
	private double diffAdjectiveToNounRatio = -1;
	private double diffLexicalDensity = -1;
	private double score = 0;
	private double diffRelativeLetterFrequency;
	private double diffRelativeLetterPairFrequencies;
	private double diffDistributionOfWordLengths;
	private double diffWordLength;
	
	//*******************Getters and Setters*********************************
	public double getScore(){
		return this.score;
	}
	public void setScore(){
		double x = diffNumNouns + diffNumAdjectives + diffNumVerbs+ diffNumVerbs+ diffTotalWords+ diffAvgWordLength+ diffAverageSentenceLength+ diffRatioWordToSentenceLength+
				diffVocabRichness+ diffFreqNoun +diffFreqVerb+ diffFreqAdjective +diffAdjectiveToNounRatio +diffLexicalDensity+ diffRelativeLetterFrequency +
				diffRelativeLetterPairFrequencies+diffDistributionOfWordLengths+ diffWordLength;
		this.score = x;
	}
	public double getDiffRelativeLetterFrequency() {
		return diffRelativeLetterFrequency;
	}
	public void setDiffRelativeLetterFrequency(double diffRelativeLetterFrequency) {
		this.diffRelativeLetterFrequency = diffRelativeLetterFrequency;
	}
	public double getDiffRelativeLetterPairFrequencies() {
		return diffRelativeLetterPairFrequencies;
	}
	public void setDiffRelativeLetterPairFrequencies(
			double diffRelativeLetterPairFrequencies) {
		this.diffRelativeLetterPairFrequencies = diffRelativeLetterPairFrequencies;
	}
	public double getDiffDistributionOfWordLengths() {
		return diffDistributionOfWordLengths;
	}
	public void setDiffDistributionOfWordLengths(
			double diffDistributionOfWordLengths) {
		this.diffDistributionOfWordLengths = diffDistributionOfWordLengths;
	}
	public double getDiffWordLength() {
		return diffWordLength;
	}
	public void setDiffWordLength(double diffWordLength) {
		this.diffWordLength = diffWordLength;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public double getDiffNumNouns() {
		return diffNumNouns;
	}
	public void setDiffNumNouns(double diffNumNouns) {
		this.diffNumNouns = diffNumNouns;
	}
	public double getDiffNumAdjectives() {
		return diffNumAdjectives;
	}
	public void setDiffNumAdjectives(double diffNumAdjectives) {
		this.diffNumAdjectives = diffNumAdjectives;
	}
	public double getDiffNumVerbs() {
		return diffNumVerbs;
	}
	public void setDiffNumVerbs(double diffNumVerbs) {
		this.diffNumVerbs = diffNumVerbs;
	}
	public double getDiffTotalWords() {
		return diffTotalWords;
	}
	public void setDiffTotalWords(double diffTotalWords) {
		this.diffTotalWords = diffTotalWords;
	}
	public double getDiffAvgWordLength() {
		return diffAvgWordLength;
	}
	public void setDiffAvgWordLength(double diffAvgWordLength) {
		this.diffAvgWordLength = diffAvgWordLength;
	}
	public double getDiffAverageSentenceLength() {
		return diffAverageSentenceLength;
	}
	public void setDiffAverageSentenceLength(double diffAverageSentenceLength) {
		this.diffAverageSentenceLength = diffAverageSentenceLength;
	}
	public double getDiffRatioWordToSentenceLength() {
		return diffRatioWordToSentenceLength;
	}
	public void setDiffRatioWordToSentenceLength(
			double diffRatioWordToSentenceLength) {
		this.diffRatioWordToSentenceLength = diffRatioWordToSentenceLength;
	}
	public double getDiffVocabRichness() {
		return diffVocabRichness;
	}
	public void setDiffVocabRichness(double diffVocabRichness) {
		this.diffVocabRichness = diffVocabRichness;
	}
	public double getDiffFreqNoun() {
		return diffFreqNoun;
	}
	public void setDiffFreqNoun(double diffFreqNoun) {
		this.diffFreqNoun = diffFreqNoun;
	}
	public double getDiffFreqVerb() {
		return diffFreqVerb;
	}
	public void setDiffFreqVerb(double diffFreqVerb) {
		this.diffFreqVerb = diffFreqVerb;
	}
	public double getDiffFreqAdjective() {
		return diffFreqAdjective;
	}
	public void setDiffFreqAdjective(double diffFreqAdjective) {
		this.diffFreqAdjective = diffFreqAdjective;
	}
	public double getDiffAdjectiveToNounRatio() {
		return diffAdjectiveToNounRatio;
	}
	public void setDiffAdjectiveToNounRatio(double diffAdjectiveToNounRatio) {
		this.diffAdjectiveToNounRatio = diffAdjectiveToNounRatio;
	}
	public double getDiffLexicalDensity() {
		return diffLexicalDensity;
	}
	public void setDiffLexicalDensity(double diffLexicalDensity) {
		this.diffLexicalDensity = diffLexicalDensity;
	}

}
