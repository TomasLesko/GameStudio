package sk.tsystems.gamestudio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "web_user")
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@Transient //not save to database
	private String repeatPassword;
	
	private User() {
	}
	
	public User(String username, String password, String repeatPassword) {
		super();
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	public boolean validatePassword() {
		return password.equals(repeatPassword);
	}

}
