import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Miscellaenous {
    public static void main(String[] args) {

        int[] arr = {5, 4, 9, 8, 3, 6, 5, 4};

        System.out.println(MergeArrayToMakePalindrome(arr, arr.length));

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
