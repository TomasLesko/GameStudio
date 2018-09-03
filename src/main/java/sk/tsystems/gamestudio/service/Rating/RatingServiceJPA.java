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
	public void changeRating(Rating rating) {
		Rating r = null;
		try {
			r = entityManager
					.createQuery("SELECT r FROM Rating r WHERE r.game= :gameName and r.username = :username",
							Rating.class)
					.setParameter("gameName", rating.getGame()).setParameter("username", rating.getUsername())
					.getSingleResult();
		} catch (NoResultException e) {
		}

		if (r != null) {
			r.setRate(rating.getRate());
		} else {
			entityManager.persist(rating);

		}

	}

	@Override
	public List<Rating> getRatings(String gameName) {
		try {
			List<Rating> ratings = entityManager
					.createQuery("SELECT r FROM Rating r WHERE r.game = :gameName", Rating.class)
					.setParameter("gameName", gameName).getResultList();
			return ratings;
		} catch (Exception ex) {
			throw new RatingException("Failed", ex);
		}
	}

}
