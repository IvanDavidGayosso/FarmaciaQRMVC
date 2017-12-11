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
import javax.swing.table.TableModel;

public class ModelMedicamentos {

    BaseDatos base_datos;
    private ArrayList<String> datos_medicamento = new ArrayList<>();
    String resultado = "";
    public String getResultado() {
        return resultado;
    }
    
    public ModelMedicamentos(BaseDatos base_datos) {
        this.base_datos = base_datos;

    }

    public ArrayList<String> getDatos_medicamento() {
        return datos_medicamento;
    }

    public void setDatos_medicamento(ArrayList<String> datos_medicamento) {
         this.datos_medicamento= datos_medicamento;
    }

    public void conectar() {
        base_datos.conectar();
        base_datos.vaciarArreglos();
        base_datos.setTabla("medicamentos");
        base_datos.verColumnas();
        for (int i = 0; i < 15; i++) {
            datos_medicamento.add(i, " ");
        }
    }

    public void llenarValores() {

        try {
            for (int i = 0; i < 15; i++) {
                datos_medicamento.set(i, base_datos.getRs().getString(i + 1));
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
        base_datos.setDatos (datos_medicamento);
        base_datos.insertar();
        moverPrimero();

    }

    public void borrar() {
        base_datos.setDatos (datos_medicamento);
        base_datos.eliminar();
        moverPrimero();

    }

    public void actualizar() {
        base_datos.setDatos (datos_medicamento);
        base_datos.modificar();
        base_datos.seleccionarTodos();
        moverPrimero();
    }

    public void buscar() {
      
    }

    public ResultSet getResultSet() {
        return base_datos.seleccionarVista("informacion_medicamentos");
    }

}
