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
public class Tarjeta {
    private int ID;
    private int ID_Cliente;
    private String Numero;
    private String Tipo;
    private String CSV;
    private String MesCaducidad;
    private String AñoCaducidad;

    public Tarjeta(int ID_Cliente, String Numero, String Tipo, String CSV, String MesCaducidad, String AñoCaducidad) {
        this.ID_Cliente = ID_Cliente;
        this.Numero = Numero;
        this.Tipo = Tipo;
        this.CSV = CSV;
        this.MesCaducidad = MesCaducidad;
        this.AñoCaducidad = AñoCaducidad;
    }
    public Tarjeta( String Numero, String Tipo, String CSV, String MesCaducidad, String AñoCaducidad) {
        this.Numero = Numero;
        this.Tipo = Tipo;
        this.CSV = CSV;
        this.MesCaducidad = MesCaducidad;
        this.AñoCaducidad = AñoCaducidad;
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
     * @return the Numero
     */
    public String getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(String Numero) {
        this.Numero = Numero;
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
     * @return the CSV
     */
    public String getCSV() {
        return CSV;
    }

    /**
     * @param CSV the CSV to set
     */
    public void setCSV(String CSV) {
        this.CSV = CSV;
    }

    /**
     * @return the MesCaducidad
     */
    public String getMesCaducidad() {
        return MesCaducidad;
    }

    /**
     * @param MesCaducidad the MesCaducidad to set
     */
    public void setMesCaducidad(String MesCaducidad) {
        this.MesCaducidad = MesCaducidad;
    }

    /**
     * @return the AñoCaducidad
     */
    public String getAñoCaducidad() {
        return AñoCaducidad;
    }

    /**
     * @param AñoCaducidad the AñoCaducidad to set
     */
    public void setAñoCaducidad(String AñoCaducidad) {
        this.AñoCaducidad = AñoCaducidad;
    }

    @Override
    public String toString() {
        return "Tarjet: " + "ID_Cliente=" + ID_Cliente + ", Numero=" + Numero + ", Tipo=" + Tipo + ", CSV=" + CSV + ", MesCaducidad=" + MesCaducidad + ", A\u00f1oCaducidad=" + AñoCaducidad;
    }
    
    
}
