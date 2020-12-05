/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author W10
 */
public class Automovil extends Vehiculo{
    int llantas,puertas;
    String tipo;
    
    public Automovil(String Marca, String Modelo, String Ubicacion, String Placa , String Color, int recorrido, int precio, String telefono){
        super( Marca,  Modelo,  Ubicacion,  Placa ,  Color,  recorrido,  precio,  telefono);
        this.llantas = 4;
        this.puertas = 4;
        this.tipo = "Terrestre";
    }
}
