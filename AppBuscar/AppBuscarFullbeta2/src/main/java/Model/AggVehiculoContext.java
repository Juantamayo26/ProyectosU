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
public class AggVehiculoContext {
  
    IAggVehiculo A;
    
    public AggVehiculoContext (IAggVehiculo A){
        this.A = A;
    }
    
    public void AggVehiculoArchivo(Map<List<String>, ArrayList<Vehiculo>> inventory, Menu menu){
        this.A.Archivo(inventory, menu);           
    }
    
    public void AggVehiculoManual(Map<List<String>, ArrayList<Vehiculo>> inventory, Menu menu){
        this.A.Manual(inventory, menu);           
    }
}
