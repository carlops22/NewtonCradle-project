
package proyectonewton;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Carlos Andres Venegas Aguayo
 */
public class Ventana extends JFrame {

    /**
     *
     * @throws IOException
     */
    public Ventana() throws IOException{
        EsferaHolder eh= new EsferaHolder();
        this.setLayout(new BorderLayout());
        PanelSimulador pc = new PanelSimulador(eh);
        this.add(pc,BorderLayout.CENTER);
        
        PanelSur ps = new PanelSur(eh);
        this.add(ps,BorderLayout.SOUTH);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setVisible(true);        
    }
}
