public class PractiseVIII {


    //Mostly BackTracking!
    public static void main(String[] args) {

        Permutations("ABC", 0, 2);
        Permutations("ABC","");
        CombinationsW("ABC", "");

    }

    public static void Permutations(String str, int l, int r) {
        if (l == r) {
            System.out.println(str);
            return;
        }

        for (int i = l; i <= r; i++) {
            str = swap(str, l, i);
            Permutations(str, l + 1, r);
            str = swap(str, l, i);
        }
    }

    public static void Permutations(String str, String curr) {
        if (str.length() == 0) {
            System.out.println(curr);
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            char x = str.charAt(i);
            String left_sub_string = str.substring(0, i);
            String right_sub_string = str.substring(i + 1);

            String new_curr = curr + x;
            String new_str = left_sub_string + right_sub_string;

            Permutations(new_str, new_curr);

        }

    }

    private static String swap(String str, int l, int i) {
        char[] s = str.toCharArray();
        char a = s[l];
        s[l] = s[i];
        s[i] = a;
        return String.valueOf(s);
    }

    public static void CombinationsW(String str, String curr) {

        System.out.println(curr);
        if (str.length() == 0) return;

        for (int i = 0; i < str.length(); i++) {
            String newcurr = curr + str.charAt(i);
            String newstr = str.substring(0, i) + str.substring(i + 1);

            CombinationsW(newstr, newcurr);
        }

    }

}
