package newsrc;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import newsrc.Words;

public class Main {
    
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        System.out.println("Welcome to wordle-solver, let's get started!\n");
        
        // get all possible answers
        Words words;
        try {
            words = new Words("lib\\possible_answers.txt");
            System.out.println("Successfully loaded dictionary, "+words.size()+" words found.");
            words.generateGuess();
        } catch (NoPossibleWords e) {
            System.out.println(e);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Fatal Error - Library could not be loaded.");
            System.exit(0);
        }

    }
}
