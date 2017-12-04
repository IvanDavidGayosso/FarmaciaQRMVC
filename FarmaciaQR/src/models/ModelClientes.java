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
    private ArrayList<String> datos_cliente = new ArrayList<>(8);

    public ModelClientes(BaseDatos base_datos) {
        this.base_datos = base_datos;

    }

    public ArrayList<String> getDatos_cliente() {
        return datos_cliente;
    }

    public void setDatos_cliente(ArrayList<String> datos_cliente) {
        this.datos_cliente = datos_cliente;
    }

    public void conectar() {
        base_datos.conectar();
        base_datos.vaciarArreglos();
        base_datos.setTabla("cliente");
        base_datos.verColumnas();
        for (int i = 0; i < 8; i++) {
            datos_cliente.add(i, " ");
        }
    }

    public void llenarValores() {

        try {
            for (int i=0;i<datos_cliente.size();i++){
                datos_cliente.set(i, base_datos.getRs().getString(i+1));
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
        base_datos.setDatos(datos_cliente);
        base_datos.insertar();
        moverPrimero();
      
    }

    public void borrar() {
        base_datos.setDatos(datos_cliente);
        base_datos.eliminar();
        moverPrimero();
     
    }

    public void actualizar() {
        base_datos.setDatos(datos_cliente);
        base_datos.modificar();
        moverPrimero();   
    }
    
}
