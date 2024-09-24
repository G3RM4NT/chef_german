package controlador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Modelo.Chef;
import vista.frmchef;



/**
 *
 * @author German G
 */
public class ctrlChef  implements MouseListener, KeyListener {
    
    
      private Chef modelo;
    private frmchef vista;
   
    //2- Crear el constructor
    public ctrlChef(Chef modelo, frmchef vista){
        this.modelo = modelo;
        this.vista = vista;
        
         vista.btnagregar.addMouseListener(this);   
        modelo.mostrar(vista.jTablechef);
        vista.btneliminar.addMouseListener(this);
        vista.jTablechef.addMouseListener(this);
        vista.btneditar.addMouseListener(this);
      
}

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.btnagregar){
            modelo.setNombre(vista.txtnombre.getText());
            modelo.setEdad_chef(Integer.parseInt(vista.txtedad.getText()));
            modelo.setPeso_chef(Integer.parseInt(vista.txtpeso.getText()));
            modelo.setCorreo_chef(vista.txtcorreo.getText());
           
            
            modelo.Guardar();
            modelo.mostrar(vista.jTablechef);
        }
        
         if(e.getSource() == vista.btneliminar){
            modelo.Eliminar(vista.jTablechef);
            modelo.mostrar(vista.jTablechef);
        }
         
           if(e.getSource() == vista.jTablechef){
            modelo.cargarDatosTabla(vista);
        }
           
           if(e.getSource() == vista.btneditar){
            modelo.setNombre(vista.txtnombre.getText());
            modelo.setEdad_chef(Integer.parseInt(vista.txtedad.getText()));
             modelo.setPeso_chef(Integer.parseInt(vista.txtpeso.getText()));
            modelo.setCorreo_chef(vista.txtcorreo.getText());
            
            modelo.Actualizar(vista.jTablechef);
            modelo.mostrar(vista.jTablechef);
        }
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
   
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}