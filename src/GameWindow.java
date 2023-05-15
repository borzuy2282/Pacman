import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameWindow extends JFrame{
    JLabel scoreNum;
    GameBoard board;
    int second = 0;
    ImageIcon heart = new ImageIcon("Images/Heart.png");

    public GameWindow(int w, int h){
//        setResizable(false);
        setLayout(new BorderLayout());
        board = new GameBoard(w, h);
        JPanel info = new JPanel();
        add(info, BorderLayout.NORTH);
        add(board);
        scoreNum = new JLabel("Score: " + board.score);
        board.setGm(this);
        info.setBackground(Color.BLACK);
        info.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        info.setPreferredSize(new Dimension(1920, 50));


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dm = toolkit.getScreenSize();
        setTitle("Pacman");
        setBounds(0, 0, dm.width, dm.height);
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
    public void endGame(){
        String name = JOptionPane.showInputDialog(board, "Please, enter you name and you will be added to a list", "To the list!", JOptionPane.QUESTION_MESSAGE);
        if(name.length() <= 3){
            JOptionPane.showMessageDialog(board, "Length must be more than 3 characters!", "Error", JOptionPane.ERROR_MESSAGE);
            endGame();
            return;
        }
        Score sc = new Score(name);
        sc.setScore(board.score);
        sc.setTime(second);
        sc.setSize(board.height * board.width - (2*board.width + 2*board.height));
        sc.setResult(sc.getScore() / sc.getTime() * sc.getSize());
        HighScore hs = new HighScore();
        hs.addScore(sc);
        hs.saveScores();
        dispose();
        SwingUtilities.invokeLater(Game::new);
    }


}
