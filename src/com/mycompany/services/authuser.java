/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.MyFirstApp.ClientBaseForm;
import com.mycompany.MyFirstApp.LoginForm;
import com.mycompany.MyFirstApp.LoginInForm;
import com.mycompany.MyFirstApp.LoginUpForm;
import com.mycompany.MyFirstApp.WalkthruForm;
import com.mycompany.MyFirstApp.afficherReclamationForm;
import com.mycompany.entities.Users;
import com.mycompany.ui.Affichage;
import com.mycompany.ui.AffichagePat;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.mindrot.BCrypt;
//import org.mindrot.jbcrypt.BCrypt;
//import org.mindrot.BCrypt;

/**
 *
 * @author brari
 */
public class authuser {
    public static Users user = new Users();
    int temp;
    
    public void login(Resources res) {
        // TextField userlogin = (TextField) SignInForm.builder.findByName("Username", SignInForm.ctn);
        //TextField passlogin = (TextField) SignInForm.builder.findByName("Password", SignInForm.ctn);
        String userlog = LoginInForm.usernam.getText();
        String passlog = LoginInForm.passwor.getText();
 /*       ConnectionRequest connectionRequest;
        
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    System.out.println(data);
                    if (data.isEmpty()) {
                        Dialog.show("error", "Email not found ! please retry ", "Cancel", "ok");
                    } else {
                        user.setId((int) Float.parseFloat(data.get("id").toString()));
                        user.setEmail(((String) data.get("email")));
                        user.setNom(((String) data.get("nom")));
                        user.setPassword(((String) data.get("password")));
                        user.setPrenom(((String) data.get("prenom")));
                        //user.setNumTel((int) Float.parseFloat(data.get("numtel").toString()));
                        user.setUsername(((String) data.get("username")));
                        user.setAdresse(((String) data.get("adresse")));               */
                        //user.setCin(((String) data.get("cin")));
                        /*Map<String, Object> data2 = (Map<String, Object>) (data.get("datenaissence"));
                        temp = (int) Float.parseFloat(data2.get("timestamp").toString());
                        Date myDate = new Date(temp * 1000L);
                        //user.setDateNaissence(myDate);*/
/*                        List<String> content = new ArrayList<>();
                        content.addAll((Collection<? extends String>) (data.get("roles")));
                        user.setRoles(content.get(0));
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                System.out.println(user);
                if (passlog.equals("")) {
                    Dialog.show("error", "Please put your password ! ", "cancel", "ok");
                } 
               /* if (!(BCrypt.checkpw(passlog, user.getPassword())))
                         {
                    System.out.println(user.getPassword());
                    System.out.println(passlog);
                    Dialog.show("error", "Wrong password please retry! ", "cancel", "ok");
                } */
   /*             else {
                    if (user.getRoles().equals("ROLE_CLIENT")) {
                       // new NewsfeedFormClient(res).show();
                    } else if (user.getRoles().equals("ROLE_GUIDE")){
                        // new Guide_UI(res).show();
                    }else if (user.getRoles().equals("ROLE_AGENCE_VOITURE")) {
                       // new AfficheForALV(res,user).show();
                        System.out.println("corect");
                    }else if (user.getRoles().equals("ROLE_HOTEL")) {
                       // new ListeChambre(res).show();
                       //new AfficheForClient(res,user).show();
                        System.out.println("correct");
                    }else{
                        Dialog.show("error", "Votre Espace n'est pas encore pret ", "cancel", "ok");
                    }
                }
            }
        };
        System.out.println(userlog);
        connectionRequest.setUrl("http://localhost/SprintWebDemo/web/app_dev.php/api/user/find/" + userlog);
        NetworkManager.getInstance().addToQueue(connectionRequest);                       */
   
   
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/user/find/" + userlog); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {         
                
                String  json = new String(con.getResponseData());
                 try {
            JSONParser j= new JSONParser();
            Map<String, Object> data = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> listuser = (List<Map<String, Object>>) data.get("root");
             System.out.println(data);
                    if (data.isEmpty()) {
                        Dialog.show("error", "Email not found ! please retry ", "Cancel", "ok");
                    } else {
                          for (Map<String, Object> obj : listuser) {
                      //  user.setId((int) Float.parseFloat(data.get("id").toString()));
                        user.setEmail(((String) obj.get("email")));
                        user.setNom(((String) obj.get("nom")));
                        user.setPassword(((String) obj.get("password")));
                        user.setPrenom(((String) obj.get("prenom")));
                        //user.setNumTel((int) Float.parseFloat(data.get("numtel").toString()));
                        user.setUsername(((String) obj.get("username")));
                        user.setAdresse(((String) obj.get("adresse")));               
                        //user.setCin(((String) data.get("cin")));
                        /*Map<String, Object> data2 = (Map<String, Object>) (data.get("datenaissence"));
                        temp = (int) Float.parseFloat(data2.get("timestamp").toString());
                        Date myDate = new Date(temp * 1000L);
                        //user.setDateNaissence(myDate);*/
                      //  List<String> content = new ArrayList<>();
                      //  content.addAll((Collection<? extends String>) (data.get("roles")));
                        //user.setRoles(((String) obj.get("roles")));
                        
                        ArrayList<String> pl=(ArrayList<String>)obj.get("roles");  
                      user.setRoles(((String) pl.get(0)));
                    }}
                  } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
   //System.out.println(user.getRoles());
                System.out.println("\nUser: "+user.toString());
                if (passlog.equals("")) {
                    Dialog.show("error", "Please put your password ! ", "cancel", "ok");
                } 
                else if (!(BCrypt.checkpw(passlog, user.getPassword())))
               
                {
                    System.out.println(user.getPassword());
                    System.out.println(passlog);
                    Dialog.show("error", "Wrong password please retry! ", "cancel", "ok");
                } 
                else {
                    if (user.getRoles().equals("ROLE_CLIENT")) {
                       
                        System.out.println("client");
                        //Dialog.show("", "Connexion etablie ", "cancel", "ok");
                         //new afficherReclamationForm(res).show();
                         Affichage af= new Affichage();
                         af.getF().show();
                    } else if (user.getRoles().equals("ROLE_ADMIN")){
                         new ClientBaseForm().show();
                         System.out.println("admin");
                        Dialog.show("", "Connexion etablie ", "cancel", "ok");
                    }else if (user.getRoles().equals("ROLE_PATISSIER")) {
                       // new AfficheForALV(res,user).show();
                        System.out.println("Patissier");
                        AffichagePat af= new AffichagePat();
                         af.getF().show();
                    }else{
                        Dialog.show("error", "Votre Espace n'est pas encore pret ", "cancel", "ok");
                    }
                }
            }
    
    public void RegisterUser(Resources res) {
        String rol = "";
        String userlog = LoginUpForm.usernam.getText();
       String pass = LoginUpForm.password.getText();
        String nom = LoginUpForm.login.getText();
        String prenom = LoginUpForm.loginn.getText();
        String passhs = BCrypt.hashpw(LoginUpForm.password.getText(), BCrypt.gensalt(13)) ;
        String conpasshs = BCrypt.hashpw(LoginUpForm.checkpassword.getText(), BCrypt.gensalt(13)) ;
        
        String email = LoginUpForm.mail.getText();
        String conpass = LoginUpForm.checkpassword.getText();
        int numtel = Integer.parseInt(LoginUpForm.tel.getText());
        String adresse = LoginUpForm.adresse.getText();
        int role = LoginUpForm.box.getSelectedIndex();
        /*affichage données test
        System.out.println(userlog);
        System.out.println(pass);
        System.out.println(conpass);
        System.out.println(numtel);
        System.out.println(adresse);
        System.out.println(role);
        System.out.println(email);
        System.out.println(role);
        System.out.println(role);*/
        if (role == 0) {
            rol = "a:1:{i:0;s:14:\"ROLE_PATISSIER\";}";
        } else if (role == 1) {
            rol = "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
        } else if (role == 2) {
            rol = "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
        } 
        if (!pass.equals(conpass)) {
            
            Dialog.show("error", "please confirm your password ", "cancel", "ok");
        } else {
            user.setUsername(userlog);
            user.setPassword(passhs);
            user.setEmail(email);
            user.setTelephone(numtel);
            user.setAdresse(adresse);
            user.setRoles(rol);
            user.setNom(nom);
            user.setPrenom(prenom);
                 Message m = new Message(" Bienvenue dans notre application"+" "+"En esperant que vous irez a la rencontre de vos saveurs");
                String textAttachmentUri = null;
                m.getAttachments().put(textAttachmentUri, "text/plain");
                String imageAttachmentUri = null;
                m.getAttachments().put(imageAttachmentUri, "image/png");
                Display.getInstance().sendMessage(new String[] {"mohamedamine.bader@esprit.tn"}, "Bienvenue", m);
               new WalkthruForm(res).show();
        }
        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                System.out.println(input);

            }

         /*  @Override
            protected void postResponse() {
                if (user.getRoles().equals("ROLE_CLIENT")) {
                        new ClientBaseForm().show();
                    /*Message m = new Message("Welcome in GoVoyage Application");
                        m.getAttachments().put("test", "text/plain");
                        //m.getAttachments().put(imageAttachmentUri, "image/png");
                        Display.getInstance().sendMessage(new String[]{user.getEmail()}, "Subject of message", m);
                    System.out.println("corect");
                } 
                else {
                    Dialog.show("error", "Votre Espace n'est pas encore pret ", "cancel", "ok");
                }
            }  */
        };
        connectionRequest.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/user/new?username=" + userlog + "&email=" + email + "&password=" + passhs + "&roles=" + rol + "&telephone=" + numtel + "&adresse=" + adresse +"&nom="+nom+"&prenom"+prenom+"&photo=profil.jpg");

//        connectionRequest.setUrl("http://localhost:8081/apijsonpi/web/app_dev.php/api/newuser?username=" + userlog + "&email=" + email + "&password=" + MD5.hash(pass) + "&role=" + rol + "&numtel=" + numtel + "&adresse=" + adresse);
        //connectionRequest.setUrl("http://localhost/apijsonpi/web/app_dev.php/api/newuser?username=" + userlog + "&email=" + email + "&password=" + MD5.hash(pass) + "&role=" + rol + "&numtel=" + numtel + "&adresse=" + adresse);

        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
   
   
   
     
}

