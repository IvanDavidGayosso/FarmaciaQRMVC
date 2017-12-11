/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import views.ViewCliente;
import models.ModelClientes;

public class ControllerClientes {

    ModelClientes model_clientes;
    ViewCliente view_clientes;
    ArrayList<String> datos_cliente = new ArrayList<>(8);

    public ControllerClientes(Object[] models, Object[] views) {
        this.model_clientes = (ModelClientes) models[2];
        this.view_clientes = (ViewCliente) views[2];
        view_clientes.jbtn_primero.addActionListener(e -> jb_moverPrimer());
        view_clientes.jbtn_siguiente.addActionListener(e -> jb_moverSiguiente());
        view_clientes.jbtn_anterior.addActionListener(e -> jb_moverAnterior());
        view_clientes.jbtn_ultimo.addActionListener(e -> jb_moverUltimo());
        view_clientes.jbtn_agregar.addActionListener(e -> jb_agregar());
        view_clientes.jbtn_eliminar.addActionListener(e -> jb_eliminar());
        view_clientes.jbtn_modificar.addActionListener(e -> jb_modificar());
        view_clientes.jbtn_nuevo.addActionListener(e -> jb_nuevo());
        view_clientes.jbtn_buscar.addActionListener(e -> jbtn_buscar());
        initView();

    }

    public void getValores() {
        view_clientes.jtf_id_cliente.setText(model_clientes.getDatos_cliente().get(0));
        view_clientes.jtf_nombre.setText(model_clientes.getDatos_cliente().get(1));
        view_clientes.jtf_apell_pat.setText(model_clientes.getDatos_cliente().get(2));
        view_clientes.jtf_apell_mat.setText(model_clientes.getDatos_cliente().get(3));
        view_clientes.jtf_calle.setText(model_clientes.getDatos_cliente().get(4));
        view_clientes.jtf_colonia.setText(model_clientes.getDatos_cliente().get(5));
        view_clientes.jtf_ciudad.setText(model_clientes.getDatos_cliente().get(6));
        view_clientes.jtf_estado.setText(model_clientes.getDatos_cliente().get(7));
    }

    public void setValores() {
        if (view_clientes.jtf_id_cliente.getText().equals("")) {
            datos_cliente.add(0, "0");
        } else {
            datos_cliente.add(0, view_clientes.jtf_id_cliente.getText());
        }
        datos_cliente.add(1, view_clientes.jtf_nombre.getText());
        datos_cliente.add(2, view_clientes.jtf_apell_pat.getText());
        datos_cliente.add(3, view_clientes.jtf_apell_mat.getText());
        datos_cliente.add(4, view_clientes.jtf_calle.getText());
        datos_cliente.add(5, view_clientes.jtf_colonia.getText());
        datos_cliente.add(6, view_clientes.jtf_ciudad.getText());
        datos_cliente.add(7, view_clientes.jtf_estado.getText());
        model_clientes.setDatos_cliente(datos_cliente);
    }

    public void jb_moverPrimer() {
        model_clientes.moverPrimero();
        getValores();

    }

    public void jb_moverSiguiente() {
        model_clientes.moverSiguiente();
        getValores();
    }

    public void jb_moverAnterior() {
        model_clientes.moverAnterior();
        getValores();
    }

    public void jb_moverUltimo() {
        model_clientes.moverUltimo();
        getValores();
    }

    public void jb_agregar() {
        datos_cliente.removeAll(datos_cliente);
        setValores();
        model_clientes.insertar();
        model_clientes.seleccionarTodos();
        getValores();

    }

    public void jb_eliminar() {
        datos_cliente.removeAll(datos_cliente);
        setValores();
        model_clientes.borrar();
        model_clientes.seleccionarTodos();
        getValores();
    }

    public void jb_modificar() {
        datos_cliente.removeAll(datos_cliente);
        setValores();
        model_clientes.actualizar();
        model_clientes.seleccionarTodos();
        getValores();
    }

    public void initView() {

        model_clientes.conectar();
        model_clientes.seleccionarTodos();
        model_clientes.llenarValores();
        getValores();

    }

    private void jb_nuevo() {
        view_clientes.jtf_id_cliente.setText(" ");
        view_clientes.jtf_nombre.setText(" ");
        view_clientes.jtf_apell_pat.setText(" ");
        view_clientes.jtf_apell_mat.setText(" ");
        view_clientes.jtf_calle.setText(" ");
        view_clientes.jtf_colonia.setText(" ");
        view_clientes.jtf_ciudad.setText(" ");
        view_clientes.jtf_estado.setText(" ");
    }

    public void jbtn_buscar() {
        datos_cliente.removeAll(datos_cliente);
        datos_cliente.add(0, view_clientes.jtf_nombre.getText());
        datos_cliente.add(1, view_clientes.jtf_apell_pat.getText());
        datos_cliente.add(2, view_clientes.jtf_apell_mat.getText());
        model_clientes.setDatos_cliente(datos_cliente);
        model_clientes.buscar();
        System.out.println(model_clientes.getResultado());
        if (model_clientes.getResultado()==null) {
            JOptionPane.showMessageDialog(view_clientes, "No encontrado");
        } else {
            JOptionPane.showMessageDialog(view_clientes, model_clientes.getResultado());
        }
        initView();

    }

}
