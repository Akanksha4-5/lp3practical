/*
===========================================================
Sr. No. 5
DESIGN AND ANALYSIS OF ALGORITHMS

Title: Analysis of Quick Sort Using Deterministic
       and Randomized Variants

CO4, CO5

Program: Analyze Quick Sort using Deterministic and Randomized variants.
===========================================================
*/

import java.util.Random;

public class DAAAssign5 {
    static long comparisons = 0;
    static Random rand = new Random(42);   // fixed seed -> repeatable demo

    static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++;
            if (a[j] <= pivot) { i++; swap(a, i, j); }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    static int randomizedPartition(int[] a, int low, int high) {
        int r = low + rand.nextInt(high - low + 1);
        swap(a, r, high);                  // random element becomes pivot
        return partition(a, low, high);
    }

    static void deterministicQuickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            deterministicQuickSort(a, low, p - 1);
            deterministicQuickSort(a, p + 1, high);
        }
    }

    static void randomizedQuickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = randomizedPartition(a, low, high);
            randomizedQuickSort(a, low, p - 1);
            randomizedQuickSort(a, p + 1, high);
        }
    }

    static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    static void printArray(int[] a) {
        for (int x : a) System.out.print(x + " ");
        System.out.println();
    }

    static void compare(String name, int[] input) {
        int n = input.length;
        int[] d = input.clone(), r = input.clone();

        comparisons = 0;
        long t = System.nanoTime();
        deterministicQuickSort(d, 0, n - 1);
        double dMs = (System.nanoTime() - t) / 1e6;
        long dComp = comparisons;

        comparisons = 0;
        t = System.nanoTime();
        randomizedQuickSort(r, 0, n - 1);
        double rMs = (System.nanoTime() - t) / 1e6;
        long rComp = comparisons;

        System.out.printf("%-14s | Deterministic: %8d comparisons, %7.2f ms | Randomized: %8d comparisons, %7.2f ms%n",
                name, dComp, dMs, rComp, rMs);
    }

    public static void main(String[] args) {
        int[] arr = {25, 12, 18, 7, 30, 15, 3};
        int[] d = arr.clone(), r = arr.clone();

        System.out.println("Original Array:");
        printArray(arr);

        deterministicQuickSort(d, 0, d.length - 1);
        System.out.println("Sorted Array using Deterministic QuickSort:");
        printArray(d);

        randomizedQuickSort(r, 0, r.length - 1);
        System.out.println("Sorted Array using Randomized QuickSort:");
        printArray(r);

        // Real analysis: n = 2000 elements
        int n = 2000;
        int[] random = new int[n], sorted = new int[n], reverse = new int[n];
        Random g = new Random(1);
        for (int i = 0; i < n; i++) { random[i] = g.nextInt(100000); sorted[i] = i; reverse[i] = n - i; }

        System.out.println("\nAnalysis for n = " + n + ":");
        compare("Random input", random);
        compare("Sorted input", sorted);
        compare("Reverse input", reverse);
    }
}


/*
==================== OUTPUT ====================
Original Array:
25 12 18 7 30 15 3 
Sorted Array using Deterministic QuickSort:
3 7 12 15 18 25 30 
Sorted Array using Randomized QuickSort:
3 7 12 15 18 25 30 

Analysis for n = 2000:
Random input   | Deterministic:    24467 comparisons,    1.34 ms | Randomized:    23831 comparisons,    1.43 ms
Sorted input   | Deterministic:  1999000 comparisons,   15.39 ms | Randomized:    24507 comparisons,    0.16 ms
Reverse input  | Deterministic:  1999000 comparisons,    8.71 ms | Randomized:    24202 comparisons,    0.57 ms
================================================
*/
