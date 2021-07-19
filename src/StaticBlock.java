class Scaler {
    static int i;

    static {
        i = 100;
        System.out.println("I am in Scaler Static!");
    }
}

public class StaticBlock {
    static {
        System.out.println("i will be always first!!");
        System.out.println("b");
    }

    public static void main(String[] args) {
        System.out.println("c");
        System.out.println(Scaler.i);
    }


}