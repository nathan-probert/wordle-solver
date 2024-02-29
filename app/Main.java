package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            words = new Words("possible_answers.txt");
            System.out.println("Successfully loaded dictionary, " + words.size() + " words found.");

            gameOn(words);

        } catch (IOException e) {
            System.out.println("Fatal Error - Library could not be loaded.");
            System.exit(0);
        }

    }

    private void gameOn(Words words) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                int possible = words.countPossible();
                if (possible == 0) {
                    System.out.println("No such word exists.\n");
                    break;
                } else if (possible == 1) {
                    System.out.println("Congratulations! The only possible word is '" + words.generateGuess() + "'!\n");
                    break;
                }
                String generatedGuess = words.generateGuess();
                System.out.println("Your Guess: " + generatedGuess);

                // get guess
                System.out.print("Please enter your guess (or 0 for the given guess): ");
                boolean repeat = true;
                while (repeat) {
                    repeat = false;
                    try {
                        String guess = stdin.readLine();
                        if (guess.equals("0")) {
                            guess = generatedGuess;
                        } else if (guess.length() != 5) {
                            repeat = true;
                        }

                        if (!repeat) {
                            words.updateWordList(guess);
                        }
                    } catch (IOException e) {
                        repeat = true;
                    }
                }
            }
        } catch (NoPossibleWords e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
