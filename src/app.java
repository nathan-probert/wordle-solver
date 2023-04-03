import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
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
        // create readers
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        Scanner wordleWords = new Scanner(new File("wordle-solver\\lib\\wordle_words2.txt"));
        Scanner commonWordleWords = new Scanner(new File("wordle-solver\\lib\\common_wordle_words.txt"));
        Scanner possibleAnswers = new Scanner(new File("wordle-solver\\lib\\possible_answers2.txt"));

        // gets the entire file called wordle_words.txt and reads it into a dictionary
        int numWordleWords = 0;
        List<String> temps = new ArrayList<String>();
        while (wordleWords.hasNextLine()) {
            temps.add(wordleWords.nextLine());
            numWordleWords++;
        }
        wordleWords.close();
        String[] wordleWordsDict = temps.toArray(new String[0]);
        System.out.println("\nThere are " + numWordleWords + " words in this dictionary.");

        // gets the entire file called common_wordle_words.txt and reads it into a dictionary
        int numCommonWordleWords=0;
		temps.clear();
        while (commonWordleWords.hasNextLine()) {
            temps.add(commonWordleWords.nextLine());
            numCommonWordleWords++;
        }
        wordleWords.close();
        String[] commonWordleWordsDict = temps.toArray(new String[0]);
		System.out.println("There are " + numCommonWordleWords + " words in the common dictionary.");

		// gets the entire file called possible_answers and reads it into a dictionary
        int numPossibleAnswers=0;
        temps.clear();
		while (possibleAnswers.hasNextLine()) {
			temps.add(possibleAnswers.nextLine());
            numPossibleAnswers++;
		}
		possibleAnswers.close();
        String[] possibleAnswersDict = temps.toArray(new String[0]);
		System.out.println("There are " + numPossibleAnswers + " words in the common dictionary.");

    }
}