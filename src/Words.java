package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Words extends ArrayList<String> {

    public Words(String filename) throws IOException {
        getDictionary(filename);
    }

    public int countPossible() {
        return this.size();
    }

    private void getDictionary(String filename) throws IOException {
        Scanner sc = new Scanner(new FileReader(filename));
        while (sc.hasNextLine()) {
            this.add(sc.nextLine());
        }
        sc.close();
    }

    public void printWords() {
        for (String w : this)
            System.out.println(w);
    }

    String getStringRepresentation(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character ch : list) {
            builder.append(ch);
        }
        return builder.toString();
    }

    // custom no possible word exception?
    public String generateGuess() throws NoPossibleWords {
        if (this.isEmpty()) {
            throw new NoPossibleWords();
        }

        String guess = null;

        ArrayList<Character> popularLetters = sortLetters();
        System.out.println("Letters sorted by popularity: \n" + popularLetters + "\n");

        // get all words with first, second...
        ArrayList<String> guesses = new ArrayList<>();
        for (int index = 5; index <= 26; index++) {
            String inOrder = getStringRepresentation(popularLetters);
            String anagram = inOrder.substring(0, index);

            Permutations perm = new Permutations(anagram.toCharArray(), 5, false);
            ArrayList<String> res = perm.permute();
            for (String temp : res) {
                if (this.contains(temp)) {
                    guesses.add(temp);
                }
            }

            System.out.println("Possibilities from top " + index + " letters: \n" + guesses + "\n");

            if (!guesses.isEmpty()) {
                // get final choice
                Random rand = new Random();
                guess = guesses.get(rand.nextInt(guesses.size()));

                break;
            }
        }

        return guess;
    }

    // sorts the letters in order of number of occurances
    public ArrayList<Character> sortLetters() {

        // count each letter's # of appearences
        HashMap<Character, Integer> map = new HashMap<>();
        for (String w : this) {
            for (char letter : w.toCharArray()) {
                if (map.containsKey(letter)) {
                    map.put(letter, map.get(letter) + 1);
                } else {
                    map.put(letter, 1);
                }
            }
        }

        // sort letters by occurence
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<Character, Integer>comparingByValue().reversed());
        ArrayList<Character> sortedChars = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : list) {
            sortedChars.add(e.getKey());
        }

        return sortedChars;
    }

    public void updateWordList(String guess) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int index = 0;

        System.out.println("\nEnter 'G' for Green, 'Y' for Yellow, 'B' for black/grey.");
        for (char letter : guess.toCharArray()) {
            boolean repeat = true;
            while (repeat) {
                repeat = false;
                System.out.print("'" + letter + "': ");
                String temp = "";
                try {
                    temp = stdin.readLine();
                } catch (IOException e) {
                    repeat = true;
                    temp = "";
                }
                if (temp.equalsIgnoreCase("y") | temp.equalsIgnoreCase("g") | temp.equalsIgnoreCase("b")) {
                    if (temp.equalsIgnoreCase("g")) {
                        green(letter, index);
                    }
                    if (temp.equalsIgnoreCase("y")) {
                        yellow(letter, index);
                    }
                    if (temp.equalsIgnoreCase("b")) {
                        grey(letter, index);
                    }
                } else {
                    repeat = true;
                    System.out.println("Invalid Input.");
                }
            }

            index++;
        }
        System.out.println();
    }

    private void green(char letter, int index) {
        ArrayList<String> temp = new ArrayList<>(this);
        for (String word : temp) {
            if (word.charAt(index) != letter) {
                this.remove(word);
            }
        }
    }

    private void yellow(char letter, int index) {
        ArrayList<String> temp = new ArrayList<>(this);
        for (String word : temp) {
            if ((word.charAt(index) == letter) || (!word.contains(letter + ""))) {
                this.remove(word);
            }
        }
    }

    private void grey(char letter, int index) {
        ArrayList<String> temp = new ArrayList<>(this);
        for (String word : temp) {
            if (word.contains("" + letter)) {
                this.remove(word);
            }
        }
    }
}
