/*
===========================================================
Sr. No. 3
DESIGN AND ANALYSIS OF ALGORITHMS

Title: 0-1 Knapsack Problem Using Dynamic Programming

CO4, CO5

Program: Solve the 0-1 Knapsack problem using dynamic programming.
===========================================================
*/

public class DAAAssign3 {
    public static void main(String[] args) {
        int[] wt  = {10, 20, 30};
        int[] val = {60, 100, 120};
        int W = 50, n = wt.length;

        // dp[i][w] = best value using first i items with capacity w
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                dp[i][w] = dp[i - 1][w];                       // skip item
                if (wt[i - 1] <= w)                            // take item
                    dp[i][w] = Math.max(dp[i][w], val[i - 1] + dp[i - 1][w - wt[i - 1]]);
            }
        }

        System.out.println("0-1 Knapsack (Dynamic Programming)");
        System.out.println("Maximum value = " + dp[n][W]);

        // trace back chosen items
        System.out.print("Items selected (weight:value) = ");
        int w = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print("(" + wt[i - 1] + ":" + val[i - 1] + ") ");
                w -= wt[i - 1];
            }
        }
        System.out.println();
        System.out.println("Time O(n*W), Space O(n*W)");
    }
}


/*
==================== OUTPUT ====================
0-1 Knapsack (Dynamic Programming)
Maximum value = 220
Items selected (weight:value) = (30:120) (20:100) 
Time O(n*W), Space O(n*W)
================================================
*/
