/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author pasito
 */
public class arrayPosiciones {
    private ArrayList<Posicion> posiciones;
    
    public arrayPosiciones(){
        this.posiciones = new ArrayList<>();
    }
    
    public void add(Posicion p){
        this.posiciones.add(p);
    }
    
    public int obtenerID(int pos){
        for (Posicion posicion : this.posiciones) {
            if(posicion.getPosicion()==pos){
                return posicion.getID();
            }
        }
        return 0;
    }
}
