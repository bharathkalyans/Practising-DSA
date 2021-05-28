import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PractiseII {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node res= ReverseLinkedList(head);
        printList(res);
        System.out.println("Size is :: " + sizeOfLinkedList(res));

    }


    public static Node ReverseLinkedList(Node node){

        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;

    }

    public static int sizeOfLinkedList(Node head){
        if (head == null)
            return 0;
        if (head.next == null)
            return 1;


        int count = 0;

        while (head!=null) {
            count++;
            head = head.next;
        }

        return count;
    }

    public static Node MiddleOfLinkedList(Node head){

        if (head == null)
            return null;

        Node fast=head,slow=head;


        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void printList(Node head){
        if (head ==null)
            return;

        while (head!=null){
            System.out.print(head.key+" --> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void CombinationsOfAString(String str,StringBuilder stringBuilder){
        //O(1) SPACE TIME COMPLEXITY!
        Combinations(str,stringBuilder,0);
    }

    public static void Combinations(String str,StringBuilder stringBuilder,int index){

        int n = str.length();

        for (int i=index;i<n;i++){

            stringBuilder.append(str.charAt(i));
            System.out.println(stringBuilder);
            Combinations(str,stringBuilder,i+1);

            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }


    }

    public static void CombinationsOfAString(String str){

        if (str.length() == 0){
            System.out.println("");
            return;
        }

        Queue<String> q = new LinkedList<>();
        int n = str.length();
        int i = 0;
        q.add("");
        while (i<n && !q.isEmpty()){
            int queueSize = q.size();
            for (int j=0;j<queueSize;j++){
                String p = q.poll();
                char character = str.charAt(i);
                String notAdded = p +"";
                String Added = p + character;
                q.add(notAdded);
                q.add(Added);

            }
            i++;
        }
        for (String s:q) System.out.println(s);

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

class Node{
    int key;
    Node next;

    Node(int key){
        this.key = key;
        this.next=null;
    }
}