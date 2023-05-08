import javax.swing.*;
import java.awt.*;

public class PacmanPanel extends JPanel {
    int direction, x, y, size;
    boolean mouth;
    public PacmanPanel(int dir, int x, int y, int s, boolean mouth){
        this.direction = dir;
        this.x = x;
        this.y = y;
        this.size = s;
        this.mouth = mouth;
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //filling backgriound
        g.setColor(Color.blue);
        g.fillRect(0,0, getWidth(), getHeight());
        //starting drawing pacman, based on its direction
        g.setColor(Color.yellow);
        if(direction == 0){
            if(mouth) {
                g.fillArc(x, y, size, size, 45, 270);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            g.fillArc(x, y, size, size, 0, 360);
//            repaint();
            }else{
                g.fillArc(x, y, size, size, 0, 360);
            }
        }else if(direction == 1){
            if(mouth) {
                g.fillArc(x, y, size, size, 135, 270);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            g.fillArc(x, y, size, size, 0, 360);
//            repaint();
            }else{
                g.fillArc(x, y, size, size, 0, 360);
            }
        }else if(direction == 2){
            if(mouth) {
                g.fillArc(x, y, size, size, 225, 270);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            g.fillArc(x, y, size, size, 0, 360);
//            repaint();
            }else{
                g.fillArc(x, y, size, size, 0, 360);
            }
        }else {
            if(mouth) {
                g.fillArc(x, y, size, size, 315, 270);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            g.fillArc(x, y, size, size, 0, 360);
//            repaint();
            }else{
                g.fillArc(x, y, size, size, 0, 360);
            }
        }

    }

    public void setMouth(boolean mouth) {
        this.mouth = mouth;
    }
}
