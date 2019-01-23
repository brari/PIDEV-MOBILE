/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
//import com.codename1.ext.filechooser.FileChooser;
//import com.codename1.ext.filechooser.FileChooserNative;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;





/**
 *
 * @author Insaf-Nefzi
 */
public class AjouterProduit {
     private Image img;
    private String imgPath;
     Form f;
     String jobPic = "";
     private Resources theme;
    TextField nompro;
    TextField prixpro;
     TextField categoriepro;
    TextField detailspro;
     TextField nompat;
    TextField stock;
    Button btnajout,btnaff,btnafff;
    Label im=new Label();
    public AjouterProduit() {
        Produit prr=new Produit();
        f = new Form("Le Coin Patissier");
        nompro = new TextField("","Nom");
        prixpro = new TextField("","Prix");
        categoriepro = new TextField("","Categorie");
        detailspro = new TextField("","Details");
        nompat = new TextField("","Nom Patisserie");
        stock = new TextField("","Stock");
        
        btnajout = new Button(" Valider l'ajout" );
        btnaff=new Button("Affichage produit");
         btnafff=new Button("Affichage commande");
//           ImageViewer imm=new ImageViewer();
//        imm.setImage(theme.getImage("photo.jpg"));
//       f.add(imm);
         Label l=new Label("Veuiller remplir les champs                   ");
         f.add(l);
          Label ll=new Label("                                                                  ");
         f.add(ll);
         Label n=new Label("NOM : ");
        f.add(n);
         f.add(nompro);
        
         Label p=new Label("PRIX : ");
          f.add(p);
        f.add(prixpro);
       
        Label c=new Label("CATEGORIE : ");
        f.add(c);
        f.add(categoriepro);
        
        
         Label g=new Label("GOUT : ");
         f.add(g);
        f.add(detailspro);
        
        Label t=new Label("PATISSERIE : ");
        f.add(t);
        f.add(nompat);
               Label tt=new Label("QUANTITE : ");
        f.add(tt);
     f.add(stock);
     
        Label i=new Label("IMAGE :                     ");
    
       f.add(i);
       
       Validator v = new Validator();
       v.addConstraint(nompro, new RegexConstraint("[a-z]", "pas de numero"));
       v.addConstraint(prixpro, new RegexConstraint("[0-9]$", "pas de lettre"));
    v.addConstraint(categoriepro, new RegexConstraint("[a-z]", "pas de numero"));
      v.addConstraint(detailspro, new RegexConstraint("[a-z]", "pas de numero"));
    v.addConstraint(nompat, new RegexConstraint("[a-z]", "pas de numero"));
    v.addConstraint(stock, new RegexConstraint("[0-9]$", "pas de lettre"));
        v.addSubmitButtons(btnajout);
//       
 //upload image 
     
        Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        Image camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
        Button jobIcon = new Button("Ajouter photo",camera);
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() +"profil.jpg";
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
       f.add(jobIcon);
        
        f.add(btnajout);

        btnajout.addActionListener((e) -> {
            ServiceProduit ser = new ServiceProduit();
            Produit pr = new Produit(nompro.getText(), Float.parseFloat(prixpro.getText()),categoriepro.getText(), detailspro.getText(),nompat.getText(),jobPic,Integer.parseInt( stock.getText()));
            System.out.println(pr);
            
            ser.ajoutProduit(pr);
             AffichageProduit a=new AffichageProduit();
        a.getF().show();
            
        });

   Toolbar tb2=f.getToolbar();
//          f.getToolbar().addCommandToRightBar("back", null, (ev)->{
//               MyApplication h=new MyApplication();
//     h.getH3().show();});
    
//             f.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt){
//            MyApplication h=new MyApplication();
//            h.getH3().show();
//        }
//        });
         tb2.addMaterialCommandToLeftSideMenu("About", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           // h3.show();
        }
        });
//           tb2.addMaterialCommandToLeftSideMenu("Ajouter Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt){
//           AjouterProduit h = new AjouterProduit();
//   h.getF().show();
//        }
//        });
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
    //}

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   

    

   

}
