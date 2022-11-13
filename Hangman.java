package Hangman;
import java.io.*;
import java.util.*;

public class Hangman {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int guesses = 6;
        int incorrect = 0;
        boolean game = true;
        List<String> words = readFile();

        System.out.println("Welcome to Hangman! You have to guess the mystery word in 6 guesses or less.");

        Random rand = new Random();
        String word  = words.get(rand.nextInt(words.size()));

        int len = word.length();

        //Creation of null guess map
        Map<String, String> letters = new HashMap<>();
        for (int i = 0; i < len; i++) {
            letters.put("pos" + i, "*");
        }

        //Initlization of guess map
        SortedMap<String, String> guessMap = new TreeMap<String,String>(new Comparator<String>() {
            public int compare(String a, String b)
            {
                return b.compareTo(a);
            }
        });

        //Creation of word breakdown
        Map<String, String> breakdown = new HashMap<>();
        for(int p = 0; p < len; p++){
            breakdown.put("spot" + p, word.substring(p, p+1));
        }

        System.out.println(word);

        while(guesses != 0 && game){
            //Print reveled word
            System.out.print("Here's what you have so far: ");
            for(int x = 0; x < len; x++){
                System.out.print( letters.get("pos" + x));
            }
            System.out.print('\n');

            //Draw
            if(guesses == 6){
                System.out.println(showStart());
            }else if(guesses == 5){
                System.out.println(showGuess1());
            }else if(guesses == 4){
                System.out.println(showGuess2());
            }else if(guesses == 3){
                System.out.println(showGuess3());
            }else if(guesses == 2){
                System.out.println(showGuess4());
            }else if(guesses == 1){
                System.out.println(showGuess5());
            }
            
            
    
            System.out.print("Here are the letters you have guessed so far: " + guessMap);

            System.out.print('\n');

            System.out.print("What letter would you like to guess next? ");
            String guess = scan.next();

            guess = guess.toLowerCase();

            if(breakdown.containsValue(guess)){
                for(int q = 0; q < letters.size(); q++){
                    if(breakdown.get("spot" + q).equals(guess)){
                        letters.replace("pos" + q, "*", guess);
                    }
                }
                System.out.println(ANSI_GREEN + "CORRECT!!!" + ANSI_RESET);
            }else if(guessMap.containsValue(guess)){
                System.out.println(ANSI_RED + "You have already guessed this value" + ANSI_RESET);
            }else{
                guessMap.put(""+incorrect, guess);
                
                System.out.println("I'm sorry…");
                incorrect++;
                guesses--;
            }

            if(!letters.containsValue("*")){
                System.out.println(ANSI_GREEN + ANSI_WHITE_BACKGROUND + "You Won!" + ANSI_RESET);
                game = false;
                break;
            }

            System.out.println("----------------------------------------------------------");
            
        }

        if(guesses == 0){
            System.out.println(ANSI_RED + showGuess6() + ANSI_RESET);
        }


        System.out.println("----------------------------------------------------------");
        System.out.print('\n' + '\n');
        System.out.println("The word is: " + word);
       
        


        scan.close();
    }
    

    private static List<String> readFile() {
        Scanner reader;
        try {
            reader = new Scanner(new File("Hangman/words.txt"));
            List<String> words = new ArrayList<String>();

            while (reader.hasNext()){
                words.add(reader.nextLine().toLowerCase());
            } 
            reader.close ();
            return words;
        } catch (IOException e) {
            e.printStackTrace();
            List<String> error = new ArrayList<String>();
            error.add("computer");
            return error;
        }

        
    }

    //Hangman Draw()

    private static String drawTop()
    {
       return("_____" + '\n' + 
              "|   |");

    }
    
    private static String showStart()
    {
        return(drawTop() + '\n' +
        "|   " + '\n' +
        "|   " + '\n' +
        "|   "); 
    }

    private static String showGuess1()
    {
        return(drawTop() + '\n' +
        "|   o" + '\n' +
        "|   " + '\n' +
        "|   "); 
    }

    private static String showGuess2()
    {
        return(drawTop() + '\n' +
        "|   o" + '\n' +
        "|   | " + '\n' +
        "|   "); 
    }

    private static String showGuess3()
    {
        return(drawTop() + '\n' +
        "|   o" + '\n' +
        "|  /| " + '\n' +
        "|   "); 
    }

    private static String showGuess4()
    {
        ;
        return(drawTop() + '\n' + 
        "|   o" + '\n' + 
        "|  /|\\ " + '\n' +
        "|   "); 
    }

    private static String showGuess5()
    {
        return(drawTop() + '\n' +
        "|   o" + '\n' +
        "|  /|\\ " + '\n' +
        "|    \\"); 
    }

    private static String showGuess6()
    {
        return(drawTop() + '\n' +
        "|   o" + '\n' +
        "|  /|\\ " + '\n' +
        "|  / \\"); 
    }


}
