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
public class ServicioEstadistico{
    private int ID;
    private String Servicio;
    private int CantidadServcio;
    private double totalCantidadPrecio;

    public ServicioEstadistico( String Servicio, int Total) {
        this.CantidadServcio = Total;
        this.Servicio = Servicio;
    }

    public ServicioEstadistico(String Servicio) {
        this.Servicio = Servicio;
    }
    public ServicioEstadistico(int Total) {
        this.CantidadServcio = Total;
    }

    public ServicioEstadistico() {
    }

    public double getTotalCantidadPrecio() {
        return totalCantidadPrecio;
    }

    public void setTotalCantidadPrecio(double totalCantidadPrecio) {
        this.totalCantidadPrecio = totalCantidadPrecio;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    public int getCantidadServcio() {
        return CantidadServcio;
    }

    public void setCantidadServcio(int CantidadServcio) {
        this.CantidadServcio = CantidadServcio;
    }

    @Override
    public String toString() {
        return "ServicioEstadistico: " + "Servicio=" + Servicio + ", Total=" + CantidadServcio;
    }
    
}
