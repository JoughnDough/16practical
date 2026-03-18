//4574790  Ungweru Nyirenda
//CSC 211 Practical 6
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final int NUMBER_OF_WORDS = 264293;
    private static final int SAMPLE_SIZE = 18;

    private static final int SUBSET_SIZE = 80000;
    private static final int NUMBER_OF_TIMING_TRIALS = 24;

    private static final double NANO_TO_MILI = Math.pow(10, -6);

    private static String[] randomSubset(String[] words){
        int startIndex = (int) (Math.random() * (NUMBER_OF_WORDS - SUBSET_SIZE - 1));
        int endIndex  = startIndex + SUBSET_SIZE - 1;

        String[] subset = new String[SUBSET_SIZE];

        int index = 0;
        for (int i = startIndex; i <= endIndex; i++)
            subset[index++] = words[i];

        return subset;
    }

    private static double nano_to_miliseconds(double time_in_nanoseconds){
        return (double) Math.round(time_in_nanoseconds * NANO_TO_MILI * 100)/100;
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



            double total_BuildBottomUp_sortTime = 0;
            double total_BuildTopDown_sortTime = 0;

            for (int i = 1; i <= NUMBER_OF_TIMING_TRIALS; i ++){
                String[] arrayOfwords = randomSubset(words);

                long now = System.nanoTime();
                tryHeapsort.sort(arrayOfwords, true);
                total_BuildBottomUp_sortTime += System.nanoTime() - now;

                now = System.nanoTime();
                tryHeapsort.sort(arrayOfwords, false);
                total_BuildTopDown_sortTime += System.nanoTime() - now;
            }

            double avg_BuildBottomUp_sortTime    = nano_to_miliseconds(total_BuildBottomUp_sortTime/NUMBER_OF_TIMING_TRIALS);
            double avg_BuildTopToBottom_sortTime = nano_to_miliseconds(total_BuildTopDown_sortTime/NUMBER_OF_TIMING_TRIALS);

            System.out.println("\n ----------------------------------------------------");
            System.out.println("Avg sort time of heap sort with heaps built from the bottom up: " + avg_BuildBottomUp_sortTime + "ms");
            System.out.println("Avg sort time of heap sort with heaps built from the top down:  " + avg_BuildTopToBottom_sortTime + "ms");


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}