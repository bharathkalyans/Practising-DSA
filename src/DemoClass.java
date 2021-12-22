import java.util.*;

public class DemoClass {

    public static void main(String[] args) {
        System.out.println(finalCount(10, 4));
    }




    static int finalCount(int n, int x) {
        int count = 0;
        int bob = x, alice = n - x;
        while (bob != alice) {
            if (alice > bob)
                alice -= bob;
            else bob -= alice;
            count++;
        }
        return count;
    }

    static int sumOfString(String num) {
        int n = num.length();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int number = Integer.parseInt(num.substring(i, j + 1));
                sum += number;
            }
        }
        return sum;
    }


    static int reverseOfDigits(int n) {
        int rev = 0;
        int temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            rev = rev * 10 + digit;
            temp = temp / 10;
        }
        return rev;
    }

    public static int Pairs(int[] arr, int sum) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        int count = 0;
        for (int j : arr) {
            if (map.containsKey(sum - j))
                count += map.get(j);

            if (sum - j == j) count -= map.get(j);
        }

        System.out.println("Count is :: " + count / 2);
        return count / 2;
    }

    public static int kIncreasing(int[] arr, int k) {
        int min = 0;
        int n = arr.length;
        for (int i = 0; i < n - k; i++) {
            if (arr[i] > arr[i + k]) {
                arr[i + k] = arr[i];
                min++;
            }
        }
        return min;
    }

    public static long getDescentPeriods(int[] prices, int n) {
        long total = 0L;
        int[] suffix = new int[n];
        Arrays.fill(suffix, 1);
        for (int i = n - 1; i > 0; i--) {
            if (prices[i - 1] - prices[i] == 1) {
                suffix[i - 1] += suffix[i];
            }
        }

        for (int x : suffix) total += x;
        return total;
    }


    public static String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        HashSet<Integer> set = new HashSet<>();
        for (int space : spaces) set.add(space);

        for (int i = 0; i < n; i++) {
            if (set.contains(i)) sb.append(" ");
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    //https://leetcode.com/contest/biweekly-contest-65/problems/check-whether-two-strings-are-almost-equivalent/
    public boolean checkAlmostEquivalent(String word1, String word2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for (char c : word1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        for (char c : word2.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            int freq1 = map1.getOrDefault(c, 0);
            int freq2 = map2.get(c);
            if (Math.abs(freq1 - freq2) > 3) return false;

        }

        return true;
    }

    // Maximize SubSet of Product Array!
    int Maximize(int[] arr, int n) {

        Arrays.sort(arr);
        long M = 1000000007;
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += (long) arr[i] * i;
        }
        return (int) ((sum) % M);
    }


    // https://leetcode.com/problems/compare-version-numbers/discuss/1628734/Simple-Merge-Sort-Style-Code!!
    public static int compareVersion(String v1, String v2) {


        String[] l = v1.split("\\.");
        String[] r = v2.split("\\.");

        int n = l.length;
        int m = r.length;

        int i = 0, j = 0;

        while (i < n && j < m) {
            int a = Integer.parseInt(l[i]);
            int b = Integer.parseInt(r[j]);

            if (a == b) {
                i++;
                j++;
            } else if (a > b) return 1;
            else if (a < b) return -1;

        }

        while (i < n) {
            int val = Integer.parseInt(l[i]);
            if (i > 0) return 1;
            i++;
        }


        while (j < m) {
            int val = Integer.parseInt(r[j]);
            if (j > 0) return 1;
            j++;
        }


        return 0;
    }

    public static long subArrayRanges(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        long sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int largest = nums[i], smallest = nums[i];
            for (int j = i; j < n; j++) {
                largest = Math.max(largest, nums[j]);
                smallest = Math.min(smallest, nums[j]);
                sum += (largest - smallest);
            }
        }
        System.out.println("Sum is :: " + sum);
        return sum;
    }

    public int countPoints(String rings) {
        int n = rings.length();

        if (n < 6) return 0;

        int count = 0;
        HashMap<Character, HashSet<Character>> map = new HashMap<>();

        for (int i = 1; i < n; i = i + 2) {
            Character color = rings.charAt(i - 1);
            Character index = rings.charAt(i);

            if (!map.containsKey(index)) {
                map.put(index, new HashSet<>());
            }
            map.get(index).add(color);
        }


        for (Map.Entry<Character, HashSet<Character>> m : map.entrySet()) {
            if (m.getValue().size() == 3) count++;
        }


        return count;
    }

    public String[] getFolderNames(String[] names) {
        TreeMap<String, Integer> map = new TreeMap<>();

        for (String name : names) {

            if (!map.containsKey(name)) {
                map.put(name, 1);
            } else {
                int getTotalFiles = map.get(name);

                String newFile = name + "(" + (getTotalFiles + 1) + ")";
                while (!map.containsKey(newFile)) {
                    getTotalFiles++;
                    newFile = name + "(" + getTotalFiles + ")";
                }
                map.put(newFile, getTotalFiles + 1);

            }
        }
        String[] result = new String[names.length];
        int index = 0;
        for (String s : map.keySet())
            result[index++] = s;

        return result;
    }

    public static void getAverages(int[] nums, int k) {

        if (k == 0) return;
        int n = nums.length;
        int[] avg = new int[n];
        Arrays.fill(avg, -1);

        long sum = 0;
        int radius = (2 * k) + 1;

        if (radius > n) return;

        for (int i = 0; i < radius; i++) sum += nums[i];

        for (int i = k; i < n - k; i++) {
            System.out.println("I am Standing at :: " + nums[i]);
            avg[i] = (int) Math.floor(sum / radius);
            if (i + k + 1 < n) {
                System.out.println("Removing ::" + nums[i - k] + " and adding " + nums[i + k + 1]);
                sum = sum - nums[i - k] + nums[i + k + 1];
            }
        }


        for (double v : avg) {
            System.out.print(v + " ");
        }
    }


}
