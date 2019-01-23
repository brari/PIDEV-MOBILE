/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Insaf-Nefzi
 */
public class Accueil {
     Form f;
     private Form current;
    private Resources theme;

    public Accueil() {
        // f=new Form();
           f = new Form("Le Coin Patissier", BoxLayout.y());
     ImageViewer im=new ImageViewer();
        im.setImage(theme.getImage("photo.jpg"));
          
        f.add(im);
SpanLabel t1= new SpanLabel ("'    Bienvenue dans notre Coin Patissier    '");

        f.add(t1);
      
       SpanLabel t11= new SpanLabel ("'     A la rencontre de vos saveurs        '");
        f.add(t11);
         ImageViewer imaq=new ImageViewer();
        imaq.setImage(theme.getImage("contact.jpg"));
          
        f.add(imaq);
         ImageViewer im1=new ImageViewer();
        im1.setImage(theme.getImage("1.jpg"));
          
        f.add(im1);
         ImageViewer ima=new ImageViewer();
        ima.setImage(theme.getImage("2.jpg"));
          
        f.add(ima);
        ImageViewer imaa=new ImageViewer();
        imaa.setImage(theme.getImage("3.jpg"));
          
        f.add(imaa);
        ImageViewer imaz=new ImageViewer();
        imaz.setImage(theme.getImage("4.jpg"));
         
        f.add(imaz);
       
//        ImageViewer imar=new ImageViewer();
//        imar.setImage(theme.getImage("image.jpg"));
//          
//        h3.add(imar);
        
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
                          f.show();
    }
    
     
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
     
}
