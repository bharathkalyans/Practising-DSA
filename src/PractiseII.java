import java.util.Arrays;

public class PractiseII {

    public static void main(String[] args) {

        frequencyOfCharacters("Bharath BhygwEFBsanksmk aDBuagfjbs #@@$872384y  jfhueHF");

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
