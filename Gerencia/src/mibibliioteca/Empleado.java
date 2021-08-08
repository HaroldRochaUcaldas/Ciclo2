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
public class Empleado extends Persona {

    protected long idCarnet;
    protected String fechaIngreso;
    protected String fechaRetiro;
    protected double sueldo;
    protected Cargo miCargo;

    /*    private String fechaIngreso;
    private String fechaRetiro;
    private double sueldo;*/
    public Empleado(long idCarnet, String fechaIngreso, String fechaRetiro, double sueldo, long cedula, String nombres, String apellidos, String telefono, String direccion, Cargo miCargo) {
//        super(cedula, nombres, apellidos, telefono, direccion);
        this.idCarnet = idCarnet;
        this.fechaIngreso = fechaIngreso;
        this.fechaRetiro = fechaRetiro;
        this.sueldo = sueldo;
        this.miCargo = miCargo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Empleado(String fechaIngreso, String fechaRetiro, double sueldo, long cedula, String nombres, String apellidos, String telefono, String direccion, Cargo miCargo) {
//        super(cedula, nombres, apellidos, telefono, direccion);
        this.fechaIngreso = fechaIngreso;
        this.fechaRetiro = fechaRetiro;
        this.sueldo = sueldo;
        this.miCargo = miCargo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Empleado(String fechaIngreso, double sueldo, long cedula, String nombres, String apellidos, String telefono, String direccion, Cargo miCargo) {
//        super(cedula, nombres, apellidos, telefono, direccion);
        this.fechaIngreso = fechaIngreso;
        this.sueldo = sueldo;
        this.miCargo = miCargo;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Empleado() {
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

    public Cargo getMiCargo() {
        return miCargo;
    }

    public long getIdCarnet() {
        return idCarnet;
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

    public boolean setMiCargo(Cargo miCargo) {
        this.miCargo = miCargo;
        return true;
    }

    public boolean setIdCarnet(long idCarnet) {
        this.idCarnet = idCarnet;
        return true;
    }

    @Override
    public void descripcion() {
        System.out.println("Nombre: " + this.nombres + "Apellidos: " + this.apellidos + "Telefono :" + this.telefono + "Direccion" + this.direccion + "Fecha Ingreso(dd/mmm/aa): " + this.fechaIngreso + "Fecha Retiro: " + this.fechaRetiro);
    }
}
