
 
package Modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import vista.frmchef;



/**
 *
 * @author German G
 */
public class Chef {
    
    private String UUID_chef;
       private String Nombre_chef;
          private int Edad_chef;
             private int Peso_chef;
                private String Correo_chef;
    
    public String getUUID_chef() {
        return UUID_chef;
    }
    
      public void setuuid_chef(String UUID_chef) {
        this. UUID_chef =  UUID_chef;
        
    }
      
        public String getNombre_chef() {
        return Nombre_chef;
    }

    public void setNombre(String Nombre_chef) {
        this.Nombre_chef = Nombre_chef;
    }
    
    
     public int getEdad_chef() {
        return Edad_chef;
    }

    public void setEdad_chef(int Edad_chef) {
        this.Edad_chef = Edad_chef;
    }
    
     public int getPeso_chef() {
        return Peso_chef;
    }

    public void setPeso_chef (int Peso_chef) {
        this.Peso_chef =Peso_chef;
    }
    
      public String getCorreo_chef() {
        return  Correo_chef;
    }

    public void setCorreo_chef(String Correo_chef) {
        this. Correo_chef =  Correo_chef;
    }
    
    
     public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addnewcheff = conexion.prepareStatement("INSERT INTO tbChef(UUID_chef, Nombre_chef, Edad_chef, Peso,Correo_chef) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addnewcheff.setString(1, UUID.randomUUID().toString());
            addnewcheff.setString(2, getNombre_chef());
            addnewcheff.setInt(3, getEdad_chef());
            addnewcheff.setInt(4, getPeso_chef());
            addnewcheff.setString(5, getCorreo_chef());
 
            //Ejecutar la consulta
            addnewcheff.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo: metodo guardar " + ex);
        }
    }
     
     
      public void mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_chef", "Nombre_chef", "Edad_chef", "Peso", "Correo_chef"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbChef");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_chef"), 
                    rs.getString("Nombre_chef"), 
                    rs.getInt("Edad_chef"), 
                     rs.getInt("Peso"), 
                    rs.getString("Correo_chef")});
            }
            //Asignamos el nuevo modelo lleno a la tablaf
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
      
      
          public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbChef where UUID_chef = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
          
          
            public void cargarDatosTabla(frmchef vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jTablechef.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jTablechef.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jTablechef.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jTablechef.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jTablechef.getValueAt(filaSeleccionada, 3).toString();
             String CorreoDeTb = vista.jTablechef.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtnombre.setText(NombreDeTB);
            vista.txtedad.setText(EdadDeTb);
            vista.txtpeso.setText(PesoDeTB);
              vista.txtedad.setText(EdadDeTb);
              vista.txtcorreo.setText(CorreoDeTb);
            
        }
    }
       
    
              public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbChef set Nombre_chef= ?, Edad_chef = ?, Peso = ?, Correo_chef = ? where UUID_chef = ?");

                updateUser.setString(1, getNombre_chef());
                updateUser.setInt(2, getEdad_chef());
                updateUser.setInt(3, getPeso_chef());
                 updateUser.setString(4, getCorreo_chef());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
            
}
