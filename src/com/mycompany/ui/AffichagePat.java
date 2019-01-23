/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.MyFirstApp.LoginInForm;
import com.mycompany.MyFirstApp.MyApplication;
import com.mycompany.entities.Patisserie;
import com.mycompany.gui.AffichageProduit;
import com.mycompany.gui.Detailspro;
import com.mycompany.myapp.ListEvenements;
import com.mycompany.services.PatisserieService;
import com.mycompany.services.ReservationService;
import com.mycompany.services.authuser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class AffichagePat {

    Form f;
    EncodedImage enc;
    Image imgs;
    String url,ch;
  
    public AffichagePat() {
        
        f = new Form("Liste Patisseries",new BoxLayout(BoxLayout.Y_AXIS));
        System.out.println("USERID::::"+authuser.user.getId());
        PatisserieService servicep=new PatisserieService();
        ArrayList<Patisserie> patisseries= servicep.afficherMesPat(authuser.user.getId());
        for( Patisserie p : patisseries){
             addItem(p);        
        }
 
          
        f.getToolbar().addCommandToLeftSideMenu("Reclamation", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }               
        });
          f.getToolbar().addCommandToLeftSideMenu("Produits", null, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            AffichageProduit a=new AffichageProduit();
        a.getF().show();
        }
        });
        
        f.getToolbar().addCommandToLeftSideMenu("Evenement", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListEvenements().start();            }
                
        });
        
        f.getToolbar().addCommandToLeftSideMenu("Recettes", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
                
        });
        
        
        f.getToolbar().addCommandToLeftSideMenu("Logout", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LoginInForm form = new LoginInForm(MyApplication.theme);
            form.show();
            }
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    public void addItem(Patisserie p){
        
        Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        ImageViewer img;
        
        try {
            enc = EncodedImage.create("/load.png");//l'image chargée en attendant
            //la recuperation de l'image.
        } catch (IOException ex) {
        }
        
        if(p.getUrl()!=null && p.getUrl()!=""){
        url="http://localhost/kk/PI/AnnuaireWeb/web/Images/"+p.getUrl();
        imgs = URLImage.createToStorage(enc, url, url,URLImage.RESIZE_SCALE);
        img=new ImageViewer(imgs);
        }
        else{
           url="http://localhost/kk/PI/AnnuaireWeb/web/Images/default.jpg";
        imgs = URLImage.createToStorage(enc, url, url,URLImage.RESIZE_SCALE);
        img=new ImageViewer(imgs);
        }
 
        Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l1=new Label(p.getNom());
        Label l2=new Label(""+p.getContact());       
        c2.add(l1); c2.add(l2); c1.add(img); c1.add(c2);
        c2.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
//                PatisserieService servicep=new PatisserieService();
//                Patisserie pat= servicep.afficherdetail(p.getIdp());
//                Dialog.show("Patisserie", "Nom: "+pat.getNom()+"\nTel: "+pat.getContact()+"Mail: "+pat.getMail()
//                        , "OK", null);
                  DetailsPatisserieP dp=new DetailsPatisserieP(p);
                  dp.getF().show();
            }
        });
        //pour que toute action sur l1 soit propagée a tt le conteneur
        //c1.setLeadComponent(l1);
        f.add(c1);
    }

}
