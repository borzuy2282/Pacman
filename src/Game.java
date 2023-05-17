import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Game extends JFrame implements Serializable {
    JPanel menu = new JPanel(new BorderLayout());
    int width, height;
    public Game(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dm = toolkit.getScreenSize();
        setTitle("Pacman");
        setBounds(dm.width/2 - 600, dm.height/2 - 360, 1200, 720 );

        menu.setBackground(Color.black);

        JPanel btn = new JPanel(new GridBagLayout());
        btn.setBackground(Color.black);
        menu.add(getPacmanTitle(), BorderLayout.NORTH);
        add(menu);
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(10, 20, 10, 20);
        gb.gridx = 0;
        btn.add(getStartButton());
        gb.gridx = 1;
        btn.add(getHighScoreButton());
        gb.gridx = 2;
        btn.add(getExitButton());
        menu.add(btn);


    }
    JLabel getPacmanTitle(){
        JLabel name = new JLabel("PACMAN", JLabel.CENTER);
        name.setOpaque(true);
        name.setPreferredSize(new Dimension(200, 100));
        name.setBackground(Color.black);
        name.setForeground(Color.ORANGE);
        name.setFont(new Font("Serif", Font.BOLD, 36));
        return name;
    }
    JButton getStartButton(){
        JButton start = new JButton("Start");
        start.setForeground(Color.ORANGE);
        start.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        start.setPreferredSize(new Dimension(140,70));
        start.setBackground(Color.BLACK);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checker = true;
                String s = JOptionPane.showInputDialog(menu, "What width do you want to play?","Width pick", JOptionPane.QUESTION_MESSAGE);
                try{
                    if(Integer.parseInt(s) >= 10 && Integer.parseInt(s) <= 100){
                        width = Integer.parseInt(s);
                    }else{
                        checker = false;
                        JOptionPane.showMessageDialog(menu, "That is not good size for playing, try again", "Inappropriate size", JOptionPane.ERROR_MESSAGE);
                    }
                }catch(NumberFormatException k){
                    checker = false;
                    JOptionPane.showMessageDialog(menu, "That is not good size for playing, try again", "Inappropriate size", JOptionPane.ERROR_MESSAGE);
                }
                if(checker) {
                    s = JOptionPane.showInputDialog(menu, "What height do you want to play?", "Height pick", JOptionPane.QUESTION_MESSAGE);
                    try {
                        if (Integer.parseInt(s) >= 10 && Integer.parseInt(s) <= 100) {
                            height = Integer.parseInt(s);
                        } else {
                            checker = false;
                            JOptionPane.showMessageDialog(menu, "That is not good size for playing, try again", "Inappropriate size", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException k) {
                        checker = false;
                        JOptionPane.showMessageDialog(menu, "That is not good size for playing, try again", "Inappropriate size", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(checker) {
                    dispose();
                    SwingUtilities.invokeLater(() -> new GameWindow(height, width));
                }
            }
        });
        return start;
    }
    JButton getHighScoreButton(){
        JButton score = new JButton("High score");
//        score.setBorder(BorderFactory.createEmptyBorder(70, 140, 70, 140));
        score.setForeground(Color.ORANGE);
        score.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        score.setPreferredSize(new Dimension(140,70));
        score.setBackground(Color.BLACK);
        score.setSize(140,70);
        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String>listModel = new DefaultListModel<>();
                ArrayList<Score> scores = getScore();
                for (Score score : scores){
                    listModel.addElement(String.valueOf(score));
                }
                JList <String> jList = new JList<>(listModel);
                jList.setForeground(Color.BLUE);
                jList.setBackground(Color.black);
                jList.setFont(new Font("Times New Roman", Font.BOLD, 15));
                JScrollPane jScrollPane = new JScrollPane(jList);
                JFrame results = new JFrame();
                results.add(jScrollPane);
                results.setSize(200, 200);

                jScrollPane.setBackground(Color.black);
                results.setVisible(true);
                results.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                results.setLocationRelativeTo(null);
            }
        });




        return score;
    }
    JButton getExitButton(){

        JButton exit = new JButton("Exit");
//        exit.setBorder(BorderFactory.createEmptyBorder(70, 140, 70, 140));
        exit.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        exit.setPreferredSize(new Dimension(140,70));
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.ORANGE);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int b = JOptionPane.showConfirmDialog(menu, "Are you sure you want to leave?", "Leaving?", JOptionPane.YES_NO_OPTION);
                if(b == 0){
                    System.exit(0);
                }
            }
        });
        return exit;
    }
    public ArrayList<Score> getScore(){
        HighScore hs = new HighScore();
        hs.loadScores();
        if(hs.getScores().size() > 1) {
            hs.sortScore();
        }
        ArrayList<Score> scores = hs.getScores();
        return scores;
    }

}
