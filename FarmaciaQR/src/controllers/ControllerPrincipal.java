/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ds.desktop.notify.DesktopNotify;
import views.*;
import models.*;

public class ControllerPrincipal {

    ViewMedicamentos view_medicamentos;
    ViewProveedores view_proveedores;
    View_Empleado view_empleado;
    ViewCliente view_clientes;
    ViewLogin view_login;
    ModelPrincipal model_principal;
    ViewPrincipal view_principal;
    ControllerLogin controller_login;
    ControllerClientes controller_clientes;
    ControllerEmpleados controller_empleados;
    ControllerProveedores controller_proveedores;
    ControllerMedicamentos controller_medicamentos;

    public ControllerPrincipal(Object[] models, Object[] views, Object[] controllers) {
        this.view_login=(ViewLogin) views[1];
        this.view_clientes = (ViewCliente) views[2];
        this.view_empleado = (View_Empleado) views[3];
        this.view_proveedores = (ViewProveedores) views[4];
        this.view_medicamentos= (ViewMedicamentos) views[5];
        this.controller_login=(ControllerLogin) controllers[1]; 
        this.controller_clientes = (ControllerClientes) controllers[2];
        this.controller_empleados = (ControllerEmpleados) controllers[3];
        this.controller_proveedores = (ControllerProveedores) controllers[4];
        this.controller_medicamentos = (ControllerMedicamentos) controllers[5];
        this.model_principal = (ModelPrincipal) models[0];
        this.view_principal = (ViewPrincipal) views[0];
        this.view_principal.jmi_clientes.addActionListener(e -> jmi_clientes());
        this.view_principal.jmi_empleado.addActionListener(e -> jmi_empleados());
        this.view_principal.jmi_proveedores.addActionListener(e -> jmi_proveedores());
        this.view_principal.jmi_salir.addActionListener(e -> jbtn_salir());
        this.view_principal.jmi_medicamentos.addActionListener(e -> jmi_medicamentos());
        this.view_login.jbtn_entrar.addActionListener(e -> principal());
        initView();
    }

    private void initView() {
        view_principal.setVisible(true);
        view_principal.setContentPane(view_login);
        view_principal.revalidate();
        view_principal.repaint();
        
       
    }

    private void jmi_clientes() {
        view_principal.setContentPane(view_clientes);
        view_principal.revalidate();
        view_principal.repaint();
        controller_clientes.initView();
    }

    private void jmi_empleados() {
        view_principal.setContentPane(view_empleado);
        view_principal.revalidate();
        view_principal.repaint();
        controller_empleados.initView();
    }

    private void jmi_proveedores() {
        view_principal.setContentPane(view_proveedores);
        view_principal.revalidate();
        view_principal.repaint();
        controller_proveedores.initView();
    }

    private void jmi_medicamentos() {
        view_principal.setContentPane(view_medicamentos);
        view_principal.revalidate();
        view_principal.repaint();
        controller_medicamentos.initView();
    }
    
    public void jbtn_salir() {
        System.exit(0);
    }

    private void principal() {
        System.out.println("Hola");
  
   
    }

}
