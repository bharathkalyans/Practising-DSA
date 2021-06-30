

public class WorkingWithThreads {

    int counter = 1;
    private static int N;

    public void printOddNumber() {
        synchronized (this) {
            while (counter < N) {
                while (counter % 2 == 1) {
                    try {
                        wait();
                    } catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(counter + " ");
                counter++;

                notify();
            }
        }
    }

    public void printEvenNumber() {
        synchronized (this) {
            while (counter < N) {
                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(counter + " ");
                counter++;

                notify();
            }
        }
    }

    public static void main(String[] args) {

        WorkingWithThreads object = new WorkingWithThreads();
        N = 50;



        Thread Thread1 = new Thread() {
            public void run() {
                object.printEvenNumber();
            }
        };

        Thread Thread2 = new Thread() {
            public void run() {
                object.printOddNumber();
            }
        };


        Thread1.start();
        Thread2.start();
    }
}
