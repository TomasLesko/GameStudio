package sk.tsystems.gamestudio.game.guessnumber.logic;

import java.util.Random;

public class Logic {
	
	private int guessedNumber;
	private int moveCount;
	private String message;
	private final int score = 20;
	
	public Logic () {
		generateNumber();
	}
	
	public int generateNumber() {
		Random random = new Random();
		guessedNumber = random.nextInt(100);
		return guessedNumber;
	}
	
	public boolean guessPart (int number) {
		
		if (number == guessedNumber) {
			moveCount++;
			message = "You won";
			return true;
		}
		if (number < guessedNumber) { 
			message = "More";
			moveCount++;
			return false;
		}else {
			message = "Less";
			moveCount++;
			return false;
		}
		
	}
	
	public int getScore() {
		return score - moveCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
