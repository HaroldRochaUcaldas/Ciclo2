/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mibibliioteca;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author harld
 */
public class ConexionMysql {

    private String url;
    private String usuario;
    private String contraseña;
    private String basedeDatos;
    private Connection miconexion;
    private ArrayList<String> hConsultas;
    //   private ArrayList<String> parametros;
    //   private String nombreTabla;

    public ConexionMysql(String url, String usuario, String contraseña, String baseDedatos) {
        this.url = "jdbc:mysql://" + url + ":3306";
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.basedeDatos = baseDedatos;
    }

    public ConexionMysql(String url, String puerto, String usuario, String contraseña, String baseDedatos) {
        this.url = "jdbc:mysql://" + url + ":" + puerto;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.basedeDatos = baseDedatos;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getBasedeDatos() {
        return basedeDatos;
    }

    public ArrayList<String> gethConsultas() {
        return hConsultas;
    }

    public boolean setUrl(String url) {
        this.url = url;
        return true;
    }

    public boolean setUsuario(String usuario) {
        this.usuario = usuario;
        return true;
    }

    public boolean setContraseña(String contraseña) {
        this.contraseña = contraseña;
        return true;
    }

    public boolean setBasedeDatos(String basedeDatos) {
        this.basedeDatos = basedeDatos;
        return true;
    }

    public boolean sethConsultas(ArrayList<String> hConsultas) {
        this.hConsultas = hConsultas;
        return true;
    }

    public boolean conectar() {
        boolean est_conexion;
        try {
            this.miconexion = DriverManager.getConnection(url + "/" + basedeDatos, usuario, contraseña);
            est_conexion = true;
        } catch (Exception e) {
            System.out.println("error en conexion" + e);
            est_conexion = false;
        }
        return est_conexion;
    }

    public void cerrarconexion() {
        try {
            this.miconexion.close();
        } catch (Exception e) {
            System.out.println("no se pudo cerrar la conexion");
        }
    }

    public boolean consultaInsert(String nombreTabla, ArrayList<String> nombresParametros, ArrayList<String> parametros) {
        String consulta = "insert into " + nombreTabla + " (";
        boolean est_consulta;
        for (int i = 0; i < nombresParametros.size(); i++) {
            if (i + 1 == nombresParametros.size()) {
                consulta = consulta + nombresParametros.get(i) + " ) values ('";
            } else {
                consulta = consulta + nombresParametros.get(i) + ", ";
            }
        }
        for (int i = 0; i < parametros.size(); i++) {
            if (i + 1 == parametros.size()) {
                consulta = consulta + parametros.get(i) + "')";
            } else {
                consulta = consulta + parametros.get(i) + "', '";
            }
        }
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            /*            for (int i = 0; i < parametros.size(); i++) {
                pst.setString(i + 1, parametros.get(i));
                // System.out.println(parametros.get(i)+"");
            }*/
            pst.executeUpdate();
            pst.close();
            est_consulta = true;
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
            est_consulta = false;
        }
        return est_consulta;
    }

    public boolean consultaUpdate(String nombreTabla, ArrayList<String> nombresParametros, ArrayList<String> parametros, String nombreCampoDonde, String elemento) {
        String consulta = "update " + nombreTabla + " set ";
        boolean est_consulta;
        for (int i = 0; i < nombresParametros.size(); i++) {
            if (i + 1 == nombresParametros.size()) {
                consulta = consulta + nombresParametros.get(i) + " = ? where " + nombreCampoDonde + " = " + elemento;
            } else {
                consulta = consulta + nombresParametros.get(i) + " = ?,";
            }
        }
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            for (int i = 0; i < parametros.size(); i++) {
                pst.setString(i + 1, parametros.get(i));
                //  System.out.println(parametros.get(i)+"");
            }
            pst.executeUpdate();
            pst.close();
            est_consulta = true;
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
            est_consulta = false;
        }
        return est_consulta;
    }

    public boolean consultaUpdate(String nombreTabla, String nombresParametro, String parametro, String nombreCampoDonde, String elemento) {
        String consulta = "update " + nombreTabla + " set ";
        boolean est_consulta;

        consulta = consulta + nombresParametro + " = ? where " + nombreCampoDonde + " = " + elemento;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            pst.setString(1, parametro);
            pst.executeUpdate();
            pst.close();
            est_consulta = true;
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
            est_consulta = false;
        }
        return est_consulta;
    }

    public String[][] consultaNameColumns(String nombreTabla) {
        String consulta = "SHOW COLUMNS FROM " +this.basedeDatos+"."+ nombreTabla + ";";
        String[][] datos = null;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
//                  int size = 10;
            rs.last();
            int sizeRow = rs.getRow();
            rs.first();
            int sizeColumn = rs.getMetaData().getColumnCount();
            rs.first();
            datos = new String[sizeRow][sizeColumn];
            //System.out.println(sizeRow);
            //System.out.println(sizeColumn);
            for (int i = 1; i <= sizeRow; i++) {
                for (int j = 1; j <= sizeColumn; j++) {
           //         System.out.println(rs.getString(j));
                    datos[i - 1][j - 1] = rs.getString(j);
                }
                rs.next();
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
        }
        return datos;
    }
    
    public String[][] consultaSelect(String nombreTabla) {
        String consulta = "select * from " + nombreTabla + ";";
        String[][] datos = null;
       System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
//                  int size = 10;
            rs.last();
            int sizeRow = rs.getRow();
            rs.first();
            int sizeColumn = rs.getMetaData().getColumnCount();
            rs.first();
            datos = new String[sizeRow][sizeColumn];
           // System.out.println(sizeRow);
           // System.out.println(sizeColumn);
            for (int i = 1; i <= sizeRow; i++) {
                for (int j = 1; j <= sizeColumn; j++) {
         //           System.out.println(rs.getString(j));
                    datos[i - 1][j - 1] = rs.getString(j);
                }
                rs.next();
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
        }
        return datos;
    }

    public String[][] consultaSelect(String nombreTabla, String nombreCampoDonde, String elemento) {
        String consulta = "select * from " + nombreTabla + " where " + nombreCampoDonde + " = " + elemento;
        String[][] datos = null;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            rs.last();
            int sizeRow = rs.getRow();
            rs.first();
            int sizeColumn = rs.getMetaData().getColumnCount();
            rs.first();
            datos = new String[sizeRow][sizeColumn];
            //System.out.println(sizeRow);
            //System.out.println(sizeColumn);
            for (int i = 1; i <= sizeRow; i++) {
                for (int j = 1; j <= sizeColumn; j++) {
       //             System.out.println(rs.getString(j));
                    datos[i - 1][j - 1] = rs.getString(j);
                }
                rs.next();
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
        }
        return datos;
    }

    public String[][] consultaSelect(String nombreTabla, ArrayList<String> nombresParametros, String nombreCampoDonde, String elemento) {
        String consulta = "select ";
        String[][] datos = null;
        for (int i = 0; i < nombresParametros.size(); i++) {
            if (i + 1 == nombresParametros.size()) {
                consulta = consulta + " " + nombresParametros.get(i) + " ";
            } else {
                consulta = consulta + " " + nombresParametros.get(i) + ", ";
            }
        }
        consulta = consulta + " from " + nombreTabla + " where " + nombreCampoDonde + " = " + elemento;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            rs.last();
            int sizeRow = rs.getRow();
            rs.first();
            int sizeColumn = rs.getMetaData().getColumnCount();
            rs.first();
            datos = new String[sizeRow][sizeColumn];
            //System.out.println(sizeRow);
            //System.out.println(sizeColumn);
            for (int i = 1; i <= sizeRow; i++) {
                for (int j = 1; j <= sizeColumn; j++) {
     //               System.out.println(rs.getString(j));
                    datos[i - 1][j - 1] = rs.getString(j);
                }
                rs.next();
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
        }
        return datos;
    }

    public String[][] consultaSelect(String nombreTabla, String nombresParametro, String nombreCampoDonde, String elemento) {
        String consulta = "select ";
        String[][] datos = null;
        consulta = consulta + " " + nombresParametro + " ";
        consulta = consulta + " from " + nombreTabla + " where " + nombreCampoDonde + " = " + elemento;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            rs.last();
            int sizeRow = rs.getRow();
            rs.first();
            int sizeColumn = rs.getMetaData().getColumnCount();
            rs.first();
            datos = new String[sizeRow][sizeColumn];
            //System.out.println(sizeRow);
           // System.out.println(sizeColumn);
            for (int i = 1; i <= sizeRow; i++) {
                for (int j = 1; j <= sizeColumn; j++) {
                   // System.out.println(rs.getString(j));
                    datos[i - 1][j - 1] = rs.getString(j);
                }
                rs.next();
            }
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
        }
        return datos;
    }

    public boolean consultaDelete(String nombreTabla, String nombreCampoDonde, String elemento) {
        String consulta = "DELETE FROM " + nombreTabla + " WHERE " + nombreCampoDonde + "=" + elemento + ";";
        boolean est_consulta;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            pst.executeUpdate();

            pst.close();
            est_consulta = true;
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
            est_consulta = false;
        }
        return est_consulta;
    }
}
