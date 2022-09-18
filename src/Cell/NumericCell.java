package Cell;

/**
 * Title: NumericCell
 * Classe specializzata per gestire dati numerici [double]
 */

public class NumericCell extends Cell<Double>{
    
        /**
     * Metodo Costruttore - NumericCell
     * @param row numero di riga
     * @param col numero di colonna
     * @param value valore da copiare nella cella
     * @param matrixCells matrice di celle
     * @throws Exception se valore non è valido
     */
    public NumericCell(int row, int col, String value, Cell<?>[][] matrixCells) throws Exception {
        super(row, col, value, matrixCells);
    }

    @Override
    public boolean isNumeric() { return true; }

    /**
     * @param value valore cella
     * @return casting del dato in double
     */
    @Override
    public Double convert(String value) { return Double.parseDouble(value); }

    /**
     * @param value valore cella
     * @return true per dato valido
     */
    @Override
    public boolean isValid(String value) {
        try {
            Double.parseDouble(value.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}