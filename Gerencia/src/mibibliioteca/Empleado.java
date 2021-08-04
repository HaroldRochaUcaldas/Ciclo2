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
public abstract class Empleado extends Persona {
    protected String fechaIngreso;
    protected String fechaRetiro;
    protected double sueldo;
    
/*    private String fechaIngreso;
    private String fechaRetiro;
    private double sueldo;*/

    /*public Empleado(String fechaIngreso, String fechaRetiro, double sueldo, long cedula, String nombres, String apellidos, String telefono, String direccion) {
//        super(cedula, nombres, apellidos, telefono, direccion);
        this.fechaIngreso = fechaIngreso;
        this.fechaRetiro = fechaRetiro;
        this.sueldo = sueldo;
    }*/
    
    
    
    @Override
    public void funciones(Cargo miCargo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public double getSueldo() {
        return sueldo;
    }

    public boolean setFechaIngreso(String FechaIngreso) {
        this.fechaIngreso = FechaIngreso;
        return true;
    }

    public boolean setFechaRetiro(String FechaRetiro) {
        this.fechaRetiro = FechaRetiro;
        return true;
    }

    public boolean setSueldo(double sueldo) {
        this.sueldo = sueldo;
        return true;
    }   
}
