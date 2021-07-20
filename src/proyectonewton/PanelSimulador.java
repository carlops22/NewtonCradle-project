
package proyectonewton;

import static Pendulos.Fisica.*;
import Pendulos.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Carlos Andres Venegas Aguayo
 */
public class PanelSimulador extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
    private EsferaHolder eh;
    private DepositoEsfera depesf;
    private int r=-1;
    private Timer t;
    /**
     *
     * @param eh : holder del numero de esferas a almacenar en el deposito de esferas
     * @throws IOException
     */
    public PanelSimulador(EsferaHolder eh) throws IOException{
        this.eh=eh;
        this.setBackground(Color.yellow);
        Proxypanel pm= Proxypanel.getInstancia();
        pm.setPanelSimulador(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        t = new Timer(10,null);
        t.addActionListener(this);
        depesf= new DepositoEsfera();
        for(int i=0;i<eh.getValor();i++)
            depesf.addEsfera(250+i*100,Esfera.largo);
    }
    public void paint(Graphics g){
        super.paint(g);
        depesf.paint(g);
    }

    /**  funcion que almacena el numero de la esfera que fue agarrada
     *
     * @param x : posicion x
     * @param y : posicion y
     */
    public void agarrarEsfera(int x,int y){
        int size= eh.getValor();
        for(int i=0;i<size;i++){
            Esfera a=depesf.getEsfera(i);
            if(a.containsXY(x,y))
                r=i; 
        }
    }

    /**  Funcion que ejecuta la colision de cada esfera con su antecesora y la siguiente
     *
     */
    public void colisionHandler(){
        for(int i=0;i<depesf.size();i++){
                    if(i<depesf.size()-1){
                        Esfera next=depesf.getEsfera(i+1);
                        Fisica.colision(depesf.getEsfera(i),next);  
                    }
                    if(i>0){
                        Esfera prev=depesf.getEsfera(i-1);
                        Fisica.colision(depesf.getEsfera(i),prev);
            
            }
        }
               
    }

    /** 
     *
     * @return
     */
    public boolean contieneEsfera(){
        boolean rb=false;
        if(r>=0)
            rb=true;
        return rb;  
    }

    /**
     *
     * @throws IOException
     */
    public void initDeposito() throws IOException{
        depesf= new DepositoEsfera();
        int size= eh.getValor();
        for(int i=0;i<size;i++)
            depesf.addEsfera(250+i*100,Esfera.largo);
        repaint();
    }
    public void mouseClicked(MouseEvent e) {

    }
      /**  evento de presionar mouse:
       *   detiene el movimiento de las esferas y las vuelve a su posicion original
     *
     * 
     */
    public void mousePressed(MouseEvent e) {
        r=-1;
        t.stop();
        try {
            initDeposito();
        } catch (IOException ex) {
            Logger.getLogger(PanelSimulador.class.getName()).log(Level.SEVERE, null, ex);
        }
        agarrarEsfera(e.getX(),e.getY());
    }

    
     /**   Evento de soltar el click
     *     empieza la animacion de las esferas
     * 
     */
    public void mouseReleased(MouseEvent e) {
        if(r!=-1)
            t.start();
      
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

     /**    Evento de arrastrar el mouse
     *     arrastra las esferas en la direccion del mouse
     * 
     */
    
    public void mouseDragged(MouseEvent e) {
        if(depesf.size()!=0){
            
            if(contieneEsfera()){
                if(r>0){
                    if(depesf.getEsfera(r).getXY().x>e.getX())
                        for(int i=r;i>=0;i--)
                            depesf.getEsfera(i).setXY((e.getX())-100*((r-i)),(e.getY()));
                    else if(depesf.getEsfera(r).getXY().x+50<e.getX())
                        for(int i=r;i<depesf.size();i++)
                            depesf.getEsfera(i).setXY((e.getX())+100*((i-r)),(e.getY()));
                }
                else{
                    if(depesf.getEsfera(r).getXY().x<e.getX())
                        for(int i=r;i<depesf.size();i++)
                            depesf.getEsfera(i).setXY(e.getX()+100*i,e.getY());
                    else
                        depesf.getEsfera(r).setXY(e.getX(),e.getY());
                }
            }
        }
        repaint();
        
    }
    public void mouseMoved(MouseEvent e) {
    }
    
     /**  evento de accion relacionado al timer:
     *    actualiza las posiciones de las esferas y realiza las colisiones
     * 
     */
    public void actionPerformed(ActionEvent e) {
        depesf.update();
        repaint();
        colisionHandler();
    }



}
