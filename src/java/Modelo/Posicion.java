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
public class Posicion {
    private int Posicion;
    private int ID;

    public Posicion(int Posicion, int ID) {
        this.Posicion = Posicion;
        this.ID = ID;
    }

    /**
     * @return the Posicion
     */
    public int getPosicion() {
        return Posicion;
    }

    /**
     * @param Posicion the Posicion to set
     */
    public void setPosicion(int Posicion) {
        this.Posicion = Posicion;
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
        return "Posicion{" + "Posicion=" + Posicion + ", ID=" + ID + '}';
    }
}
