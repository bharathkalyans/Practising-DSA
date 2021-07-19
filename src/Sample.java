

interface Animal {

     int age = 12;

    default void Sound() {
        System.out.println("I am Soind");
    }

    public abstract void greet();

}

abstract class Human {

    //    abstract void Walk();
    public void dance() {
    }

}

public class Sample extends Human implements Animal {


    public static void main(String[] args) {

        Sample s = new Sample();
        s.Sound();
        s.dance();


        int c = Animal.age;

    }


    public static void NthRoot(int x){
        
    }

    @Override
    public void greet() {

    }




/*
    public int majorityElement(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        int result = -1;
        int n = arr.length;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (value > (n / 2)) result = key;

        }
        return result;

    }

    public int maj(int[] aa) {
        int count = 1;
        int n = aa.length;
        int res = 0;


        for (int i = 1; i < n; i++) {
            if (aa[i] == aa[res]) count++;
            else count--;

            if (count == 0) {
                res = i;
                count = 1;
            }

        }


//        System.out.println(aa[res]);

        return aa[res];

    }

    public static boolean getMajority(int value, int[] a) {
        int count = 0;


        for (int i = 0; i < a.length; i++) {
            if (a[i] == value)
                count++;
        }

        return count > a.length / 2;
    }*/

}

