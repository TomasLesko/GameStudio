package sk.tsystems.gamestudio.service.CommentService;

import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;

public interface CommentService {
	 
	boolean addComment (Comment comment);
	 
	 List<Comment> getComments (String gameName);
}