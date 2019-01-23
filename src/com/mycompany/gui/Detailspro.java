/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commande;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceCommande;
import com.mycompany.services.ServiceProduit;
import java.io.IOException;
//import static java.sql.JDBCType.NULL;

/**
 *
 * @author Insaf-Nefzi
 */
public class Detailspro {
      Form f;
    private Form current;
    private Resources theme;
     Button Commander;
    TextField nom;
     Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
    Boolean test;
    public void addItem(Produit pr){
        ServiceProduit spp=new ServiceProduit();
        pr=spp.getProduitById(Produit.focusedId);
        Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
  String     url="http://localhost/kk/PI/AnnuaireWeb/web/Images/default.jpg";
       

        if (url != null) {
            try {
                enc = EncodedImage.create("/load.png");
            } catch (IOException ex) {
            }

             imgs = URLImage.createToStorage(enc, url, url,URLImage.RESIZE_SCALE);
        imgv=new ImageViewer(imgs);

        }
    //    ImageViewer img=new ImageViewer(theme.getImage(pr.getImage()));
        Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c3 =new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
        Container c4 =new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
       Label l=new Label("Nom : "+pr.getNompro());
        Label l3=new Label("Prix : "+String.valueOf(pr.getPrixpro()));
        Label l4 =new Label ("Categorie : "+pr.getCategoriepro());
       Label l1=new Label("Gout : "+pr.getDetailspro());
       Label l2=new Label("Patisserie : "+pr.getNompat());
       Label l5=new Label ("Quantite : "+String.valueOf(pr.getStock()));
       c2.add(l);
        c2.add(l3);
       c2.add(l4);
        c2.add(l1);
        c2.add(l2);
     //  c2.add(l5);
    c4.add(imgv);
       c1.add(c2);
       f.add(c4);
       f.add(c1);
        Commander = new Button("Commander");
          
            //c3.add(Commander);
          if (pr.getStock() != 0) {

           c3.add(Commander);
             f.add(c3);
          }
    //  f.add(c3);
    if(pr.getStock() == 0 )
    {
           Container c5 =new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
       Label t=new Label("Non Disponible");
       c5.add(t);
       f.add(c5);
    }
   
       
         
       f.refreshTheme();
       c1.setLeadComponent(l);
       Button b1=new Button("ok");
       
                Dialog d=new Dialog();
                d.add("Plus D'info :: ");
                d.add("Nom : " + pr.getNompro()+" , ");
                
                d.add("Prix : "+String.valueOf(pr.getPrixpro())+" , ");
                
                d.add("Categorie : " +pr.getCategoriepro()+" , ");
               
                d.add("Details : "+pr.getDetailspro()+" , ");
               
                d.add("Nom Patisserie : "+pr.getNompat()+" , ");
                
                d.add("Quantite : "+String.valueOf(pr.getStock()));
            
                d.add(b1);
                
                  b1.addActionListener(new ActionListener() {
            @Override
        
            public void actionPerformed(ActionEvent evt) {
                   
      f.show();
            }
                     }
          );
       l.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
               
               // d.show();
            }
        });
             
    }
        public Detailspro() {
        
        f = new Form();

        ServiceProduit serviceTask=new ServiceProduit();
      ServiceCommande serviceTaskk=new ServiceCommande();
//        for (int i = 0; i < serviceTask.getList2().size(); i++){
//            Produit e = s2().get(i);erviceTask.getList
ServiceProduit spp=new ServiceProduit();
      Produit  pr=spp.getProduitById(Produit.focusedId);
            addItem(pr);
           
           Commande c=new Commande();
    
 
               
                   Commander.addActionListener((eee)->{
                  serviceTaskk.ajoutCommande(c,pr);
                  Produit.focusedId=pr.getId();
       System.out.println(c);
            AfficherCommande a=new AfficherCommande();
   a.getF().show();
       
        
        });
                   
     
                 
           f.getToolbar().addCommandToRightBar("back", null, (ev)->{
              
               AffichageProduit h=new AffichageProduit();
     h.getF().show();});
                     
       // }
//        lb.setText(serviceTask.getList2().toString());

                     Toolbar tb2=f.getToolbar();
         tb2.addMaterialCommandToLeftSideMenu("About", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           // h3.show();
        }
        });
           tb2.addMaterialCommandToLeftSideMenu("Ajouter Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           AjouterProduit h = new AjouterProduit();
   h.getF().show();
        }
        });
             tb2.addMaterialCommandToLeftSideMenu("Afficher Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            AffichageProduit a=new AffichageProduit();
        a.getF().show();
        }
        });
               tb2.addMaterialCommandToLeftSideMenu("Afficher Commande", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           AfficherCommande a=new AfficherCommande();
        a.getF().show();
        }
        });
        
                       tb2.addMaterialCommandToLeftSideMenu("Afficher Favoris", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
                  Fav a=new Fav();
        a.getH1().show();
        }
        });
                                         tb2.addMaterialCommandToLeftSideMenu("Afficher Statistiques", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
             Stat a=new Stat();
        a.getF().show();
        }
                          });
    }
          public Form getF() {
        return f;
    }

    
}
