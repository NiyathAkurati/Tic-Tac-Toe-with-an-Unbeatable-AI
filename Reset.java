import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * The Reset class is responsible for resetting both
 * the game board (data) and the GUI (icons) to their
 * initial state.
 */
public class Reset {
    
    // Represents the 3x3 game board (0 = empty, 1/2 = players, etc.)
    private static int[][] board = new int[3][3];
    
    // Reference to the player object (handles board and UI interactions)
    private static Player player;
    
    // Icon used for an empty button in the UI
    private static Icon empty = new ImageIcon("Empty.png");

    /**
     * Constructor that links Reset with the Player instance.
     * @param p The Player object managing board and button states
     */
    public Reset(Player p) {
        player = p;
    }

    /**
     * Resets the logical game board (3x3 int array) to all zeros
     * and updates the player's board reference.
     */
    public static void clearBoard() {
        board = new int [3][3]; // reinitialize as empty
        player.setBoard(board); // update player with new board
    }

    /**
     * Resets all button icons on the board to the empty state.
     * Iterates over button positions 1 through 9.
     */
    public static void clearIcons() {
        for(int i = 1; i <= 9; i++) {
            player.setButtonIcon(i, empty); // set each button to empty icon
        }
    }
}
