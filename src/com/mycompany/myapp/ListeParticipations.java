/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenements;
import com.mycompany.services.EvenementService;
import com.mycompany.services.Hamburger;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ListeParticipations {
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
        Form hi = new Form("♥ Liste des participations ♥");
        //setUIID("RegisterForm");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        new Hamburger().hamb(hi);      
         
//        Button b = new Button("Ajouter un produit");
//        hi.add(b);
//        b.addActionListener(e->{
//            new AjouterEvenement().start();
//        });
       
        hi.show();
        
        hi.getToolbar().addCommandToRightBar("Logout",null,new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new MyApplication().start();
                    }
                });
       
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
                        //detailsContainer.add(event.getDescriptionE().toString());
                      //  detailsContainer.add(produit.getIdCategorie().getNomCategorie());
                            Button afficher = new Button("Afficher");
                            
                        afficher.addActionListener(e -> {
                            new AfficherParticipation().start(event, false);
                        });
                        containerProduit.add(detailsContainer);
                        Button b = new Button(event.getNomE());
                        b.addActionListener(e -> {
                            new AfficherParticipation().start(event, false);
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
