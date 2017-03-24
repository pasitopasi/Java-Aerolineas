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
public class Compra {
    private int ID;
    private int ID_Cliente;
    private int ID_Reserva;
    private double total;

    public Compra(int ID_Cliente, int ID_Reserva, double total) {
        this.ID_Cliente = ID_Cliente;
        this.ID_Reserva = ID_Reserva;
        this.total = total;
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

    /**
     * @return the ID_Cliente
     */
    public int getID_Cliente() {
        return ID_Cliente;
    }

    /**
     * @param ID_Cliente the ID_Cliente to set
     */
    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    /**
     * @return the ID_Reserva
     */
    public int getID_Reserva() {
        return ID_Reserva;
    }

    /**
     * @param ID_Reserva the ID_Reserva to set
     */
    public void setID_Reserva(int ID_Reserva) {
        this.ID_Reserva = ID_Reserva;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Compra: " + "ID_Cliente=" + ID_Cliente + ", ID_Reserva=" + ID_Reserva + ", total=" + total;
    }
    
}
