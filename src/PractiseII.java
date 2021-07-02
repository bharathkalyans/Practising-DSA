import java.util.*;

public class PractiseII {

    public static void main(String[] args) {

        /*Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);*/

        reverseStringUsingStack("Bharath Kalyan");
    }

    public static void reverseStringUsingStack(String string) {

        Stack<Character> stack = new Stack<>();
        for (char x : string.toCharArray()) {
            stack.push(x);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }

    public static void nextSmallerElement(int[] a, int n) {

        int[] nextSmaller = new int[n];
        Stack<Integer> stack = new Stack<>();
        nextSmaller[n - 1] = -1;
        stack.push(a[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && a[i] < stack.peek())
                stack.pop();

            int ele = stack.isEmpty() ? -1 : stack.peek();
            nextSmaller[i] = ele;
            stack.push(a[i]);

        }

        for (int x : nextSmaller)
            System.out.print(x + " ");

        System.out.println();
    }

    public static void nextGreaterElement(int[] a, int n) {

        int nextArray[] = new int[n];

        Stack<Integer> stack = new Stack<>();
        nextArray[n - 1] = -1;
        stack.push(a[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && a[i] > stack.peek())
                stack.pop();

            int ele = stack.isEmpty() ? -1 : stack.peek();
            nextArray[i] = ele;
            stack.push(a[i]);
        }


        for (int x : nextArray)
            System.out.print(x + " ");


        System.out.println();
    }

    public static void previousGreaterElement(int[] a, int n) {

        int[] prevArray = new int[n];
        Stack<Integer> stack = new Stack<>();
        prevArray[0] = -1;
        stack.push(a[0]);
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && a[i] > stack.peek())
                stack.pop();

            int ele = stack.isEmpty() ? -1 : stack.peek();
            prevArray[i] = ele;
            stack.push(a[i]);
        }

        for (int x : prevArray)
            System.out.print(x + " ");

        System.out.println();
    }

    public static String removeAllAdjacentInStrings(String string) {
        if (string.length() == 0 || string.length() == 1)
            return string;

        StringBuilder sb = new StringBuilder();
        for (char c : string.toCharArray()) {
            int size = sb.length();
            if (size > 0 && sb.charAt(size - 1) == c) {
                sb.deleteCharAt(size - 1);
            } else
                sb.append(c);
        }

        return sb.toString();

    }

    public static void stockSpan(int[] stockPrice) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        System.out.println("1");

        for (int i = 1; i < stockPrice.length; i++) {
            while (!stack.isEmpty() && stockPrice[stack.peek()] < stockPrice[i])
                stack.pop();
            int stock = stack.isEmpty() ? 1 : i - stack.peek();
            System.out.println(stock);
            stack.push(i);

        }

    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int sumOfDigitsUntilSingleDigit(int number) {
        if (number < 10) {
            return number;
        }
        return sumOfDigitsUntilSingleDigit(sumOfDigits(number));
    }

    public static int sumOfDigits(int number) {
        int sum = 0;
        int temp = number;

        while (temp != 0) {
            int digit = temp % 10;
            sum += digit;
            temp = temp / 10;
        }
        System.out.println("sum :: " + sum);
        return sum;
    }

    public static String EncryptMessage(String message, String key) {
        if (message.length() == 0 || key.length() == 0)
            return "";

        StringBuilder builder = new StringBuilder();
        int i = 0, j = 0;
        int messageLength = message.length();
        int keyLength = key.length();

        while (i < messageLength && j < keyLength) {
            char character = message.charAt(i);
            int repeat = key.charAt(j) - '0';
            for (int k = 0; k < repeat; k++) {
                builder.append(character);
            }
            i++;
            j++;
        }
        if (i != messageLength) {
            builder.append(message.substring(i));
        }

        return builder.toString();
    }

    public static String DecryptMessage(String encryptedMessage, String key) {
        if (encryptedMessage.length() == 0 || key.length() == 0)
            return "";

        StringBuilder builder = new StringBuilder();
        int i = 0, j = 0;
        int messageLength = encryptedMessage.length();
        int keyLength = key.length();

        while (j < keyLength) {
            char mainChar = encryptedMessage.charAt(i);
            builder.append(mainChar);
            i = i + key.charAt(j) - '0';
            j++;
        }

        builder.append(encryptedMessage.substring(i));
        return builder.toString();
    }

    //Created a Simple Function to Display tables for Kids!!XD
    public static void tables() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter times::");
        long number = sc.nextLong();
        for (long j = 0; j <= number; j++) {
            for (long i = 0; i <= 10; i++) {
                System.out.println(j + " X  " + i + " = " + (j * i));
            }
            System.out.println("***********");
        }
    }

    //This function is used when the input String doesn't contain any spaces between them.
    public static void ValueOfX2(String str) {
        String[] a = str.split("\\+|=");
        int result = 0;

        if (a[0].equals("X")) {
            result = Integer.parseInt(a[2]) - Integer.parseInt(a[1]);
        } else if (a[1].equals("X")) {
            result = Integer.parseInt(a[2]) - Integer.parseInt(a[0]);
        } else {
            result = Integer.parseInt(a[0]) + Integer.parseInt(a[1]);
        }

        System.out.println();
        System.out.println("Value of X is :: " + result);

    }

    //This function totally depends on the Input Formatting.
    //If Inout is of Wrong Format this method will not work.
    public static void ValueOfX(String string) {
        int result = 0;

        String[] a = string.split(" ");
        for (String x : a) System.out.print(x + " ");
        if (a[0].equals("X")) {
            result = Integer.parseInt(a[4]) - Integer.parseInt(a[2]);
        } else if (a[2].equals("X")) {
            result = Integer.parseInt(a[4]) - Integer.parseInt(a[0]);
        } else {
            result = Integer.parseInt(a[0]) + Integer.parseInt(a[2]);
        }
        System.out.println();
        System.out.println("Value of X is :: " + result);
    }

    public static boolean hasLoopFloydCycleDetection(Node head) {
        if (head == null || head.next == null)
            return false;

        Node fast = head, slow = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;

    }

    public static boolean hasLoop(Node root) {
        //Richard Brent Algorithm : :
        //He claims that this algorithm is 20 -34 % faster than Floyd Cycle detection Algorithm.
        if (root == null) return false;

        Node slow = root, fast = root;
        int taken = 0, limit = 2;

        while (fast.next != null) {
            fast = fast.next;
            taken++;
            if (slow == fast) return true;

            if (taken == limit) {
                taken = 0;
                limit <<= 1;    // equivalent to limit *= 2;
                slow = fast;    // teleporting the turtle (to the hare's position)
            }
        }
        return false;
    }

    //Recursive Solution
    public static Node ReverseLinkedListByK(Node head, int k) {

        if (head == null)
            return null;

        Node curr = head;
        Node prev = null;
        Node next = null;

        int count = 0;

        while (count < k && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        if (next != null)
            head.next = ReverseLinkedListByK(next, k);

        return prev;

    }

    //Stack based Approach
    public static Node ReverseLinkedListByKUsingStack(Node head, int k) {
        if (head == null)
            return null;

        Stack<Node> stack = new Stack<>();

        Node curr = head;
        Node prev = null;
        int count = 0;
        while (curr != null) {

            while (curr != null && count < k) {
                stack.add(curr);
                curr = curr.next;
                count++;
            }

            while (stack.size() > 0) {
                if (prev == null) {
                    prev = stack.peek();
                    head = prev;
                    stack.pop();
                } else {
                    prev.next = stack.peek();
                    prev = prev.next;
                    stack.pop();
                }
            }
            count = 0;

        }
        prev.next = null;
        return head;
    }

    //Recursive Solution!
    public static Node ReverseLinkedList(Node curr, Node prev) {

        if (curr == null) return prev;

        Node next = curr.next;
        curr.next = prev;

        return ReverseLinkedList(next, curr);

    }

    //Iterative Solution!
    public static Node ReverseLinkedList(Node node) {

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

    public static int sizeOfLinkedList(Node head) {
        if (head == null)
            return 0;
        if (head.next == null)
            return 1;


        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    public static Node MiddleOfLinkedList(Node head) {

        if (head == null)
            return null;

        Node fast = head, slow = head;


        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void printList(Node head) {
        if (head == null)
            return;

        while (head != null) {
            System.out.print(head.key + " --> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void CombinationsOfAString(String str, StringBuilder stringBuilder) {
        //O(1) SPACE TIME COMPLEXITY!
        Combinations(str, stringBuilder, 0);
    }

    public static void Combinations(String str, StringBuilder stringBuilder, int index) {

        int n = str.length();

        for (int i = index; i < n; i++) {

            stringBuilder.append(str.charAt(i));
            System.out.println(stringBuilder);
            Combinations(str, stringBuilder, i + 1);

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }


    }

    public static void CombinationsOfAString(String str) {

        if (str.length() == 0) {
            System.out.println("");
            return;
        }

        Queue<String> q = new LinkedList<>();
        int n = str.length();
        int i = 0;
        q.add("");
        while (i < n && !q.isEmpty()) {
            int queueSize = q.size();
            for (int j = 0; j < queueSize; j++) {
                String p = q.poll();
                char character = str.charAt(i);
                String notAdded = p + "";
                String Added = p + character;
                q.add(notAdded);
                q.add(Added);

            }
            i++;
        }
        for (String s : q) System.out.println(s);

    }

    public static void PermutationsOfAString(String str, String curr) {
        if (str.length() == 0) {
            System.out.println(curr);
            return;
        }


        for (int i = 0; i < str.length(); i++) {
            String newCurrString = curr + str.charAt(i);
            String remainingString = str.substring(0, i) + str.substring(i + 1);
            PermutationsOfAString(remainingString, newCurrString);
        }

    }

    //Below is a Efficient Function with TC O(N)
    public static void printBinaryValuesEfficient(int n) {

        if (n == 1) {
            System.out.println("1");
            return;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("1");

        while (n != 0) {

            String s = queue.poll();
            System.out.print(s + " ");

            String rs = s + "0";
            queue.add(rs);
            String ls = s + "1";
            queue.add(ls);
            n--;

        }

    }

    //Overall Time Complexity would be O(N Log N)!
    public static void printBinaryValues(int n) {
        //Outer loop Runs N times
        for (int i = 0; i <= n; i++) {
            //DecimalToBinary() is a LogN Function!
            DecimalToBinary(i);
        }
    }

    public static void DecimalToBinary(int number) {
        StringBuilder sb = new StringBuilder();

        while (number != 0) {
            int rem = number % 2;
            sb.append(rem);
            number /= 2;
        }
        System.out.print(sb.reverse().toString() + " ");

    }

    public static String CountAndSay(int n) {
        String output = "1";

        for (int i = 1; i < n; i++) {
            output = getOutputSay(output);
        }
        return output;

    }

    public static String getOutputSay(String str) {
        char[] array = str.toCharArray();
        char lastElement = array[0];
        int count = 0;
        StringBuilder buffer = new StringBuilder();

        for (char c : array) {
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

    public static void frequencyOfCharacters(String str) {
        //Using a fixed size array rather than a HasMap makes it more Efficient!
        int[] freq = new int[128];

        Arrays.fill(freq, 0);

        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)] += 1;
        }

        for (int i = 0; i < 128; i++) {
            if (freq[i] > 0) {
                System.out.println((char) (i) + " " + freq[i]);
            }
        }
    }


}

class Node {
    int key;
    Node next;

    Node(int key) {
        this.key = key;
        this.next = null;
    }
}