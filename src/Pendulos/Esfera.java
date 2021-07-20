
package Pendulos;


import static geometricas.Angular.*;
import proyectonewton.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.transform.Scale;
import javax.imageio.ImageIO;
/**
 *
 * @author Carlos Andres Venegas Aguayo
 */
public class Esfera {
    private int x,y,width,height;
    private int x_centro,y_centro,x_pendulo,y_pendulo;
    public boolean move=false;
    public static final int largo=250;
    public double angulo=0.0, angulo_v=0.0, angulo_a=0.0;
    /**
     *
     */
    public final int radio=50;
    private Polygon p;
    private Image img;

    /**
     *
     * @param x : posicion x de la imagen de esfera
     * @param y : posicion y de la imagen de esfera
     * @throws IOException
     */
    public Esfera(int x, int y) throws IOException {
        this.x=x; this.y=y;
        x_centro=x+radio; y_centro=y+radio; x_pendulo=x+radio; y_pendulo=0;
        p= new Polygon();
        this.img = ImageIO.read(getClass().getResource("/resources/esfmod.png"));
        img = img.getScaledInstance(radio*2, -1, Image.SCALE_FAST);
        width = img.getWidth(null);
        height= img.getHeight(null);

    }

    /**
     *
     * @param g : para dibujar el pendulo
     */
    public void paint(Graphics g){  
        g.drawLine(x_centro,y_centro,x_pendulo,y_pendulo);
        g.drawImage(img,x,y,null); 

    }

    /**              cambia los valores de XY en base al angulo que se forma entre la nueva posicion y la antigua
     *
     * @param x : nueva posicion x para esfera
     * @param y : nueva posicion y para esfera
     */
    public void setXY(int x, int y){
        angulo(x,y);
        if(y>=0){
            update_location();
        }

        move=true;
    }

    /**       Calcula el angulo que se forma entre el centro de la esfera y la posicion  ingresada respecto a
     *        la base del pendulo
     *
     * @param x
     * @param y
     * @return
     */     
    public void angulo(int x, int y){
        double alto,ancho;   
        
        alto=y;    //con respecto a la vertical
        ancho=x-x_centro;   
        angulo=Math.atan2((double)ancho,(double)alto);   
    }
    
    /**   Funcion que retorna verdadero si esta dentro de la esfera
     *
     * @param x
     * @param y
     * @return
     */
    public boolean containsXY(int x, int y){
        boolean r=false;    
        if(Math.pow(x_centro-x,2)+Math.pow(y_centro-y,2)<Math.pow(radio,2))
            r=true;
        return r;
    }

    /**       
     *
     * @return : retorna punto central de la esfera
     */
    public Point getXY(){
        return new Point(x_centro,y_centro); //retorna la posicion del centro de la esfera
    }
    public boolean enMovimiento(){
        return move;
    }
    
    /** funcion que actualiza todos los parametros fisicos del pendulo       
     *
     * 
     */
    public void update(){
        this.update_location();
        angulo_a=Fisica.aceleracion(angulo);
        angulo_v+=angulo_a;
        angulo+=angulo_v;
        angulo_v*=0.995;
    }
    
    
    /** funcion que actualiza la posicion del pendulo      
     *
     * 
     */
    public void update_location(){
        this.x=(int)(x_pendulo+Math.round(largo*Math.sin(angulo)))-50;
        this.y=(int) (y_pendulo+Math.round(largo*Math.cos(angulo))); 
        x_centro=this.x+radio; y_centro=this.y+radio;
      
    }
   
}
    

