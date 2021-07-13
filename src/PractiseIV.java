import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        this.left = this.right = null;
    }
}

//This Class will mostly contain Tree Questions!
public class PractiseIV {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);
        root.right.right.right = new Node(9);
        root.right.right.right.left = new Node(10);
        root.right.right.right.right = new Node(11);


        System.out.println(MaximumWidthOfBinaryTree(root));
    }


    public static int MaximumWidthOfBinaryTree(Node root) {
        if (root == null)
            return 0;

        int maxWidth = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int count = q.size();
            maxWidth = Math.max(maxWidth, count);

            while (count-- > 0) {
                Node t = q.remove();

                if (t.left != null)
                    q.add(t.left);

                if (t.right != null)
                    q.add(t.right);
            }
        }

        return maxWidth;
    }

    /*//O(n^2) Time Complexity!
    public static int MaximumWidthOfABinaryTree(Node root) {
        if (root == null)
            return 0;

        int lh = HeightOfTree(root.left);
        int rh = HeightOfTree(root.right);

        return (Math.max((lh + rh + 1),
                Math.max(MaximumWidthOfABinaryTree(root.left),
                        MaximumWidthOfABinaryTree(root.right))));

    }
*/
    public static void InorderTraversalUsingStack(Node root) {

        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (curr != null || stack.size() > 0) {

            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();

            System.out.print(curr.value + " ");

            curr = curr.right;

        }

    }

    static int MAX_LEVEL_RIGHT = -1;

    public static void RightViewOfTree(Node root) {
        RightViewOfTree(root, 0);
        System.out.println();
    }

    private static void RightViewOfTree(Node root, int level) {
        if (root == null) return;

        if (level > MAX_LEVEL_RIGHT) {
            System.out.print(root.value + " ");
            MAX_LEVEL_RIGHT = level;
        }
        RightViewOfTree(root.right, level + 1);
        RightViewOfTree(root.left, level + 1);

    }

    static int MAX_LEVEL_LEFT = -1;

    public static void LeftViewOfTree(Node root) {
        LeftViewOfTree(root, 0);
        System.out.println();
    }

    private static void LeftViewOfTree(Node root, int level) {
        if (root == null) return;

        if (level > MAX_LEVEL_LEFT) {
            System.out.print(root.value + " ");
            MAX_LEVEL_LEFT = level;
        }
        LeftViewOfTree(root.left, level + 1);
        LeftViewOfTree(root.right, level + 1);

    }

    public static int GetMaxOfTree(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;

        return Math.max(root.value,
                Math.max(GetMaxOfTree(root.left),
                        GetMaxOfTree(root.right))
        );
    }

    public static void PrintNodesAtDistanceK(Node root, int k) {
        if (root != null && k >= 0) {
            if (k == 0) {
                System.out.println(root.value);
                return;
            } else {
                PrintNodesAtDistanceK(root.left, k - 1);
                PrintNodesAtDistanceK(root.right, k - 1);
            }
        }
    }

    public static int HeightOfTree(Node root) {
        if (root == null)
            return 0;

        return (Math.max(
                HeightOfTree(root.left),
                HeightOfTree(root.right)
        ) + 1);
    }

    public static void BreadthFirstSearch(Node root) {
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node t = q.poll();
            System.out.println(t.value);

            if (t.left != null)
                q.add(t.left);

            if (t.right != null)
                q.add(t.right);
        }

        System.out.println(q.toString());

    }

    private static void InorderTraversal(Node root) {
        if (root != null) {
            InorderTraversal(root.left);
            System.out.print(root.value + "  ");
            InorderTraversal(root.right);
        }
    }

}

