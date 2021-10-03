import java.util.ArrayList;
import java.util.Arrays;


public class PractiseXI {

    static int[][] dp = new int[201][201];
    static int[][][] DP = new int[201][201][2];

    //Mostly DP Problems!
    public static void main(String[] args) {

        int[] arr = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(LongestIncreasingSubSequence(arr, arr.length));


    }


    /***This Below problems belong to  TMatrix Chain Multiplication Category! **/


    // Memoization Problem!
    public static int EggDroppingProblemDP(int floors, int eggs) {
        if (floors == 0 || floors == 1) return floors;
        if (eggs == 1) return floors;

        if (dp[floors][eggs] != -1) return dp[floors][eggs];

        int min_trails = Integer.MAX_VALUE;
        for (int k = 1; k <= floors; k++) {
            // I am considering here math.max because in question they are asking the worst case possible!
            // Hence, using Math.max!
            int temp_ans = 1 + Math.max(
                    EggDroppingProblemDP(floors - k, eggs),
                    EggDroppingProblemDP(k - 1, eggs - 1));

            min_trails = Math.min(min_trails, temp_ans);
        }


        return dp[floors][eggs] = min_trails;
    }

    //https://practice.geeksforgeeks.org/problems/egg-dropping-puzzle/0
    //Recursive Approach
    public static int EggDroppingProblem(int floors, int eggs) {
        if (floors == 0 || floors == 1) return eggs;
        if (eggs == 1) return floors;

        int min_trails = Integer.MAX_VALUE;
        for (int k = 1; k <= floors; k++) {
            // I am considering here math.max because in question they are asking the worst case possible!
            // Hence, using Math.max!
            int temp_ans = 1 + Math.max(
                    EggDroppingProblem(floors - k, eggs),
                    EggDroppingProblem(k - 1, eggs - 1));

            min_trails = Math.min(min_trails, temp_ans);
        }


        return min_trails;
    }

    // Important Problem! have to rewatch the tutorial again!
    public static boolean isScrambled(String S1, String S2) {

        if (S1.length() != S2.length()) return false;

        int n = S1.length();

        if (n == 0) return true;

        if (S1.equals(S2)) return true;


        char[] tempArray1 = S1.toCharArray();
        char[] tempArray2 = S2.toCharArray();

        // Checking condition for Anagram
        Arrays.sort(tempArray1);
        Arrays.sort(tempArray2);

        String copy_S1 = new String(tempArray1);
        String copy_S2 = new String(tempArray2);


        if (!copy_S1.equals(copy_S2)) return false;


        for (int i = 1; i < n; i++) {

            if (isScrambled(S1.substring(0, i),
                    S2.substring(0, i)) &&
                    isScrambled(S1.substring(i, n),
                            S2.substring(i, n))) {
                return true;
            }

            if (isScrambled(S1.substring(n - i, n),
                    S2.substring(0, i)) &&
                    isScrambled(S1.substring(0, n - i),
                            S2.substring(i, n))) {
                return true;
            }
        }


        return false;
    }

    public static void fillDPArray() {
        for (int[][] A : DP)
            for (int[] B : A)
                Arrays.fill(B, -1);

    }

    //Memoized Version! 3D Array!
    public static int EvaluateExpressionToTrueDP(String s, int i, int j, int isTrue) {
        if (i > j) return 0;
        if (i == j) {
            if (isTrue == 1) {
                return (s.charAt(i) == 'T') ? 1 : 0;
            } else {
                return (s.charAt(i) == 'F') ? 1 : 0;
            }
        }

        if (DP[i][j][isTrue] != -1)
            return DP[i][j][isTrue];

        int answer = 0, leftTrue, rightTrue, leftFalse, rightFalse;
        for (int k = i + 1; k < j; k += 2) {

            if (DP[i][k - 1][1] != -1) {
                leftTrue = DP[i][k - 1][1];
            } else
                leftTrue = EvaluateExpressionToTrueDP(s, i, k - 1, 1);
            if (DP[i][k - 1][0] != -1) {
                leftFalse = DP[i][k - 1][0];
            } else
                leftFalse = EvaluateExpressionToTrueDP(s, i, k - 1, 0);
            if (DP[k + 1][j][1] != -1) {
                rightTrue = DP[k + 1][j][1];
            } else rightTrue = EvaluateExpressionToTrueDP(s, k + 1, j, 1);

            if (DP[k + 1][j][0] != -1) {
                rightFalse = DP[k + 1][j][0];
            } else
                rightFalse = EvaluateExpressionToTrueDP(s, k + 1, j, 0);

            if (s.charAt(k) == '&') {
                if (isTrue == 1) {
                    answer += leftTrue * rightTrue;
                } else {
                    answer += leftFalse * rightFalse + leftTrue * rightFalse + leftFalse * rightTrue;
                }
            } else if (s.charAt(k) == '|') {
                if (isTrue == 1) {
                    answer += leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
                } else {
                    answer += leftFalse * rightFalse;
                }
            } else if (s.charAt(k) == '^') {
                if (isTrue == 1)
                    answer += leftFalse * rightTrue + leftTrue * rightFalse;
                else
                    answer += leftFalse * rightFalse + leftTrue * rightTrue;
            }
            DP[i][j][isTrue] = answer;
        }
        return answer;
    }

    //Use a 3D Array for optimization!
    //Recursive Solution
    //God level Problem🥱
    //https://practice.geeksforgeeks.org/problems/boolean-parenthesization5610/1
    public static int EvaluateExpressionToTrue(String s, int i, int j, int isTrue) {
        //As XOR is an Operation included in the String we will be needing both the False and True Values
        //That is why we are passing extra variable isTrue! so that we can get what we desire as output
        //For E.g. I want false in my left part and true in my right part and if my operation is xor ^ then it will be added to the
        //main answer!
        if (i > j) return 0;
        if (i == j) {
            if (isTrue == 1) {
                return (s.charAt(i) == 'T') ? 1 : 0;
            } else {
                return (s.charAt(i) == 'F') ? 1 : 0;
            }
        }


        int answer = 0;
        for (int k = i + 1; k < j; k += 2) {
            int leftTrue = EvaluateExpressionToTrue(s, i, k - 1, 1);
            int leftFalse = EvaluateExpressionToTrue(s, i, k - 1, 0);
            int rightTrue = EvaluateExpressionToTrue(s, k + 1, j, 1);
            int rightFalse = EvaluateExpressionToTrue(s, k + 1, j, 0);

            if (s.charAt(k) == '&') {
                if (isTrue == 1) {
                    answer += leftTrue * rightTrue;
                } else {
                    answer += leftFalse * rightFalse + leftTrue * rightFalse + leftFalse * rightTrue;
                }
            } else if (s.charAt(k) == '|') {
                if (isTrue == 1) {
                    answer += leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
                } else {
                    answer += leftFalse * rightFalse;
                }
            } else if (s.charAt(k) == '^') {
                if (isTrue == 1)
                    answer += leftFalse * rightTrue + leftTrue * rightFalse;
                else
                    answer += leftFalse * rightFalse + leftTrue * rightTrue;
            }

        }
        return answer;
    }


    //Use DP Array for better time complexity!
    //https://leetcode.com/problems/palindrome-partitioning-ii/
    //Recursive Solution
    //https://practice.geeksforgeeks.org/problems/palindromic-patitioning4845/1
    public static int PalindromePartioning(char[] str, int i, int j) {
        if (i >= j) return 0;
        if (isPalindrome(str, i, j)) return 0;
        int answer = Integer.MAX_VALUE;
        int temp_answer;
        for (int k = i; k < j; k++) {
            temp_answer = PalindromePartioning(str, i, k) +
                    PalindromePartioning(str, k + 1, j) + 1;

            if (temp_answer < answer) answer = temp_answer;
        }
        return answer;
    }

    private static boolean isPalindrome(char[] str, int i, int j) {
        while (i < j) {
            if (str[i] != str[j]) return false;
            i++;
            j--;
        }
        return true;
    }

    //Memoized Solution Faster than Recursive Solution!
    public static int MatrixChainMultiplication(int[] arr, int i, int j, int[][] dp) {
        if (i >= j) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int min = Integer.MAX_VALUE;

        for (int k = i; k <= j - 1; k++) {

            int temp_min = MatrixChainMultiplication(arr, i, k) +
                    MatrixChainMultiplication(arr, k + 1, j) +
                    (arr[i - 1] * arr[k] * arr[j]);

            if (temp_min < min) min = temp_min;
            dp[i][j] = min;
        }
        return min;
    }


    //Recursive Solution
    //https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
    // i  starts from 1 and j = n-1 !
    public static int MatrixChainMultiplication(int[] arr, int i, int j) {
        if (i >= j) return 0;

        int min = Integer.MAX_VALUE;

        for (int k = i; k <= j - 1; k++) {

            int temp_min = MatrixChainMultiplication(arr, i, k) +
                    MatrixChainMultiplication(arr, k + 1, j) +
                    (arr[i - 1] * arr[k] * arr[j]);

            if (temp_min < min) min = temp_min;
        }

        return min;
    }


    /***This Below problems belong to  The Longest Common SubSequence Category! **/


    //https://leetcode.com/problems/longest-increasing-subsequence/
    // DP Approach
    public static int LongestIncreasingSubSequence(int[] arr, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max_value = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        for (int x : dp) {
            if (x > max_value)
                max_value = x;
        }

        return max_value;
    }

    //Recursion //Previous will be passed as Integer.MIN_VALUE in main function!
    public static int LongestIncreasingSubSequence(int[] arr, int n, int prev) {
        if (n == arr.length) return 0;

        int include = 0;
        if (arr[n] > prev)
            include = LongestIncreasingSubSequence(arr, n + 1, arr[n]);
        int exclude = LongestIncreasingSubSequence(arr, n + 1, prev);
        return Math.max(include, exclude);

    }

    //https://leetcode.com/problems/edit-distance/
    //https://practice.geeksforgeeks.org/problems/edit-distance3702/1#
    //Recursive Approach
    public int EditDistance(String x, String y, int m, int n) {
        if (m == 0 || n == 0) {
            return m + n;
        }
        if (x.charAt(m - 1) == y.charAt(n - 1)) return EditDistance(x, y, m - 1, n - 1);

        return 1 + Math.min(
                Math.min(EditDistance(x, y, m - 1, n), EditDistance(x, y, m, n - 1)),
                EditDistance(x, y, m - 1, n - 1)
        );
    }

    //DP Approach built on recursive Approach!! 😉
    public int EditDistance(String x, String y) {
        int m = x.length();
        int n = y.length();

        int[][] dp = new int[m + 1][n + 1];

        //Initialisation!
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0) dp[i][j] = j;
                if (j == 0) dp[i][j] = i;
            }
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
            }
        }


        return dp[m][n];
    }

    //Wild Pattern Matching (LeetCode)
    //DP Approach! Passing only 1054 test cases out of 1081 🥲!
    public static boolean SequencePatternMatching(String x, String y) {
        int m = x.length();
        int n = y.length();
        int lcs = LongestCommonSubSequence(x, y);

        int onlyCharactersLength = getCharactersLength(x.toCharArray());

        return onlyCharactersLength == lcs;

    }

    private static int getCharactersLength(char[] x) {
        int len = 0;
        for (char a : x) if (a != '*' && a != '?') len++;
        return len;
    }

    //Might be wrong!!
    public static boolean SequencePatternMatching(String str, String pattern, int m, int n) {

        if (n == 0) return m == 0;

        if (pattern.charAt(n - 1) == '?' || pattern.charAt(n - 1) == str.charAt(m - 1)) {
            return SequencePatternMatching(str, pattern, m - 1, n - 1);
        } else if (pattern.charAt(n - 1) == '*') {
            while (n > 0 && pattern.charAt(n - 1) == '*') n--;

            return SequencePatternMatching(str, pattern, m, n);
        } else return false;
    }


    //https://practice.geeksforgeeks.org/problems/longest-repeating-subsequence/0
    //DP Approach!
    public static int LongestRepeatingSubSequence(String x) {
        int n = x.length();
        int[][] dp = new int[n + 1][n + 1];


        //Initialisation
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (x.charAt(i - 1) == x.charAt(j - 1) && i != j) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n][n];
    }

    //Recursive Solution
    public static int LongestRepeatingSubSequence(String x, String y, int m, int n) {

        if (m == 0 || n == 0) return 0;

        if (x.charAt(m - 1) == y.charAt(n - 1) && m != n) return 1 + LongestRepeatingSubSequence(x, y, m - 1, n - 1);

        else return Math.max(
                LongestRepeatingSubSequence(x, y, m - 1, n),
                LongestRepeatingSubSequence(x, y, m, n - 1)
        );

    }

    //We use LPS to solve this problem!
    //https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
    //https://practice.geeksforgeeks.org/problems/minimum-deletitions/0
    public static int MinimumDeletionsToMakeAStringPalindrome(String x) {

        int length = x.length();
        int lps = LongestPalindromicSubSequence(x);
        return length - lps;

    }

    //https://leetcode.com/problems/longest-palindromic-subsequence/submissions/
    //Real DP Solution with 33ms 🙂
    public static int LongestPalindromicSubSequenceDP(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; --i) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    //Optimized Version! using LCS!!
    public static int LongestPalindromicSubSequence(String x) {
        String y = reverseString(x);
        int lcs = LongestCommonSubSequence(x, y);
        return lcs;
    }

    public static String reverseString(String x) {
        StringBuilder sb = new StringBuilder();
        for (char a : x.toCharArray()) sb.append(a);
        return sb.reverse().toString();
    }

    public static int LongestPalindromicSubSequence(String x, int i, int j) {
        if (i == j) return 1;

        boolean b = x.charAt(i) == x.charAt(j);

        if (b && i + 1 == j) return 2;

        else if (b) return 2 + LongestPalindromicSubSequence(x, i + 1, j - 1);

        else return Math.max(
                    LongestPalindromicSubSequence(x, i + 1, j),
                    LongestPalindromicSubSequence(x, i, j - 1));

    }

    public static int MinimumNumberOfDeletionsAndInsertions(String x, String y) {
        int m = x.length();
        int n = y.length();

        int lcs = LongestCommonSubSequence(x, y);

        return (m - lcs) + (n - lcs);
    }

    //https://practice.geeksforgeeks.org/problems/shortest-common-supersequence0322/1
    //Uses Longest Common SubSequence Function!!
    public static int ShortestSuperSequence(String x, String y) {
        int m = x.length();
        int n = y.length();
        int lcs = LongestCommonSubSequence(x, y);

        return (m + n - lcs);
    }


    //https://leetcode.com/problems/shortest-common-supersequence/
    //True DP Solution,No need of LCS Function!
    public static int ShortestSuperSequenceDP(String x, String y) {
        int m = x.length();
        int n = y.length();

        int[][] dp = new int[m + 1][n + 1];

        //Initialisation!

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0) dp[i][j] = j;
                if (j == 0) dp[i][j] = i;
            }
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
            }
        }


        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        //Printing the Sequence!
        StringBuilder sb = new StringBuilder();

        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                sb.append(x.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                sb.append(y.charAt(j - 1));
                j--;
            } else {
                sb.append(x.charAt(i - 1));
                i--;
            }
        }

        while (i > 0) {
            sb.append(x.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            sb.append(y.charAt(j - 1));
            j--;
        }

        System.out.println(sb.reverse());
        return dp[m][n];

    }

    //Time Complexity id O(2^(p+q))!!
    public static int ShortestSuperSequence(String x, String y, int p, int q) {
        if (p == 0 || q == 0) return p + q;

        if (x.charAt(p - 1) == y.charAt(q - 1)) {
            return ShortestSuperSequence(x, y, p - 1, q - 1) + 1;
        }

        return Math.min(
                ShortestSuperSequence(x, y, p - 1, q) + 1,
                ShortestSuperSequence(x, y, p, q - 1) + 1
        );

    }

    public static void PrintLongestCommonSubSequence(String x, String y) {

        int m = x.length();
        int n = y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++)
            for (int j = 0; j < n + 1; j++)
                if (i == 0 || j == 0) dp[i][j] = 0;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Now Traverse the Matrix from bottom right and append the characters!
        StringBuilder sb = new StringBuilder();

        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                sb.append(x.charAt(i - 1));
                i--;
                j--;
                continue;
            }
            if (dp[i - 1][j] > dp[i][j]) {
                i = i - 1;
            } else j = j - 1;

        }


        System.out.println(sb.reverse());
    }


    //https://www.geeksforgeeks.org/longest-common-substring-dp-29/
    public static int LongestCommonSubString(String x, String y) {

        int m = x.length();
        int n = y.length();


        int[][] dp = new int[m + 1][n + 1];

        int max_length = 0;

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }


        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max_length = Math.max(max_length, dp[i][j]);
                } else dp[i][j] = 0;
            }
        }


        return max_length;
    }

    //Pure Recursive Solution!
    public static int LongestCommonSubString(String x, String y, int m, int n, int curr_max) {
        if (m == 0 || n == 0) return curr_max;

        if (x.charAt(m - 1) == y.charAt(n - 1)) return LongestCommonSubString(x, y, m - 1, n - 1, curr_max + 1);

        return Math.max(LongestCommonSubString(x, y, m - 1, n, 0), LongestCommonSubString(x, y, m, n - 1, 0));
    }


    //Bottom Up Approach! 😀
    //https://leetcode.com/problems/longest-common-subsequence/submissions/
    //https://practice.geeksforgeeks.org/problems/longest-common-subsequence/0
    public static int LongestCommonSubSequence(String x, String y) {
        int m = x.length();
        int n = y.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }


        return dp[m][n];
    }

    //Memoization Approach!
    public static int LongestCommonSubSequenceMemoization(String x, String y, int m, int n, int[][] dp) {

        if (dp[m][n] != -1)
            return dp[m][n];

        if (m == 0 || n == 0)
            return 0;
        else {
            if (x.charAt(m - 1) == y.charAt(n - 1))
                dp[m][n] = 1 + LongestCommonSubSequenceMemoization(x, y, m - 1, n - 1, dp);
            else
                dp[m][n] = Math.max(
                        LongestCommonSubSequenceMemoization(x, y, m, n - 1, dp),
                        LongestCommonSubSequenceMemoization(x, y, m - 1, n, dp)
                );
        }
        return dp[m][n];

    }

    //Time Complexity is O(2^N)!
    //Recursive Solution!!
    public static int LongestCommonSubSequenceRecursive(String x, String y, int m, int n) {
        if (m == 0 || n == 0) return 0;

        if (x.charAt(m - 1) == y.charAt(n - 1))
            return 1 + LongestCommonSubSequenceRecursive(x, y, m - 1, n - 1);

        return Math.max(
                LongestCommonSubSequenceRecursive(x, y, m - 1, n),
                LongestCommonSubSequenceRecursive(x, y, m, n - 1)
        );

    }


    //This Below problems belong to  UnBounded KnapSack Category!


    //** Important **
    // Draw Matrix Diagram for better understanding why we are using second row initialisation.
    //For this PROBLEM draw matrix diagram for better understanding!
    public static int CoinChangeII(int[] coins, int sum) {
        int n = coins.length;

        int[][] dp = new int[n + 1][sum + 1];


        //First Normal Initialisation
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0)
                    dp[i][j] = Integer.MAX_VALUE - 1; // We are subtracting -1 from Integer.MAX_VALUE because if we add 1 in the below loop it will overflow!
                if (i == 1 && j > 0) {
                    if (j % coins[0] == 0) dp[1][j] = j / coins[0];
                    else dp[1][j] = Integer.MAX_VALUE - 1;
                }

                if (j == 0) dp[i][j] = 0;
            }
        }

        dp[0][0] = Integer.MAX_VALUE - 1;

        //This problem is a bit different from others.It needs a second initialization.
        //Initialised in the above loop itself!
        /* for (int i = 1; i < n + 1; i++) {
            if (i % coins[0] == 0) dp[1][i] = i / coins[0];
            else dp[1][i] = Integer.MAX_VALUE;
        }*/

        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (coins[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
            }
        }

        return dp[n][sum];
    }

    //This DP Question is a bit Tricky compared to other's.Recursive Solution is easier but DP Solution need's some understanding of the question!
    //https://practice.geeksforgeeks.org/problems/number-of-coins1824/1
    //https://leetcode.com/problems/coin-change/
    public static int CoinChangeII(int[] coins, int sum, int n) {
        if (n == 0) return Integer.MAX_VALUE - 1;
        if (sum == 0) return 0;

        if (coins[n - 1] > sum) return CoinChangeII(coins, sum, n - 1);

        return Math.min(
                CoinChangeII(coins, sum, n - 1),
                CoinChangeII(coins, sum - coins[n - 1], n) + 1
        );
    }


    //https://practice.geeksforgeeks.org/problems/coin-change2448/1#
    //https://leetcode.com/problems/coin-change-2/
    //Same to same as SubSet Problem! with small twist.
    public static int CoinChangeI(int[] coins, int sum) {
        int n = coins.length;

        int[][] dp = new int[n + 1][sum + 1];


        //Initialization
        for (int i = 0; i < n + 1; i++) { // Array Length
            for (int j = 0; j < sum + 1; j++) { // Sum from 0 to sum + 1;
                if (i == 0) dp[i][j] = 0;
                if (j == 0) dp[i][j] = 1;
            }
        }

        dp[0][0] = 1;

        //Main Part!
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (coins[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
            }
        }

        //Answer!
        return dp[n][sum];
    }

    //Similar to SubSet Problem!! 😉 But with infinite amount of choices to make!
    public static int CoinChangeI(int[] coins, int sum, int n) {
        if (n == 0) return 0;
        if (sum == 0) return 1;

        if (coins[n - 1] > sum) return CoinChangeI(coins, sum, n - 1);
        return CoinChangeI(coins, sum, n - 1) + CoinChangeI(coins, sum - coins[n - 1], n);

    }

    //Same as UnBounded KnapSack! 😉
    //Bottom Up Approach
    public static int RodCuttingDP(int[] prices, int rod_length) {

        int n = prices.length;
        int[] length = new int[rod_length];

        for (int i = 0; i < rod_length; i++) length[i] = i + 1;

        int[][] dp = new int[n + 1][rod_length + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < rod_length + 1; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < rod_length + 1; j++) {
                if (length[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - length[i - 1]] + prices[i - 1]);
            }
        }


        return dp[n][rod_length];
    }


    //Below function is from GFG!!
    public static int RodCutting(int price[], int n) {
        if (n <= 0)
            return 0;
        int max_val = Integer.MIN_VALUE;

        // Recursively cut the rod in different pieces and
        // compare different configurations
        for (int i = 0; i < n; i++)
            max_val = Math.max(max_val,
                    price[i] + RodCutting(price, n - i - 1));

        return max_val;
    }

    //Rod Cutting is same as UnBounded KnapSack Problem.
    //Not even a single value changed!
    //This solution won't work! for some test cases and I don't know the reason!
    // Got the reason, and it is giving wrong inputs!! 😭

    public static int RodCutting(int[] prices, int[] length, int rod_length, int n) {
        if (rod_length == 0 || n == 0) return 0;

        if (length[n - 1] > rod_length) return RodCutting(prices, length, rod_length, n - 1);

        return Math.max(
                RodCutting(prices, length, rod_length, n - 1),
                RodCutting(prices, length, rod_length - length[n - 1], n) + prices[n - 1]);

    }


    //https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
    public static int UnBoundedKnapSack(int[] weight, int[] value, int W) {

        int n = weight.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }


        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (weight[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
            }
        }


        return dp[n][W];
    }

    //Recursive Solution!!
    public static int UnBoundedKnapSack(int[] weight, int[] value, int W, int n) {

        if (n == 0 || W == 0) return 0;

        if (weight[n - 1] > W) return UnBoundedKnapSack(weight, value, W, n - 1);

        return Math.max(
                UnBoundedKnapSack(weight, value, W, n - 1),
                UnBoundedKnapSack(weight, value, W - weight[n - 1], n) + value[n - 1]);

    }


    //This Below problems belong to  0-1 KnapSack Category!

    //This is also known as target sum problem -> refer leetcode!.
    //Have to use Some Simple Math Concepts to Tackle this Question!
    public static int CountSubSetWithGivenDifference(int[] arr, int diff) {
        int sum = 0;
//        sum = Arrays.stream(arr).sum();

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

}
