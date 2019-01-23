/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.MyFirstApp.LoginForm;
import com.mycompany.MyFirstApp.LoginInForm;
import static com.mycompany.MyFirstApp.LoginInForm.res;

/**
 *
 * @author brari
 */
public class HomeForm {
    Form f;
    Button btn;
    
    public HomeForm() {
        f = new Form("Home Page");
        btn = new Button("Commencer");
        Label bienvenue = new Label("Binvenue sur Le Coin Patissier App");
        f.add(bienvenue);
        f.add(btn);
        btn .addActionListener((e)->{
        LoginInForm lf = new LoginInForm(res);
        lf.show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}