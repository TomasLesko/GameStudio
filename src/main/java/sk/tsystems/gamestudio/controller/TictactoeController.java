package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.game.mines.core.Clue;
import sk.tsystems.gamestudio.game.mines.core.Mine;
import sk.tsystems.gamestudio.game.tictactoe.core.Field;
import sk.tsystems.gamestudio.game.tictactoe.core.GameState;
import sk.tsystems.gamestudio.game.tictactoe.core.Tile;
import sk.tsystems.gamestudio.game.tictactoe.core.TileState;
import sk.tsystems.gamestudio.game.tictactoe.exception.TicTacToeException;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TictactoeController {
	private static final String DB_GAME_NAME = "Tictactoe";

	private Field field = new Field(10, 10);

	@Autowired
	private UserController userController;

	@RequestMapping("/tictactoe")
	public String tictactoe(
			@RequestParam(name = "state", required = false) TileState state,
			@RequestParam(name = "row", required = false) Integer r,
			@RequestParam(name = "column", required = false) Integer c, Model model) {
		
	if(field.getGameState() == GameState.PLAYING && r!=null && c!=null) {
	
	}

	return"tictactoe";
	

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
				sb.format( "" + " X " + " </a></td>");

				
				

			}
		}
		sb.format("</table>\n");
		return sb.toString();

	}


	public String getGameState() {
		return field.getGameState().name();
	}
}
