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
        this.url = "jdbc:mysql://" + url;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setBasedeDatos(String basedeDatos) {
        this.basedeDatos = basedeDatos;
    }

    public void sethConsultas(ArrayList<String> hConsultas) {
        this.hConsultas = hConsultas;
    }
    
    public void conectar() {
        try {
            this.miconexion = DriverManager.getConnection(url + "/" + basedeDatos, usuario, contraseña);
        } catch (Exception e) {
            System.out.println("error en conexion" + e);
        }
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
                consulta = consulta + parametros.get(i)+"')";
            } else {
                consulta = consulta + parametros.get(i) +"', '";
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
    
        public boolean consultaSelect(String nombreTabla) {
        String consulta = "select * from " + nombreTabla + ";";
        boolean est_consulta;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            int i = 1;
//           int size = 10;
            int size = rs.getMetaData().getColumnCount();
            rs.first();
            System.out.println(size);
            while (i-1 != size) {
                System.out.println(rs.getString(i));
                i++;
            }
            pst.close();
            rs.close();
            est_consulta = true;
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
            est_consulta = false;
        }
        return est_consulta;
    }

    public boolean consultaSelect(String nombreTabla, String nombreCampoDonde, String elemento) {
        String consulta = "select * from " + nombreTabla + " where " + nombreCampoDonde + " = " + elemento;
        boolean est_consulta;
        System.out.println(consulta);
        try {
            PreparedStatement pst = this.miconexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            int i = 1;
//           int size = 10;
            int size = rs.getMetaData().getColumnCount();
            rs.first();
            System.out.println(size);
            while (i-1 != size) {
                System.out.println(rs.getString(i));
                i++;
            }
            pst.close();
            rs.close();
            est_consulta = true;
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
            est_consulta = false;
        }
        return est_consulta;
    }

    public boolean consultaSelect(String nombreTabla, ArrayList<String> nombresParametros, String nombreCampoDonde, String elemento) {
        String consulta = "select ";
        boolean est_consulta;
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
            int i = 1;
//           int size = 10;
            int size = rs.getMetaData().getColumnCount();
            rs.first();
            System.out.println(size);
            while (i-1 != size) {
                System.out.println(rs.getString(i));
                i++;
            }
            pst.close();
            rs.close();
            est_consulta = true;
        } catch (Exception e) {
            System.out.println("no se puedo insertar los valores" + e);
            est_consulta = false;
        }
        return est_consulta;
    }
    
    public void consultaDelete(String nombreTabla,String nombreCampoDonde, String elemento){
          String consulta="DELETE FROM " +nombreTabla + " WHERE "+ nombreCampoDonde+"="+elemento+";";
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
    }
}
