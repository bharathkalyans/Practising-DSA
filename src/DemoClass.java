import java.util.Arrays;

public class DemoClass {

    public static void main(String[] args) {

        int[] a = {7, 4, 3, 9, 1, 8, 5, 2, 6};
        getAverages(a, 3);


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
