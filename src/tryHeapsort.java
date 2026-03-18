public class tryHeapsort {

    private static class Heapsort {
        private int n = 0;
        private int max_size;
        private String[] A;
        private String[] unsortedWords;

        public Heapsort(String[] wordsToSort, boolean build_bottom_up) {
            n = 0;
            max_size = wordsToSort.length;
            unsortedWords = wordsToSort;
            A = new String[max_size];

            if (build_bottom_up)
            //Build the heap bottom up

            else
            //Build the heap top to bottom by inserting each word one by one
            //for (String word : unsortedWords)


        }
    }
}