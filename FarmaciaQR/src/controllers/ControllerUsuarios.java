/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import views.ViewUsuarios;
import models.ModelUsuarios;
import net.proteanit.sql.DbUtils;

public class ControllerUsuarios {

    ModelUsuarios model_usuarios;
    ViewUsuarios view_usuarios;
    ArrayList<String> datos_usuario = new ArrayList<>(3);

    public ControllerUsuarios(Object[] models, Object[] views) {
        this.model_usuarios = (ModelUsuarios) models[6];
        this.view_usuarios = (ViewUsuarios) views[6];
        view_usuarios.jbtn_agregar.addActionListener(e -> jbtn_agregar());
        view_usuarios.jbtn_eliminar.addActionListener(e -> jbtn_eliminar());
        view_usuarios.jbtn_modificar.addActionListener(e -> jbtn_modificar());
        view_usuarios.jbtn_nuevo.addActionListener(e -> jbtn_nuevo());
        view_usuarios.jbtn_primero.addActionListener(e -> jbtn_moverPrimer());
        view_usuarios.jbtn_anterior.addActionListener(e -> jbtn_moverAnterior());
        view_usuarios.jbtn_siguiente.addActionListener(e -> jbtn_moverSiguiente());
        view_usuarios.jbtn_ultimo.addActionListener(e -> jbtn_moverUltimo());

        initView();

    }

    public void getValores() {
        view_usuarios.jcb_empleado.setSelectedItem(model_usuarios.getNombre_empleado().get(Integer.parseInt(model_usuarios.getId_empleado().get(1))));
        view_usuarios.jtf_usuario.setText(model_usuarios.getDatos_usuario().get(0));
        view_usuarios.jp_contrasena.setText(model_usuarios.getDatos_usuario().get(2));
    }

    public void setValores() {
        if (view_usuarios.jp_contrasena.getText().equals(view_usuarios.jp_repetir_contrasena.getText())) {
            datos_usuario.add(0, view_usuarios.jtf_usuario.getText());
            datos_usuario.add(1, model_usuarios.getId_empleado().get(view_usuarios.jcb_empleado.getSelectedIndex()));
            datos_usuario.add(2, view_usuarios.jp_contrasena.getText());
            model_usuarios.setDatos_usuario(datos_usuario);
        }

    }

    public void jbtn_moverPrimer() {
        model_usuarios.moverPrimero();
        getValores();

    }

    public void jbtn_moverSiguiente() {
        model_usuarios.moverSiguiente();
        getValores();
    }

    public void jbtn_moverAnterior() {
        model_usuarios.moverAnterior();
        getValores();
    }

    public void jbtn_moverUltimo() {
        model_usuarios.moverUltimo();
        getValores();
    }

    public void jbtn_agregar() {
        datos_usuario.removeAll(datos_usuario);
        setValores();
        model_usuarios.insertar();
        model_usuarios.seleccionarTodos();
        view_usuarios.jt_usuario.setModel(DbUtils.resultSetToTableModel(model_usuarios.getResultSet()));
        getValores();

    }

    public void jbtn_eliminar() {
        datos_usuario.removeAll(datos_usuario);
        setValores();
        model_usuarios.borrar();
        model_usuarios.seleccionarTodos();
        view_usuarios.jt_usuario.setModel(DbUtils.resultSetToTableModel(model_usuarios.getResultSet()));
        getValores();
    }

    public void jbtn_modificar() {
        datos_usuario.removeAll(datos_usuario);
        setValores();
        model_usuarios.actualizar();
        model_usuarios.seleccionarTodos();
        view_usuarios.jt_usuario.setModel(DbUtils.resultSetToTableModel(model_usuarios.getResultSet()));
        getValores();
    }

    public void initView() {

        model_usuarios.conectar();
        llenarCombo();
        model_usuarios.preparar_db();
        model_usuarios.seleccionarTodos();
        model_usuarios.llenarValores();
        view_usuarios.jt_usuario.setModel(DbUtils.resultSetToTableModel(model_usuarios.getResultSet()));
        getValores();

    }

    public void jbtn_nuevo() {
        view_usuarios.jtf_usuario.setText(" ");
        view_usuarios.jp_contrasena.setText(" ");
        view_usuarios.jp_repetir_contrasena.setVisible(true);
        view_usuarios.jp_repetir_contrasena.setText(" ");
    }

    public void llenarCombo() {
        view_usuarios.jcb_empleado.removeAllItems();
        model_usuarios.llenarEmpleados();

        for (int i = 0; i < model_usuarios.getNombre_empleado().size(); i++) {
            view_usuarios.jcb_empleado.addItem(model_usuarios.getNombre_empleado().get(i));
        }

    }

}
