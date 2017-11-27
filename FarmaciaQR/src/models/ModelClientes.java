/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelClientes {

    BaseDatos base_datos;
    ArrayList<String> datos = new ArrayList<String>();
    private String id_cliente;
    private String nombre;
    private String apell_pat;
    private String apell_mat;
    private String calle;
    private String colonia;
    private String ciudad;
    private String estado;

    public String getId_cliente() {
        return id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApell_pat() {
        return apell_pat;
    }

    public String getApell_mat() {
        return apell_mat;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApell_pat(String apell_pat) {
        this.apell_pat = apell_pat;
    }

    public void setApell_mat(String apell_mat) {
        this.apell_mat = apell_mat;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void conectar() {
        base_datos.conectar();
    }

    public void llenarValores() {
        try {
            setId_cliente(base_datos.getRs().getString("id_cliente"));
            setNombre(base_datos.getRs().getString("nombre"));
            setApell_pat(base_datos.getRs().getString("apell_pat"));
            setApell_mat(base_datos.getRs().getString("apell_mat"));
            setCalle(base_datos.getRs().getString("calle"));
            setColonia(base_datos.getRs().getString("colonia "));
            setCiudad(base_datos.getRs().getString("ciudad"));
            setEstado(base_datos.getRs().getString("estado"));

        } catch (SQLException ex) {
            Logger.getLogger(ModelClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void moverPrimero() {
        base_datos.moverPrimero();
        llenarValores();
    }

     public void moverUltimo() {
        base_datos.moverUltimo();
        llenarValores();
    }

    public void moverSiguiente() {
        base_datos.moverSiguiente();
        llenarValores();
    }

    public void moverAnterior() {
        base_datos.moverAnterior();
        llenarValores();
    }

    public void seleccionarTodos() {

    }

    public void mostrarTodos() {

    }

    public void insertar() {

    }

    public void borrar(int id_pelicula) {

    }

    public void actualizar() {

    }
}
