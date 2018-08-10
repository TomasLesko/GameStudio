package sk.tsystems.gamestudio.menu;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import sk.tsystems.gamestudio.game.Game;

public class MainConsole {

	//automaticky vlozi vsetky triedy, ktore su zavedene ako Bean a implementuju Game rozhranie 
	@Autowired
	private Game[] games;
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("Choose a game:");
				System.out.println("0. Exit gamestudio");
				for (int i = 0; i < games.length; i++) {
					System.out.println((i+1)+". "+games[i].getName());
				}
				
				int input = Integer.parseInt(scanner.nextLine());
				if (input == 0) {
					return; //ukonci cely run(), teda aj while cyklus
				}
				if (input > 0 && input <= games.length) {
					games[input-1].play();
				} else {
					System.out.println("Wrong input, try again.");
				}
			} catch (Exception ex) {
				System.out.println("Error: "+ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
