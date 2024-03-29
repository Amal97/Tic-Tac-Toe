import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Quick, Simple and Naive implementation of Tic Tac Toe

// Assumptions made:
// 		- Player will not input negative numbers
//		- The input format will be x,y (that is there is no spaces)
//			- where x and y are less than 10 (this is because of how the x and y are read)

// Since its a simple 3x3 board:
//		- all the functions are done in 1 class
//		- the win and draw cases are hard coded

public class Game {
	// game board
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;

	private static String board[][] = new String [ROWS][COLUMNS];

	// initialises an empty board
	private static void initiliseBoard() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				board[i][j] = ".";
			}
		}
	}

	// prints the board
	private static void boardPrinter() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}

	// checks if the players move is valid
	private static boolean processPlayersMove(int x, int y, int player) {
		boolean nextMove = false;
		// checks if the board space is empty
		if(board[x][y].equals(".")) {
			if(player == 1) {
				board[x][y] = "X";
			}
			else {
				board[x][y] = "O";
			}
			System.out.print("Move accepted,");
			nextMove = true;
		}
		else {
			System.out.println("Oh no, a piece is already at this place! Try again... \n");
		}
		return nextMove;
	}

	// changes player
	private static int changePlayer(int player) {
		if(player == 1) {
			player = 2;
		}
		else {
			player = 1;
		}
		return player;
	}

	// checks if all fields in a row are taken by the player.
	private static boolean checkHorizontal() {
		for(int i = 0; i < ROWS; i++) {
			if((board[i][0] == "X") && (board[i][1] == "X") && (board[i][2] == "X")) {
				return true;
			}
			else if((board[i][0] == "O") && (board[i][1] == "O") && (board[i][2] == "O")) {
				return true;
			}
		}
		return false;
	}

	// checks if all fields in a column are taken by the player.
	private static boolean checkVertical() {
		for(int i = 0; i < COLUMNS; i++) {
			if((board[0][i] == "X") && (board[1][i] == "X") && (board[2][i] == "X")) {
				return true;
			}
			else if((board[0][i] == "O") && (board[1][i] == "O") && (board[2][i] == "O")) {
				return true;
			}
		}
		return false;
	}

	// checks if all fields in a diagonal are taken by the player.
	private static boolean checkDiagonal() {
		if((board[0][0] =="X") && (board[1][1] =="X") && (board[2][2] =="X")){
			return true;
		}
		else if((board[0][2] =="X") && (board[1][1] =="X") && (board[2][0] =="X")){
			return true;
		}

		if((board[0][0] =="O") && (board[1][1] =="O") && (board[2][2] =="O")){
			return true;
		}
		else if((board[0][2] =="O") && (board[1][1] =="O") && (board[2][0] =="O")){
			return true;
		}

		return false;
	}

	// checks if there is an empty space available in the board
	private static boolean checkDraw() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				if(board[i][j] == ".") {
					return false;
				}
			}
		}
		return true;
	}

	// checks all conditions of winning
	private static boolean checkWinner() {
		if(checkHorizontal() || checkVertical() || checkDiagonal()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// initilise all variables
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String userInput = "";
		int x = -1;
		int y = -1;
		int player = 1;
		String piece = "X";
		boolean playing = true;
		boolean nextMove = false;
		boolean valid = true;

		initiliseBoard();
		System.out.println("Welcome to Tic Tac Toe! \n");
		System.out.println("Here's the current board: \n");
		boardPrinter();

		while(playing) {
			try {
				// prints the board and changes player if the move is valid
				if(nextMove) {
					boardPrinter();
					player = changePlayer(player);
					if(player == 1) {
						piece = "X";
					}
					else {
						piece = "O";
					}
				}

				// get user input and print to console
				System.out.print("Player "+player+" enter a coord x,y to place your "+piece+" or enter 'q' to give up: ");
				userInput = inFromUser.readLine();
				System.out.print("\n");

				// quit the game
				if(userInput.equalsIgnoreCase("q")) {
					playing = false;
				}
				else {
					// match the matrix with user's input by -1
					x = (Integer.parseInt(userInput.substring(0,1))) - 1;	// row number
					y = (Integer.parseInt(userInput.substring(2,3))) - 1;	// column number
					if(x<=-1 || y<=-1) {
						System.out.println("Invalid Move");
						valid = false;;
					}
					else {
						valid = true;
					}
				}
			}
			catch (Exception e) {
				System.out.println("Invalid Move");
				valid = false;;
			}

			// valid moves and user didnt quit the game
			if(playing && valid) {
				nextMove = processPlayersMove(x,y,player);
				// check winner
				if(checkWinner()) {
					System.out.println(" well done you've won the game! \n");
					boardPrinter();
					playing = false;

				}
				// check draw
				else if(checkDraw()) {
					System.out.println(" the game is a draw \n");
					boardPrinter();
					playing = false;
				}
				// continue if valid move
				else if(nextMove) {
					System.out.println(" here's the current board: \n");
				}
			}
			// when user quits the game
			else if(!playing) {
				System.out.println("Player "+player+" quit the game!");

			}
		}
	}	
}
