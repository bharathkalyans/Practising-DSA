public class CustomQueue {

    private final int CAPACITY;
    private int[] Queue;
    private int size, front;

    CustomQueue(int cap) {
        this.CAPACITY = cap;
        this.size = 0;
        this.front = 0;
        this.Queue = new int[cap];
    }

    private boolean isFull() {
        return size == CAPACITY;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int getRear() {
        if (isEmpty())
            return -1;
        else
            return (front + size - 1) % CAPACITY;
    }

    private void enQueue(int ele) {
        if (isFull())
            return;

        int rear = getRear();
        rear = (rear + 1) % CAPACITY;
        Queue[rear] = ele;
        size++;
    }

    private void deQueue() {
        if (isEmpty()) return;

        Queue[front] = 0;
        front = (front + 1) % CAPACITY;
        size--;

    }

    public void printQueue() {
        for (int x : Queue)
            System.out.print(x + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        CustomQueue q = new CustomQueue(5);
        q.enQueue(12);
        q.enQueue(13);
        q.enQueue(14);
        q.enQueue(15);
        q.enQueue(16);
        q.printQueue();
        q.deQueue();
        q.deQueue();
        q.enQueue(17);
        q.printQueue();
    }
}
