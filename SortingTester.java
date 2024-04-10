import java.util.*;

public class SortingTester{
    public final static int MAX_RAND = 1000;
    

    private static int[] copyArr(int[] arr){
        int n = arr.length;
        int[] ret = new int[n];
        for(int i = 0; i < n; i++) ret[i] = arr[i];
        return ret;
    }
    // Test quicksort on an array - returns execution time
    private static long runQuicksort(int[] arr){
        String quicksortPreface = new String("Quicksort: \n");
        System.out.println(quicksortPreface);
        SortingAlgorithms.printArray(arr);
        long startTime = System.nanoTime();
        SortingAlgorithms.quicksort(arr);
        long stopTime = System.nanoTime();
        SortingAlgorithms.printArray(arr);
        System.out.println("Quicksort execution time (nanoseconds): " + (stopTime - startTime) );
        return (stopTime - startTime);
    }

    // Test merge sort, return execution time in nanoseconds
    private static long runMergesort(int[] arr){
        String mergesortPreface = new String("Merge Sort: \n");
        System.out.println(mergesortPreface);
        SortingAlgorithms.printArray(arr);
        long startTime = System.nanoTime();
        SortingAlgorithms.mergesort(arr);
        long stopTime = System.nanoTime();
        SortingAlgorithms.printArray(arr);
        System.out.println("Merge sort execution time (nanoseconds): " + (stopTime - startTime) );
        return (stopTime - startTime);
    }

    public static void main(String[] args){;

        Scanner s = new Scanner(System.in);
        Random rand = new Random();
        String enterFreeMode = new String("Now, here's your chance to play around with these algorithms.");
        String prompt = new String("Enter the desired size for an array filled with random integers to be sorted (0 to exit):\n> ");
        int[] currentCase;
        int testSize = 0;
        int mergeSortWs = 0;
        int quickSortWs = 0;

        // Begin pre-defined test cases
        int[] oneElement = {1};
        currentCase = oneElement;
        System.out.println("First, an array of one element.");
        if(runMergesort(currentCase) < runQuicksort(copyArr(currentCase))) mergeSortWs++;
        else quickSortWs++;

        int[] smallArray = {3, 1, 4, 1, 5};
        currentCase = smallArray;
        System.out.println("Next, five elements.");
        if(runMergesort(currentCase) < runQuicksort(copyArr(currentCase))) mergeSortWs++;
        else quickSortWs++;

        int[] cutoffSizeArray = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
        currentCase = cutoffSizeArray;
        System.out.println("Next, an array of the cutoff size - 15 elements. From here on out, merge sort must recurse.");
        if(runMergesort(currentCase) < runQuicksort(copyArr(currentCase))) mergeSortWs++;
        else quickSortWs++;

        int[] centMergeRandomInts = new int[100];
        int[] centQuickRandomInts = new int[100];
        int r = 0;
        
        for(int i = 0; i < 100; i++){
            r = rand.nextInt(MAX_RAND);
            centMergeRandomInts[i] = r;
            centQuickRandomInts[i] = r;
        }
        System.out.println("Next, a random array of size 100.");
        if(runMergesort(centMergeRandomInts) < runQuicksort(centQuickRandomInts)) mergeSortWs++;
        else quickSortWs++;

        
        int[] mediumAlmostArray = new int[100];
        // Now an "almost" sorted array. Flipping first and last elements.
        mediumAlmostArray[0] = 99; mediumAlmostArray[99] = 0;
        for(int i = 1; i < 99; i++){
            mediumAlmostArray[i] = i;
        }
        currentCase = mediumAlmostArray;
        System.out.println("100 Nearly sorted elements. You'll see what I mean.");
        if(runMergesort(currentCase) < runQuicksort(copyArr(currentCase))) mergeSortWs++;
        else quickSortWs++;

        
        // These tests were too big for the chosen cutoff size.

        /*
        int[] kiloMergeRandomInts = new int[1000];
        int[] kiloQuickRandomInts = new int[1000];
        
        for(int i = 0; i < 1000; i++){
            r = rand.nextInt(MAX_RAND);
            kiloMergeRandomInts[i] = r;
            kiloQuickRandomInts[i] = r;
        }
        System.out.println("1000 random elements.");
        if(runMergesort(kiloMergeRandomInts) < runQuicksort(kiloQuickRandomInts)) mergeSortWs++;
        else quickSortWs++;

        int[] largeAlmostArray = new int[100];
        // Now an "almost" sorted large array. Flipping element 100 and element 500.
        for(int i = 1; i < 1000; i++){
            largeAlmostArray[i] = i;
        }
        largeAlmostArray[100] = 500; largeAlmostArray[500] = 100;
        currentCase = largeAlmostArray;
        System.out.println("1000 Nearly sorted elements. Madness.");
        if(runMergesort(currentCase) < runQuicksort(copyArr(currentCase))) mergeSortWs++;
        else quickSortWs++;
        */

        // Output findings on who's the winning algorithm
        String winner = (mergeSortWs > quickSortWs) ? "Merge sort, with " : "Quicksort, with "; // Determine who's the winner
        int winnerWins = (mergeSortWs > quickSortWs) ? mergeSortWs : quickSortWs; // Get appropriate number of winner wins (tie handled too)
        if(mergeSortWs == quickSortWs) winner = "nobody, with both algorithms scoring "; // Set tie text if applicable

        System.out.println("The results are in. The winner was " + winner + winnerWins + " wins.");

        // Now that tests are done, enter free mode
        System.out.println(enterFreeMode);
        // Ask user for test size to make a random int array for
        while(true) {
            System.out.print(prompt);
            testSize = s.nextInt();

            // Test exit condition (negative length also qualifies for leaving)
            if(testSize <= 0) break;

            int[] mergesortRandomInts = new int[testSize];
            int[] quicksortRandomInts = new int[testSize];
            int k = 0;

            // Make two identical copies of random int arrays
            for(int i = 0; i < testSize; i++){
                k = rand.nextInt(MAX_RAND);
                mergesortRandomInts[i] = k;
                quicksortRandomInts[i] = k;
            }

            // Sort the arrays
            if( runQuicksort(quicksortRandomInts) < runMergesort(mergesortRandomInts)){
                System.out.println("Here, quicksort was faster.");
            } else {
                System.out.println("Here, merge sort was faster.");
            }
        }
        s.close();
    }
}