/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.*;
import models.*;

public class ControllerPrincipalMDI {
    ViewUsuarios view_usuarios;
    ViewMedicamentos view_medicamentos;
    ViewProveedores view_proveedores;
    View_Empleado view_empleado;
    ViewCliente view_clientes;
    ViewLogin view_login;
    ModelPrincipalMDI model_principal;
    ViewPrincipalMDI view_principal;
    ControllerLogin controller_login;
    ControllerClientes controller_clientes;
    ControllerEmpleados controller_empleados;
    ControllerProveedores controller_proveedores;
    ControllerMedicamentos controller_medicamentos;
    ControllerUsuarios controller_usuarios;

    public ControllerPrincipalMDI(Object[] models, Object[] views, Object[] controllers) {
        this.view_login = (ViewLogin) views[1];
        this.view_clientes = (ViewCliente) views[2];
        this.view_empleado = (View_Empleado) views[3];
        this.view_proveedores = (ViewProveedores) views[4];
        this.view_medicamentos = (ViewMedicamentos) views[5];
        this.view_usuarios =(ViewUsuarios) views[6];
        this.controller_login = (ControllerLogin) controllers[1];
        this.controller_clientes = (ControllerClientes) controllers[2];
        this.controller_empleados = (ControllerEmpleados) controllers[3];
        this.controller_proveedores = (ControllerProveedores) controllers[4];
        this.controller_medicamentos = (ControllerMedicamentos) controllers[5];
        this.controller_usuarios=(ControllerUsuarios) controllers[6];
        this.model_principal = (ModelPrincipalMDI) models[0];
        this.view_principal = (ViewPrincipalMDI) views[0];
        this.view_principal.jmi_clientes.addActionListener(e -> jmi_clientes());
        this.view_principal.jmi_empleados.addActionListener(e -> jmi_empleados());
        this.view_principal.jmi_proveedores.addActionListener(e -> jmi_proveedores());
        this.view_principal.jmi_salir.addActionListener(e -> jbtn_salir());
        this.view_principal.jmi_medicamentos.addActionListener(e -> jmi_medicamentos());
        this.view_principal.jmi_usuarios.addActionListener(e -> jmi_usuarios_clic());
        initView();
    }

    private void initView() {
        view_principal.setVisible(true);
        view_principal.if_view_usuarios.setContentPane(view_login);
        view_principal.if_view_usuarios.setSize(view_login.getSize());
        view_principal.if_view_usuarios.revalidate();
        view_principal.if_view_usuarios.repaint();
        view_principal.if_view_usuarios.setVisible(true);
     
    }

    private void jmi_clientes() {
        view_principal.if_view_cliente.setContentPane(view_clientes);
        view_principal.if_view_cliente.revalidate();
        view_principal.if_view_cliente.repaint();
        view_principal.if_view_cliente.setVisible(true);
        controller_clientes.initView();
    }

    private void jmi_empleados() {
        view_principal.if_view_empleado.setContentPane(view_empleado);
        view_principal.if_view_empleado.revalidate();
        view_principal.if_view_empleado.repaint();
        view_principal.if_view_empleado.setVisible(true);
        controller_empleados.initView();
    }

    private void jmi_proveedores() {
        view_principal.if_view_proveedores.setContentPane(view_proveedores);
        view_principal.if_view_proveedores.revalidate();
        view_principal.if_view_proveedores.repaint();
        view_principal.if_view_proveedores.setVisible(true);
        controller_proveedores.initView();
    }

    private void jmi_medicamentos() {
        view_principal.if_view_medicamentos.setContentPane(view_medicamentos);
        view_principal.if_view_medicamentos.revalidate();
        view_principal.if_view_medicamentos.repaint();
        view_principal.if_view_medicamentos.setVisible(true);
        controller_medicamentos.initView();
    }

    public void jbtn_salir() {
        System.exit(0);
    }


    private void jmi_usuarios_clic() {
        view_principal.if_view_usuarios.setContentPane(view_usuarios);
        view_principal.if_view_usuarios.revalidate();
        view_principal.if_view_usuarios.repaint();
        view_principal.if_view_usuarios.setVisible(true);
        controller_usuarios.initView();
    }

}
