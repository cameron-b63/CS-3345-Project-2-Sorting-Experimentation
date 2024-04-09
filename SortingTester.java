import java.util.*;

public class SortingTester{
    public final static int MAX_RAND = 1000;
    
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
        // Normal test cases go here
        String prompt = new String("Enter the desired size for an array filled with random integers to be sorted (0 to exit):\n> ");
        int testSize = 0;

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