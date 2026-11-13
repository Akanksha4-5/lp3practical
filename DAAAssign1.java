/*
===========================================================
Sr. No. 1
DESIGN AND ANALYSIS OF ALGORITHMS

Title: Non-Recursive and Recursive Fibonacci Program
       with Time and Space Complexity Analysis

CO4

Program: Calculate Fibonacci numbers using recursive and non-recursive methods.
===========================================================
*/

public class DAAAssign1 {
    static long steps = 0;

    // Non-recursive: Time O(n), Space O(1)
    static long fibIterative(int n) {
        long a = 0, b = 1;
        steps++;                       // initialisation
        for (int i = 0; i < n; i++) {
            long next = a + b;
            a = b;
            b = next;
            steps += 3;                // 3 operations per loop
        }
        return a;
    }

    // Recursive: Time O(2^n), Space O(n) (call stack depth)
    static long fibRecursive(int n) {
        steps++;                       // one step per call
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci series (first " + n + " terms):");

        steps = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(fibIterative(i)).append(" ");
        System.out.println("Non-recursive: " + sb);
        steps = 0;
        fibIterative(n);
        System.out.println("Steps for F(" + n + ") non-recursive = " + steps + "  -> Time O(n), Space O(1)");

        steps = 0;
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(fibRecursive(i)).append(" ");
        System.out.println("Recursive    : " + sb);
        steps = 0;
        fibRecursive(n);
        System.out.println("Steps for F(" + n + ") recursive     = " + steps + "  -> Time O(2^n), Space O(n)");
    }
}


/*
==================== OUTPUT ====================
Fibonacci series (first 10 terms):
Non-recursive: 0 1 1 2 3 5 8 13 21 34 
Steps for F(10) non-recursive = 31  -> Time O(n), Space O(1)
Recursive    : 0 1 1 2 3 5 8 13 21 34 
Steps for F(10) recursive     = 177  -> Time O(2^n), Space O(n)
================================================
*/
