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
        ReverseAQueue(q);
    }

    public static void ReverseAQueue(Queue<Integer> q) {
        int count = q.size();
        if (count == 0 || count == 1)
            return;

        Stack<Integer> s = new Stack<>();
        while (!q.isEmpty()){
            s.push(q.remove());
        }
        while (!s.isEmpty())
            q.add(s.pop());

        System.out.println(q.toString());
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



















