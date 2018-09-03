package sk.tsystems.gamestudio.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.guessnumber.logic.Logic;
import sk.tsystems.gamestudio.service.ScoreService.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessANumberController {
	private static final String DB_GAME_NAME = "GuessANumber";
	private Logic logic = new Logic();
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private UserController userController; 
	
	
	@RequestMapping("/guessanumber")
	public String guessanumber (
			@RequestParam(name = "number", required = false) Integer number, Model model) {
		logic.setMessage(null);
		if(number != null) {
			if (logic.guessPart(number)) {
				scoreService.addScore(
						new Score(
								DB_GAME_NAME, 
								userController.getLoggedUsername(), 
								logic.getScore(), 
								new Date()));
			} else {
				logic.guessPart(number);
			}
			
		}
		setModel(model);
		return "guessanumber";
	}
	
	private void setModel(Model model) {
		model.addAttribute("scores", scoreService.getBestScores(DB_GAME_NAME));
		model.addAttribute("message", logic.getMessage());
		
	}

	@RequestMapping("/guessNew")
	public String startNewGame() {
		logic = new Logic();
		return "redirect:/guessanumber";
	}
	

	
}
