package Graphics;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.Color;

public class MainJTable extends JTable{
    /**
     * Costruttore della tabella principale
     * @param data TableModel da dare in input al costruttore
     */
    public MainJTable(TableModel data) {

        super(data);

        this.setBackground(Color.orange);
        this.getTableHeader().setReorderingAllowed(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        //impostazione della selezione delle righe anche in verticale
        this.setCellSelectionEnabled(showVerticalLines);

        for (int i = 0; i < 26; i++) {
            this.getColumnModel().getColumn(i).setMinWidth(10);
            this.getColumnModel().getColumn(i).setMaxWidth(400);
        }        
    }
}
