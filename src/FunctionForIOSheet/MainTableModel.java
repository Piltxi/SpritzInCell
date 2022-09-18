package FunctionForIOSheet;

import Cell.*;

import javax.swing.table.AbstractTableModel;

/**
 * title: MainTableModel 
 * classe per impostare modello della tabella
 */

public class MainTableModel extends AbstractTableModel {
    private Cell<?>[][] matrixCells;

    private char[] ColName = null;
    private int numRow;

    /**
     * Costruttre di MainTable 
     * @param matrixCells matrice di celle
     * @param numRow numero di righe
     * @param numCol numero di colonne
     */
    public MainTableModel(Cell<?>[][] matrixCells, int numRow, int numCol) {
        super();
        this.matrixCells = matrixCells;
        this.numRow = numRow;
        ColName = setColname(numCol);
    }

    /**
     * Visualizzazione vettore caratteri colonne
     * @return caratteri colonne
     */
    public char[] getColName() { return ColName; }

    /**
     * Visualizzazione conteggio colonne
     * @return numero colonne
     */
    public int getColumnCount() { return ColName.length; }

    /**
     * Visualizzazione conteggio righe
     * @return numero righe
     */
    public int getRowCount() { return numRow; }

    /**
     * Visualizzazione valore con riga e colonna
     * @param row numero riga
     * @param col numero colonna
     * @return valore desiderato
     */
    public Object getValueAt(int row, int col) { 
        return matrixCells[row][col].getValue(); 
    }

    /**
     * Trasformazione in una cella editabile
     * @param row numero riga
     * @param col numero colonna
     * @return cella editabile
     */
    public boolean isCellEditable(int row, int col) { return true; }
    
    /**
     * Settaggio di un valore nella cella date le coordinate
     * @param value valore da immettere
     * @param col numero colonna
     * @param row numero riga
     */
    public void setValueAt(Object value, int row, int col) {
        setCell(value.toString(), row, col);
        verifyChanges();
        fireTableDataChanged();
    }

    /**
     * Settaggio dei caratteri
     * @param col numero colonne
     * @return array con i caratteri [nomi di colonne]
     */
    private char[] setColname(int n) {
        char[] c = new char[n];
        char temp = 'A';
        
        for(int i = 0; i < n; i++) 
            c[i] = temp++;
       
        return c;
    }
    
    /**
     * Settaggio di un valore data la tipologia di informazione 
     * @param value valore da immettere
     * @param col numero colonna
     * @param row numero riga
     */
    public void setCell(String value, int row, int col) {
        try {
            matrixCells[row][col] = new NumericCell(row, col, value, matrixCells);
        } catch (Exception e1) {
            try {
               matrixCells[row][col] = new FormulaCell(row, col, value.replace("", "").toUpperCase(), matrixCells);
               matrixCells[row][col] = new FormulaCell(row, col, value, matrixCells);
            } catch (Exception e2) {
                try {
                    matrixCells[row][col] = new TextualCell(row, col, value, matrixCells);
                } catch (Exception e3) {}
            }
        }
    }

    /** 
     * Copia dei valori nelle celle
     */
    public void verifyChanges() {
        for(int i = 0; i < this.getRowCount(); i++) {
            for(int j = 0; j < this.getColumnCount(); j++) {
                String v = matrixCells[i][j].getValue();
                setCell(v, i, j);
            }
        }
    }

    /** 
     * Copia dei valori nella matrice da array generato da file
     * @param s
     */
    public void setMatrix(String[] values) {
        int counter = 0;
        for(int i = 0; i < this.getRowCount(); i++) {
            for(int j = 0; j < this.getColumnCount(); j++) {
                String v = values[counter];
                setCell(v, i, j);
                counter++;
            }
        }
        verifyChanges();
        fireTableDataChanged();
    }

    /**
     * 
     * @param x carattere che indica la colonna da riordinare
     * @param flag indica tipologia di ordine: crescente o decrescente 
     */

    public void orderColumn(Character x, boolean flag) {
        
        int column = x - 'A';
        
        for(int i = 0; i < this.getRowCount(); i++) 
            for(int j = 0; j < this.getRowCount(); j++)  {
                String s1 = matrixCells[i][column].getValue(); 
                String s2 = matrixCells[j][column].getValue();
                
                if(flag) {
                    if(s1 != "" && s2 != "" && s1.compareTo(s2) > 0) {
                        try {
                            matrixCells[j][column] = new TextualCell(j, column, s1, matrixCells);
                            matrixCells[i][column] = new TextualCell(i, column, s2, matrixCells);
                        } catch (Exception e) {}
                    }
                } 
                else {
                    if(s1 != "" && s2 != "" && s1.compareTo(s2) < 0) {
                        try {
                            matrixCells[j][column] = new TextualCell(j, column, s1, matrixCells);
                            matrixCells[i][column] = new TextualCell(i, column, s2, matrixCells);
                        } catch (Exception e) {}
                    }
                }
            }
        verifyChanges();
        fireTableDataChanged();
    }
    
}
