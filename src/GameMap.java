import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class GameMap extends AbstractTableModel {

    int [][] fields;

    public GameMap(int[][] fields) {
        this.fields = fields;
    }

    @Override
    public int getRowCount() {
        return fields[0].length;
    }

    @Override
    public int getColumnCount() {
        return fields.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return fields[columnIndex][rowIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
