public class SortingAlgorithms{
    private static final int CUTOFF = 15; // Cutoff for quick sort

    // Insertion sort - used at the quicksort cutoff.
    private static void insertionSort(int[] arr){
        int n = arr.length;
        // For each item in list, if it's greater than the next, swap
        for(int i = 0; i < n; i++){
            int comp = arr[i];
            int j = i - 1;

            // Optimized bubble sort essentially - shuffle to make space for comp (its own little home)
            while(j >= 0 && arr[j] > comp){
                arr[j+1] = arr[j]; // Not a swap, a shift
                j--;
            }
            // Keep the correct item in its correct little home
            arr[j+1] = comp;
        }
    }
    
    // The aim of this method is to find the median of the passed int[] and sort the left, right, and median elements in the process.
    private static int medianOfThree(int[] arr, int left, int right){
        // This approach sorts the list as we find the median. It's very human readable, so I won't be adding redundant comments.
        int mid = (left + right) / 2;
        if(arr[right] < arr[left]) swap(arr, left, right);
        if(arr[mid] < arr[left]) swap(arr, mid, left);
        if(arr[right] < arr[mid]) swap(arr, right, mid);
        
        // Pivot hiding
        swap(arr, mid, right-1);
        return arr[right-1]; // Returning an actual value of arr (the pivot)
    }

    // Standard swap helper function for any sorting algorithm - swap elements at index a and b in arr
    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return;
    }

    // Quicksort implementation using median-of-three partitioning and a cutoff of 15.
    private static void quicksort(int[] arr, int left, int right){
        // Cutoff early exit - effective base case
        if(left + CUTOFF > right){
            insertionSort(arr);
            return;
        }

        // Get pivot from medianOfThree
        int pivot = medianOfThree(arr, left, right);

        int i = left;
        int j = right-1;
        // Actually quicksort
        for( ; ; ){
            while(arr[++i] >= pivot){ }
            while(arr[--j] <= pivot){ }
            if(i < j)
                swap(arr, i, j);
            else
                break;
        }

        // Recurse
        quicksort(arr, left, i-1);
        quicksort(arr, i+1, right);
    }

    // Recursively defined "divide" of the mergesort divide and conquer
    // break up into sub-arrays that are gonna get merged
    private static void mergesort(int[] arr, int[] temp, int left, int right){
        if(left >= right){
            return;
        }

        int mid = (left + right) / 2;

        // Divide
        mergesort(arr, temp, left, mid);
        mergesort(arr, temp, mid + 1, right);
        // Conquer
        merge(arr, temp, left, mid + 1, right);
    }

    private static void merge(int[] arr, int[] temp, int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int n = rightEnd - leftPos + 1;

        while(leftPos <= leftEnd && rightPos <= rightEnd){
            if(arr[leftPos] < arr[rightPos]) temp[tempPos++] = arr[leftPos++];
            else temp[tempPos++] = arr[rightPos++];
        }

        while(leftPos <= leftEnd) temp[tempPos++] = arr[leftPos++];
        while(rightPos <= rightEnd) temp[tempPos++] = arr[rightPos++];

        for(int i = 0; i < n; i++, rightEnd--){
            arr[rightEnd] = temp[rightEnd];
        }
    }

    // Quicksort initialization wrapper(to abstract the method in the driver code)
    public static void quicksort(int[] arr){
        quicksort(arr, 0, arr.length-1);
    }

    // Mergesort initialization wrapper(to abstract the method in the driver code)
    public static void mergesort(int[] arr){
        int[] temp = new int[arr.length];
        mergesort(arr, temp, 0, arr.length-1);
    }

    // public-facing method to print the entire array, and in the process double check it was correctly sorted.
    public static void printArray(int[] arr){
        boolean sorted = true;
        System.out.print("[");
        for(int i = 0; i < arr.length-1; i++){
            if(arr[i] > arr[i+1]) sorted = false;
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length-1] + "]\n");
        System.out.println("The array is sorted: " + sorted + '\n');
    }
}