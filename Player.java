import java.awt.*;    
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {
	/* Game board (3x3), 0 = empty, 1 = O, 2 = X */
	private static int [][] board;

	/* Randomly decides who starts (1 = O, 2 = X) */
	private static int random;

	/* Main game window */
	private JFrame frame;

	/* Text field that displays whose turn it is, or win/draw messages */
	private static JTextField player;	

	/* Icons for X, O, and empty spaces */
	private static Icon empty = new ImageIcon("Empty.png");
	private static Icon o = new ImageIcon("O.png");
	private static Icon x = new ImageIcon("x.png");

	/* 9 buttons for the Tic Tac Toe grid */
	private static JButton button1;
	private static JButton button2;
	private static JButton button3;
	private static JButton button4;
	private static JButton button5;
	private static JButton button6;
	private static JButton button7;
	private static JButton button8;
	private static JButton button9;
	
	/* Constructor - initializes board and decides who goes first */
	public Player() {
		/* Randomly picks X or O to start */
		random = (int)((Math.random() * 2)+ 1);

		/* Creates the text field that shows whose turn */
		player = new JTextField(Player.turn());	

		/* Initializes empty 3x3 game board */
		board = new int[3][3];
	}

	/**
	 * Creates the GUI with 9 grid buttons + reset + status field
	 */
	public void GUI() {	
		/* Create main frame */
		frame = new JFrame("Tic-Tac-Toe");

		/* Each button corresponds to one square on the grid */
		button1 = new JButton(empty);
		button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(0,0,button1); // top-left
            }
        });

		button2 = new JButton(empty);
		button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(0,1,button2); // top-middle
            }
        });

		button3 = new JButton(empty);
		button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(0,2,button3); // top-right
            }
        });

		button4 = new JButton(empty);
		button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(1,0,button4); // middle-left
            }
        });

		button5 = new JButton(empty);
		button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(1,1,button5); // center
            } 
        }); 
            
		button6 = new JButton(empty);
		button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(1,2,button6); // middle-right
            }
        });
		
		button7 = new JButton(empty);
		button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(2,0,button7); // bottom-left
            }
        });

		button8 = new JButton(empty);
		button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(2,1,button8); // bottom-middle
            }
        });

		button9 = new JButton(empty);
		button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeMove(2,2,button9); // bottom-right
            }
        });
		
		/* Reset button */
		JButton button10 = new JButton("Reset");
		button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	/* Calls Reset helper class */
            	Reset.clearBoard();
            	Reset.clearIcons();

            	/* Randomly pick who goes first after reset */
            	random = (int)(Math.random()*2)+1;
        		player.setText(turn());	
            }
        });
	
		/* Extra filler for bottom-right cell of grid */
		JLabel button12 = new JLabel();

		/* Add all components to frame */
		frame.add(button1); frame.add(button2); frame.add(button3);  
		frame.add(button4); frame.add(button5); frame.add(button6);  
		frame.add(button7); frame.add(button8); frame.add(button9);  
		frame.add(button10); frame.add(player); frame.add(button12);
		 
		/* Frame settings */
		frame.setSize(500,600);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(4,3)); // 3x3 board + controls
		frame.setVisible(true);

		/* Text field settings */
		player.setBounds(100,350,100,100);
		player.setEnabled(false); // disable typing
	}

	/**
	 * Randomly sets who goes first (X or O)
	 * @return "X's turn" or "O's turn"
	 */
	public static String turn() {
		if(random == 1) {
			return "O's turn";
		} else {
			return "X's turn";
		}
	}

	/** Sets text in the player text field */
	public void textFieldSetter(String s) {
		this.player.setText(s);
	}

	/** Gets text from the player text field */
	public String textFieldGetter() {
		return player.getText();
	}

	/** Player object toString - just returns the text field contents */
	public String toString(){
		return player.getText();
	}

	/** Replace current board with another */
	public void setBoard(int[][] i){
		this.board = i;
	}

	/** Updates a button's icon given its number (1-9) */
	public static void setButtonIcon(int buttonNumber, Icon i) {
		if (buttonNumber == 1) button1.setIcon(i);
		if (buttonNumber == 2) button2.setIcon(i);
		if (buttonNumber == 3) button3.setIcon(i);
		if (buttonNumber == 4) button4.setIcon(i);
		if (buttonNumber == 5) button5.setIcon(i);
		if (buttonNumber == 6) button6.setIcon(i);
		if (buttonNumber == 7) button7.setIcon(i);
		if (buttonNumber == 8) button8.setIcon(i);
		if (buttonNumber == 9) button9.setIcon(i);
	}

	/**
	 * Called when a player clicks a square
	 * @param x2 board row
	 * @param y board column
	 * @param activeButton which button was clicked
	 */
	public static void makeMove(int x2, int y, JButton activeButton) {
		/* Only allow moves on empty spaces */
		if(board[x2][y] != 1 && board[x2][y] != 2) {
        	if(player.getText().equals("O's turn")){
        		activeButton.setIcon(o);
        		player.setText("X's turn");
        		board[x2][y]=1; // mark O
        	} else if (player.getText().equals("X's turn")){
        		activeButton.setIcon(x);
        		player.setText("O's turn");
        		board[x2][y]=2; // mark X
        	}
        }
        /* After every move, check if game ended */
        Checker.checkWin(board);
	}
	
	/** Returns button at given row,col */
	public static JButton getButton(int potato, int i) {
		if (potato == 0) {
			if(i == 0) return button1;
			if(i == 1) return button2;
			if(i == 2) return button3;
		} 
		if (potato == 1) {
			if(i == 0) return button4;
			if(i == 1) return button5;
			if(i == 2) return button6;
		} 
		if (potato == 2) {
			if(i == 0) return button7;
			if(i == 1) return button8;
			if(i == 2) return button9;
		} 
		return null;
	}
	
	/** Returns board state */
	public static int[][] getBoard() {
		return board;
	}

	/** Returns current text (turn/win/draw) */
	public static String currentturn() {
		return player.getText();
	}
	
	/** Updates text in player status box */
	public static void setPlayerText(String i) {
		player.setText(i);
	}
}
