package AuthorshipAnalysis;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Scott Dykstra
 * @version September 2,2013
 *
 */
public class AuthorshipAnalysis {

    public static Scanner keyboard = new Scanner(System.in);
    public static String authorsName = "BEATRIX POTTER";
    public static String title = "ROLY-POLY PUDDING";
    public static String fileName = "and up; but before he reached the chimney top he came to a place where somebody had loosened a stone in the wall. There were some mutton bones lying about— “This seems funny,” said Tom Kitten. “Who has been gnawing bones up here in the chimney? I wish I had never come! And what a funny smell! It is something like mouse; only dreadfully strong. It makes me sneeze,” said Tom Kitten.Unknown  Unknown   He squeezed through the hole in the wall, and dragged himself along a most uncomfortably tight passage where there was scarcely any light. He groped his way carefully for several yards; he was at the back of the skirting- board in the attic, where there is a little mark * in the picture.Unknown  Unknown   All at once he fell head over heels in the dark, down a hole, and landed on a heap of very dirty rags. When Tom Kitten picked himself up and looked about him—he found himself in a place that he had never seen before, although he had lived all his life in the house. It was a very small stuffy fusty room, with boards, and rafters, and cobwebs, and lath and plaster. Opposite to him—as far away as he could sit—was an enormous rat. “What do you mean by tumbling into my bed all covered with smuts?” said the rat, chattering his teeth. “Please sir, the chimney wants sweeping,” said poor Tom Kitten. “Anna Maria! Anna Maria!” squeaked the rat. There was a pattering noise and an old woman rat poked her head round a rafter. All in a minute she rushed upon Tom Kitten, and before he knew what was happening— His coat was pulled off, and he was rolled up in a bundle, and tied with string in very hard knots. Anna Maria did the tying. The old rat watched her and took snuff. When she had finished, they both sat staring at him with their mouths open. “Anna Maria,” said the old man rat (whose name was Samuel Whiskers),— “Anna Maria, make me a kitten dumpling roly-poly pudding for my dinner.” “It requires dough and a pat of butter, and a rolling-pin,” said Anna Maria, considering Tom Kitten with her head on one side.Unknown  Unknown   “No,” said Samuel Whiskers, “make it properly, Anna Maria, with breadcrumbs.” “Nonsense! Butter and dough,” replied Anna Maria.Unknown  Unknown   The two rats consulted together for a few minutes and then went away. Samuel Whiskers got through a hole in the wainscot, and went boldly down the front staircase to the dairy to get the butter. He did not meet anybody. He made a second journey for the rolling- pin. He pushed it in front of him with his paws, like a brewer’s man trundling a barrel. He could hear Ribby and Tabitha talking, but they were busy lighting the candle to look into the chest. They did not see him. Anna Maria went down by way of the skirting-board and a window shutter to the kitchen to steal the dough. She borrowed a small saucer, and scooped up the dough with her paws. She did not observe Moppet. While Tom Kitten was left alone under the floor of the attic, he wriggled about and tried to mew for help. But his mouth was full of soot and cobwebs, and he was tied up in such very tight knots, he could not make anybody hear him. Except a spider, which came out of a crack in the ceiling and examined the knots critically, from a safe distance. It was a judge of knots because it had a habit of tying up unfortunate blue-bottles. It did not offer to assist him. Tom Kitten wriggled and squirmed until he was quite exhausted.Unknown  Unknown   Presently the rats came back and set to work to make him into a dumpling. First they smeared him with butter, and then they rolled him in the dough. “Will not the string be very indigestible, Anna Maria?” inquired Samuel Whiskers. Anna Maria said she thought that it was of no consequence; but she wished that Tom Kitten would hold his head still, as it disarranged the pastry. She laid hold of his ears.Unknown  Unknown   Tom Kitten bit and spat, and mewed and wriggled; and the rolling-pin went roly- poly, roly; roly, poly, roly. The rats each held an end. “His tail is sticking out! You did not fetch enough dough, Anna Maria.” “I fetched as much as I could carry,” replied Anna Maria. “I do not think”—said Samuel Whiskers, pausing to take a look at Tom Kitten—“I do not think it will be a good pudding. ";    
    public static String bookWordList = null;

    public static void main(String[] args)throws FileNotFoundException, IOException {

        Book book = new Book(authorsName, title, fileName);
        
        SqlConnection sqlConnection = new SqlConnection();
        
        
        AuthorMetrics a = sqlConnection.getAuthorMetrics(authorsName);
        AuthorMetrics b = a;
    }//end main

    private static String printMessageWithReturn(String message) {
        System.out.printf(message);
        return keyboard.next();
    }

    private static void printMessage(String message) {
        System.out.printf(message);
    }
    
    
}//end AuthorshipAnalysis
