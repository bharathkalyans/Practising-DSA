import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PractiseII {

    public static void main(String[] args) {

        PermutationsOfAString("ABC","");


    }

    public static void PermutationsOfAString(String str, String curr){
        if (str.length() == 0){
            System.out.println(curr);
            return;
        }


        for (int i=0;i<str.length();i++){
            String newCurrString = curr +  str.charAt(i);
            String remainingString = str.substring(0,i) + str.substring(i+1);
            PermutationsOfAString(remainingString,newCurrString);
        }

    }

    //Below is a Efficient Function with TC O(N)
    public static void printBinaryValuesEfficient(int n){

        if (n == 1){
            System.out.println("1");
            return;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("1");

        while (n!=0){

            String s = queue.poll();
            System.out.print(s+" ");

            String rs = s+"0";
            queue.add(rs);
            String ls = s +"1";
            queue.add(ls);
            n--;

        }

    }

    //Overall Time Complexity would be O(N Log N)!
    public static void printBinaryValues(int n){
        //Outer loop Runs N times
        for (int i=0;i<=n;i++){
            //DecimalToBinary() is a LogN Function!
            DecimalToBinary(i);
        }
    }

    public static void DecimalToBinary(int number){
        StringBuilder sb = new StringBuilder();

        while(number!=0){
            int rem = number%2;
            sb.append(rem);
            number /= 2;
        }
        System.out.print(sb.reverse().toString() + " ");

    }

    public static String CountAndSay(int n){
        String output = "1";

        for (int i=1;i<n;i++){
            output = getOutputSay(output);
        }
        return output;

    }
    public static String getOutputSay(String str){
        char[] array = str.toCharArray();
        char lastElement = array[0];
        int count = 0;
        StringBuilder buffer  = new StringBuilder();

        for (char c: array){
            if (c == lastElement)
                count++;
            else {
                buffer.append(count).append(lastElement);
                count = 1;
                lastElement = c;
            }
        }

        buffer.append(count).append(lastElement);
        return buffer.toString();

    }

    public static void frequencyOfCharacters(String str){
       //Using a fixed size array rather than a HasMap makes it more Efficient!
        int[] freq = new int[128];

        Arrays.fill(freq,0);

        for (int i=0;i<str.length();i++){
            freq[str.charAt(i)] += 1;
        }

        for (int i=0;i<128;i++){
            if (freq[i]>0){
                System.out.println((char)(i) +" " + freq[i]);
            }
        }
    }



}
