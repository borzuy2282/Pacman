import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Random;

public class GameBoard extends JPanel {
    boolean ctrl, enter, p;
    GameWindow gm;
    int score = 0;
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

//    int [][] matrixInt;
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
//        field.setCellSelectionEnabled(false);
//        field.setFocusable(false);

        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
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
                movement();
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

                } else if (e.getKeyCode() == KeyEvent.CTRL_DOWN_MASK) {
                    ctrl = true;
                } else if (e.getKeyCode() == KeyEvent.SHIFT_DOWN_MASK) {
                    enter = true;
                } else if (e.getKeyCode() == KeyEvent.VK_P) {
                    p = true;
                }

            }
        });
    }
//    private void generatingMatrix(){
//        Random rn = new Random();
//        matrix = new int[height][width];
//        int checkerHor = 0;
//        int size;
//        if(width > height){
//            size = width - height / 2;
//        }else{
//            size = height - width / 2;
//        }
//        int maxWall = height * width / 6;
////        if(size < 20){
////            maxWall = size;
////        } else if (size < 30) {
////            maxWall = size * 2;
////        }else if (size < 40) {
////            maxWall = size * 3;
////        }else if (size < 50) {
////            maxWall = size * 4;
////        }else if (size < 60) {
////            maxWall = size * 5;
////        }else if (size < 70) {
////            maxWall = size * 6;
////        }else if (size < 80) {
////            maxWall = size * 7;
////        }else if (size < 90) {
////            maxWall = size * 8;
////        }else if (size < 100) {
////            maxWall = size * 9;
////        }else{
////            maxWall = size * 10;
////        }
//
//        for (int i = 0; i < width; i++) {
//            matrix[0][i] = 0;
//        }
//
//
//        for (int i = 1; i < height - 1; i++) {
//            matrix[i][0] = 0;
//            for (int j = 1; j < width - 1; j++) {
//                if(maxWall == 0){
//                    matrix[i][j] = 1;
//                    continue;
//                }
//                if(matrix[i][j - 1] == 0){
//                    checkerHor++;
//                }
//                if( checkerHor == 4){
//                    matrix[i][j] = 1;
//                    checkerHor = 0;
//                }
//                int gen = rn.nextInt(10);
//                if(gen == 9){
//                        matrix[i][j] = 0;
//                        checkerHor++;
//                        maxWall--;
//
//                }else{
//                    matrix[i][j] = 1;
//                    checkerHor = 0;
//                }
//            }
//            matrix[i][width - 1] = 0;
//        }
//
//
//        for (int i = 0; i < width; i++) {
//            matrix[height - 1][i] = 0;
//        }
//        int pacmanPosition = 1;
////        while(matrix[1][pacmanPosition] != 1){
////            pacmanPosition ++;
////        }
//        matrix[1][pacmanPosition] = 2;
//        pacmanX = 1;
//        pacmanY = pacmanPosition;
//    }
    public void generatingMatrix(){
        Random rn = new Random();
        matrix = new ImageIcon[height][width];
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[0][i] = wall;
            matrix[height - 1][i] = wall;
        }
        int maxWalls = (height * width) / 5;
        int checkerHorizontal = 0;
        for (int i = 1; i < height - 1; i++) {
            matrix[i][0] = wall;
            checkerHorizontal++;
            for (int j = 1; j < width - 1; j++) {
                if(maxWalls == 0){
                    matrix[i][j] = point;
                    continue;
                }
                if(checkerHorizontal == 4){
                    matrix[i][j] = point;
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
                    checkerHorizontal = 0;
                }
            }
            matrix[i][width - 1] = wall;
        }
        matrix[1][1] = pacmanClosedRight;
        pacmanX = 1;
        pacmanY = 1;
    }
    public void movement() {
        int x = pacmanX + directionUD;
        int y = pacmanY + directionRL;

        if (matrix[y][x] != wall) {
            scoreCount();
            matrix[pacmanY][pacmanX] = empty;
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
        pacmanX = x;
        pacmanY = y;
        moving = !moving;
        field.repaint();
        }
    }
    private void scoreCount(){
        if(matrix[pacmanY + directionRL][pacmanX + directionUD] == point){
            score++;

        }
    }

    public void setGm(GameWindow gm) {
        this.gm = gm;
    }

}
