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
import com.mycompany.entities.Commande;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceCommande;
import java.io.IOException;


/**
 *
 * @author Insaf-Nefzi
 */
public class AfficherCommande {
    Form f;
    SpanLabel lb;
      Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
    
    Button Supprimer;
    Button Favoris;
    private Database db;
    
      
    
       
     public void addItem(Commande pr){
        Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        String     url="http://localhost/kk/PI/AnnuaireWeb/web/Images/default.jpg";

        if (url != null) {
            try {
                enc = EncodedImage.create("/load.png");
            } catch (IOException ex) {
            }

            imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(imgs);

        }
    //    ImageViewer img=new ImageViewer(theme.getImage(pr.getImage()));
        Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c3 =new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
       Label l=new Label(pr.getNompro());
       Label l1=new Label(pr.getDetailspro());
       Label l2=new Label(pr.getNompat());
       c2.add(l);
        c2.add(l1);
       c2.add(l2);
       c1.add(imgv);
       c1.add(c2);
       f.add(c1);
      f.add(c3);
       
      
          Supprimer = new Button("Annuler Commande");
          
            c3.add(Supprimer);
             Favoris = new Button("Ajouter aux favoris");
          
            c3.add(Favoris);
            
       f.refreshTheme();
       c1.setLeadComponent(l);
       Button b1=new Button("ok");
       
                Dialog d=new Dialog();
                d.add("Plus D'info :: ");
                d.add("Nom : " + pr.getNompro()+" , ");
                
                d.add("Prix : "+String.valueOf(pr.getPrixpro())+" , ");
                
                d.add("Categorie : " +pr.getCategoriepro()+" , ");
               
                d.add("Details : "+pr.getDetailspro()+" , ");
               
                d.add("Nom Patisserie : "+pr.getNompat());
                
               
            
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
                
               
                d.show();
            }
        });
             
    }

    public AfficherCommande() {
           f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceCommande serviceTask=new ServiceCommande();
        
         try {
            db=Database.openOrCreate("favvvv.db");
            db.execute("create table if not exists favori (nompro TEXT , categ TEXT , gout TEXT ,pat TEXT );");
//          db.execute("insert into favori(nompro,categ,gout,pat ) values('Pizza','sale','thon','les galets' );");
//            Cursor cr=db.executeQuery("select * from favori;");
//            while (cr.next()) {
//               Row r=cr.getRow() ;
//               String idcp  =r.getString(0); 
//               String id  =r.getString(1); 
//               String idc  =r.getString(2); 
//               String idcpp  =r.getString(3); 
//              // String nompro=r.getString(1);
//                System.out.println("nom : "+idcp +"categ"+id+"gout"+idc+"pat"+idcpp);
//                
//            }
            
        } catch (IOException ex) {
            System.out.println(""+ex.getMessage());
        }
        
        
        for (int i = 0; i < serviceTask.getList2().size(); i++){
            Commande e = serviceTask.getList2().get(i);
            addItem(e);
           
          
    
 
                 Supprimer.addActionListener((ee)->{
         serviceTask.supprimerCommande(e.getId(),e.getIdpro());
          System.out.println(e);
          f.refreshTheme(); 
          f.show();
            
        
        });
                   Button b1=new Button("ok");
             Dialog d=new Dialog();
             d.add("Merci d'avoir aimé ");
            d.add(b1);
                  Favoris.addActionListener((eu) -> {
                      
            try {
               
                db.execute("insert into  favori (nompro,categ,gout,pat )values('"+e.getNompro()+"','"+e.getCategoriepro()+"','"+e.getDetailspro()+"','"+e.getNompat()+"');'");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
                   
            ServiceCommande ser = new ServiceCommande();
           // ser.JaimeEvent(l.getText());
            //Dialog.show("", "Merci de donner votre avis ", "OK", null);
            
            d.show();
           
         
        });
                     b1.addActionListener(new ActionListener() {
            @Override
        
            public void actionPerformed(ActionEvent evt) {
                   
              Fav a=new Fav();
        a.getH1().show();
            }
                     }
          );
                 
                      
    }
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
//               tb2.addMaterialCommandToLeftSideMenu("Afficher Commande", FontImage.MATERIAL_SETTINGS, new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt){
//           AfficherCommande a=new AfficherCommande();
//        a.getF().show();
//        }
//        });
        
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
