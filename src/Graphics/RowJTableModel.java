package Graphics;

import javax.swing.table.DefaultTableModel;

public class RowJTableModel extends DefaultTableModel{
    
    public RowJTableModel() { super(); }

    public int getColumnCount() { return 1; }

    public int getRowCount() { return 100; }

    public Object getValueAt(int row, int col) {
        if(row==0)
            return "";
        return row;
    }

    public boolean isCellEditable(int row, int col) { return false; }

    public void setValueAt(Object value, int row, int col) {}
}
