import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class app {
	public static void main (String[] args) throws IOException{

        long start = System.currentTimeMillis();

        // create readers
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        
        int[] numWordleWords = {0};
        String[] wordleWords = functions.getDict("wordle_words2.txt", numWordleWords);

        int[] numCommonWordleWords = {0};
        String[] commonWordleWords = functions.getDict("common_wordle_words.txt", numCommonWordleWords);

        int[] numPossibleAnswers = {0};
        String[] possibleAnswers = functions.getDict("possible_answers2.txt", numPossibleAnswers);

        int[] numPrettyCommonWordleWords = {0};
        String[] prettyCommonWordleWords = functions.getDict("pretty_common_words.txt", numPrettyCommonWordleWords);

        String[] dictionary = possibleAnswers;

        // loop for each possible guess
        for (int i=0; i<6; i++) {
            // sort letters in order of number of occurances
            char[] sortedLetters = functions.sortLetters(dictionary);
            for (char l : sortedLetters) {
                System.out.println(l);
            }

            String[] possibleWords = functions.makeWords(sortedLetters, dictionary);
            System.out.println("\nHere are our top guesses:");
            for (String k : possibleWords) {
                System.out.print(k + " ");
            }

            // check within pretty common words
            i=0;
            String[] bestWords = new String[1000];
            System.out.println("\n\nPretty Common Wordle Words:");
            for (String w : possibleWords) {
                for (String w2 : prettyCommonWordleWords) {
                    if (w == w2) {
                        System.out.print(w + " ");
                        bestWords[i] = w;
                        i++;
                        break;
                    }
                }
            }
            if (i==0) {
                for (String w : possibleWords) {
                    bestWords[i] = w;
                    i++;
                }
            }
            String[] sizedBestWords = Arrays.copyOf(bestWords, i);

            // now randomly choose one of the most popular guesses
            Random r = new Random();
            int randint = r.ints(1, 0, sizedBestWords.length).findFirst().getAsInt();
			String bestGuess = sizedBestWords[randint];
            System.out.println("Final Guess: " + bestGuess);

            // ask the user for their guess
            System.out.print("\nPlease enter your guess (Enter 0 to use suggested guess): ");
			String guess = stdin.readLine();
            if (guess.equals("0")) {
                guess = bestGuess;
            }
            System.out.println(guess);

            String temp;
			for (int l=0; l<5; l++) {
				System.out.print("Was the letter " + guess.charAt(l) + " green (g), yellow (y), or black (b): ");
				temp = stdin.readLine();
				if (temp.equalsIgnoreCase("y") | temp.equalsIgnoreCase("g") | temp.equalsIgnoreCase("b")) {
					if (temp.equals("g")) {
						dictionary = functions.green(dictionary, guess.charAt(l));
					}
					if (temp.equals("y")) {
						dictionary = functions.yellow(dictionary, guess.charAt(l), l);
					}
					if (temp.equals("b")) {
						dictionary = functions.black(dictionary, guess.charAt(l));
					}
				}
				else {
					System.out.println("fold");
				}
            }
            System.out.println("Number of possible words remaining: " + dictionary.length);
            for (String s : dictionary) {
                System.out.println(s);
            }
        }

        // get runtime
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
    }
}