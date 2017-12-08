/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ivan97
 */
public class BaseDatos {

    private String tabla;
    ArrayList<String> columnas = new ArrayList<>();
    ArrayList<String> tipo_dato = new ArrayList<>();
    private ArrayList<String> datos = new ArrayList<>();
    Connection conexion;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    CallableStatement pro;
   

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public ArrayList<String> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<String> datos) {
        this.datos = datos;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void conectar() {
        try {
            //conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/acme_mvc", "postgres", "1234");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/farmacia", "root", "12345678");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 101");
        }
    }

    public void moverPrimero() {
        try {
            rs.first();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 102");
        }
    }

    public void moverUltimo() {
        try {
            rs.last();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 103");
        }
    }

    public void moverSiguiente() {
        try {
            if (rs.isLast() == false) {
                rs.next();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 104");
        }
    }

    public void moverAnterior() {
        try {
            if (rs.isFirst() == false) {
                rs.previous();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 105");
        }
    }

    public void seleccionarTodos() {

        try {

            sql = "select * from " + tabla + ";";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 106 " + ex + "");
        }

    }

    public void seleccionar() {

        try {
            //admin' or '1'='1
            sql = "select * from usuario_empleado where usuario = ? and contrasena = MD5(?);";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, datos.get(0));
            ps.setString(2, datos.get(1));
            rs = ps.executeQuery();
            datos.set(0, " ");
            datos.set(1, " ");
            while (rs.next()) {
                datos.set(0, rs.getString("id_empleado"));
                datos.set(1,rs.getString("cargo"));
            }
 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 106 " + ex + "");
        }
    }

    public void insertar() {
        try {
            sql = "INSERT INTO " + tabla;//" (" + columnas + ") =?) VALUES (" + datos + ");";
            String columnas = "(";
            String datos = "(";
            for (int i = 1; i < this.columnas.size(); i++) {
                if (i == this.columnas.size() - 1) {
                    columnas = columnas + this.columnas.get(i) + ") ";
                    datos = datos + "?); ";
                } else {
                    columnas = columnas + this.columnas.get(i) + ", ";
                    datos = datos + "? , ";
                }
            }
            sql = sql + columnas + " values " + datos;

            ps = conexion.prepareStatement(sql);
            for (int i = 1; i < tipo_dato.size(); i++) {
                if (tipo_dato.get(i).equals("varchar")) {
                    ps.setString(i, this.datos.get(i));
                } else if (tipo_dato.get(i).equals("int")) {
                    ps.setInt(i, Integer.parseInt(this.datos.get(i)));
                }
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 108 " + ex + "");
        }
    }

    public void insertar(String tabla) {
        try {
            sql = "INSERT INTO " + tabla;//" (" + columnas + ") =?) VALUES (" + datos + ");";
            String columnas = "(";
            String datos = "(";
            for (int i = 1; i < this.columnas.size(); i++) {
                if (i == this.columnas.size() - 1) {
                    columnas = columnas + this.columnas.get(i) + ") ";
                    datos = datos + "?); ";
                } else {
                    columnas = columnas + this.columnas.get(i) + ", ";
                    datos = datos + "? , ";
                }
            }
            sql = sql + columnas + " values " + datos;
            System.out.println(sql);
            ps = conexion.prepareStatement(sql);
            for (int i = 1; i < tipo_dato.size(); i++) {
                if (tipo_dato.get(i).equals("varchar")) {
                    ps.setString(i, this.datos.get(i));
                } else if (tipo_dato.get(i).equals("int") || tipo_dato.get(i).equals("tinyint")) {
                    ps.setInt(i, Integer.parseInt(this.datos.get(i)));
                }
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 109 " + ex + "");
        }
    }

    public void eliminar() {
        try {
            sql = "DELETE FROM " + tabla + " WHERE " + columnas.get(0) + "=?;";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, datos.get(0));
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 107 " + ex + "");
        }
    }

    public void verColumnas() {
        try {
            int con = 0;
            String tipo_dato = "";
            sql = "describe " + tabla + ";";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.columnas.add(con, rs.getString(1));
                for (int i = 0; i < rs.getString(2).length(); i++) {
                    if ('(' == rs.getString(2).charAt(i)) {
                        break;
                    }
                    tipo_dato = tipo_dato + rs.getString(2).charAt(i);

                }
                this.tipo_dato.add(tipo_dato);
                tipo_dato = "";
                con += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        try {

            String auxilar_id = "", auxiliar_dato = "";
            for (int i = 0; i < this.tipo_dato.size(); i++) {
                if (i == 0) {
                    auxilar_id = this.tipo_dato.get(i);
                    auxiliar_dato = this.datos.get(i);
                }
                if (tipo_dato.size() - 1 == i) {
                    this.tipo_dato.set(i, auxilar_id);
                    this.datos.set(i, auxiliar_dato);
                } else {
                    this.tipo_dato.set(i, this.tipo_dato.get(i + 1));
                    this.datos.set(i, this.datos.get(i + 1));
                }

            }

            sql = "UPDATE " + tabla + " SET ";//" (" + columnas + ") =?) VALUES (" + datos + ");";
            String columnas = "";
            String condicion = "";
            for (int i = 0; i < this.columnas.size(); i++) {
                if (i == 0) {
                    condicion += this.columnas.get(i) + " =? ;";
                } else if (i == this.columnas.size() - 1) {
                    columnas = columnas + this.columnas.get(i) + "=? ";
                } else {
                    columnas = columnas + this.columnas.get(i) + "=? , ";

                }
            }
            sql = sql + columnas + " where " + condicion;

            ps = conexion.prepareStatement(sql);
            for (int i = 0; i < tipo_dato.size(); i++) {

                if (tipo_dato.get(i).equals("varchar")) {

                    ps.setString(i + 1, this.datos.get(i));

                } else if (tipo_dato.get(i).equals("int")) {
                    ps.setInt(i + 1, Integer.parseInt(this.datos.get(i)));
                }
            }

            for (int i = this.tipo_dato.size() - 1; i >= 0; i--) {
                if (i == tipo_dato.size() - 1) {
                    auxilar_id = this.tipo_dato.get(i);
                    auxiliar_dato = this.datos.get(i);
                }
                if (0 == i) {
                    this.tipo_dato.set(i, auxilar_id);
                    this.datos.set(i, auxiliar_dato);
                } else {
                    this.tipo_dato.set(i, this.tipo_dato.get(i - 1));
                    this.datos.set(i, this.datos.get(i - 1));
                }

            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar_procedimientos() {
        try {
            String proceso = "CALL delCliente(?)";
            pro = conexion.prepareCall(proceso);   
                pro.setString(1, datos.get(0)); 
            pro.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void vaciarArreglos() {
        columnas.removeAll(columnas);
        datos.removeAll(datos);
        tipo_dato.removeAll(tipo_dato);
    }

    private String getCifrado(String texto, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.err.println("Error " + e.getMessage());
        }
        return "";
    }

    public String md5(String texto) {
        return getCifrado(texto, "MD5");
    }

    public String sha1(String texto) {
        return getCifrado(texto, "SHA1");
    }

    public void insertar_procedimiento() {
        try {
            String proceso = "CALL insCliente(?,?,?,?,?,?,?)";
            pro = conexion.prepareCall(proceso);
            for (int i = 1; i < datos.size(); i++) {
                pro.setString(i, datos.get(i));
            }
            pro.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar_procediemiento() {
        try {

            String proceso = "CALL updCliente(?,?,?,?,?,?,?,?)";
            pro = conexion.prepareCall(proceso);
            for (int i = 0; i < datos.size(); i++) {
                pro.setString(i + 1, datos.get(i));
            }
            pro.execute();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public String  seleccionar_procedimiento(){
         String resultado= " ";
        try {
                String proceso = "CALL selCliente(?,?,?,?)";
                pro = conexion.prepareCall(proceso);
                pro.setString(1, datos.get(0));
                pro.setString(2, datos.get(1));
                pro.setString(3, datos.get(2));
                pro.registerOutParameter("resultado", Types.VARCHAR);
                pro.execute();
                resultado = pro.getString("resultado");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}
