package ai;

import game.Board;

/**
 * 
 * 
 * @author Borna Sadeghi
 * @version Jul 30, 2019
 */
public class Minimax_AlphaBeta implements MoveSelector{

	private BoardEvaluator evaluator;
	private int searchDepth;
	
	public Minimax_AlphaBeta (int searchDepth, BoardEvaluator evaluator) {
		this.evaluator = evaluator;
		this.searchDepth = searchDepth;
	}
	
	/**
	 * 
	 */
	@Override
	public int bestMove (Board board) {
		int best = -1;
		
		if (board.isXTurn()) {
			int highest = Integer.MIN_VALUE;
			
			for (int move : board.getLegalMoves()) {
				Board childBoard = board.makeMove(move);
				int value = this.minimax(childBoard, this.searchDepth-1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
				if (value > highest) {
					highest = value;
					best = move;
				}
			}			
		} else {
			int lowest = Integer.MAX_VALUE;
			
			for (int move : board.getLegalMoves()) {
				Board childBoard = board.makeMove(move);
				int value = this.minimax(childBoard, this.searchDepth-1, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
				if (value < lowest) {
					lowest = value;
					best = move;
				}
			}
		}
		
		return best;
	}

	private int minimax (Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
		if (depth == 0 || board.gameOver())
			return this.evaluator.evaluate(board);
		
		int value; // Value of this node
		
		if (maximizingPlayer) {
			value = Integer.MIN_VALUE;
			
			for (int move : board.getLegalMoves()) {
				value = Math.max(value, minimax(board.makeMove(move), depth-1, alpha, beta, false));
				alpha = Math.max(alpha, value);
				if (alpha >= beta) break;
			}
			
		} else {
			value = Integer.MAX_VALUE;
			
			for (int move : board.getLegalMoves()) {
				value = Math.min(value, minimax(board.makeMove(move), depth-1, alpha, beta, true));
				beta = Math.min(beta, value);
				if (beta <= alpha) break;
			}
		}
		
		return value;
	}
}
