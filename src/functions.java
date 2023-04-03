import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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


    public static boolean elementOf (int[] array, int i) {
        for (int a : array) {
            if (a == i) {
                return true;
            }
        }
        return false;
    }
    // sorts the letters in order of number of occurances
    public static char[] sortLetters (String[] letters) {
		int[] allLetters = new int[26];
        int[] unSortedLetters = new int[26];
        int[] lettersInOrder = new int[26];
        char[] lettersToReturn = new char[26];

        Arrays.fill(allLetters, 0);
        Arrays.fill(unSortedLetters, 0);
        Arrays.fill(lettersInOrder, 0);

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
            for (int j=i; j<26; j++) {
                if ((allLetters[i] == unSortedLetters[j]) && (!elementOf(lettersInOrder, j))) {
                    lettersInOrder[i] = j;
                }
            }
        }

        for (int i=0; i<26; i++) {
            lettersToReturn[i] = ((char) (lettersInOrder[i]+97));
        }

        lettersToReturn = Arrays.copyOf(lettersToReturn, allLetters.length);
        return lettersToReturn;
    }


    public static boolean isAnagram(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);

        return (Arrays.equals(c1, c2));
    }


    public static boolean hasDup(CharSequence checkString, boolean needMore) {
        if (needMore) {
            if (checkString.length() == (checkString.chars().distinct().count())+1) {
                return false;
            }
        }
        return (checkString.length() != (checkString.chars().distinct().count()));
    }


    public static String[] combinations (String s, boolean checkDoubles) {
        String[] combinations = new String[1000];
        int combinationCount = 0;
        char[] letters = s.toCharArray();
        int[] iLetters = new int[letters.length];
        char[] temp = new char[5];
        boolean copy = false;

        for (int i = 0; i < letters.length; i++){
            iLetters[i] = letters[i] - '0';
        }

        temp = s.substring(0,s.length()).toCharArray();
        for (int i=0; i<s.length(); i++) {
            for (int j=0; j<s.length(); j++) {
                for (int k=0; k<s.length(); k++) {
                    for (int l=0; l<s.length(); l++) {
                        for (int m=0; m<s.length(); m++) {
                            char[] forComb = {temp[i], temp[j], temp[k], temp[l], temp[m]};
                            String comb = new String(forComb);
                            // check if it has duplicates (otherwise runtime is crazy)
                            // maybe make it check for > 2 dupes? 
                            if (hasDup(comb, checkDoubles)) {
                                copy = true;
                            }
                            else {
                                for (int o=0; o<combinationCount; o++) {
                                    // compare to old combinations
                                    if (isAnagram(combinations[o], comb)) {
                                        copy = true;
                                    }
                                }
                            }
                            if ((copy==false) && (comb!=null)) {
                                combinations[combinationCount] = comb;
                                combinationCount++;
                            }
                            copy = false;
                        }
                    }
                }
            }
        }  

        String[] properSizeCombinations = Arrays.copyOf(combinations, combinationCount);

        return properSizeCombinations;
    }


    // creates all possible words
    public static String[] makeWords (char[] letters, String[] possibleWords) {
        String[] words = new String [1000];
        int numWords = 0;
        boolean repeat = true;

        // the number of letters to initially look at
        int endIndex = 5;
        boolean ignore = false;
        boolean checkDoubles = false;

        // loop attempts at making a word with most popular first 5, then 6, etc letters
        while (repeat) {
            repeat=false;
            // take the chars, compare to each string in dict by sorting the two strings the same way
            String inOrder = new String(letters);
            String test = inOrder.substring(0, endIndex);
            String[] temp = combinations(test, checkDoubles);
            
            for (String check : possibleWords) {
                // loop through each diff combination of test
                for (String t : temp) {
                    if (isAnagram(t, check)) {
                        for (String w : words) {
                            if (w == check) {
                                ignore = true;
                            }
                        }
                        if (!ignore) {
                            words[numWords] = check;
                            numWords++;
                        }
                        ignore = false;
                    }
                }
            }

            if (words[0] == null) {
                /**
                if (!checkDoubles) {
                    checkDoubles = true;
                }
                else {
                    endIndex++;
                    checkDoubles = false;
                }
                repeat=true;
                **/
                endIndex++;
                repeat=true;
            }
        }

        String[] properSizeWords = Arrays.copyOf(words, numWords);

        return properSizeWords;
    }


    public static String[] green (String[] dict, char letter, int position) {
        String[] newDict = new String[dict.length];
        int i=0;

        for (String s : dict) {
            if (s.contains(""+letter)) {
                if (s.charAt(position) == letter) {
                    newDict[i] = s;
                    i++;
                }
            }
        }

        newDict = Arrays.copyOf(newDict, i);
        return newDict;
    }


    public static String[] black (String[] dict, char letter) {
        String[] newDict = new String[dict.length];
        int i=0;

        for (String s : dict) {
            if (!(s.contains(""+letter))) {
                newDict[i] = s;
                i++;
            }
        }

        newDict = Arrays.copyOf(newDict, i);
        return newDict;
    }


    public static String[] yellow (String[] dict, char letter, int position) {
        String[] newDict = new String[dict.length];
        int i=0;

        for (String s : dict) {
            if (s.contains(""+letter)) {
                if (s.charAt(position) != letter) {
                    newDict[i] = s;
                    i++;
                }
            }
        }

        newDict = Arrays.copyOf(newDict, i);
        return newDict;
    }
}
