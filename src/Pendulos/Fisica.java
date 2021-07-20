
package Pendulos;

import java.awt.Point;

/**
 *
 * @author Carlos Andres Venegas Aguayo
 */
public class Fisica { //fisica de
    
    /**
     *
     */
    public static final double G=3;
    private static final double m=8;

    /** Detector de colision entre dos esferas
     *
     * @param c1 : primera esfera
     * @param c2 : segunda esfera
     * @return : booleano si detecta colision
     */
    public static boolean isCollision(Esfera c1, Esfera c2){ 
    // funcion de deteccion de colisiones, a partir de 2 esferas, retorna verdadero
    //si la distancia entre sus centros es menor a sus radios
        Point p1= c1.getXY();
        Point p2= c2.getXY();
        return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) <= Math.pow(c1.radio + c2.radio, 2);
    }

    /**  Calcula las velocidades resultantes de una colision y actualiza sus posiciones
     *   
     *
     * @param c1 : primera esfera para colision
     * @param c2 : segunda esfera para colision
     */
    public static void colision(Esfera c1, Esfera c2){
        if(isCollision(c1, c2)){
            c1.move=true;
            c2.move=true;
            double v1 = c1.angulo_v*Esfera.largo;
            double v2= c2.angulo_v*Esfera.largo;
            double v1Prime = (2*m*v2) / (2*m);
            double v2Prime = (2*m*v1) / (2*m);
            
            if( Math.abs(v1-v2) < G) {
                return;
            }
            
            c1.angulo_v = v1Prime/Esfera.largo ;
            if(c1.angulo_v==0)
                c1.angulo=0;
            c1.angulo+=c1.angulo_v;
            c1.update_location();
       
            c2.angulo_v = v2Prime/Esfera.largo ;
            if(c2.angulo_v==0)
                c2.angulo=0;
            c2.angulo+=c2.angulo_v;
            c2.update_location();
    }
    }
            
    /**  Calcula la fuerza resultante del movimiento de un pendulo
     *
     * @param ang :angulo del pendulo
     * @return
     */
    public static double fuerza(double ang){
        double fuerza=G*Math.sin(ang);
        return fuerza;
    }  

    /**
     *         Calcula la aceleracion resultante del movimiento de un pendulo
     * @param ang :angulo del pendulo
     * @return
     */
    public static double aceleracion(double ang){
        double aceleracion=(-1*fuerza(ang))/Esfera.largo;
        return aceleracion;
    }
    
}
