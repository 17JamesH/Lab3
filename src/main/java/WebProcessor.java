import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * @author James
 *
 * Processes strings from web files.
 */
public class WebProcessor {

    /**
     * The ASCII value of 'z'.
     */
    public static final int Z_CHAR = 122;

    /**
     * The ASCII value of 'a'.
     */
    public static final int A_CHAR = 97;

    /**
     * Processes the text of a URL specified by args[0], and prints the total number of words,
     * occurrences of a single word specified in args[1], and unique words.
     *
     * @param args arguments passed to the test function.
     */
    public static void main(final String[] args) {
        String page = urlToString(args[0]).toLowerCase();
        String countedWord = args[1].toLowerCase();
        String currentWord;
        Scanner parser = new Scanner(page);
        int count = 0;
        int occurrences = 0;
        Set<String> uniqueWords = new HashSet<String>();

        while (parser.hasNext()) {
            currentWord = parser.next();
            currentWord = truncateSpecial(currentWord);

            if (countedWord.equals(currentWord)) {
                occurrences++;
            }
            uniqueWords.add(currentWord);
            count++;
        }
        parser.close();

        System.out.println(count + " words total.");
        System.out.println(occurrences + " occurrences of the word " + args[1]);
        System.out.println(uniqueWords.size() + " unique words.");
    }

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    /**
     * Truncates any characters that are not between a-z (special characters).
     *
     * @param input the string to remove special characters from
     * @return input without special characters
     */
    public static String truncateSpecial(final String input) {
        String output = input;

        for (int i = input.length(); i > 0; i--) {
            if ((int) output.charAt(i - 1) < A_CHAR || (int) output.charAt(i - 1) > Z_CHAR) {
                output = output.substring(0, i - 1) + output.substring(i);
            }
        }

        return output;
    }
}
