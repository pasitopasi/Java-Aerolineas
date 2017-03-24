/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author pasito
 */
public class Aeropuerto {

    private String IATA;
    private String nombre;
    private String localidad;
    private int capacidad;
    private int ID;
    
    
    public Aeropuerto(String IATA, String nombre, String localidad, int capacidad) {
        this.IATA = IATA;
        this.nombre = nombre;
        this.localidad = localidad;
        this.capacidad = capacidad;
    }

    /**
     * @return the IATA
     */
    public String getIATA() {
        return IATA;
    }

    /**
     * @param IATA the IATA to set
     */
    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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
    
    public String datosHTML(){
        return "<option value='"+this.getID()+"' > "+this.getLocalidad()+" ("+this.getIATA()+") </option>";
    }
    
    @Override
    public String toString() {
        return "Aeropuerto: " + "IATA=" + IATA + ", nombre=" + nombre + ", localidad=" + localidad + ", capacidad=" + capacidad;
    }
}
