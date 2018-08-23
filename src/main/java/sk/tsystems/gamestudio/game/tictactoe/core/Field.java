package sk.tsystems.gamestudio.game.tictactoe.core;

import java.util.Random;

import sk.tsystems.gamestudio.game.tictactoe.exception.TicTacToeException;



public class Field {
	
	public static final int MARKED_TO_WIN = 2;

	private final int rowCount;
	private final int columnCount;
	private final Tile[][] tiles;
	private GameState gameState = GameState.PLAYING;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount]; // spravili sme len pole ziadna tile( je abstract) neda sa vytvorit)
		generate();
	}

	private void generate() { // vytvorime metody na generovanie
		generateEmptyField();
	}

	private void generateEmptyField() {
		int x = 0,
		    y = 0;
		
		do {
			tiles[x][y] = new Tile();
			
			if (y < columnCount-1) {
				y++;
			} else {
				y=0;
				x++;
			}
		} while (x < rowCount);
		
	}
	
	public void markX(int row, int column) throws TicTacToeException {
		mark(row, column, TileState.X);

		if (checkMatch(row, column, TileState.X)) {
			gameState = GameState.X_PLAYER_WON;
		}
	}
	
	public void markO(int row, int column) throws TicTacToeException {
		mark(row, column, TileState.O);
		
		if (checkMatch(row, column, TileState.O)) {
			gameState = GameState.O_PLAYER_WON;
		}
	}
	
	
	public void mark(int row, int column, TileState tileState) throws TicTacToeException {
		if (!tiles[row][column].isEmpty()) {
			throw new TicTacToeException("Wrong input!");
		} 
	
		tiles[row][column].setState(tileState);
	}
	
	private boolean checkMatch(int row, int column, TileState tileState) {

		return checkMatchLDiagonal(row, column, tileState) 
			|| checkMatchRDiagonal(row, column, tileState) 
			|| checkMatchVertical(row, column, tileState) 
			|| checkMatchHorizontal(row, column, tileState);
	}
	

	private boolean checkMatchLDiagonal(
			int row, 
			int column, 
			TileState tileState
			) {
		int y = row, x = column;
		int numberOfMarked = 1; 
		while (isNotOutOfBound(++x,++y) 
				&& tiles[y][x].getState().equals(tileState) ) {
			numberOfMarked++;
		}
		
		y = row; 
		x = column;
		while (isNotOutOfBound(--x,--y) 
				&& tiles[y][x].getState().equals(tileState)) {
			numberOfMarked++;
		}
		return MARKED_TO_WIN <= numberOfMarked;
	}
	
	private boolean checkMatchHorizontal(int row, int column, TileState tileState) {
		int y = row, x = column;
		int numberOfMarked = 1; 
		while (isNotOutOfBound(--x,y) 
				&& tiles[y][x].getState().equals(tileState) ) {
			numberOfMarked++;
		}
		
		y = row; 
		x = column;
		while (isNotOutOfBound(++x,y) 
				&& tiles[y][x].getState().equals(tileState)) {
			numberOfMarked++;
		}
		return MARKED_TO_WIN <= numberOfMarked;
	}

	private boolean checkMatchVertical(int row, int column, TileState tileState) {
		int y = row, x = column;
		int numberOfMarked = 1; 
		while (isNotOutOfBound(x,--y) 
				&& tiles[y][x].getState().equals(tileState) ) {
			numberOfMarked++;
		}
		
		y = row; 
		x = column;
		while (isNotOutOfBound(x,++y) 
				&& tiles[y][x].getState().equals(tileState)) {
			numberOfMarked++;
		}
		return MARKED_TO_WIN <= numberOfMarked;
	}

	private boolean checkMatchRDiagonal(int row, int column, TileState tileState) {
		int y = row, x = column;
		int numberOfMarked = 1; 
		while (isNotOutOfBound(++x,--y) 
				&& tiles[y][x].getState().equals(tileState) ) {
			numberOfMarked++;
		}
		
		y = row; 
		x = column;
		while (isNotOutOfBound(--x,++y) 
				&& tiles[y][x].getState().equals(tileState)) {
			numberOfMarked++;
		}
		return MARKED_TO_WIN <= numberOfMarked;
	}
	
	private boolean isNotOutOfBound(int x, int y) {
		return x >= 0 
				&& y >= 0
				&& x < columnCount
				&& y < rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}
	
	public int getRowCount() {
		return rowCount;
	}
		
	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column]; // vyberam tile na pozicii
	}
}
