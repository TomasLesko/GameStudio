package sk.tsystems.gamestudio.game.tictactoe;

import sk.tsystems.gamestudio.game.tictactoe.core.Field;
import sk.tsystems.gamestudio.game.tictactoe.ui.Console;

public class TicTacToe {
	

	public static void main(String[] args){

		Field field = new Field(10, 10);
		
		Console console = new Console(field);
		
	}
}
