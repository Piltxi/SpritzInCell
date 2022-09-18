package Graphics;

import javax.swing.JTable;

public class RowJTable extends JTable{
    public RowJTable() {
        super(new RowJTableModel());
        getColumnModel().getColumn(0).setPreferredWidth(30);
        setRowHeight(0, 21);
    }
}
