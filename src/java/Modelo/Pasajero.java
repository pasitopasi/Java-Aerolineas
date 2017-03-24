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
public class Pasajero {

    private String tratamiento;
    private String nombre;
    private String apellidos;
    private String identificador;
    private String tipoIden;
    private LocalDate fecha_caducidad;
    private String nacionalidad;
    private boolean tutor;
    private String tutorS;
    private String ID_tutor;
    private LocalDate fecha_nacimiento;
    private ArrayList<Servicio> serviciosIDA;
    private ArrayList<Servicio> serviciosVUELTA;
    private String asientoI;
    private String asientoV;
    private int ID;
    private int IDReserva;

    public Pasajero(){
        
    }

    public Pasajero(String tratamiento, String nombre, String apellidos, String identificador, String tipoIden, LocalDate fecha_caducidad, String nacionalidad, boolean tutor, String ID_tutor, LocalDate fecha_nacimiento) {
        this.tratamiento = tratamiento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificador = identificador;
        this.tipoIden = tipoIden;
        this.fecha_caducidad = fecha_caducidad;
        this.nacionalidad = nacionalidad;
        this.tutor = tutor;
        this.ID_tutor = ID_tutor;
        this.fecha_nacimiento = fecha_nacimiento;
        this.serviciosIDA = new ArrayList<>();
        this.serviciosVUELTA = new ArrayList<>();
    }

    public Pasajero(String tratamiento, String nombre, String apellidos, String identificador, String tipoIden, LocalDate fecha_caducidad, String nacionalidad, boolean tutor, LocalDate fecha_nacimiento) {
        this.tratamiento = tratamiento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificador = identificador;
        this.tipoIden = tipoIden;
        this.fecha_caducidad = fecha_caducidad;
        this.nacionalidad = nacionalidad;
        this.tutor = tutor;
        this.fecha_nacimiento = fecha_nacimiento;
        this.serviciosIDA = new ArrayList<>();
        this.serviciosVUELTA = new ArrayList<>();
    }
    
    public Pasajero(String tratamiento, String nombre, String apellido, String tipoIde, String numeroIden, String nacionalidad){
        this.tratamiento = tratamiento;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.identificador = numeroIden;
        this.tipoIden = tipoIde;
        this.nacionalidad = nacionalidad;
        this.serviciosIDA = new ArrayList<>();
    }
    
    public Pasajero( String nombre, String apellido, String tipoIde, String numeroIden, String nacionalidad){
        this.tratamiento = "";
        this.nombre = nombre;
        this.apellidos = apellido;
        this.identificador = numeroIden;
        this.tipoIden = tipoIde;
        this.nacionalidad = nacionalidad;
        this.serviciosIDA = new ArrayList<>();
    }
    
    public Pasajero(String tratamiento, String nombre, String apellidos, String identificador, String tipoIden, LocalDate fecha_caducidad, String nacionalidad, String tutor, String ID_tutor, LocalDate fecha_nacimiento) {
        this.tratamiento = tratamiento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificador = identificador;
        this.tipoIden = tipoIden;
        this.fecha_caducidad = fecha_caducidad;
        this.nacionalidad = nacionalidad;
        this.tutorS = tutor;
        this.ID_tutor = ID_tutor;
        this.fecha_nacimiento = fecha_nacimiento;
        this.serviciosIDA = new ArrayList<>();
        this.serviciosVUELTA = new ArrayList<>();
    }

    public int getIDReserva() {
        return IDReserva;
    }

    public void setIDReserva(int IDReserva) {
        this.IDReserva = IDReserva;
    }

    public String getTutorS() {
        return tutorS;
    }

    public void setTutorS(String tutorS) {
        this.tutorS = tutorS;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    /**
     * @param serviciosIDA the serviciosIDA to add
     */
    public void addServiciosIDA(Servicio serviciosIDA) {
        this.serviciosIDA.add(serviciosIDA);
    }
    
    /**
     * @param serviciosVUELTA the serviciosVUELTA to add
     */
    public void addServiciosVUELTA(Servicio serviciosVUELTA) {
        this.serviciosVUELTA.add(serviciosVUELTA);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the asiento
     */
    public String getAsientoI() {
        return asientoI;
    }
    /**
     * @return the asiento
     */
    public String getAsientoV() {
        return asientoV;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param asiento the asiento to set
     */
    public void setAsientoI(String asiento) {
        this.asientoI = asiento;
    }
    /**
     * @param asiento the asiento to set
     */
    public void setAsientoV(String asiento) {
        this.asientoV = asiento;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the tipoIden
     */
    public String getTipoIden() {
        return tipoIden;
    }

    /**
     * @param tipoIden the tipoIden to set
     */
    public void setTipoIden(String tipoIden) {
        this.tipoIden = tipoIden;
    }

    /**
     * @return the fecha_caducidad
     */
    public LocalDate getFecha_caducidad() {
        return fecha_caducidad;
    }

    /**
     * @param fecha_caducidad the fecha_caducidad to set
     */
    public void setFecha_caducidad(LocalDate fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the tutor
     */
    public boolean isTutor() {
        return tutor;
    }

    /**
     * @param tutor the tutor to set
     */
    public void setTutor(boolean tutor) {
        this.tutor = tutor;
    }

    /**
     * @return the ID_tutor
     */
    public String getID_tutor() {
        return ID_tutor;
    }

    /**
     * @param ID_tutor the ID_tutor to set
     */
    public void setID_tutor(String ID_tutor) {
        this.ID_tutor = ID_tutor;
    }

    /**
     * @return the fecha_nacimiento
     */
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * @param fecha_nacimiento the fecha_nacimiento to set
     */
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * @return the servicios
     */
    public ArrayList<Servicio> getServiciosIDA() {
        return serviciosIDA;
    }
    
    /**
     * @return the tratamiento
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * @param tratamiento the tratamiento to set
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    /**
     * @return the serviciosVUELTA
     */
    public ArrayList<Servicio> getServiciosVUELTA() {
        return serviciosVUELTA;
    }
    
    public String sf(){
        return getTratamiento() + " " +getNombre() + " " + getApellidos() + " con " + tipoIden + " = " + getIdentificador() ;
    }
    
    
    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Pasajero:" + " nombre=" + getNombre() + ", apellidos=" + getApellidos() + ", Identificador=" + getIdentificador() + ", nacionalidad=" + getNacionalidad() + ", tutor=" + isTutor() + ", ID_tutor=" + getID_tutor() + ", fecha_nacimiento=" + getFecha_nacimiento();
    }


}
