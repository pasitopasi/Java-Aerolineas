/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author pasito
 */
public class Vuelo {

    private int IDConexion;
    private String CODVuelo;
    private LocalDate Fecha;
    private Time Hora_Salida;
    private Time Hora_Entrada;
    private double Precio;
    private Time Duracion;
    private int Plazas;
    private int ID;
    private int Avion;
    
    public Vuelo(int IDConexion, String CODVuelo, LocalDate Fecha, Time Hora_Salida, Time Hora_Entrada, double precio, Time Duracion, int plazas) {
        this.IDConexion = IDConexion;
        this.CODVuelo = CODVuelo;
        this.Fecha = Fecha;
        this.Hora_Salida = Hora_Salida;
        this.Hora_Entrada = Hora_Entrada;
        this.Precio = precio;
        this.Duracion = Duracion;
        this.Plazas = plazas;
    }
    
    public Vuelo(String CODVuelo, LocalDate Fecha){
        this.CODVuelo = CODVuelo;
        this.Fecha = Fecha;
    }
    
    public Vuelo(){
        
    }
    
    public Vuelo(int Id){
        this.ID=Id;
    }

    /**
     * @return the IDConexion
     */
    public int getIDConexion() {
        return IDConexion;
    }
    
    /**
     * @return the Avion
     */
    public int getAvion() {
        return Avion;
    }

    /**
     * @param IDConexion the IDConexion to set
     */
    public void setIDConexion(int IDConexion) {
        this.IDConexion = IDConexion;
    }

    /**
     * @param Avion the Avion to set
     */
    public void setAvion(int Avion) {
        this.Avion = Avion;
    }

    /**
     * @return the CODVuelo
     */
    public String getCODVuelo() {
        return CODVuelo;
    }

    /**
     * @param CODVuelo the CODVuelo to set
     */
    public void setCODVuelo(String CODVuelo) {
        this.CODVuelo = CODVuelo;
    }

    /**
     * @return the Fecha
     */
    public LocalDate getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(LocalDate Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * @return the Hora_Salida
     */
    public Time getHora_Salida() {
        return Hora_Salida;
    }

    /**
     * @param Hora_Salida the Hora_Salida to set
     */
    public void setHora_Salida(Time Hora_Salida) {
        this.Hora_Salida = Hora_Salida;
    }

    /**
     * @return the Hora_Entrada
     */
    public Time getHora_Entrada() {
        return Hora_Entrada;
    }

    /**
     * @param Hora_Entrada the Hora_Entrada to set
     */
    public void setHora_Entrada(Time Hora_Entrada) {
        this.Hora_Entrada = Hora_Entrada;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return Precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.Precio = precio;
    }

    /**
     * @return the Duracion
     */
    public Time getDuracion() {
        return Duracion;
    }

    /**
     * @param Duracion the Duracion to set
     */
    public void setDuracion(Time Duracion) {
        this.Duracion = Duracion;
    }

    /**
     * @return the plazas
     */
    public int getPlazas() {
        return Plazas;
    }

    /**
     * @param plazas the plazas to set
     */
    public void setPlazas(int plazas) {
        this.Plazas = plazas;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
    @Override
    public String toString() {
        return "Vuelo: " + "IDConexion=" + IDConexion + ", CODVuelo=" + CODVuelo + ", Fecha=" + Fecha + ", Hora_Salida=" + Hora_Salida + ", Hora_Entrada=" + Hora_Entrada + ", Precio=" + Precio + ", Duracion=" + Duracion + ", Plazas=" + Plazas;
    }
}
