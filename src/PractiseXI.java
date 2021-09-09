import java.util.Arrays;

public class PractiseXI {


    //Mostly DP Problems!
    public static void main(String[] args) {


        int[] w = new int[]{1, 2, 3};
        int[] v = new int[]{4, 5, 1};
        int W = 4;

        int[] subset = new int[]{2, 3, 5, 7, 81, 10};
        System.out.println(SubSetSum(subset, 11, subset.length));

    }


    public static boolean SubSetSum(int[] arr, int sum, int n) {
        if (n == 0)
            return false;
        if (sum == 0) return true;

        if (arr[n - 1] > sum)
            return SubSetSum(arr, sum, n - 1);

        return SubSetSum(arr, sum - arr[n - 1], n - 1) || SubSetSum(arr, sum, n - 1);
    }

    static int[][] dp = new int[1001][1001];

    public static int KnapSack01(int W, int wt[], int val[], int n) {
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return KnapSack01Problem(wt, val, W, n);
    }

    public static int KnapSack01BottomUpApproach(int[] weight, int[] value, int W, int n) {

        int[][] dp = new int[n + 1][W + 1];

        // Recursive --> Memoization and then Bottom Up Approach!

        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < W + 1; j++)
                if (i == 0 || j == 0)
                    dp[i][j] = 0;


        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < W + 1; j++) {
                if (weight[i - 1] <= j) {
                    //Assume here i is n and j is W!
                    dp[i][j] = Math.max(value[i - 1] + dp[i - 1][j - weight[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[n - 1][j];
                }
            }

        return dp[n][W];
    }

    //Recursive Approach
    public static int KnapSack01Problem(int[] weight, int[] value, int W, int n) {
        if (n == 0 || W == 0) return 0;

        if (dp[n][W] != -1) return dp[n][W];

        if (weight[n - 1] > W)
            return dp[n][W] = KnapSack01Problem(weight, value, W, n - 1);

        return dp[n][W] = Math.max(value[n - 1] + KnapSack01Problem(weight, value, W - weight[n - 1], n - 1), KnapSack01Problem(weight, value, W, n - 1));
    }

    public static void SubSequenceOfAString(String str, StringBuilder sb, int index) {

        for (int i = index; i < str.length(); i++) {
            sb.append(str.charAt(i));
            System.out.println(sb);
            SubSequenceOfAString(str, sb, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    //TODO Have to Optimize the LCS!!
    public static int LongestCommonSubSequence(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int[] array : dp)
            Arrays.fill(array, -1);

        return LongestCommonSubSequenceUtil(x, y, m, n, dp);
    }

    //Main Method! (Memoization)
    public static int LongestCommonSubSequenceUtil(String x, String y, int m, int n, int[][] dp) {


        if (m == 0 || n == 0)
            return 0;

        if (dp[m - 1][n - 1] != -1)
            return dp[m - 1][n - 1];

        if (x.charAt(m - 1) == y.charAt(n - 1))
            dp[m - 1][n - 1] = 1 + LongestCommonSubSequenceUtil(x, y, m - 1, n - 1, dp);

        return dp[m - 1][n - 1] = Math.max(
                LongestCommonSubSequenceUtil(x, y, m, n - 1, dp),
                LongestCommonSubSequenceUtil(x, y, m - 1, n, dp)
        );

    }

    //Time Complexity is O(2^N)!
    public static int LongestCommonSubSequence(String x, String y, int m, int n) {
        if (m == 0 || n == 0) return 0;

        if (x.charAt(m - 1) == y.charAt(n - 1))
            return 1 + LongestCommonSubSequence(x, y, m - 1, n - 1);

        return Math.max(
                LongestCommonSubSequence(x, y, m - 1, n),
                LongestCommonSubSequence(x, y, m, n - 1));

    }

}
