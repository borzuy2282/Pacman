import java.io.Serializable;

public class Score implements Serializable {
    private String name;
    private int score;

    public Score(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " " + score + "\n";
    }
}
