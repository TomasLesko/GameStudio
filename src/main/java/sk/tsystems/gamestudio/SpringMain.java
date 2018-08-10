package sk.tsystems.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.tsystems.gamestudio.game.guessnumber.ui.GuessNumberConsoleUI;
import sk.tsystems.gamestudio.game.mines.core.Field;
import sk.tsystems.gamestudio.game.mines.ui.MinesConsoleUI;
import sk.tsystems.gamestudio.game.puzzle.ui.PuzzleConsoleUI;
import sk.tsystems.gamestudio.menu.MainConsole;
import sk.tsystems.gamestudio.service.ScoreService.ScoreService;
import sk.tsystems.gamestudio.service.ScoreService.ScoreServiceFile;
import sk.tsystems.gamestudio.service.ScoreService.ScoreServiceJDBC;
import sk.tsystems.gamestudio.service.ScoreService.ScoreServiceJPA;

@SpringBootApplication
@Configuration
public class SpringMain {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMain.class, args); 
	}
	
	@Bean
	public CommandLineRunner runner(MainConsole mainConsole) {
//	Full anonymous implementation of CommandLineRunner	
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... arg0) throws Exception {
//				mainConsole.run();
//			}
//		};
		
//	Implementation using lambda with typed parameter 
//		return (String... args0) -> {
//			mainConsole.run();
//		};
		
//	Implementation with clean lambda
		return (args0) -> mainConsole.run();
	}
	
	@Bean
	public MainConsole xy() {
		return new MainConsole();
	}
	
	@Bean
	public ScoreService scoreService() {
//		return new ScoreServiceJDBC();
//		return new ScoreServiceFile();
		return new ScoreServiceJPA();
	}
	
	@Bean
	public MinesConsoleUI mineConsoleUI() {
		return new MinesConsoleUI(new Field(9, 9, 2));
	}
	
	@Bean
	public PuzzleConsoleUI puzzleConsoleUI() {
		return new PuzzleConsoleUI();
	}
	
	@Bean
	public GuessNumberConsoleUI guessNumberConsoleUI() {
		return new GuessNumberConsoleUI();
	}
}
