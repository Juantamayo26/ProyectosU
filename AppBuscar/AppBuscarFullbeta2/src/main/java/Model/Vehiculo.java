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
public abstract class Vehiculo {
    
    private String Modelo,Marca,Ubicacion,Placa,Color,telefono;
    private int recorrido,precio;
    
    public Vehiculo(String Marca, String Modelo, String Ubicacion, String Placa , String Color, int recorrido, int precio, String telefono){
        super();
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Ubicacion = Ubicacion;
        this.Placa = Placa;
        this.Color = Color;
        this.recorrido = recorrido;
        this.precio = precio;
        this.telefono = telefono;
    }
    //GETS
    public String getModelo(){
        return this.Modelo;
    }
    
    public String getMarca(){
        return this.Marca;
    }
    
    public String getUbicacion(){
        return this.Ubicacion;
    }
    
    public String getPlaca(){
        return this.Placa;
    }
    
    public String getColor(){
        return this.Color;
    }
    
    public int getrecorrido(){
        return this.recorrido;
    }
    
    public int getprecio(){
        return this.precio;
    }
    
    public String gettelefono(){
        return this.telefono;
    }
    
    // SETS
    
    public void setMarca(String Marca){
        this.Marca = Marca;
    }
    
    public void setModelo(String Modelo){
        this.Modelo = Modelo;
    }
    
    public void setUbicacion(String Ubicacion){
        this.Ubicacion = Ubicacion;
    }
    
    public void setPlaca(String Placa){
        this.Placa = Placa;
    }
    
    public void setColor(String Color){
        this.Color = Color;
    }
    
    public void setrecorrido(int recorrido){
        this.recorrido = recorrido;
    }
    
    public void setprecio(int precio){
        this.precio = precio;
    }
    
    public void settelefono(String telefono){
        this.telefono = telefono;
    }
    
}
