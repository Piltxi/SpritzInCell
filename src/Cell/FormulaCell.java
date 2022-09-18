package Cell;

import java.util.Vector;

/**
 * Title: FormulaCell
 * Classe specializzata per le espressioni numeriche o tra celle
 */

public class FormulaCell extends Cell<Double>{
    
    static int nOperands = 2;
    Vector<Double> operandValues;
    OperType operator;
    
    /**
     * Metodo Costruttore - FormulaCell
     * @param row numero di riga
     * @param col numero di colonna
     * @param value valore da copiare nella cella
     * @param matrixCells matrice di celle
     * @throws Exception se valore non è valido
     */

    public FormulaCell(int row, int col, String value, Cell<?>[][] matrixCells) throws Exception {
        super(row, col, value, matrixCells);
    }

    @Override
    public boolean isNumeric() { return true; }
    
    /**
     * Viene restituito il risultato dell'operazione identificata
     * @param value contenuto cella
     * @return risultato espressione inserita
     * 
     */

    @Override
    public Double convert (String value) {
        if(operator == OperType.Addition)
            return operandValues.firstElement() + operandValues.lastElement();
        
            return operandValues.firstElement() - operandValues.lastElement(); 
    }

    public boolean isValueValid(String s) {
        if(isValueDouble(s) || isValueAddableCell(s))
            return true;
        return false;
    }
    /**
     * @param string stringa contenente il numero
     * @return  true se value è double
     */
    public boolean isValueDouble(String string) {
        try {
            Double a = Double.parseDouble(string);
            operandValues.add(a);
            return true;
        } catch (Exception e) { return false; }
        
    }
    /**
     * verifica stringa sommabile
     * @param s stringa 
     * @return  se la stringa passata in input è sommabile 
     */
    public boolean isValueAddableCell(String s) {
        try {
            int column = s.charAt(0) - 'A';
            
            if(column < 0 || column >= 26)
                return false;
            
            try {
                try {
                    int row = Integer.parseInt(s.substring(1))-1;
                    if(row < 0 || row >= 100)
                         return false;
                    
                     if(matrixCells[row][column].isNumeric()) {
                        try {
                            Double info = Double.parseDouble(matrixCells[row][column].getInfo().toString());
                            operandValues.add(info);
                            return true;
                        } catch (Exception e) {}                        
                    }
    
                    return false;
                } catch (Exception e) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 
     * L'espressione nella cella viene analizzata in questo modo: 
     * 1. viene prelevato il primo carattere della stringa
     * 2. vengono contati gli operatori
     * 3. viene analizzata l'operazione: se addizione o sottrazione
     * 4. viene analizzato ogni carattere della stringa
     *
     * @return true se la stringa è valida
     * @param value stringa con dato
     */

    @Override
    public boolean isValid(String value) {
        
        operandValues = new Vector<Double>();

        if (value.toString().startsWith("=")) {
            
            String [] parts = value.substring(1).split("[+-]");
            
            if(parts.length != 2)
                return false;
            
                if(value.contains("+"))
                operator = OperType.Addition;
            else   
                operator = OperType.Subtraction; 
            
                for (String x : parts) 
                    if(!isValueValid(x))
                        return false;
                
            return true;
        }
        return false;
    }
}