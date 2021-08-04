/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibibliioteca;

/**
 *
 * @author harld
 */
public class Cargo {
    private long id;
    private String nombre;
    private  boolean disponibilidad;

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public boolean setId(long id) {
        this.id = id;
        return true; 
    }

    public boolean setNombre(String nombre) {
        this.nombre = nombre;
        return true;
    }

    public boolean setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
        return true;
    }
    
    
    
}
