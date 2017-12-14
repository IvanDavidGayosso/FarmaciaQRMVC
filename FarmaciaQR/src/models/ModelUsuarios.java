/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan97
 */
public class ModelUsuarios {

    BaseDatos base_datos;
    private ArrayList<String> datos_usuario = new ArrayList<>(3);
    private ArrayList<String> nombre_empleado = new ArrayList<>();
    private ArrayList<String> id_empleado = new ArrayList<>();

    public ArrayList<String> getNombre_empleado() {
        return nombre_empleado;
    }

    public ArrayList<String> getId_empleado() {
        return id_empleado;
    }

    public ModelUsuarios(BaseDatos base_datos) {

        this.base_datos = base_datos;

    }

    public ArrayList<String> getDatos_usuario() {
        return datos_usuario;
    }

    public void setDatos_usuario(ArrayList<String> datos_usuario) {
        this.datos_usuario = datos_usuario;
    }

    public void conectar() {
        base_datos.conectar();

    }

    public void preparar_db() {
        base_datos.vaciarArreglos();
        base_datos.setTabla("usuarios");
        base_datos.verColumnas();
        for (int i = 0; i < 3; i++) {
            datos_usuario.add(i, " ");
        }
    }

    public void llenarValores() {

        try {
            datos_usuario.set(0, base_datos.getRs().getString("usuario"));
            datos_usuario.set(1, base_datos.getRs().getString("id_empleado"));
            datos_usuario.set(2, base_datos.getRs().getString("contrasena"));
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
        base_datos.seleccionarTodos();
        base_datos.moverPrimero();
    }

    public void insertar() {
        base_datos.setDatos(datos_usuario);
        base_datos.insertar_usuario();
        moverPrimero();

    }

    public void borrar() {
        base_datos.setDatos(datos_usuario);
        base_datos.eliminar();
        moverPrimero();

    }

    public void actualizar() {
        base_datos.setDatos(datos_usuario);
        base_datos.modificar();
        moverPrimero();
    }

    public ResultSet getResultSet() {
        return base_datos.seleccionarVista("usuario_empleado");
    }

    public void llenarEmpleados() {
        try {
            base_datos.vaciarArreglos();
            base_datos.setTabla("empleado_datos_completos");
            base_datos.seleccionarTodos();
            id_empleado.removeAll(id_empleado);
            nombre_empleado.removeAll(nombre_empleado);
            while (base_datos.getRs().next()) {
                id_empleado.add(base_datos.getRs().getString("id_empleado"));
                nombre_empleado.add(base_datos.getRs().getString("nombre")+' '+base_datos.getRs().getString("apell_pat")+' '+base_datos.getRs().getString("apell_mat"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
