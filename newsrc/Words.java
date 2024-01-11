package newsrc;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Words extends ArrayList<String> {

    public Words(String filename) throws IOException {
        getDictionary(filename);
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
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    // custom no possible word exception?
    public String generateGuess() throws NoPossibleWords {
        if (this.isEmpty()) {
            throw new NoPossibleWords();
        }

        ArrayList<Character> popularLetters = sortLetters();

        // get all words with first, second...
        ArrayList<String> guesses = new ArrayList<>();
        for (int index=5; index<=26; index++) {
            // 5 combinations of anagram
            String inOrder = getStringRepresentation(popularLetters);
            String anagram = inOrder.substring(0, index);

            System.out.println(anagram);
        }

        String guess = null;


        return guess;
    }

    // sorts the letters in order of number of occurances
    public ArrayList<Character> sortLetters () {

        // count each letter's # of appearences
        HashMap<Character, Integer> map = new HashMap<>();
        for (String w : this) {
			for(char letter : w.toCharArray()) {
                if (map.containsKey(letter)) {
                    map.put(letter, map.get(letter)+1);
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

}
