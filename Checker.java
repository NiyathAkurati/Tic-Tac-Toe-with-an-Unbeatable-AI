import javax.swing.JOptionPane;

/**
 * Checker - Determines win or draw conditions in a Tic-Tac-Toe game.
 * 
 * The class checks the board after every move to see if:
 *  1. X wins
 *  2. O wins
 *  3. The game ends in a draw
 * 
 * It updates the UI text field and shows a popup box with the result.
 */
public class Checker {
    /* Text of popup box (e.g., "X Wins", "O Wins", "Draw") */
    private static String result;

    /* Reference to the Player object (handles UI updates like text field changes) */
    private static Player playerObject2;

    /* Tracks if the board is full (used for detecting draws) */
    private static boolean boardFull = true;

    /**
     * Constructs a Checker with a Player object reference.
     * @param p the Player instance
     */
    public Checker(Player p) {
        playerObject2 = p;
    }

    /**
     * Default constructor creates a new Player if none is passed in.
     */
    public Checker() {
        playerObject2 = new Player();
    }

    /**
     * The method is called every time a move is made to check for a win or a draw.
     * 
     * @param game the current board (3x3 matrix)
     *             0 = empty, 1 = O, 2 = X
     */
    public static void checkWin(int[][] game) {
        
        // ---------- ROW WINS ----------
        for (int r = 0; r < 3; r++) {
            if ((game[r][0] == game[r][1]) && (game[r][1] == game[r][2])) {
                if (game[r][0] == 2) { // X wins
                    playerObject2.textFieldSetter("X Wins!");
                    result = "X Wins";
                    JOptionPane.showMessageDialog(null, result + "!");
                }
                if (game[r][0] == 1) { // O wins
                    playerObject2.textFieldSetter("O Wins!");
                    result = "O Wins";
                    JOptionPane.showMessageDialog(null, result + "!");
                }
            }
        }

        // ---------- COLUMN WINS ----------
        for (int c = 0; c < 3; c++) {
            if ((game[0][c] == game[1][c]) && (game[1][c] == game[2][c])) {
                if (game[0][c] == 2) { // X wins
                    playerObject2.textFieldSetter("X Wins!");
                    result = "X Wins";
                    JOptionPane.showMessageDialog(null, result + "!");
                }
                if (game[0][c] == 1) { // O wins
                    playerObject2.textFieldSetter("O Wins!");
                    result = "O Wins";
                    JOptionPane.showMessageDialog(null, result + "!");
                }
            }
        }

        // ---------- DIAGONAL WINS ----------
        // Top-left to bottom-right diagonal
        if ((game[0][0] == game[1][1]) && (game[1][1] == game[2][2])) {
            if (game[0][0] == 2) { // X wins
                playerObject2.textFieldSetter("X Wins!");
                result = "X Wins";
                JOptionPane.showMessageDialog(null, result + "!");
            }
            if (game[0][0] == 1) { // O wins
                playerObject2.textFieldSetter("O Wins!");
                result = "O Wins";
                JOptionPane.showMessageDialog(null, result + "!");
            }
        }

        // Top-right to bottom-left diagonal
        if ((game[0][2] == game[1][1]) && (game[1][1] == game[2][0])) {
            if (game[0][2] == 2) { // X wins
                result = "X Wins";
                JOptionPane.showMessageDialog(null, result + "!");
                playerObject2.textFieldSetter("X Wins!");
            }
            if (game[0][2] == 1) { // O wins
                playerObject2.textFieldSetter("O Wins!");
                result = "O Wins";
                JOptionPane.showMessageDialog(null, result + "!");
            }
        }

        // ---------- DRAW CHECK ----------
        // Assume board is full, then check for empty spaces
        boardFull = true;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (game[r][c] == 0) {
                    boardFull = false; // Found an empty space
                }
            }
        }

        // If board is full and nobody has won → Draw
        if (boardFull) {
            // Ensure no one has already won
            if (!(playerObject2.textFieldGetter().equals("X Wins!")) &&
                !(playerObject2.textFieldGetter().equals("O Wins!"))) {
                playerObject2.textFieldSetter("DRAW!!!!!!!!!!!!!!!!");
                result = "Draw";
                JOptionPane.showMessageDialog(null, result + "!");
            }

            // Safety check in case textField is still on a "turn" message
            if (playerObject2.textFieldGetter().equals("X's turn") ||
                playerObject2.textFieldGetter().equals("O's turn")) {
                playerObject2.textFieldSetter("DRAW!!!!!!!!!!!!!!!!");
                System.out.println("Draw 2"); // Debug message
            }
        }
    }

    /**
     * Links this Checker to the same Player object used by the Runner class.
     * @param p the Player instance from Runner
     */
    public void setPlayerObject(Player p) {
        playerObject2 = p;
    }
}
