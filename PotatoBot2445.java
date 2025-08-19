/**
 * PotatoBot2445 - A simple Tic-Tac-Toe bot for playing as "O".
 * 
 * The bot tries to:
 * 1. Win if it can.
 * 2. Block the opponent's winning moves.
 * 3. Predict and prevent two-way traps by X.
 * 4. Otherwise, it follows a fallback strategy (corners, edges, etc.).
 */
public class PotatoBot2445 {
    // Current game board (3x3 matrix). 
    // 0 = empty, 1 = O, 2 = X
    private static int[][] board;

    // Tracks which strategy the bot is using (currently unused).
    private static int approachnum;

    // Temporary board for simulations/predictions.
    private static int[][] testingBoard = new int[3][3];

    /**
     * Makes the bot's move based on the current board state.
     * @param b the current Tic-Tac-Toe board
     */
    public static void makeBotMove(int[][] b) {
        board = b;
        int numX = 0;

        // Count how many X's are on the board
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == 2) {
                    numX++;
                }
            }
        }

        // Handle the bot's first move strategy
        if(numX == 0 || numX == 1) {
            PotatoBot2445.firstmove();
        }

        // Bot only moves if it is O's turn
        if(Player.currentturn().equals("O's turn")) {
            // First, try to win immediately
            if (!hasWinningMove()) {
                // Otherwise, block opponent's winning moves
                if(!hasForcedMove()) {
                    // Next, try to predict opponent traps (two-way wins)
                    if(!predictX()) {
                        // Otherwise, follow a fallback placement strategy:
                        // Prefer corners, then open spaces
                        
                        // Bottom-left corner
                        if(board[2][0] == 0) { 
                            Player.makeMove(2, 0, Player.getButton(2, 0));
                            return;
                        } 
                        // Top-left corner
                        else if(board[0][0] == 0){
                            Player.makeMove(0, 0, Player.getButton(0, 0));
                            return;
                        }
                        // Bottom-right corner
                        if (board[2][2] == 0) { 	
                            Player.makeMove(2, 2, Player.getButton(2, 2));
                            return;
                        }
                        // Top-left again, but with extra checks
                        if(board[1][0] == 0 && board[0][0] == 0 && board[2][0] != 2) {
                            Player.makeMove(0, 0, Player.getButton(0, 0));
                            return;
                        }
                        // Top-right with checks
                        if(board[1][2] == 0 && board[0][2] == 0 && board[2][2] != 1) {
                            Player.makeMove(0,2, Player.getButton(0, 2));
                            return;
                        }
                        // If no special spots, take the first available empty space
                        for (int r = 0; r < 3; r++) {
                            for (int c = 0; c < 3; c++) {
                                if (board[r][c] == 0) {
                                    Player.makeMove(r,c, Player.getButton(r, c));
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Attempts to block the opponent if they are one move away from winning.
     * @return true if a blocking move was made
     */
    public static boolean hasForcedMove() {
        if(PotatoBot2445.checkRowsForForcedMove()) return true;
        if(PotatoBot2445.checkColumnsForForcedMove()) return true;
        if(PotatoBot2445.checkDiagonalsForForcedMove()) return true;
        return false;
    }

    /**
     * Attempts to win immediately if the bot has a winning move.
     * @return true if a winning move was made
     */
    public static boolean hasWinningMove() {
        if(PotatoBot2445.checkRowsForWinMove()) return true;
        if(PotatoBot2445.checkColumnsForWinMove()) return true;
        if(PotatoBot2445.checkDiagonalsForWinMove()) return true;
        return false;
    }

    // ---------- WIN CHECKS ----------

    /**
     * Checks all rows for a winning move for O.
     */
    public static boolean checkRowsForWinMove() {
        int numO = 0; 
        for (int i = 0; i < 3; i++) {
            for (int s : board[i]) {
                if (s == 1) numO++;
            }
            if(numO == 2) { // Two O's, need third to win
                for (int p = 0; p < 3; p++) {
                    if (board[i][p] == 0) {
                        Player.makeMove(i,p,Player.getButton(i,p));
                        return true;
                    }	
                }
            }	
            numO = 0; // Reset counter per row
        }
        return false;
    }

    /**
     * Checks all columns for a winning move for O.
     */
    public static boolean checkColumnsForWinMove() {
        int numO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j= 0; j <3; j++) {
                if (board[j][i] == 1) numO++;
            }
            if(numO == 2) {
                for (int p = 0; p < 3; p++) {
                    if (board[p][i] == 0) {
                        Player.makeMove(p,i,Player.getButton(p,i));
                        return true;
                    }	
                }
            }
            numO = 0;
        }
        return false;
    }

    /**
     * Checks diagonals for a winning move for O.
     */
    public static boolean checkDiagonalsForWinMove() {
        int numO = 0; 
        // Left-to-right diagonal
        if(board[0][0] == 1) numO++;
        if(board[1][1] == 1) numO++;
        if(board[2][2] == 1) numO++;
        if(numO == 2) {
            if(board[0][0] == 0) { Player.makeMove(0,0,Player.getButton(0,0)); return true; }
            if(board[1][1] == 0) { Player.makeMove(1,1,Player.getButton(1,1)); return true; }
            if(board[2][2] == 0) { Player.makeMove(2,2,Player.getButton(2,2)); return true; }
        }

        // Reset and check right-to-left diagonal
        numO = 0;
        if(board[0][2] == 1) numO++;
        if(board[1][1] == 1) numO++;
        if(board[2][0] == 1) numO++;
        if(numO == 2) {
            if(board[0][2] == 0) { Player.makeMove(0,2,Player.getButton(0,2)); return true; }
            if(board[1][1] == 0) { Player.makeMove(1,1,Player.getButton(1,1)); return true; }
            if(board[2][0] == 0) { Player.makeMove(2,0,Player.getButton(2,0)); return true; }
        }
        return false;
    }

    // ---------- BLOCKING CHECKS (FORCED MOVES) ----------

    /**
     * Checks rows to block X from winning.
     */
    public static boolean checkRowsForForcedMove() {
        int numX = 0; 
        for (int i = 0; i < 3; i++) {
            for (int s : board[i]) {
                if (s == 2) numX++;
            }
            if(numX == 2) {
                for (int p = 0; p < 3; p++) {
                    if (board[i][p] == 0) {
                        Player.makeMove(i,p,Player.getButton(i,p)); 
                        return true;
                    }	
                }
            }
            numX = 0;
        }
        return false;
    }

    // (Similar logic for columns and diagonals - omitted here for brevity,
    // but they follow the same pattern as row checks above.)

    /**
     * Predicts if X is about to create a two-way winning setup 
     * (fork) and prevents it.
     */
    public static boolean predictX() {
        // Specific case: X in opposite corners
        if(board[2][0] == 2 && board[0][2] == 2 && board[1][1] == 1) {
            Player.makeMove(0, 1, Player.getButton(0, 1));
            return true;
        }

        // Copy board into testingBoard for simulation
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                testingBoard[r][c] = board[r][c];
            }
        }

        // Simulate X placing in each empty spot
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                if(board[r][c] == 0) {
                    testingBoard[r][c] = 2;
                    if (PotatoBot2445.hasTwoWay(testingBoard)) {
                        Player.makeMove(r, c, Player.getButton(r, c));
                        return true;
                    }
                    testingBoard[r][c] = 0; // Reset
                }
            }
        }
        return false;
    }

    /**
     * Determines if X has a two-way win opportunity 
     * (multiple simultaneous win paths).
     * @param testingBoard board to check
     */
    public static boolean hasTwoWay(int[][] testingBoard) {
        int winningLines = 0; 
        int numX = 0; 
        boolean hasO = false; 
        
        // Check rows
        for (int row = 0; row < 3; row++) {
            numX = 0; hasO = false;
            for(int col = 0; col < 3; col++) {
                if (testingBoard[row][col] == 1) hasO = true;
                else if (testingBoard[row][col] == 2) numX++;
            }
            if ((numX == 2) && !hasO) winningLines++;
        }
        
        // (Checks for columns and diagonals follow same logic...)

        return winningLines >= 2;
    }

    /**
     * Bot's strategy for its very first move (opening).
     */
    public static void firstmove() {
        // If X starts in the middle, take bottom-left corner
        if(board[1][1] == 2) {
            Player.makeMove(2,0,Player.getButton(2,0));
            return;
        }
        
        // If X starts on an edge, take center
        if(board[0][1] == 2 || board[1][0] == 2 || board[2][1] == 2 || board[1][2] == 2) {
            Player.makeMove(1,1,Player.getButton(1,1));
            return;
        }

        // If X starts in a corner, take center
        if(board[0][0] == 2 || board[0][2] == 2 || board[2][0] == 2 || board[2][2] == 2) {
            Player.makeMove(1,1,Player.getButton(1,1));
            return;
        }
    }
}
