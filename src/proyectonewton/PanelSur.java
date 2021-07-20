
package proyectonewton;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Carlos Andres Venegas Aguayo
 */
public class PanelSur extends JPanel{

    /**
     *
     * @param eh
     */
    public PanelSur(EsferaHolder eh){   
        this.setBackground(Color.green); 
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        SliderSur sh =new SliderSur(1,6,1,eh);
        JLabel sliderLabel = new JLabel("Numero de esferas", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(sliderLabel);
        this.add(sh);

    }   
}

class SliderSur extends JSlider implements ChangeListener{
    private EsferaHolder eh;
    public SliderSur(int min, int max, int initval,EsferaHolder eh){
        super(min,max,initval);
        setMinorTickSpacing(1);  
        setMajorTickSpacing(5); 
        this.eh=eh;
        this.addChangeListener(this);
        setPaintLabels(true);
        setPaintTicks(true);
    }
    public void stateChanged(ChangeEvent e) {  
        if (!getValueIsAdjusting()){
            
            int esferas = getValue();
            eh.setValor(esferas);
            Proxypanel pm= Proxypanel.getInstancia();
            try {
                pm.pc.initDeposito();
            } catch (IOException ex) {
                Logger.getLogger(SliderSur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}