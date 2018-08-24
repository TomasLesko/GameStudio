package sk.tsystems.gamestudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;


import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.service.Rating.RatingService;


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class RatingController {

	@Autowired
	private UserController userController;
	@Autowired
	private RatingService ratingService;
	
	@RequestMapping("/addrating")
	public String addRating (Rating rating) {
		
		String gamename =" ";
		
		rating.setUsername(userController.getLoggedUsername());
		ratingService.changeRating(rating);
		gamename = rating.getGame();
		avg(gamename);
		return  rating.getGame();
		
	}

	public double avg(String gameName) {
		List<Rating> ratings = ratingService.getRatings(gameName);
		double sum = 0;
		for (Rating rating : ratings) {
			sum= sum + rating.getRate();
		}
		double avg = sum/ratings.size();
		return avg;
		
	}
}
