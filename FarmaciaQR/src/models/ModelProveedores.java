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

public class ModelProveedores {

    BaseDatos base_datos;
    private ArrayList<String> datos_proveedor = new ArrayList<>(7);

    public ModelProveedores(BaseDatos base_datos) {
        this.base_datos = base_datos;

    }

    public ArrayList<String> getDatos_proveedor() {
        return datos_proveedor;
    }

    public void setDatos_proveedor(ArrayList<String> datos_proveedor) {
        this.datos_proveedor = datos_proveedor;
    }

    public ArrayList<String> getDatos_cliente() {
        return getDatos_proveedor();
    }

    public void setDatos_cliente(ArrayList<String> datos_proveedor) {
        this.setDatos_proveedor(datos_proveedor);
    }

    public void conectar() {
        base_datos.conectar();
        
    }
    public void preparar_db(){
        base_datos.vaciarArreglos();
        base_datos.setTabla("proveedores" );
        base_datos.verColumnas();
        for (int i = 0; i < 7; i++) {
                datos_proveedor.add(i, " ");
        }
    }

    public void llenarValores() {

        try {
            for (int i=0;i<7;i++){
                getDatos_proveedor().set(i, base_datos.getRs().getString(i+1));
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
        base_datos.seleccionarTodos( );
        base_datos.moverPrimero();
    }

    public void insertar() {
        base_datos.setDatos(getDatos_proveedor());
        base_datos.insertar();
        moverPrimero();
      
    }

    public void borrar() {
        base_datos.setDatos(getDatos_proveedor());
        base_datos.eliminar( );
        moverPrimero();
     
    }

    public void actualizar() {
        base_datos.setDatos(getDatos_proveedor());
        base_datos.modificar( );
        moverPrimero();   
    }
    
}
