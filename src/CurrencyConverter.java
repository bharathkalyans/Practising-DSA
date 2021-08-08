import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Currency :: (Double Digit)");
        double payment = sc.nextDouble();
        sc.close();

        Locale indianLocale = new Locale("en", "IN");
        Locale usLocale = new Locale("en", "US");
        NumberFormat us = NumberFormat.getCurrencyInstance(usLocale);
        NumberFormat china = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat japanese = NumberFormat.getCurrencyInstance(Locale.JAPANESE);
        NumberFormat india = NumberFormat.getCurrencyInstance(indianLocale);

        System.out.println("US: " + us.format(payment));
        System.out.println("India: " + india.format(payment));
        System.out.println("China: " + china.format(payment));
        System.out.println("France: " + japanese.format(payment));



    }
}
