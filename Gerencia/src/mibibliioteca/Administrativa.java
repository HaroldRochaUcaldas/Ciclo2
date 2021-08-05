/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibibliioteca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author harld
 */
public class Administrativa extends Empleado {

    protected String area;
    protected ArrayList<Empleado> subordinados = new ArrayList();

    //private String area;
    public Administrativa(String area, String fechaIngreso, String fechaRetiro, double sueldo, long cedula, String nombres, String apellidos, String telefono, String direccion) {
        //    super(fechaIngreso, fechaRetiro, sueldo, cedula, nombres, apellidos, telefono, direccion);
        this.area = area;//inicializar variable area
        this.fechaIngreso = fechaIngreso;
        this.fechaRetiro = fechaRetiro;
        this.sueldo = sueldo;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
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

    @Override
    public void descripcion() {
        System.out.println("Nombre: " + this.nombres + "Apellidos: " + this.apellidos + "Telefono :" + this.telefono + "Direccion" + this.direccion + "Fecha Ingreso(dd/mmm/aa): " + this.fechaIngreso + "Fecha Retiro: " + this.fechaRetiro + "el area encargada es:" + this.area);
    }

    public void crud() {
        short operaciones = 0;
        Scanner entrada = new Scanner(System.in);
        long cedula;
        String nombres;
        String apellidos;
        String telefono;
        String direccion;
        String fechaIngreso;
        double salario;
        while (operaciones != 5) {
            System.out.println("Ingrese 1.para crear subordinado, 2. para editar subordinado, 3 para mostrar informacion del subordinado, 4 para borrar subordiando 5 para salir del menu ");
            operaciones = Short.parseShort(entrada.nextLine());
            switch (operaciones) {
                case 1:
                    System.out.println("Ingrese los nombres: ");
                    nombres = entrada.nextLine();
                    System.out.println("Ingrese los apellidos: ");
                    apellidos = entrada.nextLine();
                    System.out.println("Ingrese la cedula: ");
                    cedula = Long.parseLong(entrada.nextLine());
                    System.out.println("Ingrese el telefono: ");
                    telefono = entrada.nextLine();
                    System.out.println("Ingrese la direccion: ");
                    direccion = entrada.nextLine();
                    System.out.println("Ingrese la fecha de ingreso(dd/mm/aaaa): ");
                    fechaIngreso = entrada.nextLine();
                    System.out.println("Ingrese el sueldo: ");
                    sueldo = Double.parseDouble(entrada.nextLine());

                    Cargo cargoSubordinado = new Cargo(1, "operario", true, "operara carro");

                    Empleado nuevoSubordinado = new Empleado(fechaIngreso, sueldo, cedula, nombres, apellidos, telefono, direccion, cargoSubordinado);
                    this.subordinados.add(nuevoSubordinado);
                    break;
                case 2:
                    System.out.println("Ingrese la cedula");
                    cedula = Long.parseLong(entrada.nextLine());
                    for (Empleado empleadoBusqueda : subordinados) {
                        if (empleadoBusqueda.cedula == cedula) {
                            short op=0;
                            while (op != 11) {
                            System.out.println("ingrese instruccion o campo a modificar: 1. cedula 2. nombres, 3.apellidos, 4. telefono, 5. direccion 6. carnet, 7 fecha ingreso, 8 fecha retiro, 9 sueldo, 10 asignar cargo, 11 salir menu");
                            op = Short.parseShort(entrada.nextLine());
                                switch (op) {
                                    case 1:
                                        System.out.println("Ingrese la cedula: ");
                                        cedula = Long.parseLong(entrada.nextLine());
                                        if (empleadoBusqueda.setCedula(cedula)) {
                                            System.out.println("Cambio existoso del empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Ingrese los nombres: ");
                                        nombres = entrada.nextLine();
                                        if (empleadoBusqueda.setNombres(nombres)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Ingrese los apellidos: ");
                                        apellidos = entrada.nextLine();
                                        if (empleadoBusqueda.setApellidos(apellidos)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Ingrese el telefono: ");
                                        telefono = entrada.nextLine();
                                        if (empleadoBusqueda.setTelefono(telefono)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Ingrese la direccion: ");
                                        direccion = entrada.nextLine();
                                        if (empleadoBusqueda.setDireccion(direccion)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Ingrese id de carnet: ");
                                        idCarnet = Long.parseLong(entrada.nextLine());
                                        if (empleadoBusqueda.setIdCarnet(idCarnet)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 7:
                                        System.out.println("Ingrese la fecha de ingreso: ");
                                        fechaIngreso = entrada.nextLine();
                                        if (empleadoBusqueda.setFechaIngreso(fechaIngreso)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 8:
                                        System.out.println("Ingrese la fecha de salida: ");
                                        fechaRetiro = entrada.nextLine();
                                        if (empleadoBusqueda.setFechaRetiro(fechaRetiro)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;

                                    case 9:
                                        System.out.println("Ingrese el sueldo: ");
                                        sueldo = Double.parseDouble(entrada.nextLine());
                                        if (empleadoBusqueda.setSueldo(sueldo)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 10:
                                        System.out.println("Ingrese el cargo: ");
                                        String cargo=entrada.nextLine();
                                        Cargo nuevo= new Cargo(1, "operario", true, "operar");
                                        if (empleadoBusqueda.setSueldo(sueldo)) {
                                            System.out.println("Cambio existoso de empleado " + empleadoBusqueda.getNombres());
                                        } else {
                                            System.out.println("no se puedo efectuar el cambio");
                                        }
                                        break;
                                    case 11:
                                        op = 11;
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Ingrese la cedula");
                    cedula = Long.parseLong(entrada.nextLine());
                    for (Empleado empleadoBusqueda : subordinados) {
                        if (empleadoBusqueda.cedula == cedula) {
                            empleadoBusqueda.descripcion();
                    }
                    }
                    break;
                case 4:
                    System.out.println("Ingrese la cedula");
                    cedula = Long.parseLong(entrada.nextLine());
                    for (Empleado empleadoBusqueda : subordinados) {
                    if (empleadoBusqueda.cedula == cedula) {
                            subordinados.remove(empleadoBusqueda);
                    }
                    }
                    break;
                case 5:
                    operaciones=5;
                    break;
                default:
                    System.out.println("no existe la opcion");
                    break;

            }

        }
    }
}
