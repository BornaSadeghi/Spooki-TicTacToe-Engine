package ai;

import game.Board;

/**
 * 
 * 
 * @author Borna Sadeghi
 * @version Jul 30, 2019
 */
public class SimpleEvaluator implements BoardEvaluator {

	/**
	 * 
	 */
	@Override
	public int evaluate (Board board) {
		return this.evaluateLine(board, 0, 1, 2) + 
				this.evaluateLine(board, 3, 4, 5) +
				this.evaluateLine(board, 6, 7, 8) +
				this.evaluateLine(board, 0, 3, 6) +
				this.evaluateLine(board, 1, 4, 7) +
				this.evaluateLine(board, 2, 5, 8) +
				this.evaluateLine(board, 0, 4, 8) +
				this.evaluateLine(board, 2, 4, 6);
	}

	/**
	 * 
	 * @param board
	 * @param player - 1 or -1 (x or o)
	 * @param pos1
	 * @param pos2
	 * @param pos3
	 * @return
	 */
	private int evaluateLine (Board board, int pos1, int pos2, int pos3) {
		int score = 0;

		if (board.symbolAt(pos1) == 1) // x--
			score = 1;
		else if (board.symbolAt(pos1) == -1) // o--
			score = -1;

		if (board.symbolAt(pos2) == 1) {
			if (score == 1) // xx-
				score = 10;
			else if (score == -1) // ox-
				return 0;
			else // .x-
				score = 1;
			
		} else if (board.symbolAt(pos2) == -1) {
			if (score == -1) // oo-
				score = -10;
			else if (score == 1) // xo-
				return 0;
			else // .o-
				score = -1;
		}

		// Third cell
		if (board.symbolAt(pos3) == 1) {
			if (score > 0) // xxx
				score *= 10;
			else if (score < 0) // oox
				return 0;
			else // ..x
				score = 1;
		} else if (board.symbolAt(pos3) == -1) {
			if (score < 0) // ooo
				score *= 10;
			else if (score > 1) // xxo
				return 0;
			else // //..o
				score = -1;
		}

		return score;
	}
}
