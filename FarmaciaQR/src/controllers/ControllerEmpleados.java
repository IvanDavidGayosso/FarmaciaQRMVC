/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import views.ViewEmpleado;
import models.ModelEmpleados;

public class ControllerEmpleados {

    ModelEmpleados model_empleado;
    ViewEmpleado view_empleado;
    ArrayList<String> datos_empleado= new ArrayList<>(8);

    public ControllerEmpleados(Object[] models, Object[] views) {
        this.model_empleado = (ModelEmpleados) models[2];
        this.view_empleado = (ViewEmpleado) views[2];
        view_empleado.jb_primero.addActionListener(e -> jb_moverPrimer());
        view_empleado.jb_siguiente.addActionListener(e -> jb_moverSiguiente());
        view_empleado.jb_anterior.addActionListener(e -> jb_moverAnterior());
        view_empleado.jb_ultimo.addActionListener(e -> jb_moverUltimo());
        view_empleado.jbtn_agregar.addActionListener(e -> jb_agregar());
        view_empleado.jbtn_eliminar.addActionListener(e -> jb_eliminar());
        view_empleado.jbtn_modificar.addActionListener(e -> jb_modificar());
        initView();

    }

    public void getValores() {
        view_empleado.jtf_id_empleado.setText(model_empleado.getDatos_cliente().get(0));
        view_empleado.jtf_nombre.setText(model_empleado.getDatos_cliente().get(1));
        view_empleado.jtf_apell_pat.setText(model_empleado.getDatos_cliente().get(2));
        view_empleado.jtf_apell_mat.setText(model_empleado.getDatos_cliente().get(3));
        view_empleado.jtf_calle.setText(model_empleado.getDatos_cliente().get(4));
        view_empleado.jtf_colonia.setText(model_empleado.getDatos_cliente().get(5));
        view_empleado.jtf_ciudad.setText(model_empleado.getDatos_cliente().get(6));
        view_empleado.jtf_estado.setText(model_empleado.getDatos_cliente().get(7));
    }

    public void setValores() {
        if(view_empleado.jtf_id_empleado.getText().equals("")){
             datos_empleado.add(0,"0");
        }else {
            datos_empleado.add(0, view_empleado.jtf_id_empleado.getText());
        }
        datos_empleado.add(1, view_empleado.jtf_nombre.getText());
        datos_empleado.add(2, view_empleado.jtf_apell_pat.getText());
        datos_empleado.add(3, view_empleado.jtf_apell_mat.getText());
        datos_empleado.add(4, view_empleado.jtf_calle.getText());
        datos_empleado.add(5, view_empleado.jtf_colonia.getText());
        datos_empleado.add(6, view_empleado.jtf_ciudad.getText());
        datos_empleado.add(7, view_empleado.jtf_estado.getText());
        model_empleado.setDatos_cliente(datos_empleado);
    }

    public void jb_moverPrimer() {
        model_empleado.moverPrimero();
        getValores();

    }

    public void jb_moverSiguiente() {
        model_empleado.moverSiguiente();
        getValores();
    }

    public void jb_moverAnterior() {
        model_empleado.moverAnterior();
        getValores();
    }

    public void jb_moverUltimo() {
        model_empleado.moverUltimo();
        getValores();
    }

    public void jb_agregar() {
        setValores();
        model_empleado.insertar();
        model_empleado.seleccionarTodos();
        getValores();

    }

    public void jb_eliminar() {
        setValores();
        model_empleado.borrar();
        model_empleado.seleccionarTodos();
        getValores();
    }

    public void jb_modificar() {
        setValores();
        model_empleado.actualizar();
        model_empleado.seleccionarTodos();
        getValores();
    }

    private void initView() {
        model_empleado.conectar();
        model_empleado.seleccionarTodos();
        model_empleado.llenarValores();
        getValores();
      

    }

}
