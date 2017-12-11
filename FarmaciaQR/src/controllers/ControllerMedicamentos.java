/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import views.ViewMedicamentos;
import models.ModelMedicamentos;
import net.proteanit.sql.DbUtils;

public class ControllerMedicamentos {

    ModelMedicamentos model_medicamentos;
    ViewMedicamentos view_medicamentos;
    ArrayList<String> datos_medicamento = new ArrayList<>();

    public ControllerMedicamentos(Object[] models, Object[] views) {
        this.model_medicamentos = (ModelMedicamentos) models[5];
        this.view_medicamentos = (ViewMedicamentos) views[5];
        view_medicamentos.jbtn_primero.addActionListener(e -> jb_moverPrimer());
        view_medicamentos.jbtn_siguiente.addActionListener(e -> jb_moverSiguiente());
        view_medicamentos.jbtn_anterior.addActionListener(e -> jb_moverAnterior());
        view_medicamentos.jbtn_ultimo.addActionListener(e -> jb_moverUltimo());
        view_medicamentos.jbtn_agregar.addActionListener(e -> jb_agregar());
        view_medicamentos.jbtn_eliminar.addActionListener(e -> jb_eliminar());
        view_medicamentos.jbtn_modificar.addActionListener(e -> jb_modificar());
        view_medicamentos.jbtn_nuevo.addActionListener(e -> jb_nuevo());
        view_medicamentos.jbtn_actualizar.addActionListener(e -> initView());
        view_medicamentos.jrbt_con_reseta.addActionListener(e -> cambioRestricion1());
        view_medicamentos.jrbtn_sin_reseta.addActionListener(e -> cambioRestricion2());
        view_medicamentos.jrbtn_generico.addActionListener(e -> cambioTipo1());
        view_medicamentos.jrbt_patente.addActionListener(e -> cambioTipo2());
        initView();

    }

    public void getValores() {
        view_medicamentos.jtf_id_medicamento.setText(model_medicamentos.getDatos_medicamento().get(0));
        view_medicamentos.jtf_medicamento.setText(model_medicamentos.getDatos_medicamento().get(1));
        view_medicamentos.jtf_ing_act.setText(model_medicamentos.getDatos_medicamento().get(2));
        view_medicamentos.jtf_presentacion.setText(model_medicamentos.getDatos_medicamento().get(3));
        view_medicamentos.jtf_contenedor.setText(model_medicamentos.getDatos_medicamento().get(4));
        view_medicamentos.jtf_cantidad.setText(model_medicamentos.getDatos_medicamento().get(5));
        view_medicamentos.jtf_uni_med.setText(model_medicamentos.getDatos_medicamento().get(6));
        view_medicamentos.jcb_clasificacion.setSelectedIndex(Integer.parseInt(model_medicamentos.getDatos_medicamento().get(7)) - 1);
        if (model_medicamentos.getDatos_medicamento().get(8).equals("venta con reseta")) {
            view_medicamentos.jrbt_con_reseta.setSelected(true);
            view_medicamentos.jrbtn_sin_reseta.setSelected(false);
        } else {
            view_medicamentos.jrbt_con_reseta.setSelected(false);
            view_medicamentos.jrbtn_sin_reseta.setSelected(true);
        }

        if (model_medicamentos.getDatos_medicamento().get(9).equals("Generico")) {
            view_medicamentos.jrbtn_generico.setSelected(true);
            view_medicamentos.jrbt_patente.setSelected(false);
        } else {
            view_medicamentos.jrbtn_generico.setSelected(false);
            view_medicamentos.jrbt_patente.setSelected(true);
        }

        view_medicamentos.jtf_precio_compra.setText(model_medicamentos.getDatos_medicamento().get(10));
        view_medicamentos.jtf_precio_venta.setText(model_medicamentos.getDatos_medicamento().get(11));
        view_medicamentos.jtf_stock_minimo.setText(model_medicamentos.getDatos_medicamento().get(12));
        view_medicamentos.jtf_stock_maximo.setText(model_medicamentos.getDatos_medicamento().get(13));
        view_medicamentos.jtf_laboratorio.setText(model_medicamentos.getDatos_medicamento().get(14));
    }

    public void setValores() {
        datos_medicamento.add(0, view_medicamentos.jtf_id_medicamento.getText());
        datos_medicamento.add(1, view_medicamentos.jtf_medicamento.getText());
        datos_medicamento.add(2, view_medicamentos.jtf_ing_act.getText());
        datos_medicamento.add(3, view_medicamentos.jtf_presentacion.getText());
        datos_medicamento.add(4, view_medicamentos.jtf_contenedor.getText());
        datos_medicamento.add(5, view_medicamentos.jtf_cantidad.getText());
        datos_medicamento.add(6, view_medicamentos.jtf_uni_med.getText());
        datos_medicamento.add(7, String.valueOf(view_medicamentos.jcb_clasificacion.getSelectedIndex() + 1));
        if (view_medicamentos.jrbt_con_reseta.isSelected()) {
            datos_medicamento.add(8, "venta con reseta");
        } else {
            datos_medicamento.add(8, "venta sin reseta");
        }

        if (view_medicamentos.jrbtn_generico.isSelected()) {
            datos_medicamento.add(9, "Generico");
        } else {
            datos_medicamento.add(9, "Patente");
        }
        datos_medicamento.add(10, view_medicamentos.jtf_precio_compra.getText());
        datos_medicamento.add(11, view_medicamentos.jtf_precio_venta.getText());
        datos_medicamento.add(12, view_medicamentos.jtf_stock_minimo.getText());
        datos_medicamento.add(13, view_medicamentos.jtf_stock_maximo.getText());
        datos_medicamento.add(14, view_medicamentos.jtf_laboratorio.getText());
        model_medicamentos.setDatos_medicamento(datos_medicamento);
    }

    public void jb_moverPrimer() {
        model_medicamentos.moverPrimero();
        getValores();

    }

    public void jb_moverSiguiente() {
        model_medicamentos.moverSiguiente();
        getValores();
    }

    public void jb_moverAnterior() {
        model_medicamentos.moverAnterior();
        getValores();
    }

    public void jb_moverUltimo() {
        model_medicamentos.moverUltimo();
        getValores();
    }

    public void jb_agregar() {
        datos_medicamento.removeAll(datos_medicamento);
        setValores();
        model_medicamentos.insertar();
        model_medicamentos.seleccionarTodos();
        view_medicamentos.jt_medicamentos.setModel(DbUtils.resultSetToTableModel(model_medicamentos.getResultSet()));
        getValores();

    }

    public void jb_eliminar() {
        datos_medicamento.removeAll(datos_medicamento);
        setValores();
        model_medicamentos.borrar();
        model_medicamentos.seleccionarTodos();
        view_medicamentos.jt_medicamentos.setModel(DbUtils.resultSetToTableModel(model_medicamentos.getResultSet()));
        getValores();
    }

    public void jb_modificar() {
        datos_medicamento.removeAll(datos_medicamento);
        setValores();
        model_medicamentos.actualizar();
        model_medicamentos.seleccionarTodos();
        view_medicamentos.jt_medicamentos.setModel(DbUtils.resultSetToTableModel(model_medicamentos.getResultSet()));
        getValores();
    }

    public void initView() {
        model_medicamentos.conectar();
        model_medicamentos.seleccionarTodos();
        model_medicamentos.llenarValores();
        view_medicamentos.jt_medicamentos.setModel(DbUtils.resultSetToTableModel(model_medicamentos.getResultSet()));
        getValores();

    }

    private void jb_nuevo() {

    }

    private void cambioRestricion1() {
        view_medicamentos.jrbtn_sin_reseta.setSelected(false);
    }

    private void cambioRestricion2() {
        view_medicamentos.jrbt_con_reseta.setSelected(false);

    }

    private void cambioTipo1() {

        view_medicamentos.jrbt_patente.setSelected(false);

    }

    private void cambioTipo2() {

        view_medicamentos.jrbtn_generico.setSelected(false);
    }
}
