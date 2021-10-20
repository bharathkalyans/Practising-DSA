import java.util.*;

public class Miscellaenous {

    static long[][] dp = new long[1000][1000];

    public static void main(String[] args) {


        RomanToInteger("MCMXCIV");

    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static int getValueOfRomanValue(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    public static void RomanToInteger(String roman) {

        int result = 0;

        for (int i = 0; i < roman.length(); i++) {
            int val1 = getValueOfRomanValue(roman.charAt(i));

            if (i + 1 < roman.length()) {
                int val2 = getValueOfRomanValue(roman.charAt(i + 1));

                if (val1 >= val2) result += val1;
                else {
                    result += val2 - val1;
                    i++;
                }

            } else result += val1;

        }

        System.out.println(result);
    }

    //Traversing all 8 Directions!
    static int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static int IsWordPresentInGrid(char[][] grid, String str) {

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchInGrid(grid, str, i, j)) {
                    System.out.println("Pattern found at :: " + i + " " + j);
                }
            }
        }

        return 0;
    }

    private static boolean searchInGrid(char[][] grid, String str, int row, int column) {
        if (grid[row][column] != str.charAt(0)) return false;

        int len = str.length();

        for (int directions = 0; directions < 8; directions++) {
            int characters, row_direction = row + x[directions], column_direction = column + y[directions];

            for (characters = 1; characters < len; characters++) {
                if (row_direction < 0 ||
                        column_direction < 0 ||
                        row_direction >= grid.length ||
                        column_direction >= grid[0].length
                )
                    break;

                if (grid[row_direction][column_direction] != str.charAt(characters))
                    break;


                row_direction += x[directions];
                column_direction += y[directions];

            }

            if (characters == len) return true;

        }
        return false;
    }

    public static String removeConsecutiveCharacter(String S) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {

            if (sb.isEmpty()) {
                sb.append(S.charAt(i));
                continue;
            }

            char x = S.charAt(i);
            while (!sb.isEmpty() && sb.charAt(sb.length() - 1) == x) sb.deleteCharAt(sb.length() - 1);
            sb.append(x);
        }

        return sb.toString();
    }

    static long countPS(String str) {
        // Your code here
        long mod = 1000000000 + 7;
        int n = str.length();

        return solve(str, 0, n - 1) % mod;
    }

    static long solve(String str, int m, int n) {

        if (m > n) return 0;

        if (dp[m][n] != -1) return dp[m][n];


        if (m == n) return dp[m][n] = 1;

        else if (str.charAt(m) == str.charAt(n))
            return dp[m][n] = solve(str, m, n - 1) + solve(str, m + 1, n) + 1;

        else return dp[m][n] = solve(str, m, n - 1) +
                    solve(str, m + 1, n) -
                    solve(str, m + 1, n - 1);
    }

    public static int MinimumReversals(String str) {
        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (temp == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
                continue;
            }

            stack.push(temp);
        }

        int closed = 0, opened = 0;
        while (!stack.isEmpty()) {
            if (stack.peek() == '{') opened++;
            else closed++;
            stack.pop();
        }

        int reversals = opened % 2 == 0 ? opened / 2 : opened / 2 + 1;

        reversals += closed % 2 == 0 ? closed / 2 : closed / 2 + 1;
        return reversals;
    }

    public static void ConvertToNumericKeyPad(String word) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append(getValueFromHashMap(String.valueOf(word.charAt(i))));
        }

        System.out.println(sb);

    }

    public static int getValueFromHashMap(String letter) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 2);
        map.put("B", 22);
        map.put("C", 222);
        map.put("D", 3);
        map.put("E", 33);
        map.put("F", 333);

        map.put("G", 4);
        map.put("H", 44);
        map.put("I", 444);
        map.put("J", 5);
        map.put("K", 55);
        map.put("L", 555);

        map.put("M", 6);
        map.put("N", 66);
        map.put("O", 666);
        map.put("P", 7);
        map.put("Q", 77);
        map.put("R", 777);
        map.put("S", 7777);


        map.put("T", 8);
        map.put("U", 88);
        map.put("V", 888);
        map.put("W", 9);
        map.put("X", 99);
        map.put("Y", 999);
        map.put("Z", 9999);

        return map.get(letter);
    }

    public static boolean ispar(String x) {

        char[] arr = x.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '{' || arr[i] == '(' || arr[i] == '[') {
                stack.push(arr[i]);
            }
            if (stack.isEmpty()) return false;

            char temp;
            switch (arr[i]) {
                case ')':
                    temp = stack.pop();
                    if (temp == '[' || temp == '{') return false;
                    break;
                case '}':
                    temp = stack.pop();
                    if (temp == '(' || temp == '[') return false;
                    break;
                case ']':
                    temp = stack.pop();
                    if (temp == '(' || temp == '{') return false;
                    break;
            }

        }

        return stack.isEmpty();
    }

    //https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string3411/1
    public static void LongestPalindromeInAString(String str) {
        char[] s = str.toCharArray();
        int n = str.length();

        String answer = String.valueOf(str.charAt(0));

        //aaaabbaa
        for (int i = 1; i < str.length(); i++) {
            //Two possible cases :: a.Odd Length b.Even Length

            //For ODD length
            int l = i - 1, r = i + 1;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            l++;
            r--;
            if (r - l + 1 > answer.length()) {
                answer = str.substring(l, r + 1);
            }

            //For Even Length
            int ll = i - 1, rr = i;
            while (ll >= 0 && rr < n && s[ll] == s[rr]) {
                ll--;
                rr++;
            }
            ll++;
            rr--;
            if (rr - ll + 1 > answer.length()) {
                answer = str.substring(ll, rr + 1);

            }
        }
        System.out.println(answer);
    }

    public static String LongestPalindrome(String S) {
        String result = "";
        for (int i = 0; i < S.length(); i++) {
            for (int j = i; j < S.length(); j++) {
                String subStr = S.substring(i, j);
                if (isPalindrome(subStr)) {
                    if (subStr.length() > result.length())
                        result = subStr;
                }
            }
        }

        return result;
    }

    private static boolean isPalindrome(String subStr) {
        int l = 0, h = subStr.length() - 1;
        while (l < h) {
            if (subStr.charAt(l) == subStr.charAt(h)) {
                l++;
                h--;
            } else return false;
        }
        return true;
    }

    public static int kthSmallest(int[][] mat, int n, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                pq.add(mat[i][j]);
        System.out.println(pq);
        for (int i = 0; i < k - 1; i++)
            pq.poll();

        int answer = pq.remove();
        return answer;
    }

    public static int FindSpecificPairInMatrixEff(int[][] matrix) {
        return 1;
    }

    // Bad Time Complexity! O(n^4)
    public static int FindSpecificPairInMatrix(int[][] matrix) {
        int max_value = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;

        for (int a = 0; a < m - 1; a++) {
            for (int b = 0; b < n - 1; b++) {
                for (int c = a + 1; c < m; c++) {
                    for (int d = b + 1; d < n; d++) {
                        max_value = Math.max(max_value, matrix[c][d] - matrix[a][b]);
                    }
                }
            }
        }

        return max_value;
    }


    public static int MaxRectangleInBinaryMatrix(int[][] matrix) {

        int max_area = Integer.MIN_VALUE;
        int[] hist_array = new int[matrix[0].length];

        for (int i = 0; i < matrix[0].length; i++)
            hist_array[i] = matrix[0][i];

        max_area = Math.max(max_area, MaxHistogram(hist_array));

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0)
                    hist_array[j] += 1;
                else hist_array[j] = 0;
            }
            int hist_area = MaxHistogram(hist_array);
            max_area = Math.max(max_area, hist_area);
        }

        return max_area;
    }

    //https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/
    public static int MaxHistogram(int[] arr) {
        int[] NSL = NSL(arr);
        int[] NSR = NSR(arr);

        int[] result = new int[arr.length];

        int max_area = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            result[i] = NSR[i] - NSL[i] - 1;
        }

        for (int i = 0; i < arr.length; i++) {
            max_area = Math.max(max_area, result[i] * arr[i]);
        }
        return max_area;
    }

    public static int[] NSR(int[] arr) {
        int[] result = new int[arr.length];
        int n = arr.length;
        result[arr.length - 1] = arr.length;

        Stack<Integer> s = new Stack<>();
        s.push(arr.length - 1);

        for (int i = arr.length - 2; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) s.pop();

            result[i] = s.isEmpty() ? n : s.peek();
            s.push(i);
        }

        return result;
    }

    public static int[] NSL(int[] arr) {
        int[] result = new int[arr.length];
        result[0] = -1;
        Stack<Integer> s = new Stack<>();
        s.push(0);
        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) s.pop();
            result[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }
        return result;
    }

    //Naive Approach
    public static int MaximumAreaHistogram(int[] arr) {
        int max_area = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int curr_max = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) break;
                curr_max += arr[i];
            }

            for (int k = i - 1; k >= 0; k--) {
                if (arr[k] < arr[i]) break;
                curr_max += arr[i];
            }
            max_area = Math.max(max_area, curr_max);

        }

        return max_area;
    }

    public static int[] StockSpan(int[] arr) {
        int[] result = new int[arr.length];
        result[0] = 1;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i])
                stack.pop();

            result[i] = stack.isEmpty() ? i + 1 : i - stack.peek();

            stack.push(i);
        }

        return result;
    }

    public static int[] NextGreaterElement(int[] arr) {

        if (arr == null || arr.length == 0) return new int[]{};
        if (arr.length == 1) return arr;

        int[] result = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        s.push(arr[0]);

        for (int i = arr.length - 1; i >= 0; i--) {

            while (!s.isEmpty() && s.peek() <= arr[i]) s.pop();

            if (s.isEmpty()) result[i] = -1;
            else result[i] = s.peek();

            s.push(arr[i]);

            result[i - 1] = s.peek();
        }

        return result;
    }

    public static int minOperations(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = (2 * i) + 1;

        int min_operations = 0;
        for (int i = 0; i < n / 2; i++) {
            while (arr[i] != arr[n - i - 1]) {
                arr[i] += 1;
                arr[n - i - 1] -= 1;
                min_operations++;
            }
        }
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
        return min_operations;
    }

    public static int MergeArrayToMakePalindrome(int[] arr, int n) {

        if (arr == null || arr.length <= 1) return 0;
        int i = 0, j = n - 1, answer = 0;
        while (i < j) {
            if (arr[i] == arr[j]) {
                i++;
                j--;
            } else if (arr[i] > arr[j]) {
                arr[j - 1] = arr[j - 1] + arr[j];
                j--;
                answer++;
            } else {
                arr[i + 1] = arr[i + 1] + arr[i];
                i++;
                answer++;
            }
        }
        return answer;
    }

    //Very Good Question!!You have find sub array!
    public static int minSwap(int[] arr, int n, int k) {

        int good = 0;
        for (int x : arr) if (x <= k) good++;

        int bad = 0;
        for (int i = 0; i < good; i++) if (arr[i] > k) bad++;

        int answer = bad;

        for (int i = 0, j = good; j < n; i++, j++) {
            if (arr[i] > k) bad--;
            if (arr[j] > k) bad++;
            answer = Math.min(answer, bad);
        }

        return answer;
    }

    public int solve2(int[] arr, int k) {
        int n = arr.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            int odds = 0;
            for (int j = i; j < n; j++) {
                if (arr[j] % 2 != 0) odds++;
            }
            if (odds == k)
                result++;
        }
        return result;
    }


    public static int smallestSubWithSum(int a[], int n, int x) {
        int size = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                if (sum > x)
                    size = Math.min(size, j - i + 1);
            }
        }
        return size;
    }

    public static long findMinDiff(int[] a, long n, long m) {
        Arrays.sort(a);
        long diff = Integer.MAX_VALUE;
        int children = (int) m;
        for (int i = 0; i + m < n; i++) {
            diff = Math.min(a[i + children - 1] - a[i], diff);
        }

        return diff;
    }

    public static int trappingRainWater(int[] arr, int n) {
        if (n == 1) return 0;
        int result = 0;

        for (int i = 1; i < n - 1; i++) {

            int left_max = 0, right_max = 0;
            for (int j = i - 1; j >= 0; j--) {
                left_max = Math.max(left_max, arr[j]);
            }

            for (int k = i + 1; k < n; k++) {
                right_max = Math.max(right_max, arr[k]);
            }
            int val = Math.min(left_max, right_max) - arr[i];
            if (val > 0) result += val;

        }

        return Math.max(result, 0);
    }

    public static boolean isTriplet(int[] arr, int K) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int l = i + 1, r = n - 1, mid = i;

            while (l < r) {
                if (arr[l] + arr[r] + arr[mid] == K) return true;
                else if (arr[l] + arr[r] + arr[mid] > K) mid--;
                else l++;
            }
        }

        return false;
    }

    public static String isSubset(long a1[], long a2[], long n, long m) {
        HashMap<Long, Integer> map = new HashMap<>();

        for (long x : a1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        System.out.println(map);

        for (long x : a2) {
            if (!map.containsKey(x)) return "No";
            int value = map.get(x);
            map.remove(x);
            if (value - 1 <= 0) continue;
            map.put(x, value - 1);
        }

        return "Yes";
    }

    //https://www.techiedelight.com/find-maximum-product-subarray-given-array/
    public static long MaximumProduct(int[] arr, int n) {

        long result = arr[0];
        long maxNegative = arr[0];
        long maxPositive = arr[0];

        for (int i = 1; i < n; i++) {

            long temp = maxPositive;
            maxPositive = Math.max(arr[i], Math.max(arr[i] * maxPositive, arr[i] * maxNegative));
            maxNegative = Math.min(arr[i], Math.min(arr[i] * maxNegative, arr[i] * temp));

            result = Math.max(result, maxPositive);
        }

        return result;
    }

    public int MXS(int[] arr, int n) {
        int maxPositiveProduct = arr[0];
        int maxNegativeProduct = arr[0];
        int overAllMaximumProduct = arr[0];

        for (int i = 1; i < n; i++) {
            int value = arr[i];

            if (value < 0) {
                int temp1 = maxPositiveProduct;
                int temp2 = maxNegativeProduct;

                maxPositiveProduct = Math.max(value, temp1 * value);
                maxNegativeProduct = Math.max(value, temp2 * value);

            } else {
                maxPositiveProduct = Math.max(value, maxPositiveProduct * value);
                maxNegativeProduct = Math.max(value, maxNegativeProduct * value);
            }
            overAllMaximumProduct = Math.max(overAllMaximumProduct, maxPositiveProduct);
        }


        return overAllMaximumProduct;
    }

    // Naive Solution!
    public static int MaximumProductArray(int[] arr, int n) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return arr[0];

        int result = arr[0];

        for (int i = 0; i < n; i++) {
            int curr = arr[i];
            for (int j = i + 1; j < n; j++) {
                result = Math.max(result, curr);
                curr *= arr[j];
            }
            result = Math.max(result, curr);

        }

        return result;
    }


    public static ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) {

        ArrayList<Integer> list = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int x : A) {
            map.put(x, 1);
        }

        for (int x : B) {
            if (map.containsKey(x)) {
                map.put(x, 2);
            }
        }

        for (int x : C) {
            if (map.containsKey(x) && map.get(x) == 2) {
                map.put(x, 3);
            }
        }


        for (Map.Entry<Integer, Integer> mp : map.entrySet())
            if (mp.getValue() > 1) list.add(mp.getKey());


        return list;
    }

    public static int getPairsCount(int[] arr, int k) {
        // code here
        HashMap<Integer, Integer> map = new HashMap<>();

        int result = 0;

        for (int x : arr) {
            if (map.containsKey(k - x)) {
                result += map.get(k - x);
            }
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        System.out.println(map);
        return result;
    }


    public static void permute(String str, String curr) {
        if (str.length() == 0) System.out.println(curr);

        for (int i = 0; i < str.length(); i++) {
            String newCurr = curr + str.charAt(i);
            String newStr = str.substring(0, i) + str.substring(i + 1);
            permute(newStr, newCurr);
        }
    }


    public static void insertionSort(int[] arr, int n) {

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }


    public static void merge(int[] a, int[] b, int n, int m) {

        int i = 0;
        while (i < n) {
            if (a[i] > b[0]) {
                swap(a, b, i);
                Arrays.sort(b);
                i++;
            }
        }
    }

    private static void swap(int[] a, int[] b, int i) {
        int temp = a[i];
        a[i] = b[0];
        b[0] = temp;
    }

    public static int Kadanes(int[] arr, int n) {
        int max_sum = arr[0], curr_max = arr[0];

        for (int i = 1; i < n; i++) {
            curr_max = Math.max(arr[i], curr_max + arr[i]);
            max_sum = Math.max(max_sum, curr_max);
        }

        return max_sum;
    }

    public static int Kadanes(int[] arr) {
        int max_sum = 0;

        for (int i = 0; i < arr.length; i++) {
            int curr_max = 0;
            for (int j = i; j < arr.length; j++) {
                curr_max += arr[j];
            }
            max_sum = Math.max(max_sum, curr_max);
        }


        return max_sum;
    }
}
