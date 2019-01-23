/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
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
import com.mycompany.myapp.MyApplication;
import java.io.IOException;


/**
 *
 * @author Insaf-Nefzi
 */
public class AffichageProduit {
    Form f;
    private Form current;
    private Resources theme;
    SpanLabel lb;
    SpanLabel lb1;
    Button btn_event;
 Button Modifier;
    Button Supprimer;
    Button Commander;
    Button Stat;
    TextField nom;
     Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
     public void addItem(Produit pr) {
        Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
      


            try {
                enc = EncodedImage.create("/load.png");

            } catch (IOException ex) {
            }

 imgs=URLImage.createToStorage(enc,"profil.jpg","C:/Users/INSAF-NEFZI/.cn1/"+"profil.jpg", URLImage.RESIZE_SCALE); 
      
    imgv = new ImageViewer(imgs);
    
 
        Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c3 =new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
       Label l=new Label("Voila  : " +pr.getNompro());
       Label l1=new Label("Gout : "+pr.getDetailspro());
       Label l2=new Label("De chez : "+pr.getNompat());
       Label l3=new Label("Cliquez pour details");
       
       
       Modifier = new Button("Modifier");
          Supprimer = new Button("Supprimer");
             if (pr.getCategoriepro() == "Sucre") {

          
             l.getAllStyles().setFgColor(0x0080FF);
             l1.getAllStyles().setFgColor(0x0080FF);
             l2.getAllStyles().setFgColor(0x0080FF);
             l3.getAllStyles().setFgColor(0x0080FF);
          }
              if (pr.getCategoriepro() == "Sale") {

          
             l.getAllStyles().setFgColor(0x00008B);
             l1.getAllStyles().setFgColor(0x00008B);
             l2.getAllStyles().setFgColor(0x00008B);
             l3.getAllStyles().setFgColor(0x00008B);
          }
              c2.add(l);
        c2.add(l1);
       c2.add(l2);
       c2.add(l3);
    c1.add(imgv);
       c1.add(c2);
       f.add(c1);
      f.add(c3);
         //  c3.add(Modifier);
           // c3.add(Supprimer);
           // c3.add(Commander);
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
                Produit.focusedId=pr.getId();
               Detailspro d=new Detailspro();
               
                d.getF().show();
            }
        });
             
    }
  
    public AffichageProduit()   {
        
        f = new Form("Le Coin Patissier");
 Stat = new Button("Statistique");
 f.add(Stat);
        ServiceProduit serviceTask=new ServiceProduit();
      ServiceCommande serviceTaskk=new ServiceCommande();
        for (int i = 0; i < serviceTask.getList2().size(); i++){
            Produit e = serviceTask.getList2().get(i);
          
            addItem(e);
           
           Commande c=new Commande();
   
           
                 Modifier.addActionListener((ee)->{
                     Produit.focusedId=e.getId();
        ModifierProduit a=new ModifierProduit();
        a.getF().show();
        
        });
                 

            
                  Supprimer.addPointerPressedListener(l->{
          serviceTask.supprimerProduit(e.getId());
          System.out.println(e);
            AffichageProduit a=new AffichageProduit();
        a.getF().show();
            
        });
//                   Commander.addActionListener((eee)->{
//                  serviceTaskk.ajoutCommande(c,e);
//       System.out.println(c);
//          f.refreshTheme(); 
//          f.show();
//       
//        
//        });
                   
     
//                 
//           f.getToolbar().addCommandToRightBar("back", null, (ev)->{MyApplication h=new MyApplication();
//     h.getH3().show();});
                     
        }
        
                  Stat.addPointerPressedListener(l->{
          
            Stat a=new Stat();
        a.getF().show();
            
        });
//         f.getToolbar().addCommandToRightBar("back", null, (ev)->{
//               MyApplication h=new MyApplication();
//     h.getH3().show();});
                     
        
//        lb.setText(serviceTask.getList2().toString());

    f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{
            com.mycompany.ui.Affichage a=new com.mycompany.ui.Affichage();
            a.getF().show();
        
        });

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
//             tb2.addMaterialCommandToLeftSideMenu("Afficher Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt){
//            AffichageProduit a=new AffichageProduit();
//        a.getF().show();
//        }
//        });
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

    public void setF(Form f) {
        this.f = f;
    }

    
}
