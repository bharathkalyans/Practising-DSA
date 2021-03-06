import java.util.*;

public class PractiseIII {

    public static void main(String[] args) {

    }

    public static int MinStepsToGetDesiredArray(int[] A) {
        int count = 0, n = A.length;

        while (true) {
            int zeroes = 0;
            int even = 0;

            //If Odd Numbers are found then Subtract with 1!
            for (int i = 0; i < n; i++) {
                if (A[i] % 2 == 1) {
                    A[i] = A[i] - 1;
                    count++;
                }
            }

            //If all are Zeroes then return Count!
            for (int i = 0; i < n; i++) {
                if (A[i] == 0) {
                    zeroes++;
                }
            }
            if (zeroes == n) {
                return count;
            }

            //If all are even numbers!
            for (int i = 0; i < n; i++) {
                if (A[i] % 2 == 0) {
                    even++;
                }
            }

            if (even == n) {
                for (int i = 0; i < n; i++) {
                    A[i] = A[i] / 2;
                }
                count++;
            }
        }

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

    //Time Complexity is O(N) and Space Complexity is O(K)!
    public static void MaxInWindowOfSizeK(int[] A, int k) {
        if (A.length == 0 || A.length == 1)
            return;

        Deque<Integer> dq = new LinkedList<>();
        int i = 0;
        int n = A.length;
        for (; i < k; i++) {
            while (!dq.isEmpty() && A[i] > A[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);
        }

        for (; i < n; i++) {
            System.out.println(dq.peek());
            while (!dq.isEmpty() && dq.peek() < i - k)
                dq.removeFirst();

            while (!dq.isEmpty() && A[i] > A[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);
        }


    }

    //Time Complexity is O(NLogN) and Space Complexity is O(K)!
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



















