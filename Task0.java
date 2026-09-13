// TaskC.java
import java.util.Random;

public class TaskC {

    private static long compCount = 0;

    public static void main(String[] args) {
        int[] sizes = {20, 50, 100, 500};
        Random rand = new Random(42);

        System.out.println("==========================================================================");
        System.out.println("                     PART C: ALGORITHM EXPERIMENT RESULTS                 ");
        System.out.println("==========================================================================");
        System.out.printf("%-16s | %-10s | %-18s | %-18s\n", "Algorithm", "Input Size", "Comparisons", "Execution Time (ns)");
        System.out.println("--------------------------------------------------------------------------");

        for (int n : sizes) {
            int[] original = new int[n];
            for (int i = 0; i < n; i++) {
                original[i] = rand.nextInt(1000);
            }

            runBenchmark("Selection Sort", original, "selection");
            runBenchmark("Insertion Sort", original, "insertion");
            runBenchmark("Merge Sort",     original, "merge");
            runBenchmark("Quick Sort",     original, "quick");
            System.out.println("--------------------------------------------------------------------------");
        }

        // --- Almost-Sorted Array Test (Size 100) ---
        System.out.println("\n==========================================================================");
        System.out.println("             ALMOST-SORTED ARRAY TEST (100 Elements, 5 Swaps)            ");
        System.out.println("==========================================================================");
        
        int[] almostSorted = new int[100];
        for (int i = 0; i < 100; i++) almostSorted[i] = i * 2;
        
        for (int i = 0; i < 5; i++) {
            int idx = i * 15 + 5;
            int temp = almostSorted[idx];
            almostSorted[idx] = almostSorted[idx + 1];
            almostSorted[idx + 1] = temp;
        }

        System.out.printf("%-16s | %-10s | %-18s | %-18s\n", "Algorithm", "Input Size", "Comparisons", "Execution Time (ns)");
        System.out.println("--------------------------------------------------------------------------");
        runBenchmark("Selection Sort", almostSorted, "selection");
        runBenchmark("Insertion Sort", almostSorted, "insertion");
        runBenchmark("Merge Sort",     almostSorted, "merge");
        runBenchmark("Quick Sort",     almostSorted, "quick");
        System.out.println("==========================================================================\n");
    }

    private static void runBenchmark(String name, int[] original, String algo) {
        int[] copy = original.clone();
        compCount = 0;

        long startTime = System.nanoTime();
        switch (algo) {
            case "selection": selectionSort(copy); break;
            case "insertion": insertionSort(copy); break;
            case "merge":     mergeSort(copy, 0, copy.length - 1); break;
            case "quick":     quickSort(copy, 0, copy.length - 1); break;
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.printf("%-16s | %-10d | %-18d | %-18d\n", name, original.length, compCount, duration);
    }

    private static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                compCount++;
                if (a[j] < a[minIdx]) minIdx = j;
            }
            int temp = a[i];
            a[i] = a[minIdx];
            a[minIdx] = temp;
        }
    }

    private static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0) {
                compCount++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else break;
            }
            a[j + 1] = key;
        }
    }

    private static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        int[] L = new int[n1], R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = a[left + i];
        for (int j = 0; j < n2; j++) R[j] = a[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            compCount++;
            if (L[i] <= R[j]) a[k++] = L[i++];
            else a[k++] = R[j++];
        }
        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pi = partition(a, low, high);
            quickSort(a, low, pi - 1);
            quickSort(a, pi + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            compCount++;
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
        return i + 1;
    }
}