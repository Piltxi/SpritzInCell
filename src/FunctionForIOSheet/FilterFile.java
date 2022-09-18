package FunctionForIOSheet;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FilterFile extends FileFilter{
    
    /**
     * Costruttore di classe Filter che richiama il costruttore base di FileFilter
     */
    public FilterFile() {
        super();
    }

    /**
     * verifica integrità file: 
     * si verifica che sia un file, e che l'estensione sia .spritz
     * @param file 
     */
    @Override
    public boolean accept(File file) {
        if (file.isFile() && file.getName().endsWith(".spritz"))
            return true;
        return false;
    }

    /**
     * set descrizione file
     */
    @Override
    public String getDescription() {
        return ".spritz (SpritzInCell files)";
    }
}
