/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyFirstApp;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entities.Users;
import com.mycompany.services.UsersServices;
import com.mycompany.services.authuser;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author brari
 */
public class LoginUpForm  extends BaseForm{
     String jobPic = "";
     authuser auth = new authuser();
      public static TextField usernam,mail,password,checkpassword,tel,adresse,login,loginn;
    public static ComboBox box;
    public LoginUpForm(Resources theme) {
        super(new BorderLayout());
        setUIID("LoginForm");
       getToolbar().addCommandToLeftBar( "BACK",null,e ->new LoginInForm(theme).show());
       
        Container welcome = FlowLayout.encloseCenter(
                new Label("Bienvenue, ", "WelcomeWhite"),
                new Label("Patisserie", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("LogoPIDEV.png");
        
       
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
       
         usernam= new TextField("", "Username", 20, TextField.ANY) ;
         login= new TextField("", "Nom", 20, TextField.ANY) ;
          loginn= new TextField("", "Prenom", 20, TextField.ANY) ;
        mail= new TextField("", "Adresse e_mail", 20, TextField.EMAILADDR) ;
        password = new TextField("", "Password", 20, TextField.PASSWORD) ;
         checkpassword = new TextField("", "Check_Password", 20, TextField.PASSWORD) ;
         tel= new TextField("", "Mobile_Num", 20, TextField.PHONENUMBER) ;
         adresse= new TextField("", "adresse", 20, TextField.ANY) ;
        Picker date = new Picker();
          box = new ComboBox("Role_Patissier","Role_Client","Role_Admin");
        
       
      //upload image 
      
        Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        Image camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
        Button jobIcon = new Button("Ajouter photo",camera);
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "profil.jpg";
        jobIcon.addActionListener((ActionEvent en) -> {
            jobPic = Capture.capturePhoto();
            if (jobPic != null) {
                Display.getInstance().openGallery(ee -> {
                    if (ee.getSource() != null) {
                        try {
                            jobPic = (String) ee.getSource();
                            Image img = Image.createImage((String) ee.getSource());

                            ImageIO imgIO = ImageIO.getImageIO();

                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile);
                            imgIO.save(img, os, ImageIO.FORMAT_JPEG, 1);

                            jobIcon.setIcon(img);
                            System.out.println(ee.getSource());
                            System.out.println(imgIO);
                        } catch (IOException err) {
                            ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                            Log.e(err);
                        }
                    }
                }, Display.GALLERY_IMAGE);
            }
        });
      
      
      
      
      
      
      
      
      
      
      //
        
        
        
        
        
        
        
        
        usernam.getAllStyles().setMargin(LEFT, 0);
        login.getAllStyles().setMargin(LEFT, 0);
        loginn.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        checkpassword.getAllStyles().setMargin(LEFT, 0);
        mail.getAllStyles().setMargin(LEFT, 0);
        tel.getAllStyles().setMargin(LEFT, 0);
        adresse.getAllStyles().setMargin(LEFT, 0);
        date.getAllStyles().setMargin(LEFT, 0);
       Label usernamIcon = new Label("", "TextField");
        Label loginIcon = new Label("", "TextField");
        Label loginIcon1 = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        Label checkpasswordIcon = new Label("", "TextField");
        Label mailIcon = new Label("", "TextField");
        Label telIcon = new Label("", "TextField");
        Label adresseIcon = new Label("", "TextField");
        Label dateIcon = new Label("", "TextField");
        Label patissierIcon = new Label("", "TextField");
        
        
       usernamIcon.getAllStyles().setMargin(RIGHT, 0); 
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        loginIcon1.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        checkpasswordIcon.getAllStyles().setMargin(RIGHT, 0);
        mailIcon.getAllStyles().setMargin(RIGHT, 0);
        telIcon.getAllStyles().setMargin(RIGHT, 0);
        adresseIcon.getAllStyles().setMargin(RIGHT, 0);
        dateIcon.getAllStyles().setMargin(RIGHT, 0);
        patissierIcon.getAllStyles().setMargin(RIGHT, 0);
        
        FontImage.setMaterialIcon(usernamIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(loginIcon1, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(checkpasswordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(mailIcon, FontImage.MATERIAL_MAIL, 3);
        FontImage.setMaterialIcon(telIcon, FontImage.MATERIAL_SMARTPHONE, 3);
        FontImage.setMaterialIcon(adresseIcon, FontImage.MATERIAL_LOCATION_ON, 3);
        FontImage.setMaterialIcon(dateIcon, FontImage.MATERIAL_DATE_RANGE, 3);
        Button loginButton = new Button("S'inscrire");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);
            
            
            
            /* user.setAddress(adresse.getText());
             user.setLast_name(login.getText());
             user.setFirst_name(loginn.getText());
             user.setPassword(password.getText());
             user.setMobile_number(Integer.parseInt(tel.getText()));
             
             user.setEmail(mail.getText());*/
            
                       

             
                      auth.RegisterUser(theme);
                  //new WalkthruForm(theme).show();
                 
           
          
            Toolbar.setGlobalToolbar(true);
        });
           /**validation**/
         Validator v = new Validator();
          v.addConstraint(usernam, new RegexConstraint("[a-z]", "pas de num"));
         v.addConstraint(login, new RegexConstraint("[a-z]", "pas de num"));
         v.addConstraint(loginn, new RegexConstraint("[a-z]", "pas de num"));
         v.addConstraint(mail,RegexConstraint.validEmail());
         v.addConstraint(password,new LengthConstraint(4));
         v.addConstraint(checkpassword,new LengthConstraint(4));
         v.addConstraint(tel,new RegexConstraint("[0-9]$", "pas de lettre"));
         v.addConstraint(adresse,new LengthConstraint(4));
         v.addSubmitButtons(loginButton);
        

        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,  BorderLayout.center(usernam).
                        add(BorderLayout.WEST, usernamIcon)
                ,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(loginn).
                        add(BorderLayout.WEST, loginIcon1),
                 BorderLayout.center(mail).
                        add(BorderLayout.WEST, mailIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
               BorderLayout.center(checkpassword).
                        add(BorderLayout.WEST, checkpasswordIcon),
                BorderLayout.center(tel).
                        add(BorderLayout.WEST, telIcon),
                BorderLayout.center(adresse).
                        add(BorderLayout.WEST, adresseIcon),
                 BorderLayout.center(date).
                        add(BorderLayout.WEST, dateIcon),box,
                
                 
                 jobIcon,
                loginButton
                
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    } 
    
    
    
    
    
   
}
