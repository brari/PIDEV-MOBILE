/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.services.Hamburger;
import com.mycompany.entities.Evenements;
import com.mycompany.services.EvenementService;
import com.mycompany.ui.Affichage;
import com.mycompany.ui.AffichagePat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author DELL
 */

public class ListEvenements extends BaseForm { 
     private Form current;
    private Resources theme;
    static ArrayList<Evenements> events = new ArrayList<>();
    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        Toolbar.setGlobalToolbar(true);
        Toolbar.setCenteredDefault(false);
    }
    public void start() {
      
//        if(!Display.getInstance().isTablet()) {
//            BorderLayout bl = (BorderLayout)getLayout();
//            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
//            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
//        }
        if (current != null) {
            current.show();
            return;
        }
        Form hi = new Form("♥ Liste des Evénements ♥");
        setUIID("RegisterForm");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        new Hamburger().hamb(hi);   
        
        hi.getToolbar().addCommandToRightBar("Retour", null, (ev)->{
            AffichagePat a=new AffichagePat();
            a.getF().show();
        
        });
        
//      Button video=new Button("video");
//      hi.add(video);
//      video.addActionListener(e -> {
//                            
//                            //new EvenementService().modifier(event);
//                          
//                            new Video().start();
//                            
//          });
        Button b = new Button("Ajouter un évènement");
        hi.add(b);
        b.addActionListener(e->{
            new AjouterEvenement().start();
        });
       
        hi.show();
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/event/alljson");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            Container prods = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            @Override
            public void actionPerformed(NetworkEvent evt) {

                prods.setScrollableX(false);
                prods.setScrollableY(true);
                events = 
                new EvenementService().getListTask(new String(con.getResponseData()));
                int size = events.size();
               
                for (Evenements event : events) {
                        Container containerProduit = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        EncodedImage placeholder;
                    try {
                        Image image ;
                        if (event.getImageE().equals("error")) {
                            placeholder = EncodedImage.createFromImage(Image.createImage("/patiss.jpg"), false);
                             image = URLImage.createToStorage(placeholder,"/error"
                                 , "/patiss.jpg" );
                        }else {
                        placeholder = EncodedImage.createFromImage(Image.createImage("/patiss.jpg"), false);
                         image= URLImage.createToStorage(placeholder,event.getImageE()
                                 , "http://localhost/kk/PI/AnnuaireWeb/web/Evenement/image/" +event.getImageE() );
                        
                        
                        }
                        containerProduit.add(image.scaled(200, 200));
                    } catch (IOException ex) {
                        System.out.println(ex);
                        //Logger.getLogger(ListProduits.class.getName()).log(Level.SEVERE, null, ex);
                    }
                       
                        //EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage("/error.jpg"), false);
                       // Image image = URLImage.createToStorage(placeholder, produit.getImageName(), "http://localhost/pidev/image/" + "/" + produit.getImageName());
                       // containerProduit.add(image.scaled(100, 100));
                        Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        detailsContainer.add(new Label(event.getNomE()));
//                        detailsContainer.add(event.getDescriptionE().toString());
                      //  detailsContainer.add(produit.getIdCategorie().getNomCategorie());
                            Button afficher = new Button("Afficher");
                            
                        afficher.addActionListener(e -> {
                            new AfficherEvenements().start(event, false);
                        });
                        containerProduit.add(detailsContainer);
                        Button b = new Button(event.getNomE());
                        b.addActionListener(e -> {
                            new AfficherEvenements().start(event, false);
                        });
                        b.setHidden(true);
                        containerProduit.add(b);
                        containerProduit.setLeadComponent(b);
                        prods.add(containerProduit);
                   

                }
                 hi.add(prods);
                hi.refreshTheme();
            }
        });
        
        NetworkManager.getInstance().addToQueue(con);
       
       
    }

    
    
}
