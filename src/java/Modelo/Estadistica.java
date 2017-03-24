/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author pasito
 */
public class Estadistica {
    
    private int idOrigen;
    private int idDestino;
    private String NombreOrigen;
    private String NombreDestino;
    private LocalDate fechaMIN;
    private LocalDate fechaMAX;
    private ArrayList<ServicioEstadistico> servicios;

    public Estadistica(int idOrigen, int idDestino, LocalDate fechaMIN, LocalDate fechaMAX) {
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.fechaMIN = fechaMIN;
        this.fechaMAX = fechaMAX;
        this.servicios = new ArrayList<>();
    }
    
    public Estadistica() {
        this.servicios = new ArrayList<>();
    }

    public String getNombreOrigen() {
        return NombreOrigen;
    }

    public void setNombreOrigen(String NombreOrigen) {
        this.NombreOrigen = NombreOrigen;
    }

    public String getNombreDestino() {
        return NombreDestino;
    }

    public void setNombreDestino(String NombreDestino) {
        this.NombreDestino = NombreDestino;
    }

    
    
    public ArrayList<ServicioEstadistico> getServicios() {
        return servicios;
    }

    public void addServicioEstadistico(ServicioEstadistico servicios) {
        this.servicios.add(servicios);
    }
    
    public ServicioEstadistico getServicioEstadistico(int posicion) {
        return this.servicios.get(posicion);
    }
    public ArrayList<ServicioEstadistico> getServicioEstadistico() {
        return this.servicios;
    }

    public LocalDate getFechaMIN() {
        return fechaMIN;
    }

    public void setFechaMIN(LocalDate fechaMIN) {
        this.fechaMIN = fechaMIN;
    }

    public LocalDate getFechaMAX() {
        return fechaMAX;
    }

    public void setFechaMAX(LocalDate fechaMAX) {
        this.fechaMAX = fechaMAX;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }
    
    
}
