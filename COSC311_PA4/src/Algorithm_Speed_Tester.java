import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Algorithm_Speed_Tester {

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }


    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static void mergesort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergesort(arr, l, m);
            mergesort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static void heapSort(int[] arr)
    {

        // Build a heap from the input array
        buildHeap(arr);

        // Repeat until the heap contains only one element
        for (int i = arr.length - 1; i >= 1; i--)
        {

            // Swap the root element with the last element
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Remove the last element (which is now in the correct position)
            int heapSize = i;
            heapify(arr, 0, heapSize);
        }

        // Reverse the order of the elements to obtain the sorted array
        //reverseArray(arr);
    }

    public static void buildHeap(int[] arr)
    {

        // Build a max heap from the input array
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
    }

    public static void heapify(int[] arr, int i, int heapSize)
    {

        // Heapify the subtree rooted at i in the input array
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, largest, heapSize);
        }
    }

    public static void reverseArray(int[] arr) {
        // Reverse the order of the elements to obtain the sorted array
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }


    public static void main(String args[]) {
        Scanner kbInput = new Scanner(System.in);
        System.out.println("Enter a File Name for the sorted files: ");

        String fileName = kbInput.next();
        int[] ascendingArr = new int[10000];
        int[] descendingArr = new int[10000];
        int[] randArr = new int[10000];

        /*Creating all the arrays for ascending, descending, and random data  */
        try {

            File fileAscending = new File("dataAscend.txt");
            Scanner dataAscending = new Scanner(fileAscending);
            int counter = 0;
            while (dataAscending.hasNext()) {
                ascendingArr[counter] = Integer.parseInt(dataAscending.next());
                counter++;
            }


            File fileDescending = new File("dataDescend.txt");
            Scanner dataDescending = new Scanner(fileDescending);
            counter = 0;
            while (dataDescending.hasNext()) {
                descendingArr[counter] = Integer.parseInt(dataDescending.next());
                counter++;
            }

            File fileRand = new File("dataRandom.txt");
            Scanner dataRand = new Scanner(fileRand);
            counter = 0;
            while (dataRand.hasNext()) {
                randArr[counter] = Integer.parseInt(dataRand.next());
                counter++;
            }

            long quickStart = System.nanoTime();
            quickSort(ascendingArr, 0 , ascendingArr.length - 1);
            long quickEnd = System.nanoTime();

            File quicksortAscending = new File(fileName+"_quicksortAscending.txt");
            quicksortAscending.createNewFile();
            FileWriter writer1 = new FileWriter(fileName + "_quicksortAscending.txt");
            for(int i = 0; i<ascendingArr.length; i++){
                writer1.write(ascendingArr[i] + "\n");
            }
            writer1.close();

            long heapStart = System.nanoTime();
            heapSort(ascendingArr);
            long heapEnd = System.nanoTime();

            File heapsortAscending = new File(fileName+"_heapsortAscending.txt");
            heapsortAscending.createNewFile();
            FileWriter writer4 = new FileWriter(fileName + "_heapsortAscending.txt");
            for(int i = 0; i<ascendingArr.length; i++){
                writer4.write(ascendingArr[i]+"\n");
            }
            writer4.close();

            long mergeStart = System.nanoTime();
            mergesort(ascendingArr, 0, ascendingArr.length -1);
            long mergeEnd = System.nanoTime();

            File mergesortAscending = new File(fileName+"_mergesortAscending.txt");
            mergesortAscending.createNewFile();
            FileWriter writer7 = new FileWriter(fileName + "_mergesortAscending.txt");
            for(int i = 0; i<ascendingArr.length; i++){
                writer7.write(ascendingArr[i] + "\n");

            }
            writer7.close();

            System.out.println("Ascending heap time in nano seconds: " + (heapEnd - heapStart ));
            System.out.println("Ascending quick time in nano seconds: " + (quickEnd - quickStart));
            System.out.println("Ascending merge time in nano seconds: " + (mergeEnd - mergeStart));
            System.out.println(" ");

            quickStart = System.nanoTime();
            quickSort(descendingArr, 0 , ascendingArr.length - 1);
            quickEnd = System.nanoTime();

            File quicksortDescending = new File(fileName+"_quicksortDescending.txt");
            quicksortAscending.createNewFile();
            FileWriter writer2 = new FileWriter(fileName + "_quicksortDescending.txt");
            for(int i = 0; i<descendingArr.length; i++){
                writer2.write(String.valueOf(descendingArr[i]) + "\n");
            }
            writer2.close();

            heapStart = System.nanoTime();
            heapSort(descendingArr);
            heapEnd = System.nanoTime();

            File heapsortDescending = new File(fileName+"_heapsortDescending.txt");
            heapsortDescending.createNewFile();
            FileWriter writer5 = new FileWriter(fileName + "_heapsortDescending.txt");
            for(int i = 0; i<descendingArr.length; i++){
                System.out.println(descendingArr[i]);
                writer5.write(descendingArr[i] + "\n");

            }
            writer5.close();

            mergeStart = System.nanoTime();
            mergesort(descendingArr, 0, ascendingArr.length -1);
            mergeEnd = System.nanoTime();

            File mergesortDescending = new File(fileName+"_mergesortDescending.txt");
            mergesortDescending.createNewFile();
            FileWriter writer8 = new FileWriter(fileName + "_mergesortDescending.txt");
            for(int i = 0; i<descendingArr.length; i++){
                writer8.write(descendingArr[i]+"\n");
            }
            writer8.close();

            System.out.println("Descending heap time in nano seconds: " + (heapEnd - heapStart ));
            System.out.println("Descending quick time in nano seconds: " + (quickEnd - quickStart));
            System.out.println("Descending merge time in nano seconds: " + (mergeEnd - mergeStart));
            System.out.println(" ");

            quickStart = System.nanoTime();
            quickSort(randArr, 0 , ascendingArr.length - 1);
            quickEnd = System.nanoTime();

            File quicksortRand = new File(fileName+"_quicksortRandom.txt");
            quicksortRand.createNewFile();
            FileWriter writer3 = new FileWriter(fileName + "_quicksortRandom.txt");
            for(int i = 0; i<randArr.length; i++){
                writer3.write(descendingArr[i]+"\n");
            }
            writer3.close();

            heapStart = System.nanoTime();
            heapSort(randArr);
            heapEnd = System.nanoTime();

            File heapsortRandom = new File(fileName+"_heapsortRandom.txt");
            heapsortRandom.createNewFile();
            FileWriter writer6 = new FileWriter(fileName + "_heapsortRandom.txt");
            for(int i = 0; i<randArr.length; i++){
                writer6.write(randArr[i]+"\n");
            }
            writer6.close();

            mergeStart = System.nanoTime();
            mergesort(randArr, 0, ascendingArr.length -1);
            mergeEnd = System.nanoTime();

            File mergesortRandom = new File(fileName+"_mergesortRandom.txt");
            mergesortRandom.createNewFile();
            FileWriter writer9 = new FileWriter(fileName + "_mergesortRandom.txt");
            for(int i = 0; i<randArr.length; i++){
                writer9.write(randArr[i]+"\n");
            }
            writer9.close();

            System.out.println("Random heap time in nano seconds: " + (heapEnd - heapStart ));
            System.out.println("Random quick time in nano seconds: " + (quickEnd - quickStart));
            System.out.println("Random merge time in nano seconds: " + (mergeEnd - mergeStart));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
