import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameBoard extends JPanel {
    private Dimension dimension;
    private final Font smallFont = new Font("Times New Roman", Font.BOLD, 15);
    int width, height;
    int direction = 0;
    int [][] matrix;

    JTable field;
    int pacmanX, pacmanY;

    public GameBoard(int w, int h) {
        this.width = w;
        this.height = h;
        generatingMatrix();
        field = new JTable(new GameMap(matrix));
        setVisible(true);
        setBackground(Color.BLACK);

        field.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {

            }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if(hasFocus){
//                    return new PacmanPanel(0, 0, 0, 600/matrix[0].length);
//                }

                if(value instanceof Integer){
                    int intValue = (Integer) value;

                    int size = 2;
                    if(intValue == 0){
                        c.setPreferredSize(new Dimension(size, size));
                        c.setBackground(Color.RED);

                    }else if(intValue == 2){
                        boolean mouth = true;
                        return new PacmanPanel(direction, 0, 0, 600/matrix[0].length, mouth);
                    }
                    else{
                        c.setPreferredSize(new Dimension(size, size));
                        c.setBackground(Color.blue);
                    }
                }
                return c;
            }
        });
        add(field);
        field.setTableHeader(null);
        field.setBackground(Color.blue);
        field.setPreferredSize(new Dimension(600, 600));
        field.setRowHeight(600/matrix[0].length);
        for (int i = 0; i < field.getColumnCount(); i++) {
            field.getColumnModel().getColumn(i).setPreferredWidth(600/matrix.length);
        }
        field.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    private void generatingMatrix(){
        Random rn = new Random();
        matrix = new int[height][width];
        int checkerHor = 0;
        int size;
        if(width > height){
            size = width - height / 2;
        }else{
            size = height - width / 2;
        }
        int maxWall;
        if(size < 20){
            maxWall = size;
        } else if (size < 30) {
            maxWall = size * 2;
        }else if (size < 40) {
            maxWall = size * 3;
        }else if (size < 50) {
            maxWall = size * 4;
        }else if (size < 60) {
            maxWall = size * 5;
        }else if (size < 70) {
            maxWall = size * 6;
        }else if (size < 80) {
            maxWall = size * 7;
        }else if (size < 90) {
            maxWall = size * 8;
        }else if (size < 100) {
            maxWall = size * 9;
        }else{
            maxWall = size * 10;
        }

        for (int i = 0; i < width; i++) {
            matrix[0][i] = 0;
        }


        for (int i = 1; i < height - 1; i++) {
            matrix[i][0] = 0;
            for (int j = 1; j < width - 1; j++) {
                if(maxWall == 0){
                    matrix[i][j] = 1;
                    continue;
                }
                if(matrix[i][j - 1] == 0){
                    checkerHor++;
                }
                if( checkerHor == 4){
                    matrix[i][j] = 1;
                    checkerHor = 0;
                }
                int gen = rn.nextInt(10);
                if(gen == 9){
                        matrix[i][j] = 0;
                        checkerHor++;
                        maxWall--;

                }else{
                    matrix[i][j] = 1;
                    checkerHor = 0;
                }
            }
            matrix[i][width - 1] = 0;
        }


        for (int i = 0; i < width; i++) {
            matrix[height - 1][i] = 0;
        }
        int pacmanPosition = 1;
        while(matrix[1][pacmanPosition] != 1){
            pacmanPosition ++;
        }
        matrix[1][pacmanPosition] = 2;
        pacmanX = 1;
        pacmanY = pacmanPosition;
    }
//    public void changingMatrixx(){
//        if(matrix[pacmanX][pacmanY + 1] != 0){
//            matrix[pacmanX][pacmanY] = 1;
//            matrix[pacmanX][pacmanY + 1] = 2;
//            pacmanY ++;
//        };
//
//    }
}
