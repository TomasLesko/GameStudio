package sk.tsystems.gamestudio.game.tictactoe.ui;

import java.util.Scanner;

import sk.tsystems.gamestudio.game.tictactoe.core.Field;
import sk.tsystems.gamestudio.game.tictactoe.core.GameState;
import sk.tsystems.gamestudio.game.tictactoe.core.Tile;
import sk.tsystems.gamestudio.game.tictactoe.core.TileState;
import sk.tsystems.gamestudio.game.tictactoe.exception.TicTacToeException;



public class Console {
	
	private final String INPUT_PATTERN = "[XO][A-J][1-9]";

	private Field field;
	
	private Scanner scanner = new Scanner(System.in);

	public Console(Field field) {
		this.field = field;
		startGame();
	}

	private void startGame() {

		displayField(field);	
		
		displayHint();
		
		while(true) {
			
			playerStep(TileState.O);
			
			if (!field.getGameState().equals(GameState.PLAYING)) {
				break;
			}

			playerStep(TileState.X);
			
			if (!field.getGameState().equals(GameState.PLAYING)) {
				break;
			}
		}

		if (field.getGameState().equals(GameState.O_PLAYER_WON)) {
			System.out.println("Vyhral player O. ");
			
		} else if (field.getGameState().equals(GameState.X_PLAYER_WON)) {
			System.out.println("Vyhral player X. ");
		}
	}
	
	private void playerStep(TileState tileState) {
					
		String input = "";
		
		while(true)
		{
			System.out.println("Zadaj vstup(" + tileState.toString() + " + suradnice): ");
			input = scanner.nextLine();
			
			try {
				processInput(field, input, tileState);
				break;
			} catch (TicTacToeException e) {
				System.out.println("Zly vstup:. ");
			}
		}
		
		displayField(field);

	}
	
	
	private void processInput(Field field, String input, TileState tileState) throws TicTacToeException {

		if (!input.startsWith("Q") && !input.matches(INPUT_PATTERN)) {
			throw new TicTacToeException("Wrong input. ");
		}
		
		if ("Q".equals(input)) {
			System.out.println("Koncis? Ok, cau. ");
			System.exit(0);
		}
		if (input.matches("[XO][A-I][1-9]")) {
			int row = input.charAt(1) - 'A';
			int column = input.charAt(2) - '1';
			if (input.charAt(0) == 'O')
				field.markO(row, column);
			else
				field.markX(row, column);
		} else {
			System.out.println("Invalid input!");
		}
	}

	private void displayHint() {

		System.out.println("");
		System.out.println("Pre oznacenie policka je vstup M a suradnice (napr. MA1)");
		System.out.println("");
		
	}

	private void displayField(Field field) {

		System.out.println(" ");
		
		for (int column = 0; column < field.getColumnCount(); column++) {
			System.out.print(" ");
			System.out.print(column + 1);
		}
		
		System.out.println();

		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.print((char)(row + 'A')); // 
			
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				System.out.print(" ");
				
				if(tile.isEmpty())
					System.out.print("-");
				else if (tile.isO())
					System.out.print("O");
				else 
					System.out.print("X");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
