import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel {
    boolean bSpeed, bKilling, bShield = true;
    boolean shieldActive = false;
    int allPoints = -1;
    ArrayList <Ghost> ghosts = new ArrayList<>();
    int hearts = 3;
    GameWindow gm;
    int score = 1;
    boolean moving = true;
    boolean alive = true;
    int width, height;
    int speed = 250;
    ImageIcon wall = new ImageIcon("Images/Wall.png");
    ImageIcon empty = new ImageIcon("Images/Field.png");
    ImageIcon point = new ImageIcon("Images/Point.png");
    ImageIcon pacmanRight = new ImageIcon("Images/PacmanRight.png");
    ImageIcon pacmanLeft = new ImageIcon("Images/PacmanLeft.png");
    ImageIcon pacmanDown = new ImageIcon("Images/PacmanDown.png");
    ImageIcon pacmanUp = new ImageIcon("Images/PacmanUp.png");
    ImageIcon pacmanClosedLeft = new ImageIcon("Images/PacmanClosedLeft.png");
    ImageIcon pacmanClosedRight = new ImageIcon("Images/PacmanClosedRight.png");
    ImageIcon bonus50 = new ImageIcon("Images/Bonus50.png");
    ImageIcon bonusHp = new ImageIcon("Images/BonusHp.png");
    ImageIcon bonusKilling = new ImageIcon("Images/BonusKilling.png");
    ImageIcon bonusSpeed = new ImageIcon("Images/BonusSpeed.png");
    ImageIcon bonusShield = new ImageIcon("Images/BonusShield.png");
    ImageIcon ghostBrown = new ImageIcon("Images/GhostBrown.png");
    ImageIcon ghostGreen = new ImageIcon("Images/GhostGreen.png");
    ImageIcon ghostGrey = new ImageIcon("Images/GhostGrey.png");
    ImageIcon ghostRed = new ImageIcon("Images/GhostRed.png");
    ImageIcon [][] matrix;
    JTable field;
    int pacmanX, pacmanY, directionUD, directionRL;

    public GameBoard(int w, int h) {
        this.width = w;
        this.height = h;
        generatingMatrix();
        field = new JTable(new GameMap(matrix));
        setVisible(true);
        setBackground(Color.BLACK);

        add(field);
        field.setTableHeader(null);
        int iWidth = matrix[0][0].getIconWidth();
        int iHeight = matrix[0][0].getIconHeight();
        field.setPreferredSize(new Dimension(iWidth * height, iHeight * width));
        field.setRowHeight(iHeight);
        for (int i = 0; i < field.getColumnCount(); i++) {
            field.getColumnModel().getColumn(i).setPreferredWidth(iWidth);
        }
//        field.setFocusable(true);
//        field.requestFocusInWindow();
//        field.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        new Thread(() -> {
            matrix[1][1] = pacmanRight;
            while (alive) {
                boolean checker = true;
                for (int i = 1; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if(matrix[i][j] == point){
                            checker = false;
                            break;
                        }
                    }
                }
                if(checker){
                    gm.endGame();
                }
                if(hearts <= 0){
                    alive = false;
                    break;
                }
                movement();
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < ghosts.size(); i++) {
                    if(pacmanY == ghosts.get(i).y && pacmanX == ghosts.get(i).x){
                        reset();
                    }
                }
            }
        }).start();
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                        matrix[pacmanY + 1][pacmanX] = pacmanRight;
//                        matrix[pacmanY][pacmanX] = empty;
//                        pacmanY++;
//                        repaint();
                    directionRL = 1;
                    directionUD = 0;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

//                        matrix[pacmanY - 1][pacmanX] = pacmanLeft;
//                        matrix[pacmanY][pacmanX] = empty;
//                        pacmanY--;
//                        repaint();
                    directionRL = -1;
                    directionUD = 0;

                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

//                        matrix[pacmanY][pacmanX + 1] = pacmanDown;
//                        matrix[pacmanY][pacmanX] = empty;
//                        pacmanX++;
//                        repaint();
                    directionUD = 1;
                    directionRL = 0;

                } else if (e.getKeyCode() == KeyEvent.VK_UP) {

//                        matrix[pacmanY][pacmanX - 1] = pacmanUp;
//                        matrix[pacmanY][pacmanX] = empty;
//                        pacmanX--;
//                        repaint();
                    directionUD = -1;
                    directionRL = 0;

                }
                else if(e.getKeyCode() == KeyEvent.VK_W && e.isControlDown() && e.isShiftDown()){
                    SwingUtilities.invokeLater(Game::new);
                    gm.dispose();
                }
            }
        });
    }
    public void generatingMatrix(){
        Random rn = new Random();
        matrix = new ImageIcon[height][width];
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[0][i] = wall;
            matrix[height - 1][i] = wall;
        }
        int maxWalls = (height * width) / 5;
        int checkerHorizontal = 0;
        for (int i = 0; i < matrix[0].length - 1; i++) {
            if(i == 0){
                matrix[1][i] = wall;
                matrix[1][width - 1] = wall;
                matrix[height - 2][i] = wall;
                matrix[height - 2][width - 1] = wall;
                continue;
            }
            matrix[1][i] = point;
            allPoints ++;
            matrix[height - 2][i] = point;
            allPoints++;
        }
        for (int i = 2; i < height - 1; i++) {
            matrix[i][0] = wall;
            checkerHorizontal++;
            for (int j = 1; j < width - 1; j++) {
                if(maxWalls == 0){
                    matrix[i][j] = point;
                    allPoints++;
                    continue;
                }
                if(checkerHorizontal == 4){
                    matrix[i][j] = point;
                    allPoints++;
                    checkerHorizontal = 0;
                    continue;
                }
                int gen = rn.nextInt(10);
                if(gen == 9){
                    matrix[i][j] = wall;
                    checkerHorizontal++;
                    maxWalls--;
                }else{
                    matrix[i][j] = point;
                    allPoints++;
                    checkerHorizontal = 0;
                }
            }
            matrix[i][width - 1] = wall;
        }
        matrix[1][1] = pacmanClosedRight;
        pacmanX = 1;
        pacmanY = 1;
        matrix[1][width - 2] = ghostBrown;
        Ghost brown = new Ghost(ghostBrown, 1, width - 2);
        matrix[height - 2][1] = ghostGreen;
        Ghost green = new Ghost(ghostGreen, height - 2, 1);
        matrix[height - 2][width - 2] = ghostGrey;
        Ghost grey = new Ghost(ghostGrey, height - 2, width - 2);
        matrix[height / 2][width / 2] = ghostRed;
        Ghost red = new Ghost(ghostRed, height / 2, width / 2);
        ghosts.add(brown);
        ghosts.add(green);
        ghosts.add(grey);
        ghosts.add(red);
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).setGb(this);
            ghosts.get(i).start();
        }
    }
    public void movement() {
        int x = pacmanX + directionUD;
        int y = pacmanY + directionRL;
        matrix[pacmanY][pacmanX] = empty;
        if (matrix[y][x] != wall) {
            if(matrix[y][x] == bonus50){
                score += 50;
            }else if(matrix[y][x] == bonusHp){
                if(hearts < 3){
                    hearts++;
                }
            }else if(matrix[y][x] == bonusSpeed) {
                if (bSpeed) {
                    speedBonus();
                }
            } else if (matrix[y][x] == bonusKilling) {
                if(bKilling){
                    bKilling = false;
                    int dx, dy;
                    Ghost g = ghosts.get(0);
                    dx = g.x;
                    dy = g.y;
                    g.stop();
                    matrix[dy][dx] = point;
                    ghosts.remove(0);
                }
            } else if (matrix[y][x] == bonusShield) {
                if(bShield){
                    shieldBonus();
                }
            }
            scoreCount();
            if (directionUD == 1) {
                if (moving) {
                    matrix[y][x] = pacmanDown;
                } else {
                    matrix[y][x] = pacmanClosedRight;
                }
            } else if (directionUD == -1) {
                if (moving) {
                    matrix[y][x] = pacmanUp;
                } else {
                    matrix[y][x] = pacmanClosedLeft;
                }
            } else if (directionRL == 1) {
                if (moving) {
                    matrix[y][x] = pacmanRight;
                } else {
                    matrix[y][x] = pacmanClosedRight;
                }
            } else if (directionRL == -1) {
                if (moving) {
                    matrix[y][x] = pacmanLeft;
                } else {
                    matrix[y][x] = pacmanClosedLeft;
                }
            }
            if(matrix[y][x] == ghostBrown || matrix[y][x] == ghostGreen || matrix[y][x] == ghostGrey || matrix[y][x] == ghostRed){
                if(!shieldActive) {
                    reset();
                }else{
                    shieldActive = false;
                }
            }
        pacmanX = x;
        pacmanY = y;
        moving = !moving;
        field.repaint();
        }else{
            x = pacmanX;
            y = pacmanY;
            if (directionUD == 1) {
                if (moving) {
                    matrix[y][x] = pacmanDown;
                } else {
                    matrix[y][x] = pacmanClosedRight;
                }
            } else if (directionUD == -1) {
                if (moving) {
                    matrix[y][x] = pacmanUp;
                } else {
                    matrix[y][x] = pacmanClosedLeft;
                }
            } else if (directionRL == 1) {
                if (moving) {
                    matrix[y][x] = pacmanRight;
                } else {
                    matrix[y][x] = pacmanClosedRight;
                }
            } else if (directionRL == -1) {
                if (moving) {
                    matrix[y][x] = pacmanLeft;
                } else {
                    matrix[y][x] = pacmanClosedLeft;
                }
            }
            if(matrix[y][x] == ghostBrown || matrix[y][x] == ghostGreen || matrix[y][x] == ghostGrey || matrix[y][x] == ghostRed){
                reset();
            }
            moving = !moving;
            field.repaint();
        }
    }
    private void scoreCount(){
        if(matrix[pacmanY + directionRL][pacmanX + directionUD] == point){
            score++;
            allPoints--;
        }
    }

    public void setGm(GameWindow gm) {
        this.gm = gm;
    }
    public void reset(){
        hearts --;
        pacmanX = 1;
        directionUD = 0;
        pacmanY = 1;
        directionRL = 0;
    }
    private void speedBonus(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                bSpeed = false;
                speed = 125;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                speed = 250;
                bSpeed = true;
            }
        }).start();
    }
    private void shieldBonus(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                bShield = false;
                shieldActive = true;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                shieldActive = false;
                bShield = true;
            }
        }).start();
    }


}
