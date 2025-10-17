import java.util.*;

public class Main {
    static char[][] board;
    static int n;
    static char currentPlayer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the board length:");
        n = sc.nextInt();

        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }

        currentPlayer = 'X';
        int moves = 0;
        boolean gameOver = false;

        while (!gameOver && moves < n * n) {
            printBoard();
            System.out.printf("Player %c, enter your move (row and column): ", currentPlayer);
            int r = sc.nextInt();
            int c = sc.nextInt();

            if (isValid(r, c)) {
                board[r][c] = currentPlayer;
                moves++;

                if (hasWon(r, c)) {
                    printBoard();
                    System.out.printf("Player %c wins!\n", currentPlayer);
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; 
                }
            } else {
                System.out.println("Invalid Move!");
            }
        }

        if (!gameOver) {
            printBoard();
            System.out.println("The game is a draw!");
        }

        sc.close();
    }
    private static void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n && board[r][c] == '-';
    }
    private static boolean hasWon(int r, int c) {
        return checkRow(r) || checkColumn(c) || checkDiagonal();
    }

    private static boolean checkRow(int r) {
        for (int c = 0; c < n; c++) {
            if (board[r][c] != currentPlayer)
                return false;
        }
        return true;
    }

    private static boolean checkColumn(int c) {
        for (int r = 0; r < n; r++) {
            if (board[r][c] != currentPlayer)
                return false;
        }
        return true;
    }

    private static boolean checkDiagonal() {
        boolean dia1 = true, dia2 = true;

        for (int i = 0; i < n; i++) {
            if (board[i][i] != currentPlayer)
                dia1 = false;
            if (board[i][n - i - 1] != currentPlayer)
                dia2 = false;
        }

        return dia1 || dia2;
    }
}
