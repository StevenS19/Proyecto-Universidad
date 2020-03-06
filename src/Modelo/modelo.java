package Modelo;

import Controlador.Control;
import Vista.Login;
import static Vista.Login.TxtIdE;
import static Vista.Login.TxtNota1;
import static Vista.Login.TxtNota2;
import static Vista.Login.TxtNota3;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Steven Sandoval
 */
public class modelo {
    
   Connection cc;
   Connection cn = Conexion();
   Login v;
   public Connection Conexion(){
       try {
           Class.forName("com.mysql.jdbc.Driver");
           cc= DriverManager.getConnection("jdbc:mysql://localhost/Universidad","root","");
           System.out.println("Conexion Exitosa");
       } catch (Exception e) {
           System.out.println(e);
       }
       return cc;
   } 
   
   public void VC(){
       DefaultTableModel modelo =new DefaultTableModel();
        
        modelo.addColumn("id_grupo");
        //modelo.addColumn("id_asignatura");
        modelo.addColumn("Materia");
        
        v.TBMaterias.setModel(modelo);
        
        String sql="select a.id_grupo ,b. id_asignatura, c.nombre_a from estudiantegrupo a, grupo b, asignatura c where a.id_grupo=b.id_grupo and c.id_asignatura=b.id_asignatura group by id_grupo;";
        String datos[] =new String [3];
        Statement st;
   }
   
   public void limpiar(){
        v.TxtIdE.setText("");
        v.TxtNota1.setText("");
        v.TxtNota2.setText("");
        v.TxtNota3.setText("");
        
    }
   
   public void nuevousuario (){
    try {
        
        String id =v.TxtId.getText();
        String Nombre =v.TxtNombre.getText();
        String Apellido =v.TxtApellido.getText();
        String FN =v.TxtFN.getText();
        String Correo =v.TxtCorreo.getText();
        String Telefono =v.TxtTelefono.getText();
        
        if(id.equals("")&& Nombre.equals("")&& Apellido.equals("")&& FN.equals("")&& Correo.equals("")&& Telefono.equals("")){
            JOptionPane.showMessageDialog(null, "Hay campos vacios!!");
        }else{       
             PreparedStatement pps = cn.prepareStatement("INSERT INTO estudiante(id_estudiante,nombre_e,apellidos,F_Nacimiento,correo,telefono)VALUES(?,?,?,?,?,?)");
             pps.setString(1,v.TxtId.getText());
             pps.setString(2,v.TxtNombre.getText());
             pps.setString(3,v.TxtApellido.getText());
             pps.setString(4,v.TxtFN.getText());
             pps.setString(5,v.TxtCorreo.getText());
             pps.setString(6,v.TxtTelefono.getText());
             pps.executeUpdate();
             JOptionPane.showMessageDialog(null, "Registrado Correctamente");
        }
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "FALLO");
        }   
   }
   
   public void mostrartabla3(){
        DefaultTableModel modelo =new DefaultTableModel();
        
        modelo.addColumn("id_grupo");
        //modelo.addColumn("id_asignatura");
        modelo.addColumn("Materia");
        
        v.TBMaterias.setModel(modelo);
        
        String sql="select a.id_grupo ,b. id_asignatura, c.nombre_a from estudiantegrupo a, grupo b, asignatura c where a.id_grupo=b.id_grupo and c.id_asignatura=b.id_asignatura group by id_grupo;";
        String datos[] =new String [3];
        Statement st;
         try {
             st = cn.createStatement();
             ResultSet rs= st.executeQuery(sql);
             
             while(rs.next()){
                 datos[0]=rs.getString(1);
                 datos[1]=rs.getString(3);
                 //datos[2]=rs.getString(3);
                 
                 
                 modelo.addRow(datos);
             }
             
             v.TBMaterias.setModel(modelo);
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error Tabla 3");
         }
        
    }
   
   
   public void mostrartabla(){
        
        DefaultTableModel modelo =new DefaultTableModel();
        
        modelo.addColumn("id_estudiante");
        modelo.addColumn("nombre_e");
        modelo.addColumn("apellidos");
        modelo.addColumn("F_Nacimiento");
        modelo.addColumn("correo");
        modelo.addColumn("telefono");
        modelo.addColumn("nota1");
        modelo.addColumn("nota2");
        modelo.addColumn("nota3");
        modelo.addColumn("id_grupo");
        modelo.addColumn("Materia");
        
        v.TBDatos.setModel(modelo);
        
        String sql= "select b.id_estudiante, a.nombre_e, a.apellidos, a.F_Nacimiento, a.correo, a.telefono, a.nota1, a.nota2, a.nota3, b.id_grupo, c.nombre_a from estudiante a, estudiantegrupo b, asignatura c, grupo d where a.id_estudiante="+v.TxtIdP.getText()+" and b.id_grupo=d.id_grupo and c.id_asignatura=d.id_asignatura and a.id_estudiante=b.id_estudiante;";
        
        String datos[] =new String [11];
        Statement st;
         try {
             st = cn.createStatement();
             ResultSet rs= st.executeQuery(sql);
             
             while(rs.next()){
                 datos[0]=rs.getString(1);
                 datos[1]=rs.getString(2);
                 datos[2]=rs.getString(3);
                 datos[3]=rs.getString(4);
                 datos[4]=rs.getString(5);
                 datos[5]=rs.getString(6);
                 datos[6]=rs.getString(7);
                 datos[7]=rs.getString(8);
                 datos[8]=rs.getString(9);
                 datos[9]=rs.getString(10);
                 datos[10]=rs.getString(11);
                 modelo.addRow(datos);
             }
             
             v.TBDatos.setModel(modelo);
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error Tabla Información");
         }
    }
   
    public void Select (){
        int fila=v.TBMaterias.getSelectedRow();
        if(fila>=0){
            v.TxtIdGrupo.setText(v.TBMaterias.getValueAt(fila, 0).toString());
            v.TxtMateria.setText(v.TBMaterias.getValueAt(fila, 2).toString());          
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    }
    
    public void Select2 (){
        int fila=v.TBDatosP.getSelectedRow();
        if(fila>=0){
            v.TxtBMateria.setText(v.TBDatosP.getValueAt(fila, 2).toString());
                
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    }
        
    public void RegistroMaterias(){
        
        try {
             PreparedStatement pps = cn.prepareStatement("INSERT INTO estudianteGrupo(id_estudiante,id_grupo)VALUES(?,?)");
             pps.setString(1,v.TxtIdP.getText());
             pps.setString(2,v.TxtIdGrupo.getText());
             pps.executeUpdate();
             JOptionPane.showMessageDialog(null, "Registrado Correctamente");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Registro Fallido");
         }
        
    }

   public void mostrartablaP(){
        DefaultTableModel modelo =new DefaultTableModel();
        modelo.addColumn("id_asignatura");
        modelo.addColumn("id_profesor");
        modelo.addColumn("Materia");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        v.TBDatosP.setModel(modelo);
        
        String sql= "select a. id_asignatura, a. id_profesor, b.nombre_a, c.nombre_f, c.apellidos from grupo a, asignatura b, profesor c where a.id_profesor="+v.TxtIdPP.getText() +" and a.id_asignatura=b.id_asignatura group by id_asignatura;";
        
        String datos[] =new String [6];
        Statement st;
         try {
             st = cn.createStatement();
             ResultSet rs= st.executeQuery(sql);
             
             while(rs.next()){
                 datos[0]=rs.getString(1);
                 datos[1]=rs.getString(2);
                 datos[2]=rs.getString(3);
                 datos[3]=rs.getString(4);
                 datos[4]=rs.getString(5);
                 
                 modelo.addRow(datos);
             }
             
             v.TBDatosP.setModel(modelo);
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al mostrar datos");
         }        
    }
   
   public void GuardarNotas(){
       try {
             PreparedStatement pps= cn.prepareStatement("UPDATE estudiante SET nota1="+v.TxtNota1.getText()+",nota2="+v.TxtNota2.getText()+",nota3="+v.TxtNota3.getText()+" WHERE id_estudiante="+v.TxtIdE.getText()+";");
             pps.executeUpdate();
             JOptionPane.showMessageDialog(null, "Datos Modificados");
             limpiar();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al guardar");
                     
             System.out.println(ex.getMessage());
         }
   }
   
   public void Modificar(){
       int fila=v.TBDDatosM.getSelectedRow();
        if(fila>=0){
            TxtIdE.setText(v.TBDDatosM.getValueAt(fila, 0).toString());
            TxtNota1.setText(v.TBDDatosM.getValueAt(fila, 6).toString());
            TxtNota2.setText(v.TBDDatosM.getValueAt(fila, 7).toString());
            TxtNota3.setText(v.TBDDatosM.getValueAt(fila, 8).toString());
        }
        else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
   }
 
   public void mostartabla2(){
        DefaultTableModel modelo =new DefaultTableModel();
        
        modelo.addColumn("id_estudiante");
        modelo.addColumn("id_grupo");
        modelo.addColumn("id_asignatura");
        modelo.addColumn("Materia");
        modelo.addColumn("nombre_e");
        modelo.addColumn("apellidos");
        modelo.addColumn("nota1");
        modelo.addColumn("nota2");
        modelo.addColumn("nota3");
        
        v.TBDDatosM.setModel(modelo);
        
        String sql="select a.id_estudiante, a.id_grupo, b.id_asignatura, c.nombre_a, d.nombre_e, d.apellidos,d.nota1,d.nota2,d.nota3 from estudiantegrupo a, grupo b, asignatura c, estudiante d where nombre_a='"+v.TxtBMateria.getText() +"' and a.id_grupo=b.id_grupo and c.id_asignatura=b.id_asignatura and a.id_estudiante=d.id_estudiante;";
        String datos[] =new String [9];
        Statement st;
         try {
             st = cn.createStatement();
             ResultSet rs= st.executeQuery(sql);
             
             while(rs.next()){
                 datos[0]=rs.getString(1);
                 datos[1]=rs.getString(2);
                 datos[2]=rs.getString(3);
                 datos[3]=rs.getString(4);
                 datos[4]=rs.getString(5);
                 datos[5]=rs.getString(6);
                 datos[6]=rs.getString(7);
                 datos[7]=rs.getString(8);
                 datos[8]=rs.getString(9);
                 
                 modelo.addRow(datos);
             }
             
             v.TBDDatosM.setModel(modelo);
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al mostrar");
         }
    }   
}
