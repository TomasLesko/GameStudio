package sk.tsystems.gamestudio.controller;

import java.util.Date;
import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.puzzle.core.Field;
import sk.tsystems.gamestudio.game.puzzle.core.Tile;
import sk.tsystems.gamestudio.service.ScoreService.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {
	private static final String DB_GAME_NAME = "Puzzle";

	private Field field = new Field(2, 2);

	@Autowired
	private ScoreService scoreService;

	@RequestMapping("/puzzle")
	public String puzzle(@RequestParam(name = "value", required = false) Integer val, Model model) {
		if (!field.isSolved() && val != null) {
			field.moveTile(val);
			if (field.isSolved()) {
				scoreService.addScore(
						new Score(DB_GAME_NAME, System.getProperty("user.name"), field.getScore(), new Date()));

			}

		}

		setModel(model);
		return "puzzle";
	}

	private void setModel(Model model) {
		model.addAttribute("scores", scoreService.getBestScores(DB_GAME_NAME));
	}

	public String getHTMLField() {
		Formatter sb = new Formatter();
		sb.format("<table class='puzzle_field'>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			sb.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				if (tile != null) {
					int value = tile.getValue();
					sb.format("<td>\n");
					sb.format("<a href='/puzzle?value=%d'>", value);
					sb.format("" +tile.getValue()+ "</a></td>");
					
				} else {
					sb.format("<td> </td>\n");

				}
			}
		}
		sb.format("</table>\n");
		return sb.toString();
	}

	@RequestMapping("/puzzleNew")
	public String startNewGame() {
		field = new Field(2, 2);
		return "redirect:/puzzle";
	}

	public String getGameState() {
		if (field.isSolved()) {
			return "SOLVED";
		}
		return "PLAYING";
	}

}
