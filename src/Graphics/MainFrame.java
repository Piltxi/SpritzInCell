package Graphics;

import javax.swing.*;

public class MainFrame extends JFrame {

    MainPanel _panel; 

    public MainFrame() {
        this("SpritzInCell");
    }

    public MainFrame(String title) {
        super(title);

        //impostazione dimensione finestra in apertura
        setBounds(0, 0, 900, 900);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Settaggio pannello principale con componentistica grafica
        MainPanel _panel = new MainPanel();
        
        this.setVisible(true);
        this.add(_panel);
    }
}