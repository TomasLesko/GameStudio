package sk.tsystems.gamestudio.service.ScoreService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import sk.tsystems.gamestudio.entity.Score;


public class ScoreServiceFile implements ScoreService {
	
	private List<Score> scores = new ArrayList<>();
	

	/* (non-Javadoc)
	 * @see puzzle.score.ScoreService#addScore(puzzle.score.Score)
	 */
	@Override
	public void addScore(Score score) {
		scores.add(score);
		save();
	}
	
	/* (non-Javadoc)
	 * @see puzzle.score.ScoreService#getBestScores()
	 */
	@Override
	public List<Score> getBestScores(String gameName) {
		load();
		return scores.stream()
				.filter((score)->gameName.equals(score.getGame()))
				.sorted(Comparator.comparing(Score::getPoints).reversed())
				.collect(Collectors.toList());
	}
	
	private String getFileName() {
		return "score.bin";
	}
	
	private void save() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFileName()))) {
			oos.writeObject(scores);
		} catch (IOException e) {
			System.err.println("Cannot save score!");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFileName()))) {
			scores = (List<Score>)ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Cannot load score!");
		}
	}
}
