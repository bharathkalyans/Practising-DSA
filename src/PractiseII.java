import java.util.Arrays;

public class PractiseII {

    public static void main(String[] args) {

        printBinaryValues(10);

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
        System.out.println(sb.reverse().toString() + " ");

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
