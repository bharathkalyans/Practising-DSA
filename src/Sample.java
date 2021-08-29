/**
 * Static methods cannot be overridden!
 * We can't use super keyword in static blocks !
 *
* */
interface Animal {
    int age = 12;

    default void Sound() {
        System.out.println("I am Soind");
    }

    void greet();
}

 class Human {

    //    abstract void Walk();
    public void dance() {
        System.out.println("Nigga!! in Human  Class");
    }

}

public class Sample extends Human  {


    public static void main(String[] args) {

        Human s = new Sample();
        s.dance();

    }

    public void dance(){
        System.out.println("Nigga!! in Sample Class!");
        super.dance();
    }




}

