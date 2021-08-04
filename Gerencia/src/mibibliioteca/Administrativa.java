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
public class Administrativa extends Empleado{
    protected String area;//declarar varibale area tipo 
    //private String area;
    public Administrativa(String area,String fechaIngreso,String fechaRetiro,double sueldo,long cedula, String nombres,String apellidos,String telefono, String direccion ) {
    //    super(fechaIngreso, fechaRetiro, sueldo, cedula, nombres, apellidos, telefono, direccion);
        this.area=area;//inicializar variable area
        this.fechaIngreso=fechaIngreso;
        this.fechaRetiro=fechaRetiro;
        this.sueldo=sueldo;
        this.cedula=cedula;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.telefono=telefono;
        this.direccion=direccion;       
    }

   public Administrativa() {
     //   super(null, null, 0, 0, null, null, null, null);
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    
}
