/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.AggAutomovilManualFrame;
import View.AggVehiculoArchivoFrame;
import View.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author W10
 */
public class AggAutomovil implements IAggVehiculo  {
    
    //INVENTARIO
    public Map<List<String>, ArrayList<Vehiculo>> inventory;
    
    //FRAME
    AggVehiculoArchivoFrame FrameArchivo;
    private AggAutomovilManualFrame manualView;
    
    //varsManual
    private boolean flagMarca, flagModelo, flagUbicacion, flagPlaca, flagColor, flagTelefono, flagKilometraje, flagPrecio, flagPrototipo;
    private String marca, modelo, ubicacion, placa, color, telefono;
    private int kilometraje, precio, inicialConter, conter, finalConter;
    private List<String> key;
    private Vehiculo prototipo;
    
    //lISTENERS
    private listenerSearch LS;
    private listenerAñadir LA;
    
    //ARCHIVO
    File archivo;
    
    
    public AggAutomovil(){
        this.marca = ""; 
        this.modelo = ""; 
        this.ubicacion = ""; 
        this.placa = ""; 
        this.color = ""; 
        this.telefono = "";
        this.kilometraje = 0;
        this.precio = 0;
        
        this.inicialConter = 0;
        this.conter = 0;
        this.finalConter = 0;
        
        this.flagMarca = false;
        this.flagModelo = false;
        this.flagUbicacion = false;
        this.flagPlaca = false;
        this.flagColor = false;
        this.flagTelefono = false;
        this.flagKilometraje = false; 
        this.flagPrecio = false;
        this.flagPrototipo = false;
        
        this.key = new ArrayList<>();
    }
    
    
    @Override
    public void Archivo(Map<List<String>, ArrayList<Vehiculo>> inventory, Menu menu) {
        
        this.inventory = inventory;
        
        this.FrameArchivo = new AggVehiculoArchivoFrame();
        
        this.LS = new listenerSearch(this);
        this.LA = new listenerAñadir(this,menu);
        
        this.FrameArchivo.Search.addActionListener(this.LS);
        this.FrameArchivo.AddToList.addActionListener(this.LA);
        
        this.FrameArchivo.setVisible(true);
        
        this.archivo = null;
    }
    

    @Override
    public void Manual(Map<List<String>, ArrayList<Vehiculo>> inventario, Menu menu) {
        
        this.inventory = inventario;
        manualView = new AggAutomovilManualFrame();
        
        manualView.setVisible(true);
        
        manualView.getBoxUbicacion.addActionListener(this::getBoxUbicacionActionPerformed);
        manualView.getBoxMarca.addActionListener(this::getBoxMarcaActionPerformed);
        manualView.getBoxModelo.addActionListener(this::getBoxModeloActionPerformed);
        manualView.getBoxPlaca.addActionListener(this::getBoxPlacaActionPerformed);
        manualView.getBoxColor.addActionListener(this::getBoxColorActionPerformed);
        manualView.getBoxKilometraje.addActionListener(this::getBoxKilometrajeActionPerformed);
        manualView.getBoxPrecio.addActionListener(this::getBoxPrecioActionPerformed);
        manualView.getBoxTelefono.addActionListener(this::getBoxTelefonoActionPerformed);
        
        manualView.botonAceptar.addActionListener((ActionEvent evt) -> {
            actualisarTodo();
            if (flagKilometraje || flagPrecio) {
                addNewVehiculo();
                manualView.setVisible(false);
                menu.setVisible(true);
            }
        });
        manualView.botonCancelar.addActionListener((ActionEvent evt) -> {
            manualView.setVisible(false);
            menu.setVisible(true);
        });
        
    }
    //listener manual TextField
    public void getBoxUbicacionActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxUbicacionActionPerformed

        if ("".equals(manualView.getBoxUbicacion.getText()) && flagUbicacion) {
            flagUbicacion = false;
            inicialConter--;
        }
        if(!"".equals(manualView.getBoxUbicacion.getText()) && !flagUbicacion){
            flagUbicacion = true;
            inicialConter++;    
        }
        edito();
    }//GEN-LAST:event_getBoxUbicacionActionPerformed

    public void getBoxMarcaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxMarcaActionPerformed
        
        if ("".equals(manualView.getBoxMarca.getText()) && flagMarca) {
            flagMarca = false;
            inicialConter--;
        }
        if(!"".equals(manualView.getBoxMarca.getText()) && !flagMarca){
            flagMarca = true;
            inicialConter++;
        }
        edito();
    }//GEN-LAST:event_getBoxMarcaActionPerformed

    public void getBoxModeloActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxModeloActionPerformed
        
        if ("".equals(manualView.getBoxModelo.getText()) && flagModelo) {
            flagModelo = false;
            inicialConter--;
        }
        if(!"".equals(manualView.getBoxModelo.getText()) && !flagModelo){
            flagModelo = true;
            inicialConter++;
        }
        edito();
    }//GEN-LAST:event_getBoxModeloActionPerformed

    public void getBoxPlacaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxPlacaActionPerformed
        placa = manualView.getBoxPlaca.getText();
        if ("".equals(manualView.getBoxPlaca.getText()) && flagPlaca) {
            flagPlaca = false;
            conter--;
        }
        if(!"".equals(manualView.getBoxPlaca.getText()) && !flagPlaca){
            flagPlaca = true;
            conter++;
        }
        edito();
    }//GEN-LAST:event_getBoxPlacaActionPerformed

    public void getBoxColorActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxColorActionPerformed
        
        if ("".equals(manualView.getBoxColor.getText()) && flagColor) {
            flagColor = false;
            conter--;
        }
        if(!"".equals(manualView.getBoxColor.getText()) && !flagColor){
            flagColor = true;
            conter++;
        }
        edito();
    }//GEN-LAST:event_getBoxColorActionPerformed

    public void getBoxKilometrajeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxKilometrajeActionPerformed
        
        

        if ("".equals(manualView.getBoxKilometraje.getText()) && flagKilometraje) {
            flagKilometraje = false;
            conter--;
        }
        if(!"".equals(manualView.getBoxKilometraje.getText()) && !flagKilometraje){
            flagKilometraje = true;
            conter++;
        }
        edito();
    }//GEN-LAST:event_getBoxKilometrajeActionPerformed

    public void getBoxPrecioActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxPrecioActionPerformed

        if ("".equals(manualView.getBoxPrecio.getText()) && flagPrecio) {
            flagPrecio = false;
            finalConter--;
        }
        if(!"".equals(manualView.getBoxPrecio.getText()) && !flagPrecio){
            flagPrecio = true;
            finalConter++;
        }
        edito();
    }//GEN-LAST:event_getBoxPrecioActionPerformed
    
    public void getBoxTelefonoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_getBoxTelefonoActionPerformed
        
        if ("".equals(manualView.getBoxTelefono.getText()) && flagTelefono) {
            flagTelefono = false;
            finalConter--;
        }
        if(!"".equals(manualView.getBoxTelefono.getText()) && !flagTelefono){
            flagTelefono = true;
            finalConter++;
        }
        edito();
    }//GEN-LAST:event_getBoxTelefonoActionPerformed

    public void actualisarTodo(){
        ubicacion = manualView.getBoxUbicacion.getText();
        marca = manualView.getBoxMarca.getText();
        modelo = manualView.getBoxModelo.getText();
        placa = manualView.getBoxPlaca.getText();
        color = manualView.getBoxColor.getText();
        try {
            if (!"".equals(manualView.getBoxKilometraje.getText())) {
                kilometraje =  Integer.parseInt(manualView.getBoxKilometraje.getText());
            }
        } catch (Exception e) {
            manualView.getBoxKilometraje.setText("ingrese Un Numero");
            flagKilometraje = false;
            conter--;
            manualView.botonAceptar.setEnabled(false);
        }
        try {
            if (!"".equals(manualView.getBoxPrecio.getText())) {
                precio =  Integer.parseInt(manualView.getBoxPrecio.getText());
            }
        } catch (Exception e) {
            manualView.getBoxPrecio.setText("ingrese Un Numero");
            flagPrecio = false;
            finalConter--;
            manualView.botonAceptar.setEnabled(false);
        }
        telefono = manualView.getBoxTelefono.getText();
        
        
        if(inicialConter >= 3){
            key = crearKey(ubicacion, marca, modelo);
       
        }else if(!key.isEmpty()){
            key.clear();
        }
        
        
    }
    
    
    //listener manual Button
    
    public class botonActualizarDatosListener implements ActionListener{//GEN-FIRST:event_botonActualizarDatosActionPerformed

        
        
        public botonActualizarDatosListener(Vehiculo vehiculo){
            prototipo = vehiculo;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            inicialConter = 3;
            conter = 3;
            finalConter = 2;
            manualView.barraProgreso.setValue(inicialConter+conter+finalConter);
            
            flagMarca = true;
            flagModelo = true;
            flagUbicacion = true;
            flagPlaca = true;
            flagColor = true;
            flagTelefono = true;
            flagKilometraje = true; 
            flagPrecio = true;
            flagPrototipo = true;
            
            marca = prototipo.getMarca();
            modelo = prototipo.getModelo();
            ubicacion = prototipo.getUbicacion();
            placa =  prototipo.getPlaca();
            color = prototipo.getColor();
            telefono = prototipo.gettelefono();
            kilometraje = prototipo.getrecorrido();
            precio = prototipo.getprecio();
            
            manualView.getBoxMarca.setText(marca);
            manualView.getBoxModelo.setText(modelo);
            manualView.getBoxUbicacion.setText(ubicacion);
            manualView.getBoxPlaca.setText(placa);
            manualView.getBoxColor.setText(color);
            manualView.getBoxTelefono.setText(telefono);
            manualView.getBoxKilometraje.setText(Integer.toString(kilometraje));
            manualView.getBoxPrecio.setText(Integer.toString(precio));
            
            manualView.botonActualizarDatos.setEnabled(false);
            manualView.botonAceptar.setEnabled(false);
            
            edito();
        }
    }//GEN-LAST:event_botonActualizarDatosActionPerformed

    

    private void edito(){
        manualView.barraProgreso.setValue(inicialConter+conter+finalConter);

        
        if (flagPlaca && !flagPrototipo) {
            Vehiculo laCoincidencia = ExisteCoincidencia();
            if (laCoincidencia != null) {
                manualView.textoNumeroConsidencias.setText("Ya Existe la Placa");
                manualView.botonActualizarDatos.addActionListener(new botonActualizarDatosListener(laCoincidencia));
                manualView.botonActualizarDatos.setEnabled(true);
            }else{
                manualView.textoNumeroConsidencias.setText("");
                manualView.botonActualizarDatos.setEnabled(false);
            }
        }

        if(inicialConter + conter >= 6){
            manualView.getBoxPrecio.setEditable(true);
            manualView.getBoxTelefono.setEditable(true);
        }else{
            manualView.getBoxPrecio.setEditable(false);
            manualView.getBoxTelefono.setEditable(false);
        }

        if(manualView.barraProgreso.getValue() == 8){
            manualView.botonAceptar.setEnabled(true);
        }else{
            manualView.botonAceptar.setEnabled(false);
        }

    }
    
     //manual
    public List<String> crearKey(String Ubicacion, String Marca, String Modelo){
        
        List<String> createKey = new ArrayList<>();
        createKey.add(Ubicacion);
        createKey.add(Marca);
        createKey.add(Modelo);
        
        return createKey;
    }
    
    public void addNewVehiculo(){
        ArrayList<Vehiculo> listTemp = inventory.get(key);
        Vehiculo newVehiculo;
        
        if (flagPrototipo) {
            key = crearKey(ubicacion, marca, modelo);
            List<String> tempKey = new ArrayList<>();
            tempKey.add(prototipo.getUbicacion());
            tempKey.add(prototipo.getMarca());
            tempKey.add(prototipo.getModelo());
            inventory.get(tempKey).remove(prototipo);
            prototipo.setMarca(marca);
            prototipo.setModelo(modelo);
            prototipo.setUbicacion(ubicacion);
            newVehiculo = prototipo;
            if (listTemp == null) {
                listTemp = new ArrayList<>();
            }
        }else{
            if(listTemp == null || listTemp.isEmpty()) {
                listTemp = new ArrayList<>();
                newVehiculo = new Automovil(marca, modelo, ubicacion, placa, color,kilometraje,precio,telefono);
            }else{
                ///Se aplico el patron prototipe
                newVehiculo = (Vehiculo)listTemp.get(0);
            }
        }
        newVehiculo.setPlaca(placa);
        newVehiculo.setColor(color);
        newVehiculo.setrecorrido(kilometraje);
        newVehiculo.setprecio(precio);
        newVehiculo.settelefono(telefono);
        
        listTemp.add(newVehiculo);
        inventory.put(key, (ArrayList<Vehiculo>) listTemp);
        
    }
    
    public Vehiculo ExisteCoincidencia(){
        
        for (Map.Entry<List<String>, ArrayList<Vehiculo>> entry : inventory.entrySet()) {
            ArrayList<Vehiculo> val = entry.getValue();
            
            for (Vehiculo v: val) {
                if (v.getPlaca().equals(placa)) {
                    return v;
                }
            }
        }
        return null;
    }
    
    //LISTENER JFILECHOOSER
    private static class listenerSearch implements ActionListener {
        
        AggAutomovil Clase;
        JFileChooser FC;
        
        public listenerSearch (AggAutomovil Clase){
            this.FC = new JFileChooser();
            this.Clase = Clase;
            
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV","csv");
            FC.setFileFilter(filtro);
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            this.obtenerArchivo();
            this.MapearArchivo();
        }
            
        
        private void obtenerArchivo(){
            //BUSCADOR DEL DIRECTORIO
            int select = FC.showOpenDialog(FC);
            
            if(select == JFileChooser.APPROVE_OPTION){
                //SELECCIONA EL ARCHIVO
               this.Clase.archivo = FC.getSelectedFile();
               this.Clase.FrameArchivo.PathDirectory.setText(FC.getSelectedFile().getAbsolutePath());
            }    
        }
        
        private void MapearArchivo(){
            
            
            if(this.Clase.archivo != null){
                
                //REINICIAR TEXTO
                this.Clase.FrameArchivo.ViewVehiculos.setText("<html></html>");
                try{
                    BufferedReader lector = new BufferedReader(new FileReader(this.Clase.archivo.getAbsolutePath()));
                    String line = null;
                    while((line = lector.readLine())!= null){
                        //LEE CADA LINEA Y HACER VEHICULOS Y LISTAS
                        String[] datoslinea = line.split(";");
                        //IF PARA QUE NO TOME LINEAS QUE NO SON
                        if(datoslinea.length == 8){
                            
                            Vehiculo tempvehiculo = new Automovil(datoslinea[2],datoslinea[3],datoslinea[1],datoslinea[0],datoslinea[7],parseInt(datoslinea[4]),parseInt(datoslinea[5]),datoslinea[6]);
                            List<String> busqueda = new ArrayList<>();
                            busqueda.add(datoslinea[1]);
                            busqueda.add(datoslinea[2]);
                            busqueda.add(datoslinea[3]);

                            //ACTUALIZAR TEXTO
                            String str = this.Clase.FrameArchivo.ViewVehiculos.getText();
                            str = str.replaceAll("</html>","<br>"+ Arrays.toString(datoslinea)+"</html>");
                            this.Clase.FrameArchivo.ViewVehiculos.setText(str);
                            this.Clase.FrameArchivo.pack();


                            //AGREGAR EL VEHICULO A EL INVENTARIO
                            List<Vehiculo> tempList;

                            if(this.Clase.inventory.containsKey(busqueda)){
                                tempList = this.Clase.inventory.get(busqueda);
                            }else{
                                tempList = new ArrayList<>();
                            }

                            tempList.add(tempvehiculo);

                            this.Clase.inventory.put(busqueda, (ArrayList<Vehiculo>) tempList);
                        }
                    }

                }catch(IOException ex){}
                
            }  
        }
    }
    
    
    private class listenerAñadir implements ActionListener{
        
        AggAutomovil Clase;
        Menu menu;
        
        public listenerAñadir (AggAutomovil Clase, Menu menu){
            this.Clase = Clase; 
            this.menu  = menu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.Clase.FrameArchivo.setVisible(false);
            this.Clase.FrameArchivo.dispose();
            this.menu.setVisible(true);
        }    
    }
    

    
    
    
    
    
    
    
    
   /* public static void main(String[] args) {
        AggAutomovil a = new AggAutomovil();
        
        
        Map<List<String>, ArrayList<Vehiculo>> in  = a.Archivo();
        
        for (Map.Entry<List<String>, ArrayList<Vehiculo>> entry : in.entrySet()) { 
            
        }
        
      
        
        
    }*/
    
}
