public class SingleTon {


    private static SingleTon single_instance = null;
    public String s;

    private SingleTon() {
        s = "Singleton";
    }

    public static SingleTon getInstance() {

        if (single_instance == null) {
            single_instance = new SingleTon();
        }
        return single_instance;
    }

    public static void main(String[] args) {

        SingleTon x = SingleTon.getInstance();
        SingleTon y = SingleTon.getInstance();
        SingleTon z = SingleTon.getInstance();

        x.s = (x.s).toUpperCase();

        System.out.println("From x is  " + x.s);
        System.out.println("From y is  " + y.s);
        System.out.println("From z is  " + z.s);

        z.s = z.s.toLowerCase();

        System.out.println("From x is  " + x.s);
        System.out.println("From y is  " + y.s);
        System.out.println("From z is  " + z.s);

    }
}
