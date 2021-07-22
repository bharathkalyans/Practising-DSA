
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

        System.out.println(CeilOfBSTIterative(root, 6));

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
