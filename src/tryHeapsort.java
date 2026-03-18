public class tryHeapsort {

    private static class Heapsort {
        private int n = 0;
        private int max_size;
        private String[] A;
        private String[] unsortedWords;

        private void swap(int indexA, int indexB) {
            String tempB = A[indexB];

            A[indexB] = A[indexA];
            A[indexA] = tempB;
        }

        private int parent(int childIndex) {
            return childIndex / 2;
        }

        private int left(int parentIndex) {
            return 2 * parentIndex + 1;
        }

        private int right(int parentIndex) {
            return 2 * parentIndex + 2;
        }

        private boolean A_greaterThan_B(String wordA, String wordB) {
            return wordA.compareTo(wordB) > 0;
        }

        private void insert(String word) {
            int i = n++;

            //Heapify upwards until an index, where word's placement in the heap does not violate its priority order, is found to insert word into the heap
            while (i > 0) {
                int parent_i = parent(i);

                if (A_greaterThan_B(word, A[parent_i])) {
                    swap(i, parent_i);
                    i = parent_i;
                } else
                    break;
            }

            A[i] = word;
        }

        private void heapifyDown(int i) {
            int child = left(i);

            while (child < n) {
                int right_child = child + 1;
                if (right_child < n && A_greaterThan_B(A[right_child], A[child]))
                    child = right_child;

                if (A_greaterThan_B(A[child], A[i])) {
                    swap(child, i);
                    i = child;
                    child = left(i);
                } else
                    break;
            }
        }


        private void buildUp() {
            //Construct a new heap all in one go
            for (int i = 0; i < max_size; i++)
                A[i] = unsortedWords[i];
            n = max_size;

            for (int i = (max_size / 2 - 1); i >= 0; i--)
                heapifyDown(i);
        }


        public String[] sort(){
            for (int i = (n - 1); i >= 1; i--){
                swap(i, 0);
                n--;
                heapifyDown(0);
            }

            return A;
        }

        public Heapsort(String[] wordsToSort, boolean build_bottom_up) {
            n = 0;
            max_size = wordsToSort.length;
            unsortedWords = wordsToSort;
            A = new String[max_size];

            if (build_bottom_up)
                //Build the heap bottom up
                buildUp();
            else
                //Build the heap top to bottom by inserting each word one by one
                for (String word : unsortedWords)
                    insert(word);


        }
    }
}