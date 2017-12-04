/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.*;
import models.*;

public class ControllerPrincipal {

    ModelClientes model_clientes;
    ViewCliente view_clientes;
    ModelPrincipal model_principal;
    ViewPrincipal view_principal;

    public ControllerPrincipal(Object[] models, Object[] views) {
        this.model_clientes = (ModelClientes) models[1];
        this.view_clientes = (ViewCliente) views[1];
        this.model_principal = (ModelPrincipal) models[0];
        this.view_principal = (ViewPrincipal) views[0];
        this.view_principal.jmi_clientes.addActionListener(e->jmi_clientes());
        initView();
    }

    private void initView() {
        view_principal.setVisible(true);
    }

    private void jmi_clientes() {
        view_principal.setContentPane(view_clientes);
        view_principal.revalidate();
        view_principal.repaint();
    }

   

}

    
