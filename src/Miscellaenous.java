import java.util.*;

class CharFreq {
    char character;
    int count;

    CharFreq(char c, int count) {
        character = c;
        this.count = count;
    }
}

public class Miscellaenous {

    static long[][] dp = new long[1000][1000];

    public static void main(String[] args) {

        LLNode s = new LLNode(12);
        s.next = new LLNode(15);
        s.next.next = new LLNode(10);
        s.next.next.next = new LLNode(11);
        s.next.next.next.next = new LLNode(3);


    }


    //https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1#
    public static ListNode SegregateEvenOdd(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode curr = head;
        ListNode evenStart = null;
        ListNode oddStart = null;
        ListNode evenEnd = null;
        ListNode oddEnd = null;

        while (curr != null) {
            if (curr.val % 2 == 0) {
                if (evenStart == null) {
                    evenStart = curr;
                    evenEnd = curr;
                } else {
                    evenEnd.next = curr;
                    evenEnd = evenEnd.next;
                }
            } else {
                if (oddStart == null) {
                    oddStart = curr;
                    oddEnd = curr;
                } else {
                    oddEnd.next = curr;
                    oddEnd = oddEnd.next;
                }
            }
            curr = curr.next;
        }

        if (oddStart == null) return evenStart;
        else if(evenStart == null) return oddStart;


        evenEnd.next = oddStart;
        oddEnd.next = null;
        head = evenStart;
        return head;
    }

    // Number of Islands Problem!
    public static int numberOfIslands(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];

        for (boolean[] row : visited)
            Arrays.fill(row, false);

        int number_of_islands = 0;

        // Checking every node whether we can group islands and count them!
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    dfs(visited, matrix, i, j);
                    number_of_islands++;
                }
            }
        }

        return number_of_islands;

    }

    public static void dfs(boolean[][] visited, int[][] matrix, int row, int column) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Safe Condition to proceed further!
        if (isSafe(m, n, row, column, visited, matrix)) {

            // Marking the visited node as 'TRUE'
            visited[row][column] = true;


            // Searching in all 8 Directions!
            dfs(visited, matrix, row + 1, column);
            dfs(visited, matrix, row, column + 1);
            dfs(visited, matrix, row - 1, column);
            dfs(visited, matrix, row, column - 1);
            dfs(visited, matrix, row + 1, column + 1);
            dfs(visited, matrix, row + 1, column - 1);
            dfs(visited, matrix, row - 1, column + 1);
            dfs(visited, matrix, row - 1, column - 1);
        }

        return;

    }

    public static boolean isSafe(int m, int n, int row, int column, boolean[][] visited, int[][] matrix) {
        if (row >= 0 && column >= 0 && row < m && column < n && (matrix[row][column] == 1 && !visited[row][column]))
            return true;
        return false;

    }

    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> al = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val > max) {
                max = curr.val;
                al.add(0);
            } else {
                al.add(max);
            }
            curr = curr.next;
        }
        int[] result = new int[al.size()];

        for (int i = al.size() - 1; i >= 0; i--) {
            result[i] = al.get(i);
        }
        return result;
    }


    //https://practice.geeksforgeeks.org/problems/merge-k-sorted-linked-lists/1
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0 || lists.length == 1)
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        for (ListNode l : lists) {
            if (l != null) pq.offer(l);
        }

        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;

            if (tail.next != null) pq.offer(tail.next);

        }


        return dummy.next;
    }

    // To get a Leap Year
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        else if (year % 100 == 0) return false;
        else if (year % 4 == 0) return true;

        return false;
    }

    public ListNode removeNthFromEnd(ListNode head, int K) {
        if (head == null) return null;
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int toDeleteIndex = count - K;

        temp = head;
        while (toDeleteIndex > 0) {
            temp = temp.next;
            toDeleteIndex--;
        }


        temp.next = temp.next.next;

        return head;
    }

    public static TreeNode SortUnSortedLinkedList(TreeNode root) {
        HashSet<TreeNode> set = new HashSet<>();
        TreeNode temp = root;
        TreeNode prev = null;

        while (temp != null) {
            if (!set.contains(temp)) {
                set.add(temp);
                prev = temp;
            } else {
                prev.right = temp.right;
            }
            temp = temp.right;
        }

        return null;
    }

    //https://leetcode.com/problems/add-strings/
    public static String addStrings(String num1, String num2) {
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i = n1, j = n2;
        while (i >= 0 && j >= 0) {
            int number1 = num1.charAt(i) - '0';
            int number2 = num2.charAt(j) - '0';

            int sum = number1 + number2 + carry;
            carry = 0;
            if (sum > 10) {
                carry = 1;
            }
            sb.append(sum % 10);
            i--;
            j--;
        }

        while (i >= 0) {
            if (carry == 1) {
                int number = num1.charAt(i) - '0';
                sb.append(number + 1);
                carry = 0;
            } else {
                int numb = num1.charAt(i) - '0';
                sb.append(numb);
            }
            i--;
        }

        int d = sb.length() - 1;
        while (d >= 0) {
            if (sb.charAt(d) != '0')
                return sb.reverse().toString();
        }

        if (carry == 1) sb.append(carry);
        return sb.reverse().toString();

    }

    public int minSwaps(int[] nums) {
        int swaps = 0;

        int[] arr = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != arr[i]) {
                swaps++;
                int val = nums[i];
                int real_index = map.get(nums[i]);
                //Swapping
                int temp = nums[i];
                nums[i] = arr[i];
                nums[real_index] = temp;
                //Updating the indexes in the hash map!

                map.put(nums[i], i);
                map.put(val, map.get(nums[i]));
            }
        }

        return swaps;
    }

    public int[] sortByBits(int[] arr) {
        Integer[] a = new Integer[arr.length];
        int index = 0;
        for (int x : arr) a[index++] = x;
        SortByBitCounts(a);
        index = 0;
        for (Integer x : a)
            arr[index++] = x;
        return arr;
    }

    public static void SortByBitCounts(Integer[] arr) {
        Arrays.sort(arr, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {

                int c1 = Integer.bitCount(o1);
                int c2 = Integer.bitCount(o2);
                if (c1 <= c2)
                    return 1;
                else
                    return -1;
            }
        });
    }

    public static void SubArrayWithSumZero(int[] arr, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int curr_sum = 0;

        for (int i = 0; i < arr.length; i++) {
            curr_sum += arr[i];

            if (curr_sum == sum) {
                System.out.println("Sub Array is found at :: 0 to " + i);
            }
            if (map.containsKey(curr_sum - sum)) {
                System.out.println("Sub Array is found at :: " + (map.get(curr_sum) + 1) + " to " + i);
            } else {
                map.put(curr_sum, i);
            }

        }

        System.out.println(map);
    }

    //https://practice.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x5549/1
    public static long countTriplets(long[] arr, int target) {
        Arrays.sort(arr);
        long count = 0;
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            int low = i + 1, high = n - 1;
            while (low < high) {
                if (arr[i] + arr[low] + arr[high] >= target) {
                    high--;
                } else {
                    count += high - low;
                }
            }
        }

        return count;
    }

    public int answer = 0;

    public void solve(TreeNode root, StringBuilder sb) {
        if (root == null) return;

        sb.append(root.val);
        solve(root.left, sb);
        if (root.left == null && root.right == null) {
            int value = Integer.parseInt(sb.toString());
            answer += value;

        }
        solve(root.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    //https://leetcode.com/problems/4sum/submissions/
    public ArrayList<ArrayList<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {

                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == i + 1 || (j > i + 1 && nums[j] != nums[j - 1])) {
                        int low = j + 1, high = nums.length - 1;

                        while (low < high) {
                            if (nums[i] + nums[j] + nums[low] + nums[high] == target) {
//                                list.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                                ArrayList<Integer> al = new ArrayList<>();
                                al.add(nums[i]);
                                al.add(nums[j]);
                                al.add(nums[low]);
                                al.add(nums[high]);
                                list.add(al);
                                while (low < high && nums[low] == nums[low + 1]) low++;
                                while (low < high && nums[high] == nums[high - 1]) high--;
                                low++;
                                high--;

                            } else if (nums[i] + nums[j] + nums[low] + nums[high] < target) {
                                low++;
                            } else {
                                high--;
                            }
                        }
                    }
                }
            }

        }

        return list;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int low = i + 1, high = n - 1;
                while (low < high) {
                    if (nums[i] + nums[low] + nums[high] < 0) {
                        low++;
                    } else if (nums[i] + nums[low] + nums[high] > 0) {
                        high--;
                    } else {
                        list.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    }
                }
            }
        }

        return list;
    }

    public static boolean PairWithGivenDifference(int[] arr, int diff) {

        Arrays.sort(arr);

        int low = 0, high = arr.length - 1;
        while (low < high) {
            if (arr[high] - arr[low] == diff) return true;
            else if (arr[high] - arr[low] > diff) high--;
            else low++;
        }

        return false;
    }

    //https://leetcode.com/problems/majority-element-ii/submissions/
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums)
            map.put(x, map.getOrDefault(x, 0) + 1);


        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            int key = m.getKey();
            int value = m.getValue();
            if (Math.floor(n / 3) < value) list.add(key);
        }


        return list;
    }

    public String findSubString(String str) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (char i : str.toCharArray()) set.add(i);
        int i = -1, j = -1;
        String res = str;

        while (i < str.length() - 1 && j < str.length() - 1) {
            while (i < str.length() - 1 && map.size() < set.size()) {
                i++;
                char c = str.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            while (j < i && map.size() == set.size()) {
                String temp = str.substring(j + 1, i + 1);
                if (temp.length() < res.length()) res = temp;
                j++;
                char c = str.charAt(j);
                if (map.get(c) == 1) map.remove(c);
                else map.put(c, map.get(c) - 1);
            }
        }
        return res;
    }


    public static boolean areIsomorphic(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        if (m != n) return false;

        char[] charArrayOne = new char[256];
        char[] charArrayTwo = new char[256];

        for (char c : charArrayOne) {
            System.out.print(c + " ");
        }

        for (int i = 0; i < m; i++) {
            charArrayOne[s1.charAt(i)]++;
            charArrayTwo[s2.charAt(i)]++;
            if (charArrayOne[s1.charAt(i)] != charArrayTwo[s2.charAt(i)]) return false;
        }

        return true;
    }

    //https://www.geeksforgeeks.org/transform-one-string-to-another-using-minimum-number-of-given-operation/
    public static int TransformOneStringToAnother(String A, String B) {

        int result = 0;

        if (A.length() != B.length()) return 0;

        int i = A.length() - 1, j = B.length() - 1;

        while (i >= 0) {
            if (A.charAt(i) != B.charAt(j)) {
                result++;
            } else j--;
            i--;

        }

        return result;
    }

    //https://leetcode.com/problems/sort-characters-by-frequency/
    public static void PrintCharactersFrequency(String str) {
        char[] s = str.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();

        for (Character c : s) {
            if (!map.containsKey(c)) map.put(c, 1);
            else map.put(c, map.get(c) + 1);
        }


        ArrayList<CharFreq> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> m : map.entrySet())
            list.add(new CharFreq(m.getKey(), m.getValue()));

        list.sort((o1, o2) -> o2.count - o1.count);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            CharFreq temp = list.get(i);
            for (int j = 0; j < temp.count; j++)
                sb.append(temp.character);
        }


        System.out.println(sb);
    }

    public static List<List<String>> PrintAnagramsTogether(String[] arr) {
        HashMap<String, List<String>> map = new HashMap<>();


        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            char[] a = s.toCharArray();
            Arrays.sort(a);
            String newString = String.valueOf(a);

            if (map.containsKey(newString)) {
                map.get(newString).add(s);
            } else {
                ArrayList<String> l = new ArrayList<>();
                l.add(s);
                map.put(newString, l);
            }

        }

//        for (Map.Entry<String, List<String>> mm : map.entrySet()) {
//            System.out.println(mm.getValue());
//        }

        List<List<String>> al = new ArrayList<>(map.size());
        for (int i = 0; i < map.size(); i++) al.add(new ArrayList<>());

        int index = 0;
        for (Map.Entry<String, List<String>> mm : map.entrySet()) {
            al.get(index++).addAll(mm.getValue());
        }

        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }
        return al;
    }

    public static int MinimumNumberOfSwaps(String str) {
        char[] s = str.toCharArray();
        int swaps = 0;

        int open = 0, closed = 0, imbalance = 0;

        for (int i = 0; i < s.length; i++) {
            if (s[i] == '[') {
                open++;
                if (imbalance > 0) {
                    swaps += closed - open;
                    imbalance--;
                }
            } else if (s[i] == ']') {
                closed++;

                imbalance = closed - open;
            }
        }

        return swaps;
    }

    public static void FirstRepeatedWord(String str) {
        String[] words = str.split(" ");

        String result = "";
        int index_of_string = str.length();

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) map.put(words[i], i);
            else {
                int index = map.get(words[i]);
                if (index < index_of_string) {
                    index_of_string = index;
                    result = words[i];
                }
            }
        }
        System.out.println("Result is  :: " + result);
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
