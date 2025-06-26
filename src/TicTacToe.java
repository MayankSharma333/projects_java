import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];

        // Initialize board with empty spaces
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        char player = 'X';
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard(board);
            System.out.println("Player " + getColoredText(player) + ", enter row and column (0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Validate move
            if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            // Place move
            board[row][col] = player;
            gameOver = haveWon(board, player);

            if (gameOver) {
                printBoard(board);
                System.out.println("🎉 Player " + getColoredText(player) + " wins! 🎉");
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw! 🏳");
                break;
            } else {
                player = (player == 'X') ? 'O' : 'X';  // Switch player
            }
        }
        scanner.close();
    }

    // Check if a player has won
    public static boolean haveWon(char[][] board, char player) {
        // Check rows & columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // Check if the board is full (Draw condition)
    public static boolean isBoardFull(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Print the board with colorized output
    public static void printBoard(char[][] board) {
        System.out.println("\n  0   1   2 ");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < 3; col++) {
                char cell = board[row][col];
                System.out.print(getColoredText(cell));
                if (col < 2) System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) System.out.println("  ---------");
        }
        System.out.println();
    }

    // Get colored text for X (Red) and O (Blue)
    public static String getColoredText(char ch) {
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";
        if (ch == 'X') return red + "X" + reset;
        if (ch == 'O') return blue + "O" + reset;
        return " ";
    }
}
