package FunctionForIOSheet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadIO {
    
  String path;
    /**
     * Costruttore di ReadIO
     * @param path percorso del file 
     */
    public ReadIO(String path) { 
        this.path = path;
    }

    /**
     * apertura e lettura file con saltaggio delle informazioni sugli array
     * @return matrice di stringhe con i valori delle celle
     */
    public String[] ReadObjectToFile() {
        String[]s  = new String[100*26];
        
        try {
            
            File streamFile = new File(path);
            
            Scanner myReader = new Scanner(streamFile);
            
            int i = 0;  
            while (i < (100*26) && myReader.hasNextLine()) {
              String data = myReader.nextLine();
              s[i] = data;
              i++;
            }

            myReader.close(); 
            
            return s;
          } catch (FileNotFoundException e) { //eccezione sollevata in caso di errore in fase di lettura file 
            System.out.println("Error | Reading File");
            return s;
          }
    }
}