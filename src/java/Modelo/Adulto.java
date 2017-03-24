/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author pasito
 */
public class Adulto extends Pasajero {

    public Adulto(String tratamiento, String nombre, String apellidos, String identificador, String tipoIden, LocalDate fecha_caducidad, String nacionalidad, boolean tutor, LocalDate fecha_nacimiento) {
        super(tratamiento, nombre, apellidos, identificador, tipoIden, fecha_caducidad, nacionalidad, tutor, fecha_nacimiento);
    }

    @Override
    public String toString() {
        return "Adulto: " + this.getTratamiento() + " nombre=" + this.getNombre() + ", apellidos=" + this.getApellidos() + ", Identificador=" + this.getIdentificador() + ", nacionalidad=" + this.getNacionalidad() + ", fecha_nacimiento=" + this.getFecha_nacimiento() + ", tutor=" + this.isTutor();

    }

}
