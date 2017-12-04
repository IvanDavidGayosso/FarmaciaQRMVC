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

        ModelEmpleados model_empleados = new ModelEmpleados(base_datos);
        ViewEmpleado view_empleados = new ViewEmpleado();

        ModelClientes model_clientes = new ModelClientes(base_datos);
        ViewCliente view_cliente = new ViewCliente();

        ModelPrincipal model_principal = new ModelPrincipal();
        ViewPrincipal view_principal = new ViewPrincipal();

        Object models[] = new Object[3];
        Object views[] = new Object[3];

        models[0] = model_principal;
        models[1] = model_clientes;
        models[2] = model_empleados;

        views[0] = view_principal;
        views[1] = view_cliente;
        views[2] = view_empleados;

        ControllerEmpleados controller_empleados = new ControllerEmpleados(models, views);
        ControllerClientes controller_clientes = new ControllerClientes(models, views);
        ControllerPrincipal controller_principal = new ControllerPrincipal(models, views);

    }

}
