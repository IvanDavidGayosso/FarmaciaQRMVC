/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import views.ViewEmpleados;
import models.ModelEmpleados;

public class ControllerEmpleados {
 
    ModelEmpleados model_empleado;
    ViewEmpleados view_empleado;
    ArrayList<String> datos_empleado = new ArrayList<>(10);
  
    
    public ControllerEmpleados(Object[] models, Object[] views) {
        this.model_empleado = (ModelEmpleados) models[2];
        this.view_empleado = (ViewEmpleados) views[2];
        view_empleado.jbtn_primero.addActionListener(e -> jb_moverPrimer());
        view_empleado.jbtn_siguiente.addActionListener(e -> jb_moverSiguiente());
        view_empleado.jbtn_anterior.addActionListener(e -> jb_moverAnterior());
        view_empleado.jbtn_ultimo.addActionListener(e -> jb_moverUltimo());
        view_empleado.jbtn_agregar.addActionListener(e -> jb_agregar());
        view_empleado.jbtn_eliminar.addActionListener(e -> jb_eliminar());
        view_empleado.jbtn_modificar.addActionListener(e -> jb_modificar());
        view_empleado.jbtn_nuevo.addActionListener(e -> jbtn_nuevo());
        initView();

    }

    public void getValores() {
        view_empleado.jtf_id_empleado.setText(model_empleado.getDatos_empleado().get(0));
        view_empleado.jtf_nombre.setText(model_empleado.getDatos_empleado().get(1));
        view_empleado.jtf_apell_pat.setText(model_empleado.getDatos_empleado().get(2));
        view_empleado.jtf_apell_mat.setText(model_empleado.getDatos_empleado().get(3));
        view_empleado.jtf_calle.setText(model_empleado.getDatos_empleado().get(4));
        view_empleado.jtf_colonia.setText(model_empleado.getDatos_empleado().get(5));
        view_empleado.jtf_ciudad.setText(model_empleado.getDatos_empleado().get(6));
        view_empleado.jtf_estado.setText(model_empleado.getDatos_empleado().get(7));
        view_empleado.jtf_telefono.setText(model_empleado.getDatos_empleado().get(8));
        view_empleado.jcb_cargo.setSelectedItem(model_empleado.getDatos_empleado().get(9));

    }

    public void setValores() {
        if (view_empleado.jtf_id_empleado.getText().equals("")) {
            datos_empleado.add(0, "0");
        } else {
            datos_empleado.add(0, view_empleado.jtf_id_empleado.getText());
        }
        datos_empleado.add(1, view_empleado.jtf_nombre.getText());
        datos_empleado.add(2, view_empleado.jtf_apell_pat.getText());
        datos_empleado.add(3, view_empleado.jtf_apell_mat.getText());
        datos_empleado.add(4, view_empleado.jtf_calle.getText());
        datos_empleado.add(5, view_empleado.jtf_colonia.getText());
        datos_empleado.add(6, view_empleado.jtf_ciudad.getText());
        datos_empleado.add(7, view_empleado.jtf_estado.getText());
        datos_empleado.add(8, view_empleado.jtf_telefono.getText());
        datos_empleado.add(9, String.valueOf(model_empleado.getId_cargo().get((int) view_empleado.jcb_cargo.getSelectedIndex())));
        model_empleado.setDatos_empleado(datos_empleado);
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

    public void initView() {
        model_empleado.conectar();
        model_empleado.llenar_combo();
        llenarCombo();
        model_empleado.preparar_db();
        model_empleado.seleccionarTodos();
        model_empleado.llenarValores();
        getValores();
        
    }
    
    public void llenarCombo(){
        view_empleado.jcb_cargo.removeAllItems();
        model_empleado.llenar_combo();
        for(int i=0;i<model_empleado.getCargo().size();i++)
            view_empleado.jcb_cargo.addItem(model_empleado.getCargo().get(i));
    }

    private void jbtn_nuevo() {
        view_empleado.jtf_id_empleado.setText(" ");
        view_empleado.jtf_nombre.setText(" ");
        view_empleado.jtf_apell_pat.setText(" ");
        view_empleado.jtf_apell_mat.setText(" ");
        view_empleado.jtf_calle.setText(" ");
        view_empleado.jtf_colonia.setText(" ");
        view_empleado.jtf_ciudad.setText(" ");
        view_empleado.jtf_estado.setText(" ");
        view_empleado.jtf_telefono.setText(" ");
    }
}
