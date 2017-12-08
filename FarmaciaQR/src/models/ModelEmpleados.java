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

/**
 *
 * @author ivan97
 */
public class ModelEmpleados {

    BaseDatos base_datos;
    private ArrayList<String> datos_empleado = new ArrayList<>(10);

    public ModelEmpleados(BaseDatos base_datos) {
        
        this.base_datos = base_datos;

    }

    public ArrayList<String> getDatos_empleado() {
        return datos_empleado;
    }

    public void setDatos_empleado(ArrayList<String> datos_empleado) {
        this.datos_empleado = datos_empleado;
    }

    public void conectar() {
        base_datos.conectar();
        
    }
    public void preparar_db(){
        base_datos.vaciarArreglos();
        base_datos.setTabla("empleado_datos_completos");
        base_datos.verColumnas();
        for (int i = 0; i < 10; i++) {
            datos_empleado.add(i, " ");
        }
    }
    
    public void llenarValores() {

        try {
            for (int i = 0; i < 10; i++) {
                datos_empleado.set(i, base_datos.getRs().getString(i + 1));
            }

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
        base_datos.seleccionarTodos();
        base_datos.moverPrimero();
    }

    public void insertar() {
        base_datos.setDatos(datos_empleado);
        base_datos.insertar("empleados");
        moverPrimero();

    }

    public void borrar() {
        base_datos.setDatos(datos_empleado);
        base_datos.eliminar( );
        moverPrimero();

    }

    public void actualizar() {
        base_datos.setDatos(datos_empleado);
        base_datos.modificar( );
        moverPrimero();
    }
  
}
