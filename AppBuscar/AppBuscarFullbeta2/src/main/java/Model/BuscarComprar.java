/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controlador;
import View.MostrarAutomoviles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan
 */
public class BuscarComprar {
    Controlador controlador;
    MostrarAutomoviles mostrar;
    public ArrayList<Vehiculo>  seleccionado;
    
    public BuscarComprar(Controlador controlador, MostrarAutomoviles mostrar){
        this.controlador = controlador;
        this.mostrar = mostrar;
    }
    
    public void vehiculoBorrar(Map<List<String>,ArrayList<Vehiculo>> mapVehiculo,Vehiculo aEliminar){
        
        List<String> stringTemp = new ArrayList<>();
        stringTemp.add(aEliminar.getUbicacion());
        stringTemp.add(aEliminar.getMarca());
        stringTemp.add(aEliminar.getModelo());
        
        ArrayList<Vehiculo> mapTemp = mapVehiculo.get(stringTemp);
        mapTemp.remove(aEliminar);
        mapVehiculo.put(stringTemp, mapTemp);
        
    }

    
    public void OrdenarAutomovil(String ub, String mar, String model){
        List<String> stringTemp = new ArrayList<>();
        stringTemp.add(ub);
        stringTemp.add(mar);
        stringTemp.add(model);
        ArrayList<Vehiculo> mapTemp = this.controlador.inventory.get(stringTemp);
        Collections.sort(mapTemp, new Comparator() {
            public int compare(Object o1, Object o2) {
                Vehiculo p1 = (Vehiculo) o1;
                Vehiculo p2 = (Vehiculo) o2; 
                int ret = -1;
                if (p1.getrecorrido()== p2.getrecorrido()) {
                    if (p1.getprecio() == p2.getprecio()) {
                    ret = 0;
                    } else if (p1.getprecio() > p2.getprecio()) {
                        ret = 1;
                    } else if (p1.getprecio() < p2.getprecio()) {
                        ret = -1;
                    }
                } else if (p1.getrecorrido() > p2.getrecorrido()) {
                    ret = 1;
                } else if (p1.getrecorrido() < p2.getrecorrido()) {
                    ret = -1;
                }

                return ret;
            } 
        });
        

//        DefaultTableModel mod2 = (DefaultTableModel) this.mostrar.Table.getModel();
//        mod2.getDataVector().removeAllElements();
        //this.mostrar.Table= new JTable();
        
        DefaultTableModel mod = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        mod.addColumn("Marca");
        mod.addColumn("Modelo");
        mod.addColumn("Ubicacion");
        mod.addColumn("Placa");
        mod.addColumn("Color");
        mod.addColumn("Recorrido");
        mod.addColumn("Precio");
        mod.addColumn("Telefono");
        
        this.mostrar.Table.setModel(mod);       
        for(int i=0; i<mapTemp.size(); i++){
            String [] info = new String[8];
            info[0] = mapTemp.get(i).getMarca();
            info[1] = mapTemp.get(i).getModelo();
            info[2] = mapTemp.get(i).getUbicacion();
            info[3] = mapTemp.get(i).getPlaca();
            info[4] = mapTemp.get(i).getColor();
            info[5] = String.valueOf(mapTemp.get(i).getrecorrido());
            info[6] = String.valueOf(mapTemp.get(i).getprecio());
            info[7] = mapTemp.get(i).gettelefono();
            mod.addRow(info);
            //mostrar.Table.addRow(new Object[]{"hola"});
            //buscarAutomovil.cbUbicacion.addItem(listEntry.getKey().get(0));
        }




        seleccionado = mapTemp;
    }

    
}
