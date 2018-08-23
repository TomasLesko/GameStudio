package sk.tsystems.gamestudio.game.tictactoe.core;

public class Tile {
	
	private TileState state = TileState.EMPTY;
	
	public TileState getState() {
		return state;
	}
	
	public boolean isEmpty() {
		return TileState.EMPTY.equals(state);
	}
	
	public boolean isO() {
		return TileState.O.equals(state);
	}
	
	public boolean isX() {
		return TileState.X.equals(state);
	}
	
	void setState(TileState state) {
		this.state = state;
	}
}
