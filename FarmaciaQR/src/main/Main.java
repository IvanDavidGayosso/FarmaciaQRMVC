/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import models.*;
import views.*;
import controllers.*;

public class Main {

    public static void main(String[] args) {
        BaseDatos base_datos = new BaseDatos();

        ModelProveedores model_proveedores = new ModelProveedores(base_datos);
        ViewProveedores view_proveedores = new ViewProveedores();

        ModelEmpleados model_empleados = new ModelEmpleados(base_datos);
        ViewEmpleados view_empleados = new ViewEmpleados();

        ModelClientes model_clientes = new ModelClientes(base_datos);
        ViewCliente view_cliente = new ViewCliente();

        ModelPrincipal model_principal = new ModelPrincipal();
        ViewPrincipal view_principal = new ViewPrincipal();

        Object models[] = new Object[4];
        Object views[] = new Object[4];
        Object controllers[] = new Object[4];

        models[0] = model_principal;
        models[1] = model_clientes;
        models[2] = model_empleados;
        models[3] = model_proveedores;

        views[0] = view_principal;
        views[1] = view_cliente;
        views[2] = view_empleados;
        views[3] = view_proveedores;

        ControllerProveedores controller_proveedores = new ControllerProveedores(models, views);
        ControllerEmpleados controller_empleados = new ControllerEmpleados(models, views);
        ControllerClientes controller_clientes = new ControllerClientes(models, views);
        
        controllers[1] = controller_clientes;
        controllers[2] = controller_empleados;
        controllers[3] = controller_proveedores;

        ControllerPrincipal controller_principal = new ControllerPrincipal(models, views, controllers);

    }

}
