public class PractiseVIII {


    //Mostly BackTracking!
    public static void main(String[] args) {

        int maze[][] = {
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1}};

        RatInMaze(maze);

    }

    public static void RatInMaze(int[][] Maze) {
        int R = Maze.length;
        int C = Maze[0].length;

        int[][] solution = new int[R][C];

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                solution[i][j] = 0;


        if (RatInAMazeProblem(Maze, solution, 0, 0, R, C)) {
            System.out.println("Print Solution Array!!");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(solution[i][j] + " ");
                }
                System.out.println();
            }

        } else
            System.out.println("No Solution!!");

    }

    public static boolean RatInAMazeProblem(int[][] maze, int[][] solution, int i, int j, int R, int C) {
        //This condition itself is a isSafe() function!! 😀
        if (i >= 0 && j >= 0 && i < R && j < C && maze[i][j] != 0 && solution[i][j] != 1) {
            solution[i][j] = 1;
            if (i == R - 1 && j == C - 1)
                return true;

            if (RatInAMazeProblem(maze, solution, i, j + 1, R, C))
                return true;
            if (RatInAMazeProblem(maze, solution, i + 1, j, R, C))
                return true;
            if (RatInAMazeProblem(maze, solution, i, j - 1, R, C))
                return true;
            if (RatInAMazeProblem(maze, solution, i - 1, j, R, C))
                return true;

            solution[i][j] = 0;
            return false;
        } else return false;
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
