package Graphics;

import javax.swing.JTextField;

import Cell.Cell;

/**
 * @author Elia Pitzalis 152541
 * Visualizzazione di una singola cella data la matrice 
 */

public class ViewCell extends JTextField{
   
    Cell<?>[][] matrixCells;

    public ViewCell (Cell<?>[][] matrixCells) {
        
        super();
        
        this.matrixCells = matrixCells;
        setColumns(30);
        setEditable(false);
    }
}