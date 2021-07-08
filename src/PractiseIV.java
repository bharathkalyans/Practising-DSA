import java.util.LinkedList;
import java.util.Queue;

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        this.left = this.right = null;
    }
}

//This Class will contain mostly Tree Questions!
public class PractiseIV {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.right = new Node(8);

        System.out.println(HeightOfTree(root));

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

