/**
	This interface is used as the base for any possible I/O classes for the 
	AuthorshipAnalysis project. 
	
	@version 9-2-13
*/
public interface User{
	/**
	The stdOut is used to display information to the user.
	
	I (David) am unsure what the param should be. In the video conference we 
	said to use Double but i think that would mean that adding a lot of issues
	with calls. Were a String could prevent some of the issues.
	*/
	public void stdOut(/*Double output*/ String output);
	
	/**
	getUnknownBook is used to get a file name from the user. 
	
	In the context of a GUI this would be when a button is pressed and the program 
	will receive the file name for the unknown book.
	
	@return The file location of the unknown book.
	*/
	public String getUnknownBook();
	
	/**
	getKnownBook is used to get the author and the file location of the known book
	
	In the context of a GUI this would be when a button is pressed and the program 
	will receive the author and file location of the known book.
	
	@return A String[] where the first element is the authors name and the 
	second would be file location. 
	*/
	public String[] getKnownBook();
	/**
	Used to display all the metrics of a author. 
	
	added after seeing the initial GUI Design ideas
	
	@param A array of Strings to be displayed in the table. (Most likely in the
	form percentage description: percentage)
	*/
	public void displayTable(String[] out);
}