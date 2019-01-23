/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Produit;
import java.io.IOException;

/**
 *
 * @author Insaf-Nefzi
 */
public class Fav {
    Form h1;
Database db;
 
      
    public Fav() {
      
        h1=new Form();
          try {
               Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                              Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));

                    db=Database.openOrCreate("favvvv.db");
               db.execute("create table if not exists favori (nompro TEXT , categ TEXT , gout TEXT ,pat TEXT);");
               Cursor cr=db.executeQuery("select * from favori;");
                    while (cr.next()) {
                        Row r=cr.getRow() ;
                   String idcp  =r.getString(0); 
               String id  =r.getString(1); 
               String idc  =r.getString(2); 
               String idcpp  =r.getString(3); 
              // String nompro=r.getString(1);
                System.out.println("nom : "+idcp +"categ : "+id+"gout : "+idc+"pat : "+idcpp);
                SpanLabel l=new SpanLabel("Favoris : ");
               SpanLabel l1=new SpanLabel("nom : "+idcp );
               SpanLabel l2=new SpanLabel(" Gout : "+ idc);
               SpanLabel l3=new SpanLabel("categorie : "+id);
               SpanLabel l4=new SpanLabel(" Patisserie : "+ idcpp);
//               c2.add(l);
//               c2.add(l1);
//              c2.add(l2);
//              c2.add(l3);
//              c2.add(l4);
//              c1.add(c2);
            //  h1.add(c1);
           //  h1.add(l);
               h1.add(l1);
              h1.add(l2);
//              h1.add(l3);
//              h1.add(l4);
               h1.show();
               
                        
                    }
                } catch (IOException ex) {
                    System.out.println(""+ex.getMessage());   
                }
          
               
                     
        
//        lb.setText(serviceTask.getList2().toString());

                     Toolbar tb2=h1.getToolbar();
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
        
//                       tb2.addMaterialCommandToLeftSideMenu("Afficher Favoris", FontImage.MATERIAL_SETTINGS, new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt){
//                  Fav a=new Fav();
//        a.getH1().show();
//        }
//        });
                  tb2.addMaterialCommandToLeftSideMenu("Afficher Statistiques", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
             Stat a=new Stat();
        a.getF().show();
        }
                          });
  h1.getToolbar().addCommandToRightBar("back", null, (ev)->{
                com.mycompany.ui.Affichage a=new com.mycompany.ui.Affichage();
     a.getF().show();});
    }

    public Form getH1() {
        return h1;
    }
    
    
    
}
