package sk.tsystems.gamestudio.service.Rating;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import sk.tsystems.gamestudio.entity.Rating;


@Transactional
public class RatingServiceJPA implements RatingService {
	
	@PersistenceContext
	private EntityManager entityManager;


	@Transactional
	@Override
	public void changeRating (Rating rating) {
			entityManager.merge(rating);
	}
	@Transactional
	@Override
	public List<Rating> getRatings(String gameName) {
		try {
			List<Rating> ratings = entityManager
					.createQuery("SELECT r FROM Rating r WHERE r.game = :gameName", Rating.class)
					.setParameter("gameName", gameName)
					.getResultList();
			return ratings;
		} catch (Exception ex) {
			throw new RatingException("Failed", ex);
		}
	}

	

}
