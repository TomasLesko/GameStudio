package sk.tsystems.gamestudio.service.user;

import sk.tsystems.gamestudio.entity.User;

public interface UserService {
	
	boolean register (User user);
	
	User login(String username, String password);

}
