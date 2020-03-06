package Controlador;
import Vista.Login;
import Modelo.modelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Control implements ActionListener{
    
    private modelo m;
    private Login v;

    public Control(modelo m, Login v) {
        this.m = m;
        this.v = v;
        this.v.BtnLimpiar.addActionListener(this);
        this.v.BtnGuardarU.addActionListener(this);
        this.v.BtnBuscarEE.addActionListener(this);
        this.v.BtnSeleccionar.addActionListener(this);
        this.v.BtnAgregarM.addActionListener(this);
        this.v.BtnBuscar.addActionListener(this);
        this.v.btnGuardar.addActionListener(this);
        this.v.BtnModificar.addActionListener(this);
        this.v.BtnBuscarE.addActionListener(this);
    }
    
    public void iniciar(){
        v.setTitle("Universidad");
        v.pack();
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
        m.mostrartabla3();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(v.BtnLimpiar ==e.getSource()){
            try {
                m.limpiar();
            } catch (Exception ex) {
            }
        }
        
        if(v.BtnGuardarU ==e.getSource()){
            try {
            m.nuevousuario();    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo registrar");   
            }   
        }
        if(v.BtnBuscarEE ==e.getSource()){
            try {
            m.mostrartabla();    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Mostrar Tabla");   
            }
        }
        if(v.BtnSeleccionar ==e.getSource()){
            try {
            m.Select();    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Mostrar Tabla");   
            }
        }
        if(v.BtnAgregarM ==e.getSource()){
            try {
            m.RegistroMaterias();    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Mostrar Tabla");   
            }
        }
        if(v.BtnBuscar ==e.getSource()){
            try {
            m.mostrartablaP();    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Mostrar Tabla");   
            }
        }
        if(v.btnGuardar ==e.getSource()){
            try {
            m.GuardarNotas();    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Mostrar Tabla");   
            }
        }
        if(v.BtnModificar ==e.getSource()){
            try {
            m.Modificar();    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Mostrar Tabla");   
            }
        }
        if(v.BtnBuscarE ==e.getSource()){
            try {
            m.Select2();
            m.mostartabla2();    
            
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo Mostrar Tabla");   
            }
        }
    }
  
}
