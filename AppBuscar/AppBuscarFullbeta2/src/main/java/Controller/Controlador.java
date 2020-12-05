package Controller;


import Model.BuscarComprar;
import Model.ProxyAggVehiculo;
import Model.Vehiculo;
import View.BuscarAutomovilView;
import View.Menu;
import View.ComprarView;
import View.MostrarAutomoviles;
import View.ProxyAggVehiculoFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author W10
 */
public class Controlador {
    
    
    //VISTA
    public Menu menu;
    
    //Agregados
    public int agregados =0;
    
    
    //LISTENERS
    private final listeneragArchivo AgArchivol;
    private final listeneragManual AgManualL;
    private final listenerBuscar BuscarL;
    
    //INVENTARIO
    public Map<List<String>, ArrayList<Vehiculo>> inventory;
    private Object Map;
    
    public Controlador(){
        
        this.menu = new Menu();
        this.menu.setVisible(true);
        
        this.inventory = new HashMap<>();
        
        this.AgArchivol = new listeneragArchivo(this);
        this.menu.AgArchivo.addActionListener(AgArchivol);
        
        this.AgManualL = new listeneragManual(this);
        this.menu.AgManual.addActionListener(AgManualL);
        
        this.BuscarL = new listenerBuscar(this);
        this.menu.BuscaryComprar.addActionListener(BuscarL);
        
        
        
        
    }
    
    
    // LISTENER AGG ARCHIVO
    private class listeneragArchivo implements ActionListener{
        
        Controlador controlador;
        listenerContinuar LC;
        ProxyAggVehiculoFrame proxyaggvehiculoframe;
        
        public listeneragArchivo(Controlador controlador){
            //INSTANCIO LOS ATRIBUTOS
            this.controlador = controlador;
            this.proxyaggvehiculoframe = new ProxyAggVehiculoFrame();
            this.LC = new listenerContinuar(this.proxyaggvehiculoframe,this.controlador);
            this.proxyaggvehiculoframe.Continuar.addActionListener(LC);
            //AÑADO LOS TIPOS DE VEHICULOS
            this.proxyaggvehiculoframe.VehiculoElegido.addItem("Automovil");
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
           this.controlador.menu.setVisible(false);
           this.proxyaggvehiculoframe.setVisible(true);
           this.controlador.agregados+=1;
        }
        
        //LISTENER DE EL BOTON CONTINUAR DE EL FRAME PROXY
        
        private class listenerContinuar implements ActionListener {
            ProxyAggVehiculoFrame proxyaggvehiculoframe;
            Controlador controlador;
            ProxyAggVehiculo proxy;
            
            public listenerContinuar(ProxyAggVehiculoFrame proxyaggvehiculoframe, Controlador controlador){
                this.proxyaggvehiculoframe = proxyaggvehiculoframe;
                this.controlador = controlador;
                this.proxy = new ProxyAggVehiculo();
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(this.proxyaggvehiculoframe.VehiculoElegido.getSelectedItem() == "Automovil"){
                    this.agregarAutomovil();
                }
                this.proxyaggvehiculoframe.UsuarioText.setText("");
                this.proxyaggvehiculoframe.ContraseñaText.setText("");
            }
            
            private void agregarAutomovil(){
                this.proxyaggvehiculoframe.setVisible(false);
                this.proxy.AggAutomovilArchivo(this.proxyaggvehiculoframe.UsuarioText.getText(),this.proxyaggvehiculoframe.ContraseñaText.getPassword(),this.controlador.inventory,this.controlador.menu); 
            }
            
        }
    }
    
    
    // LISTENER AGG MANUAL
    private class listeneragManual implements ActionListener{

        Controlador controlador;
        listenerContinuar LC;
        ProxyAggVehiculoFrame proxyaggvehiculoframe;
        ///private AggAutomovilManual manualModel;
                
        public listeneragManual(Controlador controlador){

            //INSTANCIO LOS ATRIBUTOS
            this.controlador = controlador;
            this.proxyaggvehiculoframe = new ProxyAggVehiculoFrame();
            this.LC = new listenerContinuar(this.proxyaggvehiculoframe,this.controlador);
            this.proxyaggvehiculoframe.Continuar.addActionListener(LC);
            //AÑADO LOS TIPOS DE VEHICULOS
            this.proxyaggvehiculoframe.VehiculoElegido.addItem("Automovil");
            
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
           this.controlador.menu.setVisible(false);
           this.proxyaggvehiculoframe.setVisible(true);
           this.controlador.agregados+=1;
        }
        
        private class listenerContinuar implements ActionListener {
            ProxyAggVehiculoFrame proxyaggvehiculoframe;
            Controlador controlador;
            ProxyAggVehiculo proxy;
            
            public listenerContinuar(ProxyAggVehiculoFrame proxyaggvehiculoframe, Controlador controlador){
                this.proxyaggvehiculoframe = proxyaggvehiculoframe;
                this.controlador = controlador;
                this.proxy = new ProxyAggVehiculo();
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(this.proxyaggvehiculoframe.VehiculoElegido.getSelectedItem() == "Automovil"){
                    this.agregarAutomovil();
                }
                this.proxyaggvehiculoframe.UsuarioText.setText("");
                this.proxyaggvehiculoframe.ContraseñaText.setText("");
            }
            
            private void agregarAutomovil(){
                this.proxyaggvehiculoframe.setVisible(false);
                this.proxy.AggAutomovilManual(this.proxyaggvehiculoframe.UsuarioText.getText(),this.proxyaggvehiculoframe.ContraseñaText.getPassword(),this.controlador.inventory,this.controlador.menu); 
            }
            
        }
        
    }
    
        
    //LISTENER BUSCAR
    private class listenerBuscar implements ActionListener{
        //CONTROLADOR
        Controlador controlador;
        
        //LISTENERS
        Buscar LC;
        listenerComboUbicacion LCU;
        listenerCombomarca LCM;
        listenerBotonVolver LBV;
        
        //FRAME
        BuscarAutomovilView buscarAutomovil;
        
       
   
        
        public listenerBuscar(Controlador m){
            this.controlador = m;
            this.buscarAutomovil = new BuscarAutomovilView();
            this.LC = new Buscar(this.buscarAutomovil, this.controlador);
            this.buscarAutomovil.Buscar.addActionListener(LC);        
            
            this.LCU = new listenerComboUbicacion(this.buscarAutomovil,this.controlador.inventory);
            this.buscarAutomovil.cbUbicacion.addItemListener(this.LCU);
            
            this.LCM = new listenerCombomarca(this.buscarAutomovil,this.controlador.inventory);
            this.buscarAutomovil.cbMarca.addItemListener(this.LCM);
            
            this.LBV = new listenerBotonVolver(this.controlador,this.buscarAutomovil);
            this.buscarAutomovil.BotonVolver.addActionListener(this.LBV);
            
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            this.borrarllavesvacias();
             this.llenar(this.buscarAutomovil);
            if(this.controlador.inventory.isEmpty()){
              this.buscarAutomovil.Buscar.setEnabled(false);
            }
            this.controlador.menu.setVisible(false);
            this.buscarAutomovil.setVisible(true);
        }
        
        private void borrarllavesvacias(){
            ArrayList<List<String>> borrar = new ArrayList<>();
            
            
            for(Map.Entry<List<String>, ArrayList<Vehiculo>> listEntry: this.controlador.inventory.entrySet()){
                
                if(listEntry.getValue().isEmpty()){
                    borrar.add(listEntry.getKey());
                }
            }
            for(List<String> key : borrar){
                this.controlador.inventory.remove(key);
            }
            
        }
        
        
        private void llenar(BuscarAutomovilView buscarAutomovil){
            this.buscarAutomovil.cbUbicacion.removeAllItems();
            for(Map.Entry<List<String>, ArrayList<Vehiculo>> listEntry: this.controlador.inventory.entrySet()){
                
                if(buscarAutomovil.modelUbicacion.getIndexOf(listEntry.getKey().get(0)) == -1){
                    buscarAutomovil.cbUbicacion.addItem(listEntry.getKey().get(0));
                }
            }
              
        }
        
        //LISTENER COMBOBOX DE UBICACION
        private class listenerComboUbicacion implements ItemListener{
             
            BuscarAutomovilView buscarAutomovil;
            Map<List<String>, ArrayList<Vehiculo>> inventory;
             
            public listenerComboUbicacion(BuscarAutomovilView buscarAutomovil,Map<List<String>, ArrayList<Vehiculo>> inventory){
                this.buscarAutomovil = buscarAutomovil;
                this.inventory = inventory;
            }
             
            @Override
            public void itemStateChanged(ItemEvent e) {
                this.buscarAutomovil.cbMarca.removeAllItems();
                String selectedUbication = "";
                if(this.buscarAutomovil.cbUbicacion.getSelectedItem() != null){
                     selectedUbication = this.buscarAutomovil.cbUbicacion.getSelectedItem().toString();
                }
                for(Map.Entry<List<String>, ArrayList<Vehiculo>> listEntry: this.inventory.entrySet()){
                    
                        if(listEntry.getKey().get(0).equals(selectedUbication)){

                            if(buscarAutomovil.modelMarca.getIndexOf(listEntry.getKey().get(1)) == -1){
                                buscarAutomovil.cbMarca.addItem(listEntry.getKey().get(1));
                            }
                        }
                    
                    
                    
                }
            }
         }
        
        //LISTENER COMBOBOX DE MARCA
        private class listenerCombomarca implements ItemListener{
             
            Map<List<String>, ArrayList<Vehiculo>> inventory;
            BuscarAutomovilView buscarAutomovil;
             
            public listenerCombomarca(BuscarAutomovilView buscarAutomovil,Map<List<String>, ArrayList<Vehiculo>> inventory){
                this.buscarAutomovil = buscarAutomovil;
                this.inventory = inventory;
            }
             
            @Override
            public void itemStateChanged(ItemEvent e) {
                this.buscarAutomovil.cbModelo.removeAllItems();
                
                if(this.buscarAutomovil.cbMarca.getSelectedItem() != null){
                    String selectedMarca = this.buscarAutomovil.cbMarca.getSelectedItem().toString();
                    String selectedUbication = this.buscarAutomovil.cbUbicacion.getSelectedItem().toString();
                    
                    //ITERAR Y LLENAR LOS MODELOS
                    for(Map.Entry<List<String>, ArrayList<Vehiculo>> listEntry: this.inventory.entrySet()){
                            if(listEntry.getKey().get(1).equals(selectedMarca) && listEntry.getKey().get(0).equals(selectedUbication) ){

                                if(buscarAutomovil.modelModelo.getIndexOf(listEntry.getKey().get(2)) == -1){
                                    buscarAutomovil.cbModelo.addItem(listEntry.getKey().get(2));
                                }
                            }
                    }
                }
            }
        }
        
        //LISTENER BOTON VOLVER
        private class listenerBotonVolver implements ActionListener{
            
            BuscarAutomovilView buscarAutomovil;
            Controlador controlador;
            
            public listenerBotonVolver(Controlador m,BuscarAutomovilView buscarAutomovil ){
                this.buscarAutomovil = buscarAutomovil;
                this.controlador = m;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                this.controlador.menu.setVisible(true);
                this.buscarAutomovil.setVisible(false);
            }
            
        }
        
        private class Buscar implements ActionListener{      
            //variable.getSelectedItem().toString();
            //variable2.setText(variable)
            Controlador controlador;
            MostrarAutomoviles mostrar;
            String ub, mar, model;
            BuscarComprar buscarcomprar;
            ListenerComprar comprar;
            ListenerSalir salir;
                      
            BuscarAutomovilView buscarAutomovil;
            public Buscar(BuscarAutomovilView buscarAutomovil, Controlador controlador){
                this.controlador = controlador;
                this.buscarAutomovil = buscarAutomovil;
//                this.mostrar = new MostrarAutomoviles();
//                this.buscarcomprar = new BuscarComprar(this.controlador, this.mostrar);
//                this.comprar = new ListenerComprar(this.controlador, this.mostrar, this.buscarcomprar);
//                this.mostrar.btnComprar.addActionListener(comprar);
//                this.salir = new ListenerSalir(this.controlador, this.mostrar, this.buscarAutomovil);
//                this.mostrar.btnSalir.addActionListener(salir);
            }
            
            @Override
            public void actionPerformed(ActionEvent e) { 
                this.mostrar = new MostrarAutomoviles();
                this.buscarcomprar = new BuscarComprar(this.controlador, this.mostrar);
                this.comprar = new ListenerComprar(this.controlador, this.mostrar, this.buscarcomprar);
                this.mostrar.btnComprar.addActionListener(comprar);
                this.salir = new ListenerSalir(this.controlador, this.mostrar, this.buscarAutomovil);
                this.mostrar.btnSalir.addActionListener(salir);
                this.buscarAutomovil.setVisible(false);
                if(!this.controlador.inventory.isEmpty()){
                    this.ub = this.buscarAutomovil.cbUbicacion.getSelectedItem().toString();
                    this.mar = this.buscarAutomovil.cbMarca.getSelectedItem().toString();
                    this.model = this.buscarAutomovil.cbModelo.getSelectedItem().toString();
                    this.buscarcomprar.OrdenarAutomovil(ub, mar, model);
                }
                
                this.mostrar.setVisible(true);
            }
            
            private class ListenerSalir implements ActionListener{
                Controlador controlador;
                BuscarAutomovilView buscarAutomovil;
                MostrarAutomoviles mostrar;
                public ListenerSalir(Controlador controlador, MostrarAutomoviles mostrar, BuscarAutomovilView buscarAutomovil){
                    this.mostrar = mostrar;
                    this.controlador = controlador;
                    this.buscarAutomovil = buscarAutomovil;
                }
                @Override
                public void actionPerformed(ActionEvent ae) {
                   this.mostrar.dispose();
                   this.mostrar.setVisible(false);
                   this.buscarAutomovil.setVisible(false);
                   this.controlador.menu.setVisible(true);
                }
                
            }
            
            private class ListenerComprar implements ActionListener{
                Controlador controlador;
                MostrarAutomoviles mostrar;
                BuscarComprar buscarcomprar;
                ComprarView comp;
                btnComprar compListener;
                public ListenerComprar(Controlador controlador, MostrarAutomoviles mostrar, BuscarComprar buscarcomprar){
                    this.controlador = controlador;
                    this.mostrar = mostrar; 
                    this.buscarcomprar = buscarcomprar;             
                }

                @Override
                public void actionPerformed(ActionEvent ae) {
                    int aux = this.mostrar.Table.getSelectedRow();
                    if(aux != -1){
                        this.comp = new ComprarView();
                        this.compListener = new btnComprar(this.comp, this.buscarcomprar.seleccionado.get(aux));
                        this.comp.btnOk.addActionListener(compListener);

                        
                        this.buscarcomprar.vehiculoBorrar(inventory, buscarcomprar.seleccionado.get(aux));
                        ((DefaultTableModel)this.mostrar.Table.getModel()).removeRow(aux);
                        
                        //this.mostrar.Table.getSelectionModel().removeIndexInterval(aux, aux);
                    }else{
                        this.mostrar.ladvertencia.setText("SELECCIONA UN AUTOMOVIL");
                    }
                }
                
                private class btnComprar implements ActionListener{
                   ComprarView comp;
                   public btnComprar(ComprarView comp, Vehiculo v){
                       this.comp = comp;
                       this.comp.TextColor.setText(v.getColor());
                       this.comp.TextKilometraje.setText(String.valueOf(v.getrecorrido()));
                       this.comp.TextMarca.setText(v.getMarca());
                       this.comp.TextModelo.setText(v.getModelo());
                       this.comp.TextPlaca.setText(v.getPlaca());
                       this.comp.TextPrecio.setText(String.valueOf(v.getprecio()));
                       this.comp.TextTelefono.setText(v.gettelefono());
                       this.comp.TextUbicacion.setText(v.getUbicacion());
                       this.comp.setVisible(true);
                   }

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        this.comp.setVisible(false);
                    }
                }
               
            }
        }
    }
    
    
    public static void main(String[] args) {
        Controlador c = new Controlador();
        
        
    }
    
}
