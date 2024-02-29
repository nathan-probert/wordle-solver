package app;

import java.util.ArrayList;

public class Permutations {
    private char[] array;
    private int numElements;
    private boolean allowDuplicates;

    public Permutations(char[] array, int numElements, boolean allowDuplicates) {
        this.array = array;
        this.numElements = numElements;
        this.allowDuplicates = allowDuplicates;
    }

    public ArrayList<String> permute() {
        ArrayList<String> result = new ArrayList<>();
        permute("", array, numElements, result);
        return result;
    }

    private void permute(String prefix, char[] array, int numElements, ArrayList<String> result) {
        if (numElements == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < array.length; i++) {
                if (!allowDuplicates && prefix.indexOf(array[i]) != -1)
                    continue;
                permute(prefix + array[i], array, numElements - 1, result);
            }
        }
    }
}
