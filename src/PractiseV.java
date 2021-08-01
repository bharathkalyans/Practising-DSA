import java.util.TreeSet;

public class PractiseV {

    //This Class will contain mostly BST Problems!
    public static void main(String[] args) {
        Node root = new Node(10);
        InsertIntoBST(root, 5);
        InsertIntoBST(root, 12);
        InsertIntoBST(root, 4);
        InsertIntoBST(root, 13);
        InsertIntoBST(root, 7);
        InsertIntoBST(root, 11);
        InsertIntoBST(root, 100);





    }



    public static long GetnthUglyNumber(int n) {

        TreeSet<Long> t = new TreeSet<>();

        //Fist Ugly Number
        t.add(1L);
        int i = 1;
        // when i==n we have the nth ugly number
        while (i < n) {
            // remove the ith ugly number and add back its
            // multiples of 2,3 and 5 back to the set
            long temp = t.pollFirst();
            t.add(temp * 2);
            t.add(temp * 3);
            t.add(temp * 5);
            i++;
            // the first element of set is always the ith
            // ugly number
        }

        return t.pollFirst();
    }

    //Naive Approach!
    public static int GetNthUglyNumber(int n) {

        int t = 0;
        int UGLY_NUMBER = 1;
        int nthUgly = 0;
        while (nthUgly != n) {
            if (isUglyNumber(t)) {
                UGLY_NUMBER = t;
                nthUgly++;
            }
            t++;
        }

        return UGLY_NUMBER;
    }

    public static boolean isUglyNumber(int n) {
        if (n == 1)
            return true;

        if (n == 0) return false;

        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;

        return n == 1;
    }

    public static void GenerateAllCompositeNumbers(int N) {

        if (N == 1) {
            System.out.println("1");
            return;
        }

        while (N % 2 == 0) {
            System.out.print("2" + " ");
            N /= 2;
        }

        for (int i = 3; i * i < N; i += 2) {
            if (N % i == 0) {
                System.out.print(i + " ");
                N = N / i;
            }
        }

        if (N > 2)
            System.out.print(N);

    }

    public static long powerOfTwo(int n) {
        if (n == 1)
            return 2;
        return powerOfTwo(n - 1) * 2;
    }

    public static int CeilOfBSTIterative(Node root, int Key) {
        Node res = null;

        while (root != null) {

            if (root.value == Key)
                return root.value;
            else if (root.value < Key)
                root = root.right;
            else {
                res = root;
                root = root.left;
            }

        }
        return res.value;
    }

    public static int FloorOfBSTIterative(Node root, int Key) {

        Node res = null;

        while (root != null) {
            if (root.value == Key)
                return root.value;
            else if (root.value > Key) {
                root = root.left;
            } else {
                res = root;
                root = root.right;
            }
        }

        assert res != null;
        return res.value;
    }

    public static int MIN_VALUE = Integer.MIN_VALUE;

    // Inefficient and recursive!
    public static void FloorOfBST(Node root, int Key) {
        if (root != null) {
            FloorOfBST(root.left, Key);
            if (root.value <= Key)
                MIN_VALUE = Math.max(MIN_VALUE, root.value);
            FloorOfBST(root.right, Key);
        }
    }

    public static Integer GetMaxInBST(Node root) {
        if (root == null || root.right == null)
            return Integer.MAX_VALUE;

        Node curr = root.right;
        while (curr.right != null)
            curr = curr.right;

        return curr.value;
    }

    public static Integer GetMinInBST(Node root) {
        if (root == null || root.left == null)
            return Integer.MIN_VALUE;

        Node curr = root.left;
        while (curr.left != null)
            curr = curr.left;

        return curr.value;
    }

    public static Node DeleteInBST(Node root, int key) {

        if (root == null)
            return null;
        if (root.value > key)
            root.left = DeleteInBST(root.left, key);
        else if (root.value < key)
            root.right = DeleteInBST(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                Node src = getSourceNode(root);
                System.out.println("Source is :: " + src.value);
                root.value = src.value;
                root.right = DeleteInBST(root.right, src.value);
            }

        }

        return root;
    }

    private static Node getSourceNode(Node root) {
        Node curr = root.right;

        while (curr != null && curr.left != null)
            curr = curr.left;

        return curr;
    }

    public static boolean SearchInBST(Node root, int key) {
        if (root == null)
            return false;

        if (root.value == key)
            return true;

        if (root.value > key)
            return SearchInBST(root.left, key);
        else
            return SearchInBST(root.right, key);
    }

    public static Node InsertIntoBST(Node root, int key) {
        if (root == null)
            return new Node(key);

        if (root.value == key)
            return root;
        if (root.value > key)
            root.left = InsertIntoBST(root.left, key);
        else
            root.right = InsertIntoBST(root.right, key);

        return root;
    }

    public static void InorderTraversal(Node root) {
        if (root != null) {
            InorderTraversal(root.left);
            System.out.print(root.value + "  ");
            InorderTraversal(root.right);
        }
    }


}
