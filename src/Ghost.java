import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ghost implements Runnable {
    ImageIcon color;
    int x, y, px, py, startX, startY;
    ImageIcon last;

    GameBoard gb;



    public Ghost(ImageIcon color, int x, int y) {
        this.color = color;
        this.x = y;
        this.y = x;
        last = new ImageIcon("Images/Point.png");
    }

    public void setGb(GameBoard gb) {
        this.gb = gb;
    }

    @Override
    public void run() {
        while (gb.alive) {
            /*if(y > x) {
                if (py > y) {
                    if (gb.matrix[y + 1][x] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y + 1][x];
                        gb.matrix[y + 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y++;
                        gb.repaint();
                    }else{
                        ImageIcon tmp = gb.matrix[y - 1][x];
                        gb.matrix[y - 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y++;
                        gb.repaint();
                    }
                } else if (py < y) {
                    if (gb.matrix[y - 1][x] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y - 1][x];
                        gb.matrix[y - 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y--;
                        gb.repaint();
                    }else{
                        ImageIcon tmp = gb.matrix[y + 1][x];
                        gb.matrix[y + 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y++;
                        gb.repaint();
                    }
                } else if (px > x) {
                    if (gb.matrix[y][x + 1] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y][x + 1];
                        gb.matrix[y][x + 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x++;
                        gb.repaint();
                    }else{
                        ImageIcon tmp = gb.matrix[y][x - 1];
                        gb.matrix[y][x - 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x++;
                        gb.repaint();
                    }
                } else if (px < x) {
                    if (gb.matrix[y][x - 1] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y][x - 1];
                        gb.matrix[y][x - 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x--;
                        gb.repaint();
                    }else{
                        ImageIcon tmp = gb.matrix[y][x + 1];
                        gb.matrix[y][x + 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x++;
                        gb.repaint();
                    }
                } else {
                    gb.setHearts(gb.hearts - 1);
                    last = new ImageIcon("Images/Field.png");
                    gb.reset();
                    gb.repaint();
                }
            } else if (x > y) {
                if (px > x) {
                    if (gb.matrix[y][x + 1] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y][x + 1];
                        gb.matrix[y][x + 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x++;
                        gb.repaint();
                    }else{
                        ImageIcon tmp = gb.matrix[y][x - 1];
                        gb.matrix[y][x - 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x--;
                        gb.repaint();
                    }
                } else if (px < x) {
                    if (gb.matrix[y][x - 1] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y][x - 1];
                        gb.matrix[y][x - 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x--;
                        gb.repaint();
                    }else{
                        ImageIcon tmp = gb.matrix[y][x + 1];
                        gb.matrix[y][x + 1] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        x++;
                        gb.repaint();
                    }
                }else if (py > y) {
                    if (gb.matrix[y + 1][x] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y + 1][x];
                        gb.matrix[y + 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y++;
                        gb.repaint();
                    } else{
                        ImageIcon tmp = gb.matrix[y - 1][x];
                        gb.matrix[y - 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y--;
                        gb.repaint();
                    }
                } else if (py < y) {
                    if (gb.matrix[y - 1][x] != gb.wall) {
                        ImageIcon tmp = gb.matrix[y - 1][x];
                        gb.matrix[y - 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y--;
                        gb.repaint();
                    }else{
                        ImageIcon tmp = gb.matrix[y + 1][x];
                        gb.matrix[y + 1][x] = color;
                        gb.matrix[y][x] = last;
                        last = tmp;
                        y++;
                        gb.repaint();
                    }
                }
                else {
                    gb.setHearts(gb.hearts - 1);
                    last = new ImageIcon("Images/Field.png");
                    gb.reset();
                    gb.repaint();
                }
            }*/
            /*int dx = px - x;
            int dy = py - y;


            // calculate the angle between ghost and Pacman using SOH-CAH-TOA ratios
            double angle = Math.atan2(dy, dx);

            // determine the direction in which the ghost should move
            int rowDiff = 0;
            int colDiff = 0;

            if (angle < -Math.PI / 4 && angle >= -3 * Math.PI / 4) {
                // move up
                rowDiff = -1;
                ImageIcon tmp = gb.matrix[y][x + rowDiff];
                gb.matrix[y][x + rowDiff] = color;
                gb.matrix[y][x] = last;
                last= tmp;

            } else if (angle >= -Math.PI / 4 && angle < Math.PI / 4) {
                // move right
                colDiff = 1;
                ImageIcon tmp = gb.matrix[y + colDiff][x];
                gb.matrix[y + colDiff][x] = color;
                gb.matrix[y][x] = last;
                last= tmp;
            } else if (angle >= Math.PI / 4 && angle < 3 * Math.PI / 4) {
                // move down
                rowDiff = 1;
                ImageIcon tmp = gb.matrix[y][x + rowDiff];
                gb.matrix[y][x + rowDiff] = color;
                gb.matrix[y][x] = last;
                last= tmp;
            } else {
                // move left
                colDiff = -1;
                ImageIcon tmp = gb.matrix[y + colDiff][x];
                gb.matrix[y + colDiff][x] = color;
                gb.matrix[y][x] = last;
                last= tmp;
            }
            if(px == x && py == y){
                gb.reset();
                gb.repaint();
            }

            // move the ghost in the calculated direction
            int newRow = y + rowDiff;
            int newCol = x + colDiff;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            px = gb.getPacmanX();
            py = gb.getPacmanY();
            gb.repaint();*/
            Random rn = new Random();
            int pick = rn.nextInt(7, 11);
            switch (pick){
                case 7 -> moveRight();
                case 8 -> moveLeft();
                case 9 -> moveUp();
                case 10 -> moveDown();
            }
            if(x == px && y == py){
                if(last == gb.pacmanClosedLeft || last == gb.pacmanClosedRight || last == gb.pacmanLeft || last == gb.pacmanRight  || last == gb.pacmanUp  || last == gb.pacmanDown){
                    last = gb.empty;
                }
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void moveRight(){
        if(gb.matrix[y + 1][x] != gb.wall){
            ImageIcon tmp = gb.matrix[y + 1][x];
            gb.matrix[y + 1][x] = color;
            if(last != gb.point && last != gb.empty){
                last = gb.point;
            }
            gb.matrix[y][x] = last;
            last = tmp;
            y++;
            gb.repaint();
        }
    }
    public void moveLeft(){
        if(gb.matrix[y - 1][x] != gb.wall){
            ImageIcon tmp = gb.matrix[y - 1][x];
            gb.matrix[y - 1][x] = color;
            if(last != gb.point && last != gb.empty){
                last = gb.point;
            }
            gb.matrix[y][x] = last;
            last = tmp;
            y--;
            gb.repaint();
        }
    }
    public void moveUp(){
        if(gb.matrix[y][x - 1] != gb.wall){
            ImageIcon tmp = gb.matrix[y][x - 1];
            gb.matrix[y][x - 1] = color;
            if(last != gb.point && last != gb.empty){
                last = gb.point;
            }
            gb.matrix[y][x] = last;
            last = tmp;
            x--;
            gb.repaint();
        }
    }
    public void moveDown(){
        if(gb.matrix[y][x + 1] != gb.wall){
            ImageIcon tmp = gb.matrix[y][x + 1];
            gb.matrix[y][x + 1] = color;
            if(last != gb.point && last != gb.empty){
                last = gb.point;
            }
            gb.matrix[y][x] = last;
            last = tmp;
            x++;
            gb.repaint();
        }
    }
    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
