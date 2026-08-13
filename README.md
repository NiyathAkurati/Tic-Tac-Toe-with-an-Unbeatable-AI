Tic-Tac-Toe with an Unbeatable AI
Overview

Tic-Tac-Toe with an Unbeatable AI is a Java-based desktop game built with Java Swing. The project allows a player to compete against an AI opponent through a graphical interface. The AI uses strategic decision-making to evaluate the current board and select moves designed to prevent the player from winning.

Features
Interactive Java Swing graphical interface.
AI opponent with strategic move selection.
Automatic win and draw detection.
Randomized starting player.
Reset functionality for starting a new game.
3×3 board representation using arrays.
Separate classes for game logic, GUI, AI, and reset functionality.
Background thread for handling AI moves.
Project Structure

Runner.java → Entry point of the program
Player.java → Main GUI and player interaction
PotatoBot2445.java → Contains the AI decision-making logic
Checker.java → Checks for wins, losses, and draws
Reset.java → Handles resetting the game board
threadstheclass.java → Handles the AI's background thread
X.png → X player icon
O.png → O player icon
Empty.png → Empty board-space icon
README.md → Project documentation

AI Decision-Making

The AI evaluates the current board and uses a series of strategic checks to determine its next move.

The decision-making process includes:

Checking for available winning moves.
Identifying forced moves.
Predicting possible opponent moves.
Evaluating board positions such as corners.
Selecting an available move when higher-priority strategies are unavailable.

The AI logic is primarily implemented in PotatoBot2445.java.

How to Run

Clone the repository:

git clone https://github.com/NiyathAkurati/Tic-Tac-Toe-with-an-Unbeatable-AI.git

Compile the Java files:

javac *.java

Run the program:

java Runner
Example Flow
Launch the program through Runner.java.
The Tic-Tac-Toe board appears through the Java Swing interface.
The player selects an available space.
The AI evaluates the board and selects its move.
The game continues until a player wins or the board results in a draw.
Use the reset functionality to start another game.
