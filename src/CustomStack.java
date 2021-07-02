public class CustomStack {

    private static final int MAX_SIZE = 3;
    int[] Stack = new int[MAX_SIZE];
    private int top = -1;

    public void push(int ele) {
        if (top >=MAX_SIZE-1)
            System.out.println("OverFlow");
        else {
            Stack[++top] = ele;
        }
    }

    public int pop() {
        if (top == -1)
            return -1;
        else
            return Stack[top--];
    }

    public int peek() {
        return top == -1 ? -1 : Stack[top];
    }

    public static void main(String[] args) {
        CustomStack s = new CustomStack();
        System.out.println(s.pop());
        s.push(12);
        s.push(13);
        s.push(14);
        s.push(15);
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());

    }
}
