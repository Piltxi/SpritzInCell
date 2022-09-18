package FunctionForIOSheet;
import java.io.FileWriter;
import java.io.IOException;

import Cell.Cell;

public class WriteIO {
    private String path;
    /**
     * Costruttore di WriteObjectIO
     * @param path contiene il path del file su cui scrivere
     */
    public WriteIO(String path) {        
        this.path = path;
    }
    /**
     * ritorna il path del file su cui scrivere
     * @return path del file su cui scrivere
     */
    public String getPath() {
        return path;
    }
    /**
     * Costruttore di WriteObjectIO()
     */
    public WriteIO() { 
        path = "";
    }
    /**
     * Setta il path del file su cui si deve scrivere
     * @param path path del file
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * Scrive sul file la matrice di celle passate come parametro
     * @param m matrice di celle da passare come parametro
     */
    public void WriteObjectToFile(Cell<?>[][] m) {
        try {  
            FileWriter myWriter = new FileWriter(path);
            for(int i = 0; i < 100; i++) {
                for(int j=0; j < 26; j++) {
                    myWriter.write( m[i][j].toString());
                }
            }
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}

