import java.util.*;

public class DemoClass {
    public static void main(String[] args) {
        DemoClass obj = new DemoClass();
        obj.kthLargestNumber(new String[]{"2", "21", "12", "1"}, 3);
    }

    //https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/
    public void kthLargestNumber(String[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums, (x, y) -> {
            int l1 = x.length();
            int l2 = y.length();
            if (l1 > l2) return 1;
            else if (l1 < l2) return -1;
            else {
                return x.compareTo(y);
            }
        });
        for (String j : nums) System.out.print(j + " ");
        System.out.println(nums[n - k]);
    }

    public static String FirstNonRepeating(String A) {

        StringBuilder sb = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        Character nr = '#';
        for (char c : A.toCharArray()) {
            System.out.println("Character is :: " + c);
            if (!set.contains(c)) {
                sb.append(c);
                nr = c;
            } else {
                nr = '#';
                System.out.println("Non Repeating is :: " + nr);
                sb.append(nr);
            }
            set.add(c);
            System.out.println("Current String is :: " + sb.toString());
        }
        return sb.toString();
    }

    // Program 1
    public static int sol(int[] arr) {
        int count = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int[] temp = getSum(arr[i]);
            if (temp[0] == 0 && temp[1] == 0) continue;
            if ((temp[0] != 0 && arr[i] % temp[0] == 0) || (temp[1] != 0 && arr[i] % temp[1] == 0)) count++;
        }

        return count;
    }

    public static int[] getSum(int x) {
        int sum = 0, product = 1;
        int temp = x;
        while (temp > 0) {
            int digit = temp % 10;
            sum += digit;
            product *= digit;
            temp = temp / 10;
        }
        return new int[]{sum, product};
    }


    public static int s(int[] arr) {
        int n = arr.length;
        int maxPrime = Integer.MIN_VALUE;
        int minPrime = Integer.MAX_VALUE;

        for (int j : arr) {
            if (isPrime(j)) {
                maxPrime = Math.max(maxPrime, j);
                minPrime = Math.min(minPrime, j);
            }
        }
        return Math.abs(maxPrime - minPrime);
    }

    public static boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        for (int i = 3; i < x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public static double angle(String g) {

        String[] time = g.split(":");
        int h = Integer.parseInt(time[0]);
        int m = Integer.parseInt(time[1]);

        if (h == 12)
            h = 0;
        if (m == 60) {
            m = 0;
            h += 1;
            if (h > 12)
                h = h - 12;
        }

        double hour_angle = (0.5 * (h * 60 + m));
        double minute_angle = (6 * m);

        // Find the difference between two angles
        double angle = Math.abs(hour_angle - minute_angle);

        // smaller angle of two possible angles
        angle = Math.min(360 - angle, angle);

        return round(angle, 1);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    // Program 2
    public static int numSpecial(int[] arr) {
        int count = 0;
        for (int j : arr) {
            String temp = String.valueOf(j);
            String[] c = temp.split("");
            Arrays.sort(c, Collections.reverseOrder());
            StringBuilder sb = new StringBuilder();
            for (String g : c) sb.append(g);
            int new_number = Integer.parseInt(sb.toString());
            if (new_number == j) count++;
        }

        return count;
    }


    public static int numTriplets(int[] arr) {
        int count = 0, n = arr.length;
        if (n < 3) return 0;
        for (int i = 0; i < n - 2; i++) {
            if ((arr[i] + arr[i + 1] + arr[i + 2]) % 3 == 0) count++;
        }
        return count;
    }

    public static int findNum(int[] arr) {
        int n = arr.length;
        if (n == 1) return -1;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                for (int k = 0; k < i; k++)
                    if (arr[j] * arr[k] == arr[i]) return i;

        return -1;
    }

    public static int count(int X) {
        int count = 0;
        int even = 0, sum = 0;
        while (even <= X) {
            if (sum % 2 == 0 || isPrime(sum)) {
                sum += even;
                count++;
            }
            even += 2;
        }
        return count;
    }

    public static int check(int[] A) {
        int n = A.length;
        int sum = 0;
        for (int x : A) sum += x;
        return sum * n;
    }

    public int findJudge(int n, int[][] trust) {
        int m = trust.length;
        if (m == 1) return trust[0][1];
        HashSet<Long> set = new HashSet<>();

        for (int[] ints : trust) {
            set.add((long) ints[0]);
        }

        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) return i;
        }

        return -1;
    }

    //https://leetcode.com/contest/weekly-contest-274
    public static int maximumInvitations(int[] favorite) {
        int total_people = 0;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < favorite.length; i++) {
            if (!map.containsKey(favorite[i])) {
                map.put(favorite[i], new ArrayList<>());
            }
            if (map.get(favorite[i]).size() < 2) map.get(favorite[i]).add(i);
        }

        for (ArrayList<Integer> al : map.values()) {
            total_people += al.size();
        }
        return total_people;
    }

    public static int numberOfBeams(String[] bank) {
        int n = bank.length;
        if (n == 0 || n == 1) return 0;

        int beams = 0;

        int prev_ones = 0;
        String first = bank[0];
        for (int i = 0; i < bank[0].length(); i++) {
            if (first.charAt(i) == '1') {
                prev_ones++;
                System.out.println("Got 1 in Prev::");
            }
        }


        for (int i = 1; i < n; i++) {
            String curr_layer = bank[i];
            int curr_ones = 0;
            for (int j = 0; j < curr_layer.length(); j++)
                if (curr_layer.charAt(j) == '1') curr_ones++;

            if (curr_ones == 0) continue;
            else {
                beams += prev_ones * curr_ones;
                prev_ones = curr_ones;
            }
        }

        return beams;
    }

    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for (int a : asteroids)
            pq.add(a);

        for (int as : pq) {
            int temp = pq.poll();
            if (temp < mass) mass += temp;
            else return false;
        }

        return true;
    }

    //https://leetcode.com/problems/simplify-path/submissions/
    public static String simplifyPath(String path) {
        ArrayDeque<String> s = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        String[] str = path.split("/");
        for (String s1 : str) System.out.print(s1 + " ");
        System.out.println("String length :: " + str.length);
        for (String name : str) {
            if (name.length() == 0) continue;
            if (name.equals("..")) {
                if (!s.isEmpty())
                    s.removeLast();
            } else if (name.equals(".")) {
                continue;
            } else {
                s.add(name);
            }
        }
        if (s.isEmpty()) return "/";

        while (!s.isEmpty()) {
            sb.append("/").append(s.remove());
        }
        return sb.toString();
    }

    // LeetCode Weekly Contest 273
    public int[] executeInstructions(int n, int[] startPos, String s) {

        int[] result = new int[s.length()];
        if (n == 1) return result;

        int r = startPos[0];
        int c = startPos[1];
        for (int i = 0; i < s.length(); i++) {
            result[i] = dfs(s, i, r, c, n, n);
        }
        for (int x : result) System.out.print(x + " ");
        return result;
    }

    public boolean isSafe(String s, int r, int c, int R, int C) {
        if (r >= 0 && (r < R) && c >= 0 && (c < C)) return true;
        else return false;
    }

    public int dfs(String s, int index, int r, int c, int R, int C) {
        if (index < s.length() && r >= 0 && (r < R) && c >= 0 && (c < C)) {
//        if (index < s.length() && r >= 0 && (r < R) && c >= 0 && (c < C)) {
            char direction = s.charAt(index);
            if (direction == 'L') {
                if (isSafe(s, r, c - 1, R, C))
                    return 1 + dfs(s, index + 1, r, c - 1, R, C);
            } else if (direction == 'R') {
                if (isSafe(s, r, c + 1, R, C))
                    return 1 + dfs(s, index + 1, r, c + 1, R, C);
            } else if (direction == 'U') {
                if (isSafe(s, r - 1, c, R, C))
                    return 1 + dfs(s, index + 1, r - 1, c, R, C);
            } else if (direction == 'D') {
                if (isSafe(s, r + 1, c, R, C))
                    return 1 + dfs(s, index + 1, r + 1, c, R, C);
            } else return 0;
        }
        return 0;
    }


    public long[] getDistances(int[] arr, int m) {
        int n = arr.length;
        long[] result = new long[n];

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        // Creating a map! and list of its indices!
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i]))
                map.put(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        for (int i = 0; i < n; i++) {
            long diff = 0;
            ArrayList<Integer> temp = map.get(arr[i]);
            // Now traverse the ArrayList and add the diff!
            for (int p = 0; p < temp.size(); p++) {
                diff += Math.abs(i - temp.get(p));
            }
            result[i] = diff;
        }

//        for (long x : result) System.out.print(x + " ");
        return result;
    }

    public long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] result = new long[n];

        for (int i = 0; i < n; i++) {
            long diff = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
//                    System.out.println("Found a Value :: " + arr[i]);
                    diff += Math.abs(i - j);
                }
            }

            for (int k = i + 1; k < n; k++) {
                if (arr[i] == arr[k]) {
//                    System.out.println("Found a Value :: " + arr[i]);
                    diff += Math.abs(i - k);
                }
            }
            result[i] = diff;
        }
//        for (long x : result) System.out.print(x + " ");
        return result;
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
