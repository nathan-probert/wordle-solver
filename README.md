# Wordle-Solver

## About the Program

The `wordle-solver` program is designed to accurately guess the Wordle word of the day. It achieves this by utilizing a dictionary of possible answers pulled from the site, and a dictionary of common words that are more likely to appear as the word of the day.

The program employs a strategic approach to make the best possible guess. It counts the appearance of each character and its position in each word, then compares these occurrences. The goal is to make a guess that eliminates as many alternate words as possible, thereby increasing the chances of guessing the correct word.

## Running the Program

Running the `wordle-solver` program is straightforward. Follow these steps:

1. Ensure that you have Java installed on your machine.<br/><br/>
3. First compile the program using (skip this step if you are downloading the .jar release file): <br/>
```gradle build```<br/><br/>
4. Execute the jar using the following command (Use absolute path if you downloaded the release version):<br/>
```java -jar build/libs/wordleSolver.jar```<br/>This is necessary as there is no gui component, double-clicking the jar file will not properly open it.<br/><br/>
3. Follow the prompts in the terminal to use the program.
