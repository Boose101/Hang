import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;


public class HangNo {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String word = findWord();
        int len = word.length();
        int guessLeft = 5;
        String wordGuess = "";
        String guessList = "";

        String userGuess = "";
        Boolean game = true;
        int won = 0;
        

        System.out.println("Welcome to Hangman! You have to guess the mystery word in 5 guesses or less.");

        System.out.println(word);

        for(int x = 0; x < len; x++){
            wordGuess += "*";
        }
        
        while(game){
            System.out.println("Here's what you have so far: "+ wordGuess);
            System.out.println("You have " + guessLeft + " guesses left.");
            System.out.println("Here are the letters you have guessed so far:" + guessList);
            System.out.println("What letter would you like to guess next?");
    
            userGuess = scan.nextLine();

            userGuess = userGuess.toLowerCase();

            String buffer  = "";

            Boolean fits = false;
    
            for(int y = 0; y < len; y++){
    
                
                if(userGuess.equals(Character.toString(word.charAt(y)))){
                    buffer += userGuess;
                    fits = true;
                }else{
                    buffer += Character.toString(wordGuess.charAt(y));
                }

            }
            wordGuess = buffer;


            if(fits){
                System.out.println(ANSI_GREEN + "CORRECT!!!" + ANSI_RESET);
            }else{
                System.out.println(ANSI_RED + "I'm sorry…" + ANSI_RESET);
                guessLeft--;
                guessList += (" " + userGuess);
            }

            if(guessLeft <= 0){
                System.out.println(ANSI_WHITE_BACKGROUND + ANSI_RED + "You have lost..." + ANSI_RESET);
                System.out.println("The word was " + word);
                game = false;
            }

            won = 0;
            for(int z = 0; z < len; z++){
                if(wordGuess.charAt(z) != '*'){
                    won++;
                }
            }
            if(won == len){
                System.out.println(ANSI_WHITE_BACKGROUND + ANSI_GREEN + "You have won!!!" + ANSI_RESET);
                System.out.println("The word was " + word);
                game = false;
            }
            
       
        }




        

        
        
    }

    private static String findWord() {

        
        Scanner reader;

        try {
            File fWord = new File("words.txt");

            reader = new Scanner(fWord);

            int len = 0;

            

            while(reader.hasNextLine()){
                reader.nextLine();
                len++;
            }
            

            int ran = (int)(len*Math.random());

            String line = FileUtils.readLines(fWord, Charset.defaultCharset()).get(ran);

            reader.close();
            return line;

        

            
        } catch (IOException e) {
            e.printStackTrace();

            return "computer";
        }

        
    }
}
