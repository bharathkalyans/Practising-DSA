import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class PractiseVII {


    //Greedy Problems! Mostly!!!
    public static void main(String[] args) {
        int[] A = new int[]{101, 758, 315, 730, 472, 619, 460, 479};


        int B[][] = new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}};
        int[][] C = new int[][]{{1, 100000}};

        System.out.println(maxEvents(C));



    }

    public static double FractionalKnapSack(ArrayList<Item> arr, int weight) {

        double max_value = 0.0;
        Collections.sort(arr, (o1, o2) -> (o1.value * o2.weight) - (o2.value * o1.weight));
        for (int i = 0; i < arr.size(); i++) {
            if (weight - arr.get(i).weight >= 0) {
                max_value += arr.get(i).value;
                weight -= arr.get(i).weight;
            } else {
                max_value += arr.get(i).value * (double) (weight / arr.get(i).weight);
                break;
            }
        }
        return max_value;
    }

    //0-1 Knapsack Problem
    //Recursive Solution!
    public static int KnapSack01(int index, int[] weight, int[] value, int W, int n) {

        if (W == 0 || index >= n) return 0;

        if (weight[index] <= W) {
            //Will take the weight!!
            int left = value[index] + KnapSack01(index + 1, weight, value, W - weight[index], n);
            //Will not take the weight!
            int right = KnapSack01(index + 1, weight, value, W, n);

            return Math.max(left, right);
        } else {
            return KnapSack01(index + 1, weight, value, W, n);
        }

    }


    //Maximum Events we can Attend!! in a Day 🙄, But LeetCode Question is Twisted! 🤥. After some digging understood the goddamn question 😂
    //Still giving TLE for this Approach, have to use Min Heap I guess🥲
    //https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
    public static int maxEvents(int[][] events) {

        if (events == null || events.length == 0) return 0;

        if (events.length == 1)
            return 1;
        // Sorting the Array! Greedy Approach!
        Arrays.sort(events, (o1, o2) -> {
            if (o1[1] != o2[1])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        //Is that day is added to the meeting or not!
        boolean[] isAdded = new boolean[events[events.length - 1][1] + 1];

        int max_meetings = 0;

        int index = 0;
        while (index < events.length) {
            for (int i = events[index][0]; i <= events[index][1]; i++) {
                if (!isAdded[i]) {
                    isAdded[i] = true;
                    max_meetings++;
                    break;
                }
            }
            index++;
        }

        return max_meetings;


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

class Item {
    int value, weight;

    Item() {
    }

    Item(int v, int w) {
        this.value = v;
        this.weight = v;
    }
}