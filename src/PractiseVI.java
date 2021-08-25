import java.util.*;

//Graphs Problems!!
public class PractiseVI {

    public static void main(String[] args) {
        int V = 7;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(V);

        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        AddEdge(graph, 0, 1);
        AddEdge(graph, 0, 4);
        AddEdge(graph, 1, 2);
        AddEdge(graph, 1, 3);
        AddEdge(graph, 1, 4);
        AddEdge(graph, 2, 3);
        AddEdge(graph, 2, 4);
        AddEdge(graph, 5, 5);

        ArticulationPointDriver(graph, V);
    }


    public static void ArticulationPointDriver(ArrayList<ArrayList<Integer>> adj, int v) {

        boolean[] visited = new boolean[v];
        int[] timeOfInsertion = new int[v];
        int[] lowTime = new int[v];

        boolean[] isArticulationPoint = new boolean[v];
        int timer = 0;
        for (int i = 0; i < v; i++) {
            if (!visited[i])
                ArticulationPoint(i, -1, adj, visited, timeOfInsertion, lowTime, timer, isArticulationPoint);

        }

        for (int i = 0; i < v; i++)
            if (isArticulationPoint[i]) System.out.println(i + " is an Articulation Point!");

    }

    private static void ArticulationPoint(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int[] timeOfInsertion, int[] lowTime, int timer, boolean[] isArticulationPoint) {
        visited[node] = true;
        timeOfInsertion[node] = lowTime[node] = timer++;
        int child = 0;
        for (int x : adj.get(node)) {
            if (x == parent) continue;

            if (!visited[x]) {
                ArticulationPoint(x, node, adj, visited, timeOfInsertion, lowTime, timer, isArticulationPoint);
                lowTime[node] = Math.min(lowTime[node], lowTime[x]);

                if (lowTime[x] >= lowTime[node] && parent != -1)
                    isArticulationPoint[node] = true;

                //Child is Used if a node has more than 2 not connected adjacents!
                //In that case the node will become a Articulation Point if child>1;
                child++;
            } else {
                lowTime[node] = Math.min(lowTime[node], timeOfInsertion[x]);
            }
        }
        //Little Confused Here, should we take parent == -1??🧐
        if (parent != -1 && child > 1) isArticulationPoint[node] = true;
    }

    public static void gen(String str) {
        ArrayList<String> al = new ArrayList<String>();
        StringGenerate(str.toCharArray(), 0, al);
        for (String x : al) System.out.println(x);
    }

    public static void StringGenerate(char[] str, int i, ArrayList<String> al) {
        if (i == str.length) {
            al.add(String.valueOf(str));
            return;
        }

        if (str[i] == '?') {
            str[i] = '0';
            StringGenerate(str, i + 1, al);
            str[i] = '1';
            StringGenerate(str, i + 1, al);
            //Important Step (Back Tracking)!!!!!!!!
            str[i] = '?';
        } else
            StringGenerate(str, i + 1, al);

    }

    public static void BridgesInAGraph(ArrayList<ArrayList<Integer>> adj, int v) {
        Boolean[] visited = new Boolean[v];
        int[] timeOfInsertion = new int[v];
        int[] lowTime = new int[v];

        Arrays.fill(visited, false);

        int timer = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i])
                BridgesInAGraphUtil(i, adj, visited, timeOfInsertion, lowTime, -1, timer);
        }

    }

    private static void BridgesInAGraphUtil(int node, ArrayList<ArrayList<Integer>> adj, Boolean[] visited, int[] timeOfInsertion, int[] lowTime, int parent, int timer) {
        visited[node] = true;
        lowTime[node] = timeOfInsertion[node] = timer++;

        for (int adjacentNode : adj.get(node)) {

            if (adjacentNode == parent) continue;

            if (!visited[adjacentNode]) {
                //DFS Call!
                BridgesInAGraphUtil(adjacentNode, adj, visited, timeOfInsertion, lowTime, node, timer);
                lowTime[node] = Math.min(lowTime[adjacentNode], lowTime[node]);

                if (lowTime[adjacentNode] > timeOfInsertion[node]) {
                    System.out.println("Bridge b/w " + node + "" + adjacentNode);
                }
            } else {
                lowTime[node] = Math.min(lowTime[node], timeOfInsertion[adjacentNode]);
            }
        }


    }

    //Minimum Cost to connect all Cities

    public static int minCost(int[][] graph) {
        int V = graph.length;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        Arrays.fill(parent, -1);
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);

        int minimum_cost = 0;

        parent[0] = -1;
        key[0] = 0;

        for (int i = 0; i < V - 1; i++) {
            //Get Min Value from Key Array!
            int u = getMinValue(V, key, mstSet);

            mstSet[u] = true;

            for (int j = 0; j < V; j++) {
                if (graph[i][j] != 0 && !mstSet[j] && graph[i][j] < key[j]) {
                    parent[j] = u;
                    key[j] = graph[i][j];
                }
            }

        }

        for (int i = 1; i < V; i++) {
            minimum_cost += graph[parent[i]][i];
        }


        return minimum_cost;
    }

    public static int getMinValue(int v, int[] key, boolean[] mstSet) {
        int mini_value = Integer.MAX_VALUE;
        int min_index = -1;

        for (int i = 0; i < v; i++) {
            if (!mstSet[i] && key[i] < mini_value) {
                mini_value = key[i];
                min_index = i;
            }
        }
        return min_index;
    }

    //Minimum Spanning Tree
    public static void PrimsAlgorithm(ArrayList<ArrayList<node>> adj, int v) {

        int[] key = new int[v];
        boolean[] mstSet = new boolean[v];
        int[] parent = new int[v];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);
        Arrays.fill(parent, -1);

        //Starting with '0' as Source Node.
        key[0] = 0;


        //Only a Total of V-1 Edges are allowed else it will make a cycle!
        for (int i = 0; i < v - 1; i++) {
            int mini = Integer.MAX_VALUE, u = 0;

            //Finding Min Element in the Key Array!
            for (int c = 0; c < v; c++) {
                if (!mstSet[c] && key[c] < mini) {
                    mini = key[c];
                    u = c; // Keeping track of the Index to Traverse their adjacents
                }
            }

            //Marking that node as visited or added to MST!
            mstSet[u] = true;

            for (node x : adj.get(u)) {
                if (!mstSet[x.vertice] && x.weight < key[x.vertice]) {
                    parent[x.vertice] = u;
                    key[x.vertice] = x.weight;
                }
            }
        }

        for (int i = 1; i < v; i++) System.out.println(parent[i] + " - " + i);

    }

    public static void DijkstrasAlgorithm(ArrayList<ArrayList<node>> adj, int src, int v) {

        int[] distance = new int[v];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<node> pq = new PriorityQueue<>(v, new node());

        pq.add(new node(0, src));
        distance[src] = 0;

        while (!pq.isEmpty()) {
            node t = pq.poll();

            for (node x : adj.get(t.vertice)) {
                if (distance[x.vertice] > distance[t.vertice] + x.weight) {
                    distance[x.vertice] = distance[t.vertice] + x.weight;
                    pq.add(new node(distance[x.vertice], x.vertice));
                }
            }
        }
        for (int x : distance) System.out.println("Distance from Source " + src + " to other vertices is :" + x);
    }

    public static void ShortestPathUsingTopoLogicalSort(ArrayList<ArrayList<Integer>> adj, int src, int v) {
        Boolean[] visited = new Boolean[v];
        Stack<Integer> s = new Stack<>();
        int[] distance = new int[v];

        TopoSort(adj, s, src, visited);
        //Now your stack has The Topological Sort.

        //Marking it with INFINITY.
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[src] = 0;

        //Have to use Pair!
        /*
        Class pair{
            int vertice;
            int weight;
            pair(int v,int weight){
                vertice = v;
                weight = weight;
            }
        }
        * */
        int weight = 0;

        while (!s.isEmpty()) {
            int node = s.pop();

            if (distance[node] != Integer.MAX_VALUE) {
                for (int x : adj.get(node)) {
                    if (distance[x] > distance[node] + weight)
                        distance[x] = distance[node] + weight;
                }
            }
        }

        for (int x : distance) System.out.println(x + " from  source : " + src);

    }

    //Get The Highest Number from an Array
    public static void GetMaximumNumberFromArray(String[] arr) {

        Arrays.sort(arr, new Comparator<String>() {

            @Override
            public int compare(String X, String Y) {

                String XY = X + Y;
                String YX = Y + X;

                return XY.compareTo(YX) > 0 ? -1 : 1;
            }
        });

        for (String x : arr)
            System.out.print(x);

        System.out.println();
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //Convert 2D to Adjacent List
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < prerequisites.length; i++) {
            int secondCourse = prerequisites[i][1];
            int firstCourse = prerequisites[i][0];
            adj.get(secondCourse).add(firstCourse);
        }

        Stack<Integer> stack = new Stack<>();
        int[] array = new int[stack.size()];

        int index = 0;
        while (!stack.isEmpty()) {
            array[index++] = stack.pop();

        }

        return array;
    }

    public static void TopoSort(ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack, int i, Boolean[] visited) {
        visited[i] = true;

        for (int x : adj.get(i)) {
            if (!visited[x])
                TopoSort(adj, stack, x, visited);
        }
        stack.push(i);
    }


    public int[] FO(int numCourses, int[][] prerequisites) {

        //Convert 2D to Adjacent List
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < prerequisites.length; i++) {
            int secondCourse = prerequisites[i][1];
            int firstCourse = prerequisites[i][0];
            adj.get(secondCourse).add(firstCourse);
        }


        //Used for BFS(Kahn's Algorithm)
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();


        //InDegree
        int[] InDegree = new int[numCourses];
        for (int i = 0; i < adj.size(); i++)
            for (int x : adj.get(i))
                InDegree[x]++;


        for (int i = 0; i < InDegree.length; i++)
            if (InDegree[i] == 0)
                q.add(i);


        while (!q.isEmpty()) {
            int element = q.poll();

            for (int x : adj.get(element)) {
                InDegree[x]--;
                if (InDegree[x] == 0)
                    q.add(x);
            }
        }

        return new int[]{};
    }

    //Kahn's Algorithm
    public static void TopologicalSortBFS(ArrayList<ArrayList<Integer>> adj, int v) {

        int[] toposort = new int[v];
        Queue<Integer> q = new LinkedList<>();

        //Finding InDegree ::
        int[] indegree = new int[v];

        for (int i = 0; i < v; i++) {
            for (int x : adj.get(i)) {
                indegree[x]++;
            }
        }

        for (int i = 0; i < v; i++)
            if (indegree[i] == 0)
                q.add(i);

        int index = 0;
        while (!q.isEmpty()) {
            int ele = q.poll();

//            System.out.println("Polled : " + ele);

            toposort[index++] = ele;

            for (int x : adj.get(ele)) {
                indegree[x]--;
                if (indegree[x] == 0)
                    q.add(x);
            }
        }

        for (int x : toposort) System.out.print(x + " ");

    }

    public static void TopologicalSortDriver(ArrayList<ArrayList<Integer>> adj, int v) {
        Boolean[] visited = new Boolean[v];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                TopologicalSortDFS(adj, visited, stack, i);
            }
        }

        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }

    private static void TopologicalSortDFS(ArrayList<ArrayList<Integer>> adj, Boolean[] visited, Stack<Integer> stack, int i) {
        visited[i] = true;

        for (int x : adj.get(i)) {
            if (!visited[x]) {
                TopologicalSortDFS(adj, visited, stack, x);
            }
        }


        stack.push(i);
    }

    public static boolean HasACycleInDirectedGraph(ArrayList<ArrayList<Integer>> adj, int v, Boolean[] visited, Boolean[] recursiveStack, int i) {
        visited[i] = true;
        recursiveStack[i] = true;

        for (int x : adj.get(i)) {
            if (!visited[x]) {
                if (HasACycleInDirectedGraph(adj, v, visited, recursiveStack, x))
                    return true;
            } else {
                //I will be entering this block of Code if the Node is visited!
                //So all I need is to check the recursive Stack!
                return recursiveStack[x];
            }

        }

        recursiveStack[i] = false;
        return false;
    }

    public static boolean IsBiPartite(ArrayList<ArrayList<Integer>> adj, int v) {

        int[] color = new int[v + 1];
        Arrays.fill(color, -1);

        for (int i = 0; i < v; i++) {
            if (checkBipartite(adj, color, i))
                return false;
        }

        return true;
    }

    private static boolean checkBipartite(ArrayList<ArrayList<Integer>> adj, int[] color, int i) {

        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        color[i] = 1;

        while (!q.isEmpty()) {
            int value = q.poll();
            for (int x : adj.get(value)) {
                if (color[x] == -1) {
                    color[x] = 1 - color[value];
                    q.add(x);
                } else {
                    if (color[x] == color[value])
                        return false;
                }
            }
        }

        return true;
    }

    public static void DepthFirstSearchOfGraph(ArrayList<ArrayList<Integer>> adj, int v, int source) {

        Boolean[] visited = new Boolean[v];
        Arrays.fill(visited, false);
        DFSRecursive(adj, visited, v, source);
    }

    private static void DFSRecursive(ArrayList<ArrayList<Integer>> adj, Boolean[] visited, int v, int source) {
        System.out.print(source + " ");
        visited[source] = true;

        for (int f : adj.get(source)) {
            if (!visited[f])
                DFSRecursive(adj, visited, v, f);
        }

    }

    public static void PrintGraph(ArrayList<ArrayList<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex " + i);
            System.out.print("head");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> " + adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    //For UnDirected Graph Only!
    public static void AddEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void BFSOFDISCONNECTEDGRAPH(ArrayList<ArrayList<Integer>> al, int v) {
        Boolean[] visited = new Boolean[v];
        Arrays.fill(visited, false);
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                BFSOfGraph(al, visited, v, i);
            }
        }
    }

    public static void BFSOfGraph(ArrayList<ArrayList<Integer>> al, Boolean[] visited, int v, int s) {
        Queue<Integer> q = new LinkedList<>();
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            System.out.print(q.peek() + " ");
            int x = q.poll();

            ArrayList<Integer> temp = al.get(x);
            for (int g : temp) {
                if (!visited[g]) {
                    q.add(g);
                    visited[g] = true;
                }
            }
        }
        System.out.println();
    }

    public static void BFSOfGraph(ArrayList<ArrayList<Integer>> al, int v, int s) {

        Queue<Integer> q = new LinkedList<>();
        Boolean[] visited = new Boolean[v];

        Arrays.fill(visited, false);
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            System.out.print(q.peek() + " ");
            int x = q.poll();

            for (int g : al.get(x)) {
                if (!visited[g]) {
                    q.add(g);
                    visited[g] = true;
                }
            }
        }
        System.out.println();
    }

}

class node implements Comparator<node> {
    int weight, vertice;

    node() {
    }

    node(int d, int v) {
        this.weight = d;
        this.vertice = v;
    }

    @Override
    public int compare(node o1, node o2) {
        if (o1.weight < o2.weight) return -1;
        if (o1.weight > o2.weight) return 1;
        return 0;
    }
}