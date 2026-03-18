//4574790  Ungweru Nyirenda
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final int NUMBER_OF_WORDS = 264293;
    private static final int SAMPLE_SIZE = 18;

    private static final int SUBSET_SIZE = 15;

    private static String[] randomSubset(String[] words){
        int startIndex = (int) (Math.random() * (NUMBER_OF_WORDS - SUBSET_SIZE - 1));
        int endIndex  = startIndex + SUBSET_SIZE - 1;

        String[] subset = new String[SUBSET_SIZE];

        int index = 0;
        for (int i = startIndex; i <= endIndex; i++)
            subset[index++] = words[i];

        return subset;
    }

    public static void main(String[] args) {


        try (BufferedReader br = new BufferedReader(new FileReader("joyce1922_ulysses.text"))) {
            String[] words = new String[NUMBER_OF_WORDS];
            String[] sample = new String[SAMPLE_SIZE];

            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                //Separate the line into words
                String[] raw_words = line.split("\\s+");

                for (String w : raw_words) {
                    //Clean up the word
                    w = w.replaceAll("[0123456789(),.;:_!?-]", "");
                    w = w.toLowerCase();

                    if (!w.isEmpty()) {
                        //If 'w' is actually a word, add it to the arrays
                        if (index < SAMPLE_SIZE)
                            sample[index] = w;

                        words[index++] = w;
                    }
                }
            }

            String[] sortedSampleA = tryHeapsort.sort(sample, true);
            String[] sortedSampleB = tryHeapsort.sort(sample, false);

            System.out.println("\nHeap sort with the heap being built bottom up:");
            System.out.println(Arrays.toString(sortedSampleA));
            System.out.println("Heap sort with the heap being built top down:");
            System.out.println(Arrays.toString(sortedSampleB) + "\n");



            for (int i = 0; i < 4; i++)
                System.out.println("subset: " + Arrays.toString(randomSubset(words)));

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}