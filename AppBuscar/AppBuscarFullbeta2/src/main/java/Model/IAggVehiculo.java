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
public interface IAggVehiculo {
    public void Archivo(Map<List<String>, ArrayList<Vehiculo>> inventory, Menu menu);
    public void Manual(Map<List<String>, ArrayList<Vehiculo>> inventario, Menu menu);
}
