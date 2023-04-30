import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    public Menu(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dm = toolkit.getScreenSize();
        setTitle("Pacman");
        setBounds(dm.width/2 - 250, dm.height/2 - 150, 500, 300 );
        JPanel jPanel = new JPanel();
        add(jPanel);
        jPanel.add(getStartButton());
        jPanel.add(getHighScoreButton());
        jPanel.add(getExitButton());
    }
    static JButton getStartButton(){
        JButton start = new JButton("Start");
        return start;
    }
    static JButton getHighScoreButton(){
        JButton score = new JButton("High score");
        return score;
    }
    static JButton getExitButton(){
        JButton exit = new JButton("Exit");
        return exit;
    }

}
