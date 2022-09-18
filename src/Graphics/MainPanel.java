package Graphics;

import Cell.*;
import FunctionForIOSheet.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*; 
import java.awt.event.*;
import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.filechooser.FileSystemView;

/**
 * @author Elia Pitzalis 152541
 * Title: MainPanel 
 * caratteristiche panello principale
 */

public class MainPanel extends JPanel{
    
    public MainPanel () {

        Timestamp ts = Timestamp.from(Instant.now());
        String defaultFilePath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath().toString() + "/" + "SIC -"+ts.toString().replace(".", "_").replace(":", "_")+ ".spritz";

        System.out.println(defaultFilePath); 

        int numCol = 26;
        int numRow = 100;
        Cell<?>[][] matrixCells = new Cell[numRow][numCol];

        for(int i = 0; i < numRow; i++) {
            for(int j = 0; j < numCol; j++)
                try {
                    matrixCells[i][j] = new TextualCell(i, j, "", matrixCells);
                } catch (Exception e) {} 
        }

        MainTableModel dataModel = new MainTableModel (matrixCells, numRow, numCol);
        
        JTable mainTable = new MainJTable (dataModel);
        JScrollPane scrollPane = new JScrollPane(mainTable); //settaggio pannello scroll
        JTable rowTable = new RowHeader(mainTable); //settaggio pannello sinistro 
        ViewCell _viewCell = new ViewCell (matrixCells); //settaggio label per singola cella
        
        /**
         * settaggio mouseEventListener per evidenziare cella selezionata
         * vengono ricavate le coordinate della matrice [][]
         * il valore della matrice viene castato in una stringa per essere inserita nella JTextField 
         */
        mainTable.addMouseListener (new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {

                    final JTable jTable = (JTable)e.getSource();
                    
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                
                    _viewCell.setText(jTable.getColumnName(column)+(row+1)+" = "+ matrixCells[row][column].getInfo().toString());
                }
            }
        });

        setLayout(new BorderLayout());
        
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,rowTable.getTableHeader());

        //componenti scelta interazione con celle
        JButton orderButton = new JButton("Order");
        JCheckBox descending = new JCheckBox("descending");

        JLabel orderLabel = new JLabel ("Order column:");
        Character col[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        JComboBox<Character> orderlist = new JComboBox<Character>(col);
        
        //CheckBox per selezionare il salvataggio automatico
        JCheckBox autosave = new JCheckBox("autosave");

        //Componenti grafici per impostare il menu a tendina option for file
        JMenu m1 = new JMenu("Option for File         ");
        m1.setBackground(Color.red);
        JMenuItem m11_open = new JMenuItem("Open File..."); 
        JMenuItem m12_save = new JMenuItem("Save File..."); 
        m1.add(m11_open); m1.add(m12_save);
        JMenuBar mb = new JMenuBar (); 
        mb.add(m1); 

        JPanel northPanel = new JPanel (); 
        northPanel.setLayout(new BorderLayout());

        //Pannello con primi componenti
        JPanel _grid = new JPanel();
        _grid.setBackground(Color.RED);
        
        _grid.setLayout(new GridLayout(1, 6));
       
        _grid.add(mb); 
        _grid.add(orderButton); 
        _grid.add(descending); 
        _grid.add(orderLabel); 
        _grid.add(orderlist); 
        _grid.add(autosave); 
                
        //Aggiunta dei componenti al pannello principale
        northPanel.add(_grid, BorderLayout.NORTH);
        northPanel.add(_viewCell, BorderLayout.SOUTH); 


        this.add(northPanel, BorderLayout.NORTH); 
        this.add(scrollPane, BorderLayout.CENTER); 

        orderButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                dataModel.orderColumn((Character) orderlist.getSelectedItem(), descending.isSelected());
            } 
        } );

        m11_open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FilterFile());
                int result = fileChooser.showOpenDialog(MainPanel.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ReadIO obR = new ReadIO(selectedFile.getAbsolutePath());
                    String[] s = obR.ReadObjectToFile();
                    ((MainTableModel) dataModel).setMatrix(s);
                }
            } 
        } );

        WriteIO writingStream = new WriteIO(); 

        m12_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FilterFile());
                fileChooser.setSelectedFile(new File("NewFile.spritz"));

                int result = fileChooser.showSaveDialog(MainPanel.this);
                
                if(result == 0) {
                    File fileStream = fileChooser.getSelectedFile();
                    int ConfirmationResult = 0;
                    
                    if(fileStream.isFile())
                        ConfirmationResult = JOptionPane.showConfirmDialog(fileChooser,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_OPTION);
                    
                    if(ConfirmationResult == 0) {
                        File selectedFile = fileChooser.getSelectedFile();
                        writingStream.setPath(selectedFile.getAbsolutePath());
                        writingStream.WriteObjectToFile(matrixCells);
                        File myObj = new File(defaultFilePath); 
                        myObj.delete();
                    }
                }
            } 
        }); 

        autosave.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(!autosave.isSelected()) {
                    File myObj = new File(defaultFilePath); 
                    myObj.delete();
                }
            }
        });

        Timer timer = new Timer();
        WriteIO wrAutoSave = new WriteIO (defaultFilePath);
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(autosave.isSelected()) {
                    wrAutoSave.WriteObjectToFile(matrixCells);
                }
            }
        }, 0, 2000);

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
            if(autosave.isSelected()) {
                File myObj =  new File(defaultFilePath); 
                ReadIO obR = new ReadIO(myObj.getAbsolutePath());
                String[] s = obR.ReadObjectToFile();
                ((MainTableModel) dataModel).setMatrix(s);
                if(new File(writingStream.getPath()).isFile()){
                    new File(writingStream.getPath()).delete();
                    WriteIO wrAutoSave = new WriteIO(writingStream.getPath());
                    wrAutoSave.WriteObjectToFile(matrixCells);
                }
            }
            File myObj = new File(defaultFilePath); 
            myObj.delete();
            }
        });

    }

}
