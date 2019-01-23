/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyFirstApp;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.authuser;

/**
 * GUI builder created Form
 *
 * @author brari
 */
public class LoginInForm extends BaseForm {
    authuser auth = new authuser();
    public static TextField usernam,passwor;
    public static Resources res;
    public LoginInForm (Resources res) {
        super(new BorderLayout());
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("LoginForm");
        
        add(BorderLayout.NORTH, new Label(res.getImage("photo.jpg"), "LogoLabel"));
        
        usernam = new TextField("", "Username", 20, TextField.ANY);
        passwor = new TextField("", "Password", 20, TextField.PASSWORD);
        usernam.setSingleLineTextArea(true);
        passwor.setSingleLineTextArea(true);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new LoginUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(usernam),
             createLineSeparator(),
                new FloatingHint(passwor),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        //e -> new NewsfeedForm(res).show()
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                auth.login(res);
            }
        });
    }
} 

