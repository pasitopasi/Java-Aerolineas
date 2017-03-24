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
public class Bebe extends Pasajero{

    public Bebe(String nombre, String apellidos, String identificador, String tipoIden, LocalDate fecha_caducidad, String nacionalidad, String ID_tutor, LocalDate fecha_nacimiento) {
        super("", nombre, apellidos, identificador, tipoIden, fecha_caducidad, nacionalidad, false, ID_tutor, fecha_nacimiento);
    }
    
    
    
    @Override
    public String toString() {
        return "Bebe:" + " nombre=" + this.getNombre() + ", apellidos=" + this.getApellidos() + ", identificador=" + this.getIdentificador() + ", nacionalidad=" + this.getNacionalidad() + ", fecha_nacimiento=" + this.getFecha_nacimiento() + ", IDTutor=" + this.getID_tutor();
    }
}
