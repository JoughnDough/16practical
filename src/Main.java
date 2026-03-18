import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        try (BufferedReader br = new BufferedReader(new FileReader("joyce1922_ulysses.text"))) {

            String line;
            int numOfWords = 0;
            while ((line = br.readLine()) != null) {
                //Separate the line into words
                String[] raw_words = line.split("\\s+");

                for (String w : raw_words) {
                    //Clean up the word
                    w = w.replaceAll("[0123456789(),.;:_!?-]", "");
                    w = w.toLowerCase();

                    if (!w.isEmpty()) {
                        //If 'w' is actually a word, add it to the arrays
                        System.out.println(w);
                        numOfWords++;
                    }
                }
            }

            System.out.println("There are " + numOfWords + " words");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}