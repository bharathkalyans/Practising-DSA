import java.util.ArrayList;
import java.util.Arrays;

public class PractiseXI {


    //Mostly DP Problems!
    public static void main(String[] args) {


        int[] w = new int[]{1, 2, 3};
        int[] v = new int[]{4, 5, 1};
        int W = 4;

        int[] subset = new int[]{1, 1, 2, 3};
        System.out.println(CountSubSetWithGivenDifference(subset, 1));

    }


    //Have to use Some Simple Math Concepts to Tackle this Question!
    public static int CountSubSetWithGivenDifference(int[] arr, int diff) {
        int sum = 0;
        for (int x : arr) sum += x;
        int n = arr.length;

        //Best Approach is Below::
        /* SubSet(1) + SubSet(2) = sum
           SubSet(1) - SubSet(2) = diff (Asking in the Question!)
           ------------------------------
           2 * SubSet(1) = sum + diff (Simple Cancellation!)

           SubSet(1) = (sum + diff)/2 = value_sum
           Therefore All you need to do is to find no. of subsets with value_sum!!
        * */

        int value_sum = (sum + diff) / 2;

        int[][] dp = new int[n + 1][value_sum + 1];


        /**
         * if (n == 0) return 0;
         *         if (sum == 0) return 1;
         *
         *         if (arr[n - 1] > sum)
         *             return CountSubSetsWithGivenSum(arr, sum, n - 1);
         *
         *         return CountSubSetsWithGivenSum(arr, sum - arr[n - 1], n - 1) + CountSubSetsWithGivenSum(arr, sum, n - 1);*/


        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < value_sum + 1; j++) {
                if (i == 0) dp[i][j] = 0;
                if (j == 0) dp[i][j] = 1;
            }

        dp[0][0] = 1;


        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < value_sum + 1; j++) {
                if (arr[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
            }
        }


        return dp[n][value_sum];
//        return CountSubSetsWithGivenDifference(arr, n, diff, sum, 0);
    }

    //Recursive Code! Same as in Minimum Difference in SubSet!
    public static int CountSubSetsWithGivenDifference(int[] arr, int n, int diff, int total_sum, int sumCalculated) {
        if (n == 0)
            if ((total_sum - sumCalculated) - sumCalculated == diff) return 1;
            else return 0;

        return CountSubSetsWithGivenDifference(arr, n - 1, diff, total_sum, sumCalculated)
                + CountSubSetsWithGivenDifference(arr, n - 1, diff, total_sum, sumCalculated + arr[n - 1]);
    }

    //https://practice.geeksforgeeks.org/problems/minimum-sum-partition/0
    //Bottom Up Approach! Using Sub Set Problem!
    public static int MinimumSubSetDifference(int[] arr, int n) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        // Normal SubSet Problem Answer
        boolean[][] dp = new boolean[n + 1][totalSum + 1];

        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < totalSum + 1; j++) {
                if (j == 0) dp[i][j] = true;
                if (i == 0) dp[i][j] = false;
            }

        dp[0][0] = true;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < totalSum + 1; j++) {
                if (arr[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
            }
        }

        //Now the last row of the DP Array will be containing all the subsets possible with length == n;
        //Traverse that last row push into a ArrayList! and find the minimum difference!

        ArrayList<Integer> al = new ArrayList<>();

        for (int i = 0; i < totalSum + 1; i++)
            if (dp[n][i])
                al.add(i);


        int minimum_difference = Integer.MAX_VALUE;

        for (int i = 0; i <= al.size() / 2; i++) {
            minimum_difference = Math.abs(Math.min(minimum_difference, totalSum - (2 * al.get(i))));
        }

        return minimum_difference;
    }


    //Recursive Approach!
    public static int MinimumSubSetDifference(int[] arr, int totalSum, int sumCalculatedInSubSet, int n) {
        //Sum of SubSet is sumCalculatedInSubSet and other Subset is totalSum - sumCalculatedInSubSet.
        //So after subtracting each other we will get the required Answer!
        if (n == 0) return Math.abs((totalSum - sumCalculatedInSubSet) - sumCalculatedInSubSet);

        return Math.min(
                MinimumSubSetDifference(arr, totalSum, sumCalculatedInSubSet + arr[n - 1], n - 1),
                MinimumSubSetDifference(arr, totalSum, sumCalculatedInSubSet, n - 1)
        );

    }

    //https://practice.geeksforgeeks.org/problems/perfect-sum-problem5633/1
    //Bottom Up Approach
    public static int CountSubSetsWithGivenSum(int[] arr, int sum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0) dp[i][j] = 0;
                if (j == 0) dp[i][j] = 1;
            }
        }

        dp[0][0] = 1;

        /*for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }*/


        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (arr[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }

    //Recursive Approach!!
    public static int CountSubSetsWithGivenSum(int[] arr, int sum, int n) {

        if (n == 0) return 0;
        if (sum == 0) return 1;

        if (arr[n - 1] > sum)
            return CountSubSetsWithGivenSum(arr, sum, n - 1);

        return CountSubSetsWithGivenSum(arr, sum - arr[n - 1], n - 1) + CountSubSetsWithGivenSum(arr, sum, n - 1);


    }

    //https://practice.geeksforgeeks.org/problems/subset-sum-problem2014/1
    public static boolean EqualSumPartition(int[] arr, int n) {

        int sum = 0;
        for (int x : arr) sum += x;

        if (sum % 2 != 0) return false;

//        return SubSetSum(arr, sum / 2, arr.length);
        return SubSetSumDPTopDownApproach(arr, sum / 2, arr.length);

    }

    // Best Approach for SubSet Sum Problem!
    public static boolean SubSetSumDPTopDownApproach(int[] arr, int sum, int n) {
        boolean[][] dp = new boolean[n + 1][sum + 1];


        //Initialization!
        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0) dp[i][j] = false;
                if (j == 0) dp[i][j] = true;

            }

        //This value will override the false!
        dp[0][0] = true;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (arr[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }


    //Memoization!!
    public static boolean SubSetSumDP(int[] arr, int sum, int n, boolean[][] dp) {
        if (n == 0)
            return false;
        if (sum == 0)
            return dp[n][sum] = true;

        if (arr[n - 1] > sum)
            return dp[n][sum] = SubSetSumDP(arr, sum, n - 1, dp);

        return dp[n][sum] = SubSetSumDP(arr, sum - arr[n - 1], n - 1, dp) || SubSetSumDP(arr, sum, n - 1, dp);

    }

    //https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/?category[]=Dynamic%20Programming&category[]=Dynamic%20Programming&page=4&query=category[]Dynamic%20Programmingpage4category[]Dynamic%20Programming
    // Recursive Approach!
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
