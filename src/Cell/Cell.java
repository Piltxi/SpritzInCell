package Cell;

/**
 * Title: Cell
 * Classe astratta per specializzare i tipi di informazione che possono contenere le celle
 */

public abstract class Cell<TypeofCellData>{
    
    int row;
    int col;
    String value;
    TypeofCellData info;
    Cell<?>[][] matrixCells;
   
    /**
     * Metodo Costruttore - Cell
     * @param row numero di riga
     * @param col numero di colonna
     * @param value valore da copiare nella cella
     * @param matrixCells matrice di celle
     * @throws Exception se valore non è valido
     */

    public Cell(int row, int col, String value, Cell<?>[][] matrixCells) throws Exception{
        
        this.matrixCells = matrixCells;
        setRow(row);
        setCol(col);
        
        if(!isValid(value))
            throw new Exception("");
        else
            setInfoAndValue(value);
    }

    /**
     * Settaggio del numero di riga
     * @param row numero di riga
     */
    public void setRow(int row) { this.row = row; }

    /**
     * Settaggio del numero di colonna
     * @param row numero di colonna
     */
    public void setCol(int col) { this.col = col; }

    /**
     * Settaggio del valore
     * @param value valore da inserire
     */
    private void setValue(String value) { this.value = value; }

    /**
     * Settaggio tipo di dato all'interno del generic
     * @param info tipologia del dato
     */
    private void setInfo(TypeofCellData info) { this.info = info; }

    
    /** 
     * Inserimento valore | tipologia di informazione
     * @param value valore
     */
    public void setInfoAndValue(String value) {
        setValue(value); 
        setInfo(convert(value)); 
    }

    /** 
     * Lettura numero riga
     * @return row numero riga
     */
    public int getRow() { return this.row; }

    /** 
     * Lettura numero colonna
     * @return col numero colonna
     */
    public int getCol() { return this.col; }

    /** 
     * Lettura valore
     * @return valore cella
     */
    public String getValue() { return this.value; }

    /** 
     * Lettura tipo di informazione
     * @return tipo info cella
     */
    public TypeofCellData getInfo() { return info; }

    /** 
     * Definizione del metodo per verificare se il valore è NUMERICO
     * @return true se il valore è numerico
     */
    public abstract boolean isNumeric();

    /** 
     * Definizione del metodo per verificare se il valore è NUMERICO
     * @return true se il valore è valido
     */
    public abstract boolean isValid(String value);

     /** 
     * Definizione del metodo per verificare il tipo del dato
     * @return true se il valore è numerico
     */
    public abstract TypeofCellData convert(String value);
    
     /** 
     * Definizione del metodo per stampare il valore
     */
    public String toString() { return value+System.lineSeparator(); }
}
