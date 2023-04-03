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
}
