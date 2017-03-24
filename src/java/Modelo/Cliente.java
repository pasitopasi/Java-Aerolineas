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
public class Cliente {

    private int ID;
    private String Nombre;
    private String Apellido;
    private String DNI;
    private String tipoIden;
    private String Direccion;
    private String Correo;
    private String Usuario;
    private String Password;
    private String telf;
    private String tratamiento;

    public Cliente(String tratamiento, String Nombre, String Apellido, String tipoIden,String DNI, String Direccion, String Correo, String Usuario, String Password, String telf) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.Usuario = Usuario;
        this.Password = Password;
        this.telf = telf;
        this.tratamiento = tratamiento;
        this.tipoIden = tipoIden;
    }
    
    public Cliente(int ID){
        this.ID = ID;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getTipoIden() {
        return tipoIden;
    }

    public void setTipoIden(String tipoIden) {
        this.tipoIden = tipoIden;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }
    
    /**
     * @return the telf
     */
    public String getTelf() {
        return telf;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
    /**
     * @param telf the telf to set
     */
    public void setTelf(String telf) {
        this.telf = telf;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the Usuario
     */
    public String getUsuario() {
        return Usuario;
    }

    /**
     * @return the Correo
     */
    public String getCorreo() {
        return Correo;
    }

    /**
     * @param Correo the Correo to set
     */
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    /**
     * @param Usuario the Usuario to set
     */
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "Cliente: " + "Nombre=" + Nombre + ", Apellido=" + Apellido + ", DNI=" + DNI + ", Direccion=" + Direccion + ", Correo=" + Correo + ", Password=" + Password+ ", Telefono=" + telf;
    }

}
