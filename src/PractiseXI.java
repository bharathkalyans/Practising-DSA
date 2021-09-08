import java.util.Arrays;

public class PractiseXI {


    //Mostly DP Problems!
    public static void main(String[] args) {


        int[] w = new int[]{1, 3, 4, 5};
        int[] v = new int[]{1, 4, 5, 7};
        int W = 7;

        System.out.println("Weight :: " + KnapSack01Problem(w, v, W, w.length));
    }


    //Recursive Approach
    public static int KnapSack01Problem(int[] weight, int[] value, int W, int n) {
        if (n == 0 || W == 0) return 0;

        if (weight[n - 1] > W)
            return KnapSack01Problem(weight, value, W, n - 1);

        return Math.max(value[n - 1] + KnapSack01Problem(weight, value, W - weight[n - 1], n - 1), KnapSack01Problem(weight, value, W, n - 1));
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
