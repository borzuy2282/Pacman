import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame{
    JLabel scoreNum;
    GameBoard board;
    int second = 0;

    public GameWindow(int w, int h){
        setLayout(new BorderLayout());
        board = new GameBoard(w, h, this);
        JPanel info = new JPanel();
        add(info, BorderLayout.NORTH);
        add(board);
        scoreNum = new JLabel("Score: " + board.score);
        info.setBackground(Color.BLACK);
        info.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        info.setPreferredSize(new Dimension(board.field.getWidth(), 50));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pacman");
        setBounds(0, 0, board.field.getWidth(), board.field.getHeight() + info.getHeight() + 30);
        setLocationRelativeTo(null);
        //changing the size:
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                board.field.setRowHeight(board.field.getHeight() / board.field.getRowCount());
                for (int i = 0; i < board.field.getColumnCount(); i++) {
                    board.field.getColumnModel().getColumn(i).setPreferredWidth(board.field.getWidth() / board.field.getColumnCount());
                }
            }
        });
        scoreNum.setForeground(Color.BLUE);
        JLabel time = new JLabel(second + "s");
        time.setForeground(Color.BLUE);
        JLabel ht = new JLabel("Lives: " + board.hearts);
        ht.setForeground(Color.red);
        info.add(time);
        info.add(scoreNum);
        info.add(ht);
//      thread for time and hearts counting:
        new Thread(() -> {
            while(board.alive){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                second++;
                time.setText(second + "s");
                ht.setText("Lives: " + board.hearts);
                ht.repaint();
                time.repaint();
            }
        }).start();
//        thread for score counting:
        new Thread(() -> {
            while (board.alive){
                scoreNum.setText("Score: " + board.score);
                scoreNum.repaint();
                try {
                    Thread.sleep(board.speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            endGame();

        }).start();



    }
//    end game func:
    public void endGame(){
        String name = JOptionPane.showInputDialog(board, "Please, enter you name and you will be added to a list", "To the list!", JOptionPane.QUESTION_MESSAGE);
        if(name == null || name.length() < 3){
            JOptionPane.showMessageDialog(board, "Length must be more than 3 characters!", "Error: 1", JOptionPane.ERROR_MESSAGE);
            endGame();
            return;
        }
        Score sc = new Score(name);
        sc.setScore(board.score);
        HighScore hs = new HighScore();
        hs.loadScores();
        for(Score s : hs.getScores()){
            if(s.getName().equals(name)){
                JOptionPane.showMessageDialog(board, "There already was such a name, try another", "Error: 2", JOptionPane.ERROR_MESSAGE);
                endGame();
                return;
            }
        }
        hs.addScore(sc);
        hs.saveScores();
        dispose();
        SwingUtilities.invokeLater(Game::new);
    }


}
