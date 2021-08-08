/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibibliioteca;

import java.util.ArrayList;

/**
 *
 * @author harld
 */
public class Cargo {
    private long id;
    private String nombre;
    private  boolean disponibilidad;
    private String funciones;
    private ConexionMysql conexioCargos;

    public Cargo(long id, String nombre, boolean disponibilidad, String funciones) {
        this.id = id;
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.funciones = funciones;
    }    

    public Cargo(ConexionMysql conexionCargos) {
        this.conexioCargos=conexionCargos;
    }
    
    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public String getFunciones() {
        return funciones;
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

    public boolean setFunciones(String funciones) {
        this.funciones = funciones;
        return true;
    }
    
    public String[][] consultarCargos(){
       conexioCargos.conectar();
       ArrayList<String> nombresParametros=new ArrayList<>();
       nombresParametros.add("id");
       nombresParametros.add("nombre");
       String[][] datos=conexioCargos.consultaSelect("cargos", nombresParametros, "disponibilidad", "1");
       conexioCargos.cerrarconexion();
       return datos;
    }
    
    public boolean cambiarDisponibilidad(String disponibilidad,String idCargo){
        conexioCargos.conectar();
        conexioCargos.consultaUpdate("cargos", "disponibilidad",disponibilidad,"id", idCargo);
        conexioCargos.cerrarconexion();
        return true;
    }
}
