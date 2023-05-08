import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame{
    GameBoard board;

    public GameWindow(int w, int h){
//        setResizable(false);
        setLayout(new BorderLayout());
        board = new GameBoard(w, h);
        JPanel info = new JPanel();
        add(info, BorderLayout.EAST);
        add(board);
        info.setBackground(Color.BLACK);
        info.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        info.setPreferredSize(new Dimension(100, 1080));


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dm = toolkit.getScreenSize();
        setTitle("Pacman");
        setBounds(0, 0, dm.width, dm.height);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = board.getSize();
                board.field.setPreferredScrollableViewportSize(size);
                board.field.revalidate();
            }
        });


    }
}
