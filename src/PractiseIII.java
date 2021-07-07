import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PractiseIII {

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
/*        ReverseAQueue(q);
        ReverseAQueueRecursion(q);
        System.out.println(q.toString());*/

//        ReverseQueueByK(q, 3);
        String s = "123456789";
//        PossibleSet(s.toCharArray(),4,"1");
        PossibleSetOfKLength(s.toCharArray(),7,"",s.length());
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



















