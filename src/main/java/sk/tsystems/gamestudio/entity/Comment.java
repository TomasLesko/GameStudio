package sk.tsystems.gamestudio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	private Date added;
	private String game;
	private String comm;
	
	
	private Comment () {
		
	}


	public Comment(String username, Date added, String game, String comm) {
		super();
		this.username = username;
		this.added = added;
		this.game = game;
		this.comm = comm;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Date getAdded() {
		return added;
	}


	public void setAdded(Date added) {
		this.added = added;
	}


	public String getGame() {
		return game;
	}


	public void setGame(String game) {
		this.game = game;
	}


	public String getComm() {
		return comm;
	}


	public void setComm(String comm) {
		this.comm = comm;
	}
	
	@Override
    public String toString() {
        return "Comment{" +
                "game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", added=" + added +
                ", comm=" + comm +
                '}';
	
	}
}