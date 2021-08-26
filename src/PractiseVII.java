import java.util.Arrays;
import java.util.HashMap;

public class PractiseVII {


    public static void main(String[] args) {
        int[] arr = new int[]{101, 758, 315, 730, 472,
                619, 460, 479};


        String x = Integer.toBinaryString(8);
        int c = Integer.parseInt(x,2);
        System.out.println(x + " :: - > answer :: " + c);

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
