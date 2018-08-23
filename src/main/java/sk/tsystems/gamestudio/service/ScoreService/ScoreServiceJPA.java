package sk.tsystems.gamestudio.service.ScoreService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import sk.tsystems.gamestudio.entity.Score;

public class ScoreServiceJPA implements ScoreService{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void addScore(Score score) throws ScoreException {
		try {
			System.out.println("pred persist");
			entityManager.persist(score);
			
			System.out.println("za persist");
		} catch (Exception ex) {
			throw new ScoreException("Cannot save score", ex);
		}
		
	}
	
	@Transactional
	public void delete(Score score) {
		score = entityManager.merge(score);
		entityManager.remove(score);
	}
	
	@Transactional
	public void update(Score score) {
		entityManager.merge(score);
	}

	@Override
	@Transactional
	public List<Score> getBestScores(String gameName) throws ScoreException {
		try {
			List<Score> scores = entityManager
					.createQuery("SELECT s FROM Score s WHERE s.game = :gameName ORDER BY s.points DESC", Score.class)
					.setParameter("gameName", gameName)
					.setMaxResults(10)
					.getResultList();
			return scores;
		} catch (Exception ex) {
			throw new ScoreException("Error getting score for game "+gameName, ex);
		}
	}

}
