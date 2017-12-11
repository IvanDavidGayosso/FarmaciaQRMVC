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
        
        ModelMedicamentos model_medicamentos = new ModelMedicamentos(base_datos);
        ViewMedicamentos view_medicamentos = new ViewMedicamentos();

        ModelProveedores model_proveedores = new ModelProveedores(base_datos);
        ViewProveedores view_proveedores = new ViewProveedores();

        ModelEmpleados model_empleados = new ModelEmpleados(base_datos);
        View_Empleado view_empleados = new View_Empleado();

        ModelClientes model_clientes = new ModelClientes(base_datos);
        ViewCliente view_cliente = new ViewCliente();
        
        ModelLogin model_login = new ModelLogin(base_datos);
        ViewLogin view_login = new ViewLogin();
    
        ModelPrincipal model_principal = new ModelPrincipal();
        ViewPrincipal view_principal = new ViewPrincipal();

        Object models[] = new Object[6];
        Object views[] = new Object[6];
        Object controllers[] = new Object[6];

        models[0] = model_principal;
        models[1] = model_login;
        models[2] = model_clientes;
        models[3] = model_empleados;
        models[4] = model_proveedores;
        models[5] = model_medicamentos;

        views[0] = view_principal;
        views[1] = view_login;
        views[2] = view_cliente;
        views[3] = view_empleados;
        views[4] = view_proveedores;
        views[5] = view_medicamentos;
        
        ControllerMedicamentos controller_medicamentos = new ControllerMedicamentos(models, views);
        ControllerProveedores controller_proveedores = new ControllerProveedores(models, views);
        ControllerLogin controller_login = new ControllerLogin(models, views);
        ControllerEmpleados controller_empleados = new ControllerEmpleados(models, views);
        ControllerClientes controller_clientes = new ControllerClientes(models, views);
        
        controllers[1] = controller_login;
        controllers[2] = controller_clientes;
        controllers[3] = controller_empleados;
        controllers[4] = controller_proveedores;
        controllers[5] = controller_medicamentos;

        ControllerPrincipal controller_principal = new ControllerPrincipal(models, views, controllers);

    }

}
