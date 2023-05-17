import javax.swing.*;
import java.util.Random;

public class Ghost extends Thread {
    ImageIcon color;
    int x, y, px, py;
    ImageIcon last;

    GameBoard gb;
    static ImageIcon bonus50 = new ImageIcon("Images/Bonus50.png");
    static ImageIcon bonusHp = new ImageIcon("Images/BonusHp.png");
    static ImageIcon bonusKilling = new ImageIcon("Images/BonusKilling.png");
    static ImageIcon bonusSpeed = new ImageIcon("Images/BonusSpeed.png");
    static ImageIcon bonusShield = new ImageIcon("Images/BonusShield.png");



    public Ghost(ImageIcon color, int y, int x) {
        this.color = color;
        this.x = x;
        this.y = y;
        last = new ImageIcon("Images/Point.png");
    }

    public void setGb(GameBoard gb) {
        this.gb = gb;
    }

    @Override
    public void run() {
        while (gb.alive) {
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
            if(last != gb.point && last != gb.empty && last != bonus50 && last != bonusHp && last != bonusShield && last != bonusSpeed &&  last != bonusKilling ){
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
            if(last != gb.point && last != gb.empty && last != bonus50 && last != bonusHp && last != bonusShield && last != bonusSpeed &&  last != bonusKilling ){
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

            if(last != gb.point && last != gb.empty && last != bonus50 && last != bonusHp && last != bonusShield && last != bonusSpeed &&  last != bonusKilling ){
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
            if(last != gb.point && last != gb.empty && last != bonus50 && last != bonusHp && last != bonusShield && last != bonusSpeed &&  last != bonusKilling ){
                last = gb.point;
            }
            gb.matrix[y][x] = last;
            last = tmp;
            x++;
            gb.repaint();
        }
    }
    public void dropBonus() {
        new Thread(() -> {
            while (gb.alive) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Random rn = new Random();
                int probability = rn.nextInt(1, 101);
                if (probability < 25) {
                    int pickBonus = rn.nextInt(1, 6);
                    switch (pickBonus) {
                        case 1 -> last = bonus50;
                        case 2 -> last = bonusHp;
                        case 3 -> last = bonusShield;
                        case 4 -> last = bonusSpeed;
                    }
                    if (pickBonus == 5 && !gb.killed) {
                        last = bonusKilling;
                        gb.killed = true;
                    }

                }
            }
        }).start();
    }
}
