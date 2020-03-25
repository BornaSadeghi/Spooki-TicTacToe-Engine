package game;

import java.util.Scanner;

import ai.BoardEvaluator;
import ai.Minimax;
import ai.Minimax_AlphaBeta;
import ai.MoveSelector;
import ai.SimpleEvaluator;

/**
 * 
 * 
 * @author Borna Sadeghi
 * @version Jul 29, 2019
 */
public class Game {
	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);
		Board board = new Board ();
		
		boolean xIsComp = false;
		boolean oIsComp = true;
		
		BoardEvaluator evaluator = new SimpleEvaluator();
		MoveSelector engine = new Minimax_AlphaBeta (9, evaluator);
		
		boolean runGame = true;
		
		int in = 0;
		while (!board.gameOver() && runGame) {
			if (board.isXTurn() && xIsComp || !board.isXTurn() && oIsComp) {
				board = board.makeMove(engine.bestMove(board));
				
				continue;
			}
			
			System.out.println(board);
			
			if (board.isXTurn() && xIsComp || !board.isXTurn() && oIsComp) {
				board = board.makeMove(engine.bestMove(board));
			} else {
				in = input.nextInt();
				if (in == -1) break;
				
				if (board.moveIsLegalNumpad(in)) {
					board = board.makeMoveNumpad (in);
				} else {
					System.out.format("Invalid move: %d\n", in);
				}
			}
		}
		
		System.out.println("Final position\n" + board);
	}

}
