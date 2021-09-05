import java.util.Arrays;

public class PractiseVIII {


    //Mostly BackTracking!
    public static void main(String[] args) {


        //Sudoku Board!
        int[][] board = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        /**
         * 3 1 6 5 7 8 4 9 2
         * 5 2 9 1 3 4 7 6 8
         * 4 8 7 6 2 9 5 3 1
         * 2 6 3 4 1 5 9 8 7
         * 9 7 4 8 6 3 1 2 5
         * 8 5 1 7 9 2 6 4 3
         * 1 3 8 9 4 7 2 5 6
         * 6 9 2 3 5 1 8 7 4
         * 7 4 5 2 8 6 3 1 9
         * */

//        if (SudokuSolver(board, 0, 0, board.length))
//            printMatrix(board);
//        else System.out.println("No Solution!");

        if (SudokuSolver(board, board.length))
            printMatrix(board);
        else System.out.println("No Solution!");

    }


    public static boolean SudokuSolver(int[][] board, int length) {

        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;

                    // We still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }

            if (!isEmpty) {
                break;
            }
        }

        // No empty space left
        if (isEmpty) {
            return true;
        }


        for (int number = 1; number <= length; number++) {
            if (isSafeForSudoku(board, row, col, number)) {
                board[row][col] = number;

                if (SudokuSolver(board, length))
                    return true;
                board[row][col] = 0;

            }
        }


        return false;

    }

    // Not a BackTracking Solution!! Time Complexity is O(9^(N*N))!! --> same for BackTracking!
    public static boolean SudokuSolver(int[][] matrix, int row, int col, int N) {

        if (row == N - 1 && col == N)
            return true;


        if (col == N) {
            col = 0;
            row++;
        }

        if (matrix[row][col] != 0)
            return SudokuSolver(matrix, row, col + 1, N);

        //number -> (1-9)
        for (int number = 1; number <= N; number++) {
            if (isSafeForSudoku(matrix, row, col, number)) {
                matrix[row][col] = number;

                if (SudokuSolver(matrix, row, col + 1, N))
                    return true;

                matrix[row][col] = 0;
            }
        }

        return false;
    }

    public static boolean isSafeForSudoku(int[][] matrix, int row, int column, int number) {

        //Searching in Row!!
        for (int i = 0; i < matrix.length; i++)
            if (matrix[row][i] == number)
                return false;


        //Searching in Column!!
        for (int i = 0; i < matrix.length; i++)
            if (matrix[i][column] == number)
                return false;


        //Searching in the Sub Grid!

        int sqrt = (int) Math.sqrt(matrix.length); // sqrt = 3;In most of the cases!
        int columnStart = column - column % sqrt;
        int rowStart = row - row % sqrt;


        for (int i = 0; i < sqrt; i++)
            for (int j = 0; j < sqrt; j++)
                if (matrix[i + rowStart][j + columnStart] == number)
                    return false;


        return true;
    }

    public static void PrintAllNQueenSolutions(int[][] matrix, int N, int col) {
        if (col >= N) {
            printMatrix(matrix);
            System.out.println("--------");
        }

        //Traversing all the row!!
        for (int i = 0; i < N; i++) {
            if (isSafeForNQueen(matrix, i, col, N)) {
                matrix[i][col] = 1;
                PrintAllNQueenSolutions(matrix, N, col + 1);
                matrix[i][col] = 0;
            }
        }
    }

    public static void NQueenProblemDriver(int n) {
        int N = n;

        int[][] solution = new int[N][N];
        for (int[] a : solution)
            Arrays.fill(a, 0);


//        if (!NQueenProblem(solution, N, 0)) {
//            System.out.println("Not Possible!!");
//        }
//        printMatrix(solution);

        PrintAllNQueenSolutions(solution, N, 0);

    }

    public static boolean NQueenProblem(int[][] matrix, int N, int col) {
        if (col >= N) {
            return true;
        }

        //Traversing all the row!!
        for (int i = 0; i < N; i++) {
            if (isSafeForNQueen(matrix, i, col, N)) {
                matrix[i][col] = 1;
                if (NQueenProblem(matrix, N, col + 1)) {
                    return true;
                }
                matrix[i][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafeForNQueen(int[][] board, int row, int col, int N) {

        //As we are fixing Queens in a Column there is no need to check right side of the matrix!!
        int i, j;
        /* Check this row on left side */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;


        return true;
    }

    public static void KnightTourProblem() {
        int N = 8;
        int[][] solution = new int[8][8];

        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                solution[x][y] = -1;


        solution[0][0] = 0;

        //Knight Moves!!
        int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};


        if (!KnightTourProblemSolver(solution, 0, 0, N, 1, xMove, yMove)) {
            System.out.println("Not Possible");
        } else {
            //Printing the Result!!
            printMatrix(solution);
        }
    }

    private static void printMatrix(int[][] solution) {
        int n = solution.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSafe(int x, int y, int sol[][], int N) {
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    //Time Complexity is 8^(n^2).
    private static boolean KnightTourProblemSolver(int[][] solution, int i, int j, int n, int index, int[] xMove, int[] yMove) {

        int next_x_move, next_y_move;

        int square = n * n;
        if (index == square)
            return true;
        //You could have used 8 if statements! Copied this method from GFG!!🥸
        for (int k = 0; k < 8; k++) {
            next_x_move = i + xMove[k];
            next_y_move = j + yMove[k];
            if (isSafe(next_x_move, next_y_move, solution, n)) {
                solution[next_x_move][next_y_move] = index;
                if (KnightTourProblemSolver(solution, next_x_move, next_y_move, n, index + 1, xMove, yMove))
                    return true;
                else solution[next_x_move][next_y_move] = -1; // Backtracking Important Step!
            }

        }
        return false;
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
