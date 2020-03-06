package principal;
import Controlador.Control;
import Modelo.modelo;
import Vista.Login;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 *
 * @author Steven Sandoval
 */
public class Principal {
    public static void main (String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
            modelo m = new modelo();
            Login v =new Login();
            Control c =new Control(m,v);
            c.iniciar();
    }
}
