import java.util.*;

public class DemoClass {

    public static void main(String[] args) {

        System.out.println(compareVersion("1.01", "1.001"));
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
