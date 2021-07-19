
public class PractiseV {

    public static void main(String[] args) {
        Node root = new Node(10);
        InsertIntoBST(root, 5);
        InsertIntoBST(root, 12);
        InsertIntoBST(root, 4);
        InsertIntoBST(root, 13);

        InorderTraversal(root);
        System.out.println();
        DeleteInBST(root, 13);
        DeleteInBST(root, 10);
        InorderTraversal(root);
        System.out.println();


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
