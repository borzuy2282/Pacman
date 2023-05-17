import java.io.*;
import java.util.ArrayList;

public class HighScore implements Serializable {
    private ArrayList<Score> scores = new ArrayList<>();

    public ArrayList<Score> getScores() {
        return scores;
    }
    public void sortScore(){
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = 0; j < scores.size() - 1 - i; j++) {
                if(scores.get(j).getScore() < scores.get(j+1).getScore()){
                    Score tmp = scores.get(j);
                    scores.set(j, scores.get(j+1));
                    scores.set(j+1, tmp);
                }
            }
        }
    }

    public void addScore(Score score){
        scores.add(score);
    }
    public void saveScores(){
        try{
            FileOutputStream fileOut = new FileOutputStream("HighScore.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(scores);
            out.close();
            fileOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadScores(){
        try{
            FileInputStream fileIn = new FileInputStream("HighScore.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            scores = (ArrayList<Score>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e ){
            e.printStackTrace();
        }
    }

}
