/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import views.ViewProveedores;
import models.ModelProveedores;

public class ControllerProveedores {

    ModelProveedores model_proveedores;
    ViewProveedores view_proveedores;
    ArrayList<String> datos_proveedor = new ArrayList<>(8);

    public ControllerProveedores(Object[] models, Object[] views) {
        this.model_proveedores = (ModelProveedores) models[4];
        this.view_proveedores = (ViewProveedores) views[4];
        view_proveedores.jbtn_primero.addActionListener(e -> jb_moverPrimer());
        view_proveedores.jbtn_siguiente.addActionListener(e -> jb_moverSiguiente());
        view_proveedores.jbtn_anterior.addActionListener(e -> jb_moverAnterior());
        view_proveedores.jbtn_ultimo.addActionListener(e -> jb_moverUltimo());
        view_proveedores.jbtn_agregar.addActionListener(e -> jb_agregar());
        view_proveedores.jbtn_eliminar.addActionListener(e -> jb_eliminar());
        view_proveedores.jbtn_modificar.addActionListener(e -> jb_modificar());
        view_proveedores.jbtn_nuevo.addActionListener(e -> jb_nuevo());
        view_proveedores.jbtn_actualizar.addActionListener(e -> initView());
        initView();

    }

    public void getValores() {
        view_proveedores.jtf_rfc.setText(model_proveedores.getDatos_proveedor().get(0));
        view_proveedores.jtf_proveedor.setText(model_proveedores.getDatos_proveedor().get(1));
        view_proveedores.jtf_correo_electronico.setText(model_proveedores.getDatos_proveedor().get(2));
        view_proveedores.jtf_calle.setText(model_proveedores.getDatos_proveedor().get(3));
        view_proveedores.jtf_colonia.setText(model_proveedores.getDatos_proveedor().get(4));
        view_proveedores.jtf_ciudad.setText(model_proveedores.getDatos_proveedor().get(5));
        view_proveedores.jtf_estado.setText(model_proveedores.getDatos_proveedor().get(6));
    }

    public void setValores() {
        if (view_proveedores.jtf_rfc.getText().equals("")) {
            datos_proveedor.add(0, "0");
        } else {
            datos_proveedor.add(0, view_proveedores.jtf_rfc.getText());
        }
        datos_proveedor.add(1, view_proveedores.jtf_proveedor.getText());
        datos_proveedor.add(2, view_proveedores.jtf_correo_electronico.getText());
        datos_proveedor.add(3, view_proveedores.jtf_calle.getText());
        datos_proveedor.add(4, view_proveedores.jtf_colonia.getText());
        datos_proveedor.add(5, view_proveedores.jtf_ciudad.getText());
        datos_proveedor.add(6, view_proveedores.jtf_estado.getText());
        model_proveedores.setDatos_cliente(datos_proveedor);
    }

    public void jb_moverPrimer() {
        model_proveedores.moverPrimero();
        getValores();

    }

    public void jb_moverSiguiente() {
        model_proveedores.moverSiguiente();
        getValores();
    }

    public void jb_moverAnterior() {
        model_proveedores.moverAnterior();
        getValores();
    }

    public void jb_moverUltimo() {
        model_proveedores.moverUltimo();
        getValores();
    }

    public void jb_agregar() {
        datos_proveedor.removeAll(datos_proveedor);
        setValores();
        model_proveedores.insertar();
        model_proveedores.seleccionarTodos();
        getValores();

    }

    public void jb_eliminar() {
        datos_proveedor.removeAll(datos_proveedor);
        setValores();
        model_proveedores.borrar();
        model_proveedores.seleccionarTodos();
        getValores();
    }

    public void jb_modificar() {
        datos_proveedor.removeAll(datos_proveedor);
        setValores();
        model_proveedores.actualizar();
        model_proveedores.seleccionarTodos();
        getValores();
    }

    public void initView() { 
        model_proveedores.conectar();
        model_proveedores.preparar_db();
        model_proveedores.seleccionarTodos();
        model_proveedores.llenarValores();
        getValores();

    }

    private void jb_nuevo() {
        view_proveedores.jtf_rfc.setText(" ");
        view_proveedores.jtf_proveedor.setText(" ");
        view_proveedores.jtf_correo_electronico.setText(" ");
        view_proveedores.jtf_calle.setText(" ");
        view_proveedores.jtf_colonia.setText(" ");
        view_proveedores.jtf_ciudad.setText(" ");
        view_proveedores.jtf_estado.setText(" ");
    }

}
