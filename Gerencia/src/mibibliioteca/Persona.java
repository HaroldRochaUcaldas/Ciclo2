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
public abstract class Persona {
    
    protected long cedula;
    protected String nombres;
    protected String apellidos;
    protected String telefono;
    protected String direccion;
    
   /* private long cedula;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;*/

/*    public Persona(long cedula, String nombres, String apellidos, String telefono, String direccion) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }*/
    
    
    public long getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean setCedula(long cedula) {
        this.cedula = cedula;
        return true;
    }

    public boolean setNombres(String nombres) {
        this.nombres = nombres;
        return true;
    }

    public boolean setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return true;
    }

    public boolean setTelefono(String telefono) {
        this.telefono = telefono;
        return true;
    }

    public boolean setDireccion(String direccion) {
        this.direccion = direccion;
        return true;
    }
    
    public abstract void descripcion();
}
