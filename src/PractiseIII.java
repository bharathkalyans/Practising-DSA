import java.util.*;

public class PractiseIII {

    public static void main(String[] args) {

        System.out.println(LookAndSaySequence(5));

    }


    public static String LookAndSaySequence(int n) {
        String c = "1";
        for (int i = 1; i < n; i++) {
            c = getLookAndSaySequence(c);
            System.out.println(c);
        }

        return c;
    }

    public static String getLookAndSaySequence(String str) {

        char[] array = str.toCharArray();
        char lastElement = array[0];
        int count = 0;
        StringBuilder buffer = new StringBuilder();

        for (char c : array) {
            if (c == lastElement)
                count++;
            else {
                buffer.append(count).append(lastElement);
                count = 1;
                lastElement = c;
            }
        }

        buffer.append(count).append(lastElement);
        return buffer.toString();
    }

    public static void MaxInWindowSizeOfK(int[] A, int k) {

        PriorityQueue<Integer> q = new PriorityQueue<>(k, Collections.reverseOrder());
        int i = 0;
        for (; i < k; i++) {
            q.add(A[i]);
        }

        System.out.println(q.peek());
        q.remove(A[0]);

        for (; i < A.length; i++) {
            q.add(A[i]);
            System.out.println(q.peek());
            q.remove(A[i - k + 1]);
        }

        System.out.println();

    }

    //Short Answer! will not pass All TEST CASES!!!
    public static void PossibleSet(char[] a, int k, String str) {
        if (str.length() == k) {
            System.out.println(str);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            String newString = str + a[i];
            PossibleSet(a, k, newString);
        }
    }

    public static void PossibleSetOfKLength(char[] a, int k, String str, int n) {
        if (k == 0) {
            System.out.println(str);
            return;
        }
        for (int i = 0; i < n; i++) {
            String newString = str + a[i];
            PossibleSetOfKLength(a, k - 1, newString, n);
        }
    }

    public static void ReverseQueueByK(Queue<Integer> q, int K) {
        if (K > q.size())
            return;
        Queue<Integer> t = new LinkedList<>();
        int count = K;
        while (count > 0) {
            t.add(q.remove());
            count--;
        }
        t = ReverseAQueue(t);
        System.out.println(t.toString());
        while (!q.isEmpty()) {
            t.add(q.remove());
        }

        System.out.println(t.toString());

    }

    public static void ReverseAQueueRecursion(Queue<Integer> q) {
        if (q.size() == 1)
            return;
        int x = q.remove();
        ReverseAQueueRecursion(q);
        q.add(x);
    }

    public static Queue<Integer> ReverseAQueue(Queue<Integer> q) {
        int count = q.size();
        if (count == 0 || count == 1)
            return q;

        Stack<Integer> s = new Stack<>();
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        while (!s.isEmpty())
            q.add(s.pop());

//        System.out.println(q.toString());
        return q;
    }

    public static void RotateAQueueByX(Queue<Integer> q, int X) {
        int count = q.size();
        if (count == 0 || count == 1)
            return;

        while (X > 1) {
            q.add(q.remove());
            X--;
        }
        System.out.println(q.toString());
    }

}



















