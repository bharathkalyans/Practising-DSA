public class DisjointSets {

    static int n = 7;
    private static int[] parent = new int[100000];
    private static int[] rank = new int[100000];

    //We will use Rank and Parent for Disjoint Sets.
    public static void main(String[] args) {
        makeSet();
    }


    private static void makeSet() {
        //Making all nodes parents as itself!
        for (int i = 1; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }


    private static int findParent(int node) {
        if (parent[node] == node)
            return node;

        //7 --> 6 --> 5 --> 3
        //After Path Compression.
        //7 -->3
        //6 -->3
        //5 -->3

        //Path Compression! --> Efficient way!
        return parent[node] = findParent(parent[node]);
    }


    private static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (rank[a] < rank[b]) {
            parent[a] = b;
        } else if (rank[b] < rank[a]) {
            parent[b] = a;
        } else {
            parent[a] = b;
            rank[b]++;
        }

    }


}












