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
public class Avion {
    private String CODAvion;
    private String Tipo;
    private int Plazas;
    private int Filas;
    private int Columnas;

    public Avion(String CODAvion, String Tipo, int Plazas, int Filas, int Columnas) {
        this.CODAvion = CODAvion;
        this.Tipo = Tipo;
        this.Plazas = Plazas;
        this.Filas = Filas;
        this.Columnas = Columnas;
    }

    /**
     * @return the CODAvion
     */
    public String getCODAvion() {
        return CODAvion;
    }

    /**
     * @param CODAvion the CODAvion to set
     */
    public void setCODAvion(String CODAvion) {
        this.CODAvion = CODAvion;
    }

    /**
     * @return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the Plazas
     */
    public int getPlazas() {
        return Plazas;
    }

    /**
     * @param Plazas the Plazas to set
     */
    public void setPlazas(int Plazas) {
        this.Plazas = Plazas;
    }

    /**
     * @return the Filas
     */
    public int getFilas() {
        return Filas;
    }

    /**
     * @param Filas the Filas to set
     */
    public void setFilas(int Filas) {
        this.Filas = Filas;
    }

    /**
     * @return the Columnas
     */
    public int getColumnas() {
        return Columnas;
    }

    /**
     * @param Columnas the Columnas to set
     */
    public void setColumnas(int Columnas) {
        this.Columnas = Columnas;
    }

    @Override
    public String toString() {
        return "Avion: " + "CODAvion=" + CODAvion + ", Tipo=" + Tipo + ", Plazas=" + Plazas + ", Filas=" + Filas + ", Columnas=" + Columnas;
    }
    
    
}
