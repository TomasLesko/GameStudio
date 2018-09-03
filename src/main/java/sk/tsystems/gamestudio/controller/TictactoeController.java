package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;


import sk.tsystems.gamestudio.game.tictactoe.core.Field;
import sk.tsystems.gamestudio.game.tictactoe.core.GameState;
import sk.tsystems.gamestudio.game.tictactoe.core.Tile;
import sk.tsystems.gamestudio.game.tictactoe.exception.TicTacToeException;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TictactoeController {

	private Field field = new Field(10, 10);

	@Autowired
	private UserController userController;
	
	private boolean xPlaying = true;

	@RequestMapping("/tictactoe")
	public String tictactoe(
			@RequestParam(name = "row", required = false) Integer row,
			@RequestParam(name = "column", required = false) Integer column, 
			Model model) throws TicTacToeException {
		
		if(field.getGameState() == GameState.PLAYING 
				&& row!=null 
				&& column!=null) {
			if (xPlaying) {
				field.markX(row, column);
			} else {
				field.markO(row, column);
			}
			xPlaying = !xPlaying;
		}
		
		model.addAttribute("xPlaying", xPlaying);
		return "tictactoe";
	}

	@RequestMapping("/tictactoeNew")
	public String startNewGame() {
		field = new Field(10, 10);
		return "redirect:/tictactoe";
	}

	public String getHTMLField() {
		Formatter sb = new Formatter();
		sb.format("<table class='tictactoe_field'>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			sb.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				sb.format("<td>\n");
				sb.format("<a href='/tictactoe?row=%d&column=%d'>", row, column);
				sb.format("<img src='/images/tictactoe/" + getImageName(tile) + ".png'>\n");
				sb.format("</a></td>");
			}
		}
		sb.format("</table>\n");
		return sb.toString();
	}
	
	public String getImageName(Tile tile) { //public, len ak nepouzivame getHTMLField()
		switch (tile.getState()) {
		case EMPTY:
			return "closed";
		case O:
			return "omarked";
		case X:
			return "xmarked";
		}
		throw new IllegalArgumentException("Uns. tile state " + tile.getState());
	}


	public String getGameState() {
		GameState state= field.getGameState();
		switch (state) {
			case PLAYING:
				return "Playing...";
			case O_PLAYER_WON:
				return "Player O won";
			case X_PLAYER_WON:
				return "Player X won";
		}
		return "shouldn't happen";
	}
}
