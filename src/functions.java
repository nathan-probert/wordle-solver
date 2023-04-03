import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class functions {

    // gets the entire file and reads it into a dictionary
    public static String[] getDict (String filename, int[] numWords) throws IOException {
        Scanner myFile = new Scanner(new File("wordle-solver\\lib\\" + filename));

        List<String> temps = new ArrayList<String>();
        while (myFile.hasNextLine()) {
            temps.add(myFile.nextLine());
            numWords[0]++;
        }
        myFile.close();
        String[] allWords = temps.toArray(new String[0]);
        System.out.println("There are " + numWords[0] + " words in this dictionary.");

        return allWords;
    }

    // sorts the letters in order of number of occurances
    public static char[] sortLetters (String[] letters) {
		int[] allLetters = new int[26];
        int[] unSortedLetters = new int[26];
        int[] lettersInOrder = new int[26];
        char[] lettersToReturn = new char[26];

        // count each characters appearance
		for (String word : letters) {
			for(int i = 0; i < 5; i++){  
				allLetters[word.charAt(i) - 'a']++;
                unSortedLetters[word.charAt(i) - 'a']++;
			}
		}

        // sort the letters
        for (int i=0; i<26; i++) {
            for (int j=i; j<26; j++) {
                if (allLetters[i] < allLetters[j]) {
                    int temp = allLetters[i];
                    allLetters[i] = allLetters[j];
                    allLetters[j] = temp;
                }
            }
        }

        // sort positions of letters
        for (int i=0; i<26; i++) {
            for (int j=0; j<26; j++) {
                if (allLetters[i] == unSortedLetters[j]) {
                    lettersInOrder[i] = j;
                }
            }
        }

        for (int i=0; i<26; i++) {
            lettersToReturn[i] = ((char) (lettersInOrder[i]+97));
        }

        return lettersToReturn;
    }

    // creates all possible words
    public static String[] makeWords (char[] letters, String[] possibleWords) {
        String[] words = {""};

        // loop attempts at making a word with most popular first 5, then 6, etc letters
            // take the chars, compare to each string in dict by sorting the two strings the same way
        for (int i=0; i<possibleWords.length; i++) {
            String inOrder = new String(letters);
            String test = inOrder.substring(0, 5);
        }

        return words;
    }
}
