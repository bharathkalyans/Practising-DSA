import java.util.Arrays;
import java.util.TreeMap;

public class DemoClass {

    public static void main(String[] args) {

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
