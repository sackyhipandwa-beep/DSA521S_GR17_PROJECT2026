// TaskB.java
import java.util.Arrays;

public class TaskB {

    // --- Task B1: Selection Sort ---
    public static void selectionSort(int[] arr) {
        System.out.println("=== TASK B1: SELECTION SORT ===");
        int[] a = arr.clone();
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < a.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                comparisons++;
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = a[i];
                a[i] = a[minIdx];
                a[minIdx] = temp;
                swaps++;
            }

            if (i < 3) {
                System.out.println("After Pass " + (i + 1) + ": " + Arrays.toString(a));
            }
        }
        System.out.println("Final Sorted Array : " + Arrays.toString(a));
        System.out.println("Total Comparisons  : " + comparisons);
        System.out.println("Total Swaps        : " + swaps + "\n");
    }

    // --- Task B2: Insertion Sort ---
    public static void insertionSort(int[] arr) {
        System.out.println("=== TASK B2: INSERTION SORT ===");
        int[] a = arr.clone();
        int comparisons = 0;
        int shifts = 0;

        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;

            if (i <= 3) {
                System.out.println("After Pass " + i + ": " + Arrays.toString(a));
            }
        }
        System.out.println("Final Sorted Array : " + Arrays.toString(a));
        System.out.println("Total Comparisons  : " + comparisons);
        System.out.println("Total Shifts       : " + shifts + "\n");
    }

    // --- Task B3: Merge Sort ---
    private static int mergeComparisons = 0;

    public static void mergeSort(int[] arr) {
        System.out.println("=== TASK B3: MERGE SORT ===");
        int[] a = arr.clone();
        mergeComparisons = 0;
        
        System.out.println("Initial Array: " + Arrays.toString(a));
        mergeSortRecursive(a, 0, a.length - 1);
        
        System.out.println("Final Sorted Array : " + Arrays.toString(a));
        System.out.println("Total Comparisons  : " + mergeComparisons + "\n");
    }

    private static void mergeSortRecursive(int[] a, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortRecursive(a, left, mid);
            mergeSortRecursive(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = a[left + i];
        for (int j = 0; j < n2; j++) R[j] = a[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            mergeComparisons++;
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }

    // --- Task B4: Quick Sort ---
    private static int quickComparisons = 0;
    private static int partitionCount = 0;

    public static void quickSort(int[] arr) {
        System.out.println("=== TASK B4: QUICK SORT ===");
        int[] a = arr.clone();
        quickComparisons = 0;
        partitionCount = 0;

        System.out.println("Pivot Selection Rule: Last Element (a[right])");
        quickSortRecursive(a, 0, a.length - 1);

        System.out.println("Final Sorted Array : " + Arrays.toString(a));
        System.out.println("Total Comparisons  : " + quickComparisons + "\n");
    }

    private static void quickSortRecursive(int[] a, int low, int high) {
        if (low < high) {
            int pi = partition(a, low, high);
            quickSortRecursive(a, low, pi - 1);
            quickSortRecursive(a, pi + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            quickComparisons++;
            if (a[j] < pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;

        partitionCount++;
        if (partitionCount <= 2) {
            System.out.println("Partition " + partitionCount + " [Pivot=" + pivot + "]: " + Arrays.toString(a));
        }

        return i + 1;
    }

    public static void main(String[] args) {
        int[] initialArray = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        selectionSort(initialArray);
        insertionSort(initialArray);
        mergeSort(initialArray);
        quickSort(initialArray);
    }
}