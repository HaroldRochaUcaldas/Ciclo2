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
    protected ArrayList<Cargo> Cargos = new ArrayList();
    protected ConexionMysql conexionAdministrador;

    //private String area;
    public Administrativa(String area, String fechaIngreso, String fechaRetiro, double sueldo, long cedula, String nombres, String apellidos, String telefono, String direccion, ConexionMysql conexionAdministrador) {
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
        this.conexionAdministrador = conexionAdministrador;
    }

    public Administrativa(ConexionMysql conexionAdministrador) {
        //   super(null, null, 0, 0, null, null, null, null);
        this.conexionAdministrador = conexionAdministrador;
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
        String id;
        String[][] cargosDisponibles;
        ArrayList<String> parametros = new ArrayList<>();
        ArrayList<String> nombresParametros = new ArrayList<>();
        nombresParametros.add("nombres");
        nombresParametros.add("apellidos");
        nombresParametros.add("cedula");
        nombresParametros.add("telefono");
        nombresParametros.add("direccion");
        nombresParametros.add("fecha_ingreso");
//        nombresParametros.add("fecha_retiro");
        nombresParametros.add("sueldo");
//        nombresParametros.add("idCarnet");
        nombresParametros.add("id_cargo");
        while (operaciones != 5) {
            System.out.println("Ingrese 1.para crear subordinado, 2. para editar subordinado, 3 para mostrar informacion del subordinado, 4 para borrar subordiando 5 para salir del menu ");
            operaciones = Short.parseShort(entrada.nextLine());
            switch (operaciones) {
                case 1:
                    System.out.println("Ingrese los nombres: ");
                    nombres = entrada.nextLine();
                    parametros.add(nombres);
                    System.out.println("Ingrese los apellidos: ");
                    apellidos = entrada.nextLine();
                    parametros.add(apellidos);
                    System.out.println("Ingrese la cedula: ");
                    cedula = Long.parseLong(entrada.nextLine());
                    parametros.add(cedula + "");
                    System.out.println("Ingrese el telefono: ");
                    telefono = entrada.nextLine();
                    parametros.add(telefono);
                    System.out.println("Ingrese la direccion: ");
                    direccion = entrada.nextLine();
                    parametros.add(direccion);
                    System.out.println("Ingrese la fecha de ingreso(aaaa/mm/dd): ");
                    fechaIngreso = entrada.nextLine();
                    parametros.add(fechaIngreso);
                    System.out.println("Ingrese el sueldo: ");
                    sueldo = Double.parseDouble(entrada.nextLine());
                    parametros.add(sueldo + "");
                    conexionAdministrador.conectar();
                    //conexionAdministrador.consultaSelect("cargos");
                    Cargo cargoSubordinado = new Cargo(conexionAdministrador);
                    cargosDisponibles = cargoSubordinado.consultarCargos();
                    System.out.println("Seleccione un cargo disponible");
                    for (int j = 0; j < cargosDisponibles.length; j++) {
                        System.out.println(cargosDisponibles[0][j] + "." + cargosDisponibles[j][1]);
                    }
                    id = entrada.nextLine();
                    parametros.add(id);
                    Empleado nuevoSubordinado = new Empleado(fechaIngreso, sueldo, cedula, nombres, apellidos, telefono, direccion, cargoSubordinado);
                    this.subordinados.add(nuevoSubordinado);
                    conexionAdministrador.conectar();
                    conexionAdministrador.consultaInsert("empleados", nombresParametros, parametros);
                    conexionAdministrador.cerrarconexion();
                    cargoSubordinado.cambiarDisponibilidad("0", id);
                    break;
                case 2:
                    System.out.println("Ingrese la cedula");
                    cedula = Long.parseLong(entrada.nextLine());
                    String cedula_s = String.valueOf(cedula);
                    System.out.println(cedula_s);
                    conexionAdministrador.conectar();
                    String[][] subordinado = conexionAdministrador.consultaSelect("empleados", "nombres", "cedula", cedula_s);
                    conexionAdministrador.cerrarconexion();
                    System.out.println(subordinado.length);
                    for (int i = 0; i < subordinado.length; i++) {
                        short op = 0;
                        while (op != 11) {
                            System.out.println("ingrese instruccion o campo a modificar: 1. cedula 2. nombres, 3.apellidos, 4. telefono, 5. direccion 6. carnet, 7 fecha ingreso, 8 fecha retiro, 9 sueldo, 10 asignar cargo, 11 salir menu");
                            op = Short.parseShort(entrada.nextLine());
                            switch (op) {
                                case 1:
                                    System.out.println("Ingrese la cedula: ");
                                    cedula = Long.parseLong(entrada.nextLine());
                                    String cedula_s2 = cedula + "";
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "cedula", cedula_s2, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso del empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 2:
                                    System.out.println("Ingrese los nombres: ");
                                    nombres = entrada.nextLine();
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "nombres", nombres, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 3:
                                    System.out.println("Ingrese los apellidos: ");
                                    apellidos = entrada.nextLine();
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "apellidos", apellidos, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 4:
                                    System.out.println("Ingrese el telefono: ");
                                    telefono = entrada.nextLine();
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "telefono", telefono, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 5:
                                    System.out.println("Ingrese la direccion: ");
                                    direccion = entrada.nextLine();
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "direccion", direccion, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 6:
                                    System.out.println("Ingrese id de carnet: ");
                                    idCarnet = Long.parseLong(entrada.nextLine());
                                    String idCarnet_S = idCarnet + "";
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "idCarnet", idCarnet_S, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 7:
                                    System.out.println("Ingrese la fecha de ingreso: ");
                                    fechaIngreso = entrada.nextLine();
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "fecha_ingreso", fechaIngreso, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 8:
                                    System.out.println("Ingrese la fecha de salida: ");
                                    fechaRetiro = entrada.nextLine();
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "fecha_retiro", fechaRetiro, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;

                                case 9:
                                    System.out.println("Ingrese el sueldo: ");
                                    sueldo = Double.parseDouble(entrada.nextLine());
                                    String sueldo_s = sueldo + "";
                                    conexionAdministrador.conectar();
                                    if (conexionAdministrador.consultaUpdate("empleados", "sueldo", sueldo_s, "cedula", cedula_s)) {
                                        System.out.println("Cambio existoso de empleado ");
                                        conexionAdministrador.cerrarconexion();
                                    } else {
                                        System.out.println("no se puedo efectuar el cambio");
                                        conexionAdministrador.cerrarconexion();
                                    }
                                    break;
                                case 10:
                                    Cargo nuevo = new Cargo(conexionAdministrador);
                                    conexionAdministrador.conectar();
                                    String[][] cargoviejo = conexionAdministrador.consultaSelect("empleados", "id_cargo", "cedula", cedula_s);
                                    conexionAdministrador.cerrarconexion();
                                    cargosDisponibles = nuevo.consultarCargos();
                                    System.out.println("Seleccione un cargo disponible");
                                    for (int j = 0; j < cargosDisponibles.length; j++) {
                                        System.out.println(cargosDisponibles[0][j] + "." + cargosDisponibles[j][1]);
                                    }
                                    id = entrada.nextLine();
                                    conexionAdministrador.conectar();
                                    conexionAdministrador.consultaUpdate("empleados", "id_cargo", id, "cedula", cedula_s);
                                    conexionAdministrador.cerrarconexion();
                                    nuevo.cambiarDisponibilidad("1", cargoviejo[0][0]);
                                    if (nuevo.cambiarDisponibilidad("0", id)) {
                                        System.out.println("Cambio existoso de empleado ");
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
                    for (Empleado empleadoBusqueda : subordinados) {
                        if (empleadoBusqueda.cedula == cedula) {
                            short op = 0;
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
                                        String cargo = entrada.nextLine();
                                        Cargo nuevo = new Cargo(1, "operario", true, "operar");
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
                    cedula_s = cedula + "";
                    conexionAdministrador.conectar();
                    String[][] empleado = conexionAdministrador.consultaSelect("empleados", "cedula", cedula_s);
                    conexionAdministrador.cerrarconexion();
                    conexionAdministrador.conectar();
                    String[][] campos = conexionAdministrador.consultaNameColumns("empleados");
                    conexionAdministrador.cerrarconexion();
                    for (int i = 0; i < campos.length; i++) {
                        System.out.print(campos[i][0] + ":");
                        System.out.println(empleado[0][i]);
                    }
                    for (Empleado empleadoBusqueda : subordinados) {
                        if (empleadoBusqueda.cedula == cedula) {
                            empleadoBusqueda.descripcion();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Ingrese la cedula");
                    cedula = Long.parseLong(entrada.nextLine());
                    cedula_s = cedula + "";
                    conexionAdministrador.conectar();
                    if (conexionAdministrador.consultaDelete("empleados", "cedula", cedula_s)) {
                        System.out.println("el empleado ha sido borrado existosamente!!");
                    } else {
                        System.out.println("el empleado no se ha podido borrar de la base de datos");
                    }
                    conexionAdministrador.cerrarconexion();
                    for (Empleado empleadoBusqueda : subordinados) {
                        if (empleadoBusqueda.cedula == cedula) {
                            subordinados.remove(empleadoBusqueda);
                        }
                    }
                    break;
                case 5:
                    operaciones = 5;
                    break;
                default:
                    System.out.println("no existe la opcion");
                    break;

            }

        }
    }
}
