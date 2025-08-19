public class Runner {
   // Static reference to a Thread object that will be used for running background tasks
   private static Thread thread;

   public static void main(String[] args) {
      // Creates an instance of PotatoBot2445 (likely the AI/bot logic for the game)
      PotatoBot2445 Sonny = new PotatoBot2445();

      // Creates the player object, probably holds game state or player info
      Player playerObject = new Player();

      // Checker object created with a reference to the player (probably handles move validation)
      Checker checkerObject = new Checker(playerObject);

      // Reset object to handle restarting/resetting the player/game
      Reset reseterObject = new Reset(playerObject);

      // Creates an instance of a Runnable (threadstheclass) to run in parallel
      threadstheclass obj = new threadstheclass();

      // Initializes and starts the thread
      thread = new Thread(obj);
      thread.start();

      /* Runs GUI */
      playerObject.GUI();
   }

   // Getter method to allow access to the background thread from other classes
   public static Thread getThread() {
      return thread;
   }
}
