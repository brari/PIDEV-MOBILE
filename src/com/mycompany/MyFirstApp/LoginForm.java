/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyFirstApp;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.CoordinateLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.authuser;

/**
 *
 * @author brari
 */
public class LoginForm {
    Form formlogin;
    authuser auth = new authuser();
   public static TextField usernam;
   public static TextField passwor;
   public static Resources res;
    public LoginForm(){
     formlogin = new Form();
     
     /*   CoordinateLayout layout = new CoordinateLayout(formlogin.getWidth(),formlogin.getHeight());
        formlogin.setLayout(layout);
        Label username = new Label("Username : ");
        username.setY(80);
        Label password = new Label("Password : ");
        password.setY(300);
         usernam = new TextField();
        usernam.setMaxSize(50);
        usernam.setY(200);
        usernam.setX(20);
         passwor = new TextField();
        passwor.setY(400);
       passwor.setX(20);
         Button connecter = new Button("Connecter");
         connecter.setY(600);
         connecter.setX(400);
        Button inscrire = new Button("S'inscrire");
         inscrire.setY(700);
         inscrire.setX(400);
        passwor.setConstraint(TextArea.PASSWORD);
        formlogin.add(usernam);
        formlogin.add(username);
        formlogin.add(passwor);
        formlogin.add(password);
        formlogin.add(connecter);
        formlogin.add(inscrire);*/
     passwor = new TextField("", "password",20, TextField.PASSWORD);
     usernam = new TextField("", "username",20, TextField.ANY);
    Button connecter = new Button("connecter");
    Button inscrire = new Button("inscrire");
       Container content = BoxLayout.encloseY(
                new FloatingHint(usernam),
              
                new FloatingHint(passwor),
                
                connecter,
                
             inscrire   
        );
       content.setScrollableY(true);
       formlogin.add(content);
       formlogin.show();
        connecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                auth.login(res);
            }
        });
        
    }

    public Form getFormlogin() {
        return formlogin;
    }

    public void setFormlogin(Form formlogin) {
        this.formlogin = formlogin;
    }
    
}
