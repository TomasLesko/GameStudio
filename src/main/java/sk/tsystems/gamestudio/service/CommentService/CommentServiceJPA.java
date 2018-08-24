package sk.tsystems.gamestudio.service.CommentService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import sk.tsystems.gamestudio.entity.Comment;

@Transactional
public class CommentServiceJPA implements CommentService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean addComment(Comment comment) {
		try {
			entityManager.persist (comment);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Comment> getComments(String gameName) {
		try {
			List<Comment> comments = entityManager
					.createQuery("SELECT c FROM Comment c WHERE c.game = :gameName", Comment.class)
					.setParameter("gameName", gameName)
					.setMaxResults(5)
					.getResultList();
			return comments;
		} catch (Exception ex) {
			throw new CommentException("Failed", ex);
		}
		
	}

}