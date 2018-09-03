package sk.tsystems.gamestudio.service.Rating;

import java.util.List;

import sk.tsystems.gamestudio.entity.Rating;

public interface RatingService {
	
	
	List<Rating> getRatings (String gameName);
	
	void changeRating(Rating rating);


}
