// This class runs a separate thread that continuously checks the game state 
// and makes a move for the AI (PotatoBot2445) when it's O's turn.
public class threadstheclass implements Runnable {

    // Reference to the main game thread from Runner
    private static Thread thread = Runner.getThread();

    // The run() method is executed when the thread is started.
    // It loops indefinitely, checking if it's the AI's turn.
    public void run() {
        while (true) {
            try {
                // Sleep for 300ms to avoid excessive CPU usage
                Thread.sleep(300);
            } catch (Exception e) {
                // Exception is ignored to prevent crashing if sleep is interrupted
            }

            // Check if it's O's turn in the game
            if ((Player.currentturn()).equals("O's turn")) {
                // Call the AI to make its move on the current game board
                PotatoBot2445.makeBotMove(Player.getBoard());
            }
        }
    }
}
