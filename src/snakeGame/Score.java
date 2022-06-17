package snakeGame;

public class Score {
	int id;
	int score;

	public Score(int score) {
		super();
		this.score = score;
	}

	public Score(int id, int score) {
		super();
		this.id = id;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
