

public class WorkingWithThreads {
    public static void main(String[] args) {

        Thread Thread1 = new Thread() {
            public void run() {
                System.out.println("Hello I am in a Thread" + this.getName());

            }
        };

        Thread Thread2 = new Thread() {
            public void run() {
                System.out.println("Hello I am in a Thread" + this.getName());
            }
        };

        Thread1.start();
        Thread2.start();
    }
}
