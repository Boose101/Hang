import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


public class HangNo {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static void main(String[] args) {

        String word = findWord();

        int len = word.length();

        System.out.println("Welcome to Hangman! You have to guess the mystery word in 5 guesses or less.");

        String guess = "";

        for(int x = 0; x < len; x++){
            guess += "*";
        }

        System.out.print(guess);


        

        
        
    }

    private static String findWord() {
        Scanner reader;

        try {
            File fWord = new File("words.txt");

            reader = new Scanner(fWord);

            int len = 0;

            for(int x = 0; reader.hasNext(); x++){
                len++;
            }

            int ran = (int)(len*Math.random());

            String line = FileUtils.readLines(fWord).get(ran);

        

            
        } catch (IOException e) {
            e.printStackTrace();

            return "computer";
        }

        
    }
}
