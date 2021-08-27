import java.util.Arrays;
import java.util.HashMap;

public class PractiseVII {


    public static void main(String[] args) {
        int[] arr = new int[]{101, 758, 315, 730, 472,
                619, 460, 479};

        System.out.println(hasSquareIntegers(8));

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
