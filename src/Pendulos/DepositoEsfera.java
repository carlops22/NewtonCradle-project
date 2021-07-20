
package Pendulos;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Carlos Andres Venegas Aguayo
 */
public class DepositoEsfera{
    private ArrayList<Esfera> depesf;
    
    /**
     *
     */
    public DepositoEsfera(){
        depesf= new ArrayList();
    }
    
    /**
     *
     * @param x
     * @param y
     * @throws IOException
     */
    public void addEsfera(int x, int y) throws IOException{
        depesf.add(new Esfera(x,y));
    }

    /**
     *
     * @return
     */
    public Esfera remEsfera(){
        if(!depesf.isEmpty())
            return depesf.remove(0);
        return null;
    }

    /**
     *
     * @param i
     * @return
     */
    public Esfera getEsfera(int i){
        if(!depesf.isEmpty())
            return depesf.get(i);
        return null;
    }

    /**
     *
     * @param g
     */
    public void paint(Graphics g){
        for(int i=0;i<depesf.size();i++)
            depesf.get(i).paint(g);
    }
    
    public void update(){
        for(int i=0;i<depesf.size();i++)
            depesf.get(i).update();
    }
    /**
     *
     * @return
     */
    public int size(){
        return depesf.size();
    }
}
