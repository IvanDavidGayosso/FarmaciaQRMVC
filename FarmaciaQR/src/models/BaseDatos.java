/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/acme_mvc", "root", "12345678");

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

    public void seleccionarTodos(String tabla) {

        try {
            sql = "select * from " + tabla + ";";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 106 " + ex + "");
        }

    }

    public void seleccionar(String user, String pass) {

        try {
            sql = "select * from usuarios where user=" + user + " and password=" + pass + ";";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 106 " + ex + "");
        }

    }

    public void insertar() {
        try {
            sql = "INSERT INTO " + getTabla();//" (" + columnas + ") =?) VALUES (" + datos + ");";
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
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 108 " + ex + "");
        }
    }

    public void eliminar(String columna, String clave) {
        try {
            sql = "DELETE FROM " + tabla + " WHERE " + columna + "=?;";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, clave);
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 107 " + ex + "");
        }
    }

    public void verColumnas() {
        try {
            String tipo_dato = "";
            int con = 0;
            sql = "describe " + getTabla() + ";";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                this.columnas.add(con, rs.getString(1));
                for (int i = 0; i < rs.getString(2).length(); i++) {
                    if ('(' == rs.getString(2).charAt(i)) {
                        this.tipo_dato.add(con, rs.getString(2).substring(0, i));
                    }
                }
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

            sql = "UPDATE " + getTabla() + " SET ";//" (" + columnas + ") =?) VALUES (" + datos + ");";
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
                }
                if (0 == i) {
                    this.tipo_dato.set(i, auxilar_id);
                } else {
                    this.tipo_dato.set(i, this.tipo_dato.get(i - 1));
                }

            }
            ps.executeUpdate();
            moverPrimero();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
