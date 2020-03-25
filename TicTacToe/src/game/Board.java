package game;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Borna Sadeghi
 * @version Jul 29, 2019
 */
public class Board {
	private static int NUM_SQUARES = 9;
	private int[] grid = new int[NUM_SQUARES];
	private boolean xTurn = true;
	
	public Board() {
	}
	
	public Board (String layout) {
		for (int i=0; i<NUM_SQUARES; i++) {
			switch (layout.charAt(i)) {
			case 'x':
				this.grid[i] = 1;
				break;
			case 'o':
				this.grid[i] = -1;
				break;
			}
		}
	}
	
	public Board (Board boardToCopy) {
		for (int i=0; i<NUM_SQUARES; i++) {
			this.grid[i] = boardToCopy.grid[i];
		}
		this.xTurn = boardToCopy.xTurn;
	}
	

	public Board makeMove (int square) {
		Board updatedBoard = new Board (this);
		updatedBoard.grid[square] = this.xTurn ? 1 : -1;
		updatedBoard.endTurn();
		return updatedBoard;
	}
	
	public Board makeMoveNumpad (int num) {
		Board updatedBoard = new Board (this);
		
		switch(num) {
		case 7:
			updatedBoard.grid[0] = this.xTurn ? 1 : -1;
			break;
		case 8:
			updatedBoard.grid[1] = this.xTurn ? 1 : -1;
			break;
		case 9:
			updatedBoard.grid[2] = this.xTurn ? 1 : -1;
			break;
		case 4:
			updatedBoard.grid[3] = this.xTurn ? 1 : -1;
			break;
		case 5:
			updatedBoard.grid[4] = this.xTurn ? 1 : -1;
			break;
		case 6:
			updatedBoard.grid[5] = this.xTurn ? 1 : -1;
			break;
		case 1:
			updatedBoard.grid[6] = this.xTurn ? 1 : -1;
			break;
		case 2:
			updatedBoard.grid[7] = this.xTurn ? 1 : -1;
			break;
		case 3:
			updatedBoard.grid[8] = this.xTurn ? 1 : -1;
			break;
		}
		
		updatedBoard.endTurn();
		return updatedBoard;
	}
	
	public boolean gameOver () {
		for (int i=0; i<3; i++) { // columns
			if (this.grid[i] == this.grid[i+3] && this.grid[i+3] == this.grid[i+6] && this.grid[i] != 0)
				return true; // winner on column
		}
		for (int i=0; i<3; i++) { // rows
			if (this.grid[i*3] == this.grid[i*3+1] && this.grid[i*3+1] == this.grid[i*3+2] && this.grid[i*3] != 0)
				return true; // winner on row
		}
		
		if (this.grid[0] == this.grid[4] && this.grid[4] == this.grid[8] && this.grid[0] != 0)
			return true;
		if (this.grid[2] == this.grid[4] && this.grid[4] == this.grid[6] && this.grid[2] != 0)
			return true;
		
		
		for (int i=0; i<NUM_SQUARES; i++) {
			if (this.grid[i] == 0) return false;
		}
		return true; // draw
	}

	public ArrayList<Integer> getLegalMoves () {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		for (int i=0; i<NUM_SQUARES; i++) {
			if (this.grid[i] == 0)
				moves.add(i);
		}
		
		return moves;
	}
	
	public boolean moveIsLegal (int square) {
		return 0 <= square && square <= 8 && this.grid[square] == 0;
	}
	
	public boolean moveIsLegalNumpad (int num) {
		return 1 <= num && num <= 9;	
	}
	
	private void endTurn () {
		this.xTurn = !this.xTurn;
	}

	public boolean squareOccupied (int square) {
		return this.grid[square] != 0;
	}
	
	public int symbolAt (int pos) {
		return this.grid[pos];
	}
	
	public boolean isXTurn () {
		return this.xTurn;
	}
	
	public String toString () {
		String out = String.format("%s to move\n", this.xTurn ? "x" : "o");
		
		for (int i=0; i<NUM_SQUARES; i++) {
			if (this.grid[i] == 1) out += "x ";
			else if (this.grid[i] == -1) out += "o ";
			else out += ". ";
			
			if ((i+1)%3 == 0) out += "\n";
		}
		
		return out;
	}
}
