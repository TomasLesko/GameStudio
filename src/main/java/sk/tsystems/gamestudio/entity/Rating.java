package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Rating {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	private String game;
	private int rate;
	
	
	private Rating () {
		
	}


	public Rating(String username, String game, int rate) {
		super();
		this.username = username;
		this.game = game;
		this.rate = rate;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGame() {
		return game;
	}


	public void setGame(String game) {
		this.game = game;
	}


	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}
	
	

}
