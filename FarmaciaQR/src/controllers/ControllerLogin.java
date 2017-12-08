/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import views.ViewLogin;
import models.ModelLogin;

public class ControllerLogin {

    ModelLogin model_login;
    ViewLogin view_login;
    ArrayList<String> datos_usuario = new ArrayList<>();

    public ControllerLogin(Object[] models, Object[] views) {
        this.model_login = (ModelLogin) models[1];
        this.view_login = (ViewLogin) views[1];
        //view_login.jbtn_entrar.addActionListener(e -> jbtn_entrar());
        initView();

    }

  
    public void initView() {
      model_login.conectar();
        
    }

    public boolean jbtn_entrar() {
        datos_usuario.removeAll(datos_usuario);
        datos_usuario.add(0,view_login.jtf_usuario.getText());
        datos_usuario.add(1,view_login.jpf_contrasena.getText());
       model_login.setDatos_usuario(datos_usuario);
       model_login.seleccionarTodos();
       if(!model_login.getDatos_usuario().get(1).equals(" ")){
           JOptionPane.showMessageDialog(view_login, "Bienvenido");
           return true;
       }else{
           JOptionPane.showMessageDialog(view_login, "No se encontro en el sistema");
       }
       return false;
    }

}
