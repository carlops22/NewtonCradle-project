 
package proyectonewton;

/**
 *
 * @author Carlos Andres Venegas Aguayo
 */
public class Proxypanel {
    private static Proxypanel instancia=null;

    /**
     *
     */
    protected PanelSimulador pc;
    private Proxypanel(){
        pc=null;
    }

    /**
     *
     * @param pc  : puntero al panel
     */
    public void setPanelSimulador(PanelSimulador pc){
        this.pc=pc;
    }   

    /**    
     *
     * @return : instancia del panel simulador para utilizar sus metodos desde otra clase
     */
    public static Proxypanel getInstancia(){
        if(instancia==null)instancia=new Proxypanel();
        return instancia;    
    }
}
