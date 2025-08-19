public class PotatoBot2445 {
	private static int[][] board;
	private static int approachnum;  // tracks which method or approach the bot uses (not used or not required)
	private static int[][] testingBoard = new int[3][3];
	/**
	 * Makes the best move
	 * @param b
	 */
	public static void makeBotMove(int[][] b) {
		board = b;
		int numX = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 2) {
					numX++;
				}
			}
		}
		if(numX == 0 || numX == 1) {
			PotatoBot2445.firstmove();
		}
		
		/* this if statement checks if it is bot's turn*/
		if(Player.currentturn().equals("O's turn")) {
			if (!hasWinningMove()) {
				if(!hasForcedMove()) {
					if(!predictX()) {
						/* checks if corners are free, then it places bot move */
						if(board[2][0] != 1 && board[2][0] != 2) { // checks left bottom corner
							Player.makeMove(2, 0, Player.getButton(2, 0));
							return; // the return is to stop the method from doing other moves
						} else if((board[0][0] != 1) && (board[0][0] != 2)){
							Player.makeMove(0, 0, Player.getButton(0, 0));
							return; // the return is to stop the method from doing other moves
						}
						if (board[2][2] != 1 && board[2][2] != 2) { // check right bottom corner 	
							Player.makeMove(2, 2, Player.getButton(2, 2));
							return;
						}
						if(board[1][0] != 2 && board[1][0] != 1 && board[0][0] != 1 && board[0][0] != 2 && board[2][0] != 2 ) { // places bot move in left top corner, if the left middle , left bottom and left top corner is free
							Player.makeMove(0, 0, Player.getButton(0, 0));
							return;
						}
						if (board[1][2] != 2 && board[1][2] != 1 && board[0][2] != 1 && board[0][2] != 2  && board[2][2] != 1) { // places bot move in right top corner, if the right middle, right bottom and right top corner is free
							Player.makeMove(0,2, Player.getButton(0, 2));
							return;
						}
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
	 *
	 * @return true if there is a forced and does the move
	 */
	public static boolean hasForcedMove() {
		if(PotatoBot2445.checkRowsForForcedMove()) {
			return true;
		}
		if(PotatoBot2445.checkColumnsForForcedMove()) {
			return true;
		}
		if(PotatoBot2445.checkDiagonalsForForcedMove()) {
			return true;
		}
		return false;
	}
	
	/**
	 * If it can win in one move it does and returns true
	 * @return
	 */
	public static boolean hasWinningMove() {
		if(PotatoBot2445.checkRowsForWinMove()) {
			return true;
		}
		if(PotatoBot2445.checkColumnsForWinMove()) {
			return true;
		}
		if(PotatoBot2445.checkDiagonalsForWinMove()) {
			return true;
		}
		return false;
	}
	
	/**
	 *
	 * @return true if it found a one move win
	 */
	public static boolean checkRowsForWinMove() {
		int numO = 0; // Number of O's in a row
		for (int i = 0; i < 3; i++) {
			for (int s : board[i]) {
				if (s == 1) {
					numO++;
				}
			}
			if(numO == 2) {
				for (int p = 0; p < 3; p++) {
					if (board[i][p] != 1 && board[i][p] != 2) {
						Player.makeMove(i,p,Player.getButton(i,p));
						return true;
					}	
				}
			}	
			numO = 0;
}
		return false;
	}
	
	/**
	 *
	 * @return true if it found a one move win
	 */
	public static boolean checkColumnsForWinMove() {
		int numO = 0; // Number of O's in a column
		for (int i = 0; i < 3; i++) {
			for (int j= 0; j <3; j++) {
				if (board[j][i] == 1) {
					numO++;
				}
			}
			if(numO == 2) {
				for (int p = 0; p < 3; p++) {
					if (board[p][i] != 1 && board[p][i] != 2) {
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
	 *
	 * @return true if it found a one move win
	 */
	public static boolean checkDiagonalsForWinMove() {
		int numO = 0; // Number of O's in a column
		if(board[0][0] == 1) {
			numO++;
		}
		if(board[1][1] == 1) {
			numO++;
		}
		if(board[2][2] == 1) {
			numO++;
		}
		
		if(numO == 2) {
			if(board[0][0] != 2 && board[0][0] != 1) {
				Player.makeMove(0,0,Player.getButton(0,0));
				return true;
			}
			if(board[1][1] != 2 && board[1][1] != 1) {
				Player.makeMove(1,1,Player.getButton(1,1));
				return true;
			}
			if(board[2][2] != 2 && board[2][2] != 1) {
				Player.makeMove(2,2,Player.getButton(2,2));
				return true;
			}
		}
		
		numO = 0;
		if(board[0][2] == 1) {
			numO++;
		}
		if(board[1][1] == 1) {
			numO++;
		}
		if(board[2][0] == 1) {
			numO++;
		}
		if(numO == 2) {
			if(board[0][2] != 2 && board[0][2] != 1) {
				Player.makeMove(0,2,Player.getButton(0,2));
				return true;
			}
			if(board[1][1] != 2 && board[1][1] != 1) {
				Player.makeMove(1,1,Player.getButton(1,1));
				return true;
			}
			if(board[2][0] != 2 && board[2][0] != 1) {
				Player.makeMove(2,0,Player.getButton(2,0));
				return true;
			}
		
	}
		return false;
}
	/**
	 *
	 * @return true if it found a one forced win
	 */
	public static boolean checkRowsForForcedMove() {
		int numX = 0; // Number of X's in a row
		for (int i = 0; i < 3; i++) {
			for (int s : board[i]) {
				if (s == 2) {
					numX++;
				}
			}
			if(numX == 2) {
				for (int p = 0; p < 3; p++) {
					if (board[i][p] != 2 && board[i][p] != 1) {
						Player.makeMove(i,p,Player.getButton(i,p)); //makes the forced move
						return true;
					}	
				}
			}
			numX =0;
}
		return false;
	}
	
	/**
	 *
	 * @return true if it found a one forced win
	 */
	public static boolean checkColumnsForForcedMove() {
		int numX = 0; // Number of X's in a column
		for (int i = 0; i < 3; i++) {
			for (int j= 0; j <3; j++) {
				if (board[j][i] == 2) {
					numX++;
				}
			}
			if(numX == 2) {
				for (int p = 0; p < 3; p++) {
					if (board[p][i] != 2 && board[p][i] != 1) {
						Player.makeMove(p,i,Player.getButton(p,i));
						return true;
					}	
				}
			}
			numX =0;
}
		return false;
	}
	
	/**
	 *
	 * @return true if it found a one forced win
	 */
	public static boolean checkDiagonalsForForcedMove() {
		int numX = 0; // Number of X's in a column
		if(board[0][0] == 2) {
			numX++;
		}
		if(board[1][1] == 2) {
			numX++;
		}
		if(board[2][2] == 2) {
			numX++;
		}
		
		if(numX == 2) {
			if(board[0][0] != 2 && board[0][0] != 1) {
				Player.makeMove(0,0,Player.getButton(0,0));
				return true;
			}
			if(board[1][1] != 2 && board[1][1] != 1) {
				Player.makeMove(1,1,Player.getButton(1,1));
				return true;
			}
			if(board[2][2] != 2 && board[2][2] != 1) {
				Player.makeMove(2,2,Player.getButton(2,2));
				return true;
			}
		}
		
		numX = 0;
		if(board[0][2] == 2) {
			numX++;
		}
		if(board[1][1] == 2) {
			numX++;
		}
		if(board[2][0] == 2) {
			numX++;
		}
		if(numX == 2) {
			if(board[0][2] != 2 && board[0][2] != 1) {
				Player.makeMove(0,2,Player.getButton(0,2));
				return true;
			}
			if(board[1][1] != 2 && board[1][1] != 1) {
				Player.makeMove(1,1,Player.getButton(1,1));
				return true;
			}
			if(board[2][0] != 2 && board[2][0] != 1) {
				Player.makeMove(2,0,Player.getButton(2,0));
				return true;
			}
		
	}
		return false;
}
	/**
	 * Prevents X from setting up two way wins
	 */
	public static boolean predictX() {
		if(board[2][0] == 2 && board[0][2] == 2 && board[1][1] == 1) {
			Player.makeMove(0, 1, Player.getButton(0, 1));
			return true;
		}
		/* fill testingBoard */
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				testingBoard[r][c] = board[r][c];
			}
		}
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(board[r][c] == 0) {
					testingBoard[r][c] = 2;
					if (PotatoBot2445.hasTwoWay(testingBoard)) {
						Player.makeMove(r, c, Player.getButton(r, c));
						return true;
					}
					testingBoard[r][c] = 0;
				}
			}
		}
		return false;
	}
	
	/**
	 *
	 * @param i the board
	 * @return true if the board has a two way win for X
	 */
	public static boolean hasTwoWay(int[][] testingBoard) {
		/* check every row and column if there is 2 X's with no O's, return true*/
		int winningLines = 0; // Number of lines with two way wins
		int numX = 0; // Number of X's per current line
		boolean hasO = false; // Does the current line contain an O
		
		/* Check each row */
		for (int row = 0; row < 3; row++) {
			numX = 0;
			hasO = false;
			for(int col = 0; col < 3; col++) {
				if (testingBoard[row][col] == 1) {
					hasO = true;
				} else if (testingBoard[row][col] == 2) {
					numX++;
				}
			}
			if ((numX == 2) && !hasO) {
				winningLines++;
			}
		}
		
		/* Check each column */
		for (int col = 0; col < 3; col++) {
			numX = 0;
			hasO = false;
			for(int row = 0; row < 3; row++) {
				if (testingBoard[row][col] == 1) {
					hasO = true;
				} else if (testingBoard[row][col] == 2) {
					numX++;
				}
			}
			if ((numX == 2) && !hasO) {
				winningLines++;
			}
		}
		
		/* Left to Right diagonal */
		numX = 0;
		hasO = false;
		for(int cord = 0; cord < 3; cord++) {
			if (testingBoard[cord][cord] == 0) {
				hasO = true;
			} else if (testingBoard[cord][cord] == 2) {
				numX++;
			}
		}
		if ((numX == 2) && !hasO) {
			winningLines++;
		}
		
		/* Right the Left diagonal */
		numX = 0;
		hasO = false;
		for(int cord = 0; cord < 3; cord++) {
			if (testingBoard[cord][2 - cord] == 0) {
				hasO = true;
			} else if (testingBoard[cord][2 - cord] == 2) {
				numX++;
			}
		}
		if ((numX == 2) && !hasO) {
			winningLines++;
		}
		
		/* Return true if it has a two way win */
		if (winningLines >= 2) {
			return true;
		} else {
			return false;
		}
	}
	public static void firstmove() {
		/*Middle*/
		if(board[1][1] == 2) {
			Player.makeMove(2,0,Player.getButton(2,0));
			return;
		}
		
		/* Edges */
		if(board[0][1] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		if(board[1][0] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		if(board[2][1] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		if(board[1][2] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		
		/* Corners */
		if(board[0][0] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		if(board[0][2] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		if(board[2][0] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		if(board[2][2] == 2) {
			Player.makeMove(1,1,Player.getButton(1,1));
			return;
		}
		
	}
}


