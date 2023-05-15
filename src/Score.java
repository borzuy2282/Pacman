import java.io.Serializable;

public class Score implements Serializable {
    private String name;
    private int time, score, size;
    private double result;

    public Score(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public double getResult() {
        return result;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return name + " " + score + "\n";
    }
}
