/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author W10
 */
public class ProxyAggVehiculo {
    
    
    public ProxyAggVehiculo(){}
    
    
    public void AggAutomovilArchivo(String Usuario,char[] contraseña, Map<List<String>, ArrayList<Vehiculo>> inventory, Menu menu){
        //ARREGLAR PASSWORD
        String Contraseña = new String(contraseña);
        
        //FORMALIZAR STRINGS
        if(Usuario != null & null != Contraseña){
            Usuario = Usuario.trim();
            Contraseña = Contraseña.trim();
        }
        
        //COMPROBAR DATOS
        if("Administrador".equals(Usuario) && "12345".equals(Contraseña) ){
            menu.errorLabel.setText("");
            AggVehiculoContext c = new AggVehiculoContext(new AggAutomovil());
            c.AggVehiculoArchivo(inventory,menu);
        }else{
            menu.errorLabel.setText("USUARIO O CONTRASEÑA INVALIDA");
            menu.setVisible(true);  
        }     
    }
    
    public void AggAutomovilManual(String Usuario,char[] contraseña, Map<List<String>, ArrayList<Vehiculo>> inventory, Menu menu){
        //ARREGLAR PASSWORD
        String Contraseña = new String(contraseña);
        
        //FORMALIZAR STRINGS
        if(Usuario != null & null != Contraseña){
            Usuario = Usuario.trim();
            Contraseña = Contraseña.trim();
        }
        
        //COMPROBAR DATOS
        if("Administrador".equals(Usuario) && "12345".equals(Contraseña) ){
            menu.errorLabel.setText("");
            AggVehiculoContext c = new AggVehiculoContext(new AggAutomovil());
            c.AggVehiculoManual(inventory,menu);
        }else{
            menu.errorLabel.setText("USUARIO O CONTRASEÑA INVALIDA");
            menu.setVisible(true);  
        }     
    }
    
    
    
    
}
