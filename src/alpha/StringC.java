
package alpha;

public class StringC {
     static class Node {

        Node child[];
        boolean eow;

        Node() {
            this.child = new Node[26];
            for (int i = 0; i < 26; i++) {
                child[i] = null;
            }
            this.eow = false;
        }
    }

   
    static Node node = new Node();
    public static Node insert(String word) {
        Node root = node;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (root.child[idx] == null) {
                root.child[idx] = new Node();
            }
            if (i == word.length() - 1) {
                root.child[idx].eow = true;
            }
            root = root.child[idx];
        }
        return root;
    }
    
    public static boolean search(String key) {
        Node root = node;
        for(int i=0; i<key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if(root.child[idx] == null) {
                return false;
            }
            if(i == key.length()-1 && !root.child[idx].eow) {
                return false;
            }
            root = root.child[idx];
        }
        return true;
    }
    public boolean wordBreak(String key) {
        if(key.length() == 0) {
            return true;
        }
        for(int i=0; i<key.length(); i++) {
            String firstPartOfString = key.substring(0, i+1);
            String secondPartOfString = key.substring(i+1);
            if(search(firstPartOfString) && wordBreak(secondPartOfString)) {
                return true;
            }
        }
        return false;
    }
    public boolean startsWith(String key) {
        Node root  = node;
        for(int i=0; i<key.length(); i++) {
            int idx = key.charAt(i)-'a'; 
            if(root.child[idx] == null) {
                return false;
            }
            root = root.child[idx];
        }
        return true;
    }
    public int countNodes(Node root) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        for(int i=0; i<26; i++) {
            if(root.child[i] != null) {
                count += countNodes(root.child[i]);
            }
        }
        return count+1;
    }
    public static String ans = "";
    public void longestWord(Node root, StringBuilder temp) {
        if(root == null) {
            return;
        }
        for(int i=0; i<26; i++) {
            if(root.child[i] != null && root.child[i].eow == true) {
                temp.append((char)(i+'a'));
                if(temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWord(root.child[i], temp);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }
    private static void printPermutation(String str, String ans) {
        if(str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            String newString = str.substring(0, i) + str.substring(i+1);
            printPermutation(newString, ans+ch);
        }
    }
    private static boolean isSafe(char board[][], int row, int col) {
        // vertical up
        for(int i=row-1; i>=0; i--) {
            if(board[i][col] == 'Q') {
                return false;
            }
        }
        // diagnal left 
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        
        // diagnal right
        for(int i=row-1, j=col+1; i>=0 && j<board.length; i--, j++) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    private static void printboard(char board[][]) {
        System.out.println("-------- chess board --------");
        int n = board.length;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static boolean nQueens(char board[][], int row) {
        // base case!
        if(row == board.length) {
            count++;
            return true;
        }
        for(int col=0; col<board.length; col++) {
            if(isSafe(board, row, col)){
                board[row][col] = 'Q';
                if(nQueens(board, row+1))return true;
                board[row][col] = 'x';
            }
        }
        return false;
    }
    static int  count = 0;
    private static int gridWays(int n, int m) {
        return fact(n-1+m-1)/(fact(n-1) * fact(m-1));
    }
    private static int fact(int n) {
        if(n == 1) return 1;
        return n * fact(n-1);
    }
    public static void printboard(int board[][]) {
        int n = board.length;
        System.out.println("------- board is -------");
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static boolean isSafe(int board[][], int row, int col, int digit) {
        // colum
        for(int i=0; i<=8; i++) {
            if(board[i][col] == digit) {
                return false;
            }
        }
        // row
        for(int j=0; j<=8; j++) {
            if(board[row][j] == digit) {
                return false;
            }
        }
        // grid
        int sr = (row/3) *3;
        int sc = (col/3) *3;
        for(int i=sr; i<sr+3; i++) {
            for(int j=sc; j<sc+3; j++) {
                if(board[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean sudokuSolver(int board[][], int row, int col) {
        if(row == board.length) {
            return true;
        }
        int newrow = row, newcol = col+1;
        if(col+1 == board.length) {
            newcol = 0;
            newrow = row+1;
        }
        if(board[row][col] != 0) {
            return sudokuSolver(board, newrow, newcol);
        }
        for(int i=1; i<=9; i++) {
            if(isSafe(board, row, col, i)) {
                board[row][col] = i;
                if(sudokuSolver(board, newrow, newcol)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }
    private static boolean isSafe(int maze[][], int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 1;
    }
    private static boolean ratmazeSolution(int maze[][], int x, int y, int sol[][]) {
        if(x == maze.length-1 && y == maze[0].length-1 && maze[x][y ] == 1) {
            sol[x][y] = 1;
            return true;
        }
        if(isSafe(maze, x, y)){
            if(sol[x][y] == 1) {
                return false;
            }
            sol[x][y] = 1;
            if(ratmazeSolution(maze, x+1, y, sol)) {
                return true;
            }
            if(ratmazeSolution(maze, x, y+1, sol)) {
                return true;
            }
            if(ratmazeSolution(maze, x, y-1, sol)) {
                return true;
            }
            if(ratmazeSolution(maze, x-1, y, sol)) {
                return true;
            }
            sol[x][y] = 0;
            //return false;
        }
        return false;
    }
    private static void printSolution(int sol[][]) {
        for(int i[] : sol) {
            for(int j : i) {
                System.out.print(j+ " ");
            }
            System.out.println();
        }
    }
    private static void ratInMaze(int maze[][]) {
        int n = maze.length;
        int m = maze[0].length;
        int sol[][] = new int[n][m];
        if(!ratmazeSolution(maze, 0, 0, sol)) {
            System.out.println("Solution doesn't Exist!");
        } else {
            System.out.println("Solution does Exist! that is : ");
            printSolution(sol);
        }
    }
    private static void printCombinations(String str) {
        int len = str.length();
        combinations(0, len, new StringBuilder(), str);
    }
    private static void combinations(int pos, int length, StringBuilder sb, String str) {
        if(pos == length) {
            System.out.println(sb);
            return;
        }
        char[] letters = keyPad[Character.getNumericValue(str.charAt(pos))];
        for(int i=0; i<letters.length; i++) {
            combinations(pos+1, length, new StringBuilder(sb).append(letters[i]), str);
        }
    }
    private static boolean isSafeKnight(int board[][], int x, int y) {
        return x >= 0 && y >= 0 && x < board.length && y < board.length && board[x][y] == 0;
    }
    private static boolean knightsTourSolution(int x, int y, int move, int xmove[], int ymove[], int board[][]) {
        // base case!
        if(move == board.length * board.length) {
            return true;
        }
        for(int i=0; i<board.length; i++) {
            int next_x = x + xmove[i];
            int next_y = y + ymove[i];
            if(isSafeKnight(board, next_x, next_y)) {
                board[next_x][next_y] = move;
                if(knightsTourSolution(next_x, next_y, move+1, xmove, ymove, board)) {
                    return true;
                }
                board[next_x][next_y] = 0;
            }
        }
        return false;
    }
    private static void knightsTour(int board[][]) {
        int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};
        if(!knightsTourSolution(0, 0, 1, xMove, yMove, board)) {
            System.out.println("Solution Doesn't Exist!");
        } else {
            printboard(board);
        }
    } 
    static char[][] keyPad = {{}, {}, {'a','b', 'c'}, 
        {'d','e', 'f'}, {'g','h', 'i'}, {'j', 'k', 'l' }, 
        {'m', 'n', 'o' }, {'p','q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z' } };
    public static void main(String args[]) {
//        int board[][] = new int[8][8];
//        knightsTour(board);
        
//        printCombinations("23");
//        
        int maze[][] = {
            {1, 1, 1, 1 },
            {0, 0, 1, 0 },
            {0, 1, 1, 0 },
            {0, 1, 0, 0 },
            {0, 1, 1, 1 } 
        };
        ratInMaze(maze);

//        String str = "xyz";
//        printPermutation(str, "");
//        int n = 2;
//        char board[][] = new char[n][n];
//        for(int i=0; i<n; i++) {
//            for(int j=0; j<n; j++) {
//                board[i][j] = 'x';
//            }
//        }
//        boolean f = nQueens(board, 0);
//        //System.out.println(count);
//        if(f) {
//            System.out.println("solution is posible!");
//            printboard(board);
//        } else {
//            System.out.println("solution not posible!");
//        }
//        int val = gridWays(3, 3);
//        System.out.println(val);

//        int sudoku[][] = {
//            {0, 0, 8, 0, 0, 0, 0, 0, 0},
//            {4, 9, 0, 1, 5, 7, 0, 0, 2},
//            {0, 0, 3, 0, 0, 4, 1, 9, 0},
//            {1, 8, 5, 0, 6, 0, 0, 2, 0},
//            {0, 0, 0, 0, 2, 0, 0, 6, 0},
//            {9, 6, 0, 4, 0, 5, 3, 0, 0},
//            {0, 3, 0, 0, 7, 2, 0, 0, 4},
//            {0, 4, 9, 0, 3, 0, 0, 5, 7},
//            {8, 2, 7, 0, 0, 9, 0, 1, 3}    
//        };
//        
//        if(sudokuSolver(sudoku, 0, 0)) {
//            System.out.println("solution exist");
//            printboard(sudoku);
//        } else {
//            System.out.println("solution does not exist");
//        }
    }
}
