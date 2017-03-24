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
public class Reserva {

    private int ID;
    private int Nadultos;
    private int Nninos;
    private int Nbebes;
    private Vuelo vueloIda;
    private Vuelo vueloVuelta;
    private double aPagar;
    private ArrayList<Pasajero> pasajeros;
    private int idTarjeta;
    private Cliente cliente;
    private Tarjeta tarjeta;
    private String usuario;
    private String COD_Reserva;
    private String FacturacionIda;
    private String FacturacionVuelta;
    private LocalDate fecha;

    public Reserva(String COD_Reserva, Cliente cliente, Vuelo vIda, Vuelo vVuelta, LocalDate fecha, int idTarjeta, double aPagar, String FacturacionIda, String FacturacionVuelta) {
        this.COD_Reserva = COD_Reserva;
        this.cliente = cliente;
        this.vueloIda = vIda;
        this.vueloVuelta = vVuelta;
        this.fecha = fecha;
        this.idTarjeta = idTarjeta;
        this.aPagar = aPagar;
        this.FacturacionIda = FacturacionIda;
        this.FacturacionVuelta = FacturacionVuelta;
        this.pasajeros = new ArrayList<>();
    }

    public Reserva(int Nadultos, int Nninos, int Nbebes) {
        this.Nadultos = Nadultos;
        this.Nninos = Nninos;
        this.Nbebes = Nbebes;
        this.pasajeros = new ArrayList<>();
    }

    public Reserva(String CODReserva) {
        this.COD_Reserva = CODReserva;
        this.pasajeros = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFacturacionIda() {
        return FacturacionIda;
    }

    public void setFacturacionIda(String FacturacionIda) {
        this.FacturacionIda = FacturacionIda;
    }

    public String getFacturacionVuelta() {
        return FacturacionVuelta;
    }

    public void setFacturacionVuelta(String FacturacionVuelta) {
        this.FacturacionVuelta = FacturacionVuelta;
    }

    public String getCOD_Reserva() {
        return COD_Reserva;
    }

    public void setCOD_Reserva(String COD_Reserva) {
        this.COD_Reserva = COD_Reserva;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjetas) {
        this.tarjeta = (tarjetas);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    /**
     *
     * @param p
     */
    public void addPasajero(Pasajero p) {
        this.pasajeros.add(p);
    }

    /**
     * @return the Nadultos
     */
    public int getNadultos() {
        return Nadultos;
    }

    /**
     * @param Nadultos the Nadultos to set
     */
    public void setNadultos(int Nadultos) {
        this.Nadultos = Nadultos;
    }

    /**
     * @return the Nninos
     */
    public int getNninos() {
        return Nninos;
    }

    /**
     * @param Nninos the Nninos to set
     */
    public void setNninos(int Nninos) {
        this.Nninos = Nninos;
    }

    /**
     * @return the Nbebes
     */
    public int getNbebes() {
        return Nbebes;
    }

    /**
     * @param Nbebes the Nbebes to set
     */
    public void setNbebes(int Nbebes) {
        this.Nbebes = Nbebes;
    }

    /**
     *
     * @param pasaj
     */
    public void setPasajero(Pasajero pasaj) {
        this.pasajeros.add(pasaj);
    }

    /**
     *
     * @param pos
     * @return Pasajero
     */
    public Pasajero getPasajero(int pos) {
        return this.pasajeros.get(pos);
    }

    /**
     * @return the vueloIda
     */
    public Vuelo getVueloIda() {
        return vueloIda;
    }

    /**
     * @param vueloIda the vueloIda to set
     */
    public void setVueloIda(Vuelo vueloIda) {
        this.vueloIda = vueloIda;
    }

    /**
     * @return the vueloVuelta
     */
    public Vuelo getVueloVuelta() {
        return vueloVuelta;
    }

    /**
     * @param vueloVuelta the vueloVuelta to set
     */
    public void setVueloVuelta(Vuelo vueloVuelta) {
        this.vueloVuelta = vueloVuelta;
    }

    /**
     * @return the aPagar
     */
    public double getaPagar() {
        return aPagar;
    }

    /**
     * @param aPagar the aPagar to set
     */
    public void setaPagar(double aPagar) {
        this.aPagar = aPagar;
    }

    /**
     *
     * @return the pasajeros
     */
    public ArrayList<Pasajero> getPasajeros() {
        return this.pasajeros;
    }

//    @Override
//    public String toString() {
//        String salida= "Reserva: " + "Nadultos=" + Nadultos + ", Nninos=" + Nninos + ", Nbebes=" + Nbebes;
//        Iterator<Pasajero> it = this.pasajeros.iterator();
//        salida+="\nPasajeros:";
//        while(it.hasNext()){
//            Pasajero p = it.next();
//            salida+="\n"+p.toString();
//        }
//        return salida;
//    }
    @Override
    public String toString() {
        return "Reserva: " + "Nadultos=" + Nadultos + ", Nninos=" + Nninos + ", Nbebes=" + Nbebes + ", aPagar=" + aPagar;
    }

}
