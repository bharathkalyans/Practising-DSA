import java.util.Arrays;
import java.util.HashMap;

public class PractiseVII {


    //Greedy Problems! Mostly!!!
    public static void main(String[] args) {
        int[] arr = new int[]{101, 758, 315, 730, 472,
                619, 460, 479};

        int[] brr = new int[]{5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5};
        System.out.println(lemonadeChange(brr));
    }


    //Lemonade Change Problem LeetCode!!
    //https://leetcode.com/problems/lemonade-change/
    public static boolean lemonadeChange(int[] bills) {

        int dollar_5_bill = 0;
        int dollar_10_bill = 0;
        int dollar_20_bill = 0;

        for (int i = 0; i < bills.length; i++) {
            int amount = bills[i];

            if (amount == 5) {
                dollar_5_bill++;
                continue;
            } else if (amount == 10) {
                dollar_10_bill++;
                amount -= 5;
                if (dollar_5_bill > 0) {
                    dollar_5_bill--;
                    continue;
                } else return false;

            } else {
                amount -= 5;
                dollar_20_bill++;
                if (dollar_10_bill > 0 && dollar_5_bill > 0) {
                    dollar_10_bill--;
                    dollar_5_bill--;

                }else if(dollar_5_bill > 3){
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
