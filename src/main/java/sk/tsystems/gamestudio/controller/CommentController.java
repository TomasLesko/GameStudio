package sk.tsystems.gamestudio.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.service.CommentService.CommentService;
import sk.tsystems.gamestudio.service.user.UserService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CommentController {
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private CommentService commentService;
	
	
	@RequestMapping("/addcomment")
	public String addComment(Comment comment, Model model) {
		comment.setUsername(userController.getLoggedUsername());
		comment.setAdded(new Date());
		String message;
		
		if (commentService.addComment(comment)) {
			//OK
			message = "Your comment has been added";
		} else {
			//FAIL COMMENT
			message = "Something wrong";
		}
		model.addAttribute("commentMessage", message);
		return comment.getGame();
	}
	
	
	
}