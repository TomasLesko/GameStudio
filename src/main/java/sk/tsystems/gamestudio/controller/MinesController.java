package sk.tsystems.gamestudio.controller;

import java.util.Date;
import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.mines.core.Clue;
import sk.tsystems.gamestudio.game.mines.core.Field;
import sk.tsystems.gamestudio.game.mines.core.GameState;
import sk.tsystems.gamestudio.game.mines.core.Mine;
import sk.tsystems.gamestudio.game.mines.core.Tile;
import sk.tsystems.gamestudio.service.ScoreService.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesController {
	private static final String DB_GAME_NAME = "Mines";
	
	private enum FieldSize { 
		SMALL,
		MEDIUM,
		BIG
	}
	
	
	private FieldSize fieldSize = FieldSize.SMALL;
	

	private Field field = new Field(9, 9, 2);
	
	private boolean marking = false;
	
	@RequestMapping ("/minesChangeSize")
	public String changeSize () { 
		switch (fieldSize) {
		case SMALL: 
			fieldSize = FieldSize.MEDIUM;
			field = new Field (18,18,9);
			break;
		case MEDIUM:
			fieldSize = FieldSize.BIG;
			field = new Field (36,36,18);
			break;
		case BIG:
			fieldSize = FieldSize.SMALL;
			field = new Field (9,9,2);
			break;
		}
		return "redirect:/mines";
	}
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private UserController userController; 

	@RequestMapping("/mines")
	public String mines(
			@RequestParam(name = "row", required = false) Integer r,
			@RequestParam(name = "column", required = false) Integer c,
			Model model) {
		
		if (field.getState() == GameState.PLAYING && r != null && c != null) {
			//TODO: test range of numbers
			if (marking) {
				field.markTile(r, c);
			} else {
				field.openTile(r, c);
				if (field.getState() == GameState.SOLVED) {
					scoreService.addScore(new Score(
							DB_GAME_NAME,
							userController.getLoggedUsername(),
							field.getScore(),
							new Date()));
				}
			}
		}
		setModel(model);
		return "mines";
	}

	private void setModel(Model model) {
		model.addAttribute("marking",marking);
		model.addAttribute("scores", scoreService.getBestScores(DB_GAME_NAME));
		//model.addAttribute("field",field); //potrebne, len ak nechceme pouzit getHTMLfield() metodu
	}
	
	@RequestMapping("/minesNew")
	public String startNewGame() {
		field = new Field(9, 9, 2);
		return "redirect:/mines";
	}
	
	@RequestMapping("/minesChangeMarking")
	public String changeMarking() {
		marking = !marking;
		return "redirect:/mines";
	}
	
	public String getGameState() {
		return field.getState().name();
	}
	
	public String getHTMLField() {
		Formatter sb = new Formatter();
		sb.format("<table class='mines_field'>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			sb.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				sb.format("<td>\n");
				sb.format("<a href='/mines?row=%d&column=%d'>", row, column);
				sb.format("<img src='/images/mines/" + getImageName(tile) + ".png'>\n");
				sb.format("</a>");
			}
		}
		sb.format("</table>\n");
		return sb.toString();
	}

	public String getImageName(Tile tile) { //public, len ak nepouzivame getHTMLField()
		switch (tile.getState()) {
		case CLOSED:
			return "closed";
		case MARKED:
			return "marked";
		case OPENED:
			if (tile instanceof Mine)
				return "mine";
			else
				return "open" + ((Clue) tile).getValue();
		}
		throw new IllegalArgumentException("Uns. tile state " + tile.getState());
	}

}
