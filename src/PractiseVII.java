import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PractiseVII {


    //Greedy Problems! Mostly!!!
    public static void main(String[] args) {
        int[] A = new int[]{101, 758, 315, 730, 472, 619, 460, 479};


        int B[][] = new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}};

        maxEvents(B);

    }


    //Maximum Events we can Attend!! in a Day 🙄, But LeetCode Question is Twisted! 🤥.
    //https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
    public static int maxEvents(int[][] events) {

        if (events == null || events[0].length == 0) return 0;

        if (events[0].length == 1)
            return 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=1;i<100000;i++)
            pq.add(i);

        Arrays.sort(events, (a, b) -> a[1] != b[1]? a[1] - b[1]: a[0] - b[0]);


        for (int[] a : events) {
            for (int x : a)
                System.out.print(x + " ");

            System.out.println();
        }

        int max_meeting = 1;
        int previous_meeting = 0;

        for (int i = 1; i < events.length; i++) {

            if (events[i][0] == events[previous_meeting][0] && events[i][1] == events[previous_meeting][1]) {
                max_meeting++;
                continue;
            }

            if (events[i][0] >= events[previous_meeting][1]) {
                max_meeting++;
                previous_meeting = i;
            }
        }

        return max_meeting;
    }

    //Lemonade Change Problem LeetCode!!
    //https://leetcode.com/problems/lemonade-change/
    public static boolean lemonadeChange(int[] bills) {

        int dollar_5_bill = 0;
        int dollar_10_bill = 0;

        for (int i = 0; i < bills.length; i++) {
            int amount = bills[i];

            if (amount == 5) {
                dollar_5_bill++;
                continue;
            } else if (amount == 10) {
                dollar_10_bill++;

                if (dollar_5_bill > 0) {
                    dollar_5_bill--;
                    continue;
                } else return false;

            } else {
                if (dollar_10_bill > 0 && dollar_5_bill > 0) {
                    dollar_10_bill--;
                    dollar_5_bill--;

                } else if (dollar_5_bill > 3) {
                    dollar_5_bill -= 3;
                } else return false;


            }
        }
        return true;
    }

    public static boolean hasSquareIntegers(int c) {

        if (c < 0) return false;

        if (c == 1) return true;
        int size = c / 2 + 1;

        int low = 0, high = (int) Math.sqrt(c);
        while (low <= high) {
            if (Math.pow(low, 2) + Math.pow(high, 2) == c) return true;
            else if (Math.pow(low, 2) + Math.pow(high, 2) > c) high--;
            else low++;
        }

        return false;
    }

    public static int MinimumSwapsToSortArray(int[] arr) {

        int swaps = 0;
        int[] temp = Arrays.copyOfRange(arr, 0, arr.length);
        HashMap<Integer, Integer> map = new HashMap<>();

        Arrays.sort(temp);
        for (int i = 0; i < arr.length; i++)
            map.put(arr[i], i);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != temp[i]) {

                swaps++;

                int value = arr[i];
                swap(arr, i, map.get(temp[i]));

                map.put(value, map.get(temp[i]));
                map.put(temp[i], i);

            }
        }
        return swaps;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
