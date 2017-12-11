/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.util.ArrayList;


public class ModelLogin {

    BaseDatos base_datos;
    private ArrayList<String> datos_usuario = new ArrayList<>(2);
   
    public ModelLogin(BaseDatos base_datos) {
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
        base_datos.vaciarArreglos();
        base_datos.setTabla("usuario_empleado");
        base_datos.verColumnas();
        for (int i = 0; i < 2; i++) {
            datos_usuario.add(i, " ");
        }
    }

    public void seleccionarTodos() {
        base_datos.setDatos(datos_usuario);
        base_datos.seleccionar();
        datos_usuario = base_datos.getDatos();
    }

}
