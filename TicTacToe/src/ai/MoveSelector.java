package ai;

import game.Board;

/**
 * 
 * 
 * @author Borna Sadeghi
 * @version Jul 30, 2019
 */
public interface MoveSelector {

	public int bestMove (Board board);
	
}
