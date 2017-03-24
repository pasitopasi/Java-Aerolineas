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
public class ConexionAreopuertos {
    private String ID;
    private String ID_Origen;
    private String ID_Destino;

    public ConexionAreopuertos(String ID_Origen, String ID_Destino) {
        this.ID_Origen = ID_Origen;
        this.ID_Destino = ID_Destino;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the ID_Origen
     */
    public String getID_Origen() {
        return ID_Origen;
    }

    /**
     * @param ID_Origen the ID_Origen to set
     */
    public void setID_Origen(String ID_Origen) {
        this.ID_Origen = ID_Origen;
    }

    /**
     * @return the ID_Destino
     */
    public String getID_Destino() {
        return ID_Destino;
    }

    /**
     * @param ID_Destino the ID_Destino to set
     */
    public void setID_Destino(String ID_Destino) {
        this.ID_Destino = ID_Destino;
    }

    
    @Override
    public String toString() {
        return "Conexion: " + "ID_Origen=" + getID_Origen() + ", ID_Destino=" + getID_Destino();
    }
}
