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
import com.mycompany.gui.MesRecetteForm;
import com.mycompany.gui.RecettesForm;
import com.mycompany.myapp.AfficherEvenementsClient;
import com.mycompany.myapp.ListEvenementsClient;
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
public class Affichage {

    Form f;
    EncodedImage enc;
    Image imgs;
    String url,ch;
  
    public Affichage() {
        
        f = new Form("Liste Patisseries",new BoxLayout(BoxLayout.Y_AXIS));
        System.out.println("USERID:+++++ "+authuser.user.getId());
        PatisserieService servicep=new PatisserieService();
        ArrayList<Patisserie> patisseries= servicep.afficherPat();
        for( Patisserie p : patisseries){
             addItem(p);        
        }
//        f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{HomeForm h=new HomeForm();
//          h.getF().show();
//          });
          
        f.getToolbar().addCommandToLeftSideMenu("Reservations", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form hi=new Form("Vos reservations");
                String url2="http://localhost/kk/PImobile/voirreservation?id="+authuser.user.getId();
                ConnectionRequest con=new ConnectionRequest(url2); 
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        try {
                            ch = new String(con.getResponseData(), "utf-8");
                            System.out.println(ch);
                        } catch (UnsupportedEncodingException ex) {
                            System.out.println("Unsuported encoding exc");
                        }catch (NullPointerException ex){
                                System.out.println("--------null---------");
                        }
//                        lb.setText(ch);                    
//                        reserver.refreshTheme();
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(con);
                SpanLabel lb=new SpanLabel();
                lb.setText(ch); 
                hi.add(lb);
                hi.getToolbar().addCommandToRightBar("Retour", null, (event) -> {
                    Affichage aff=new Affichage();
                    aff.getF().show();
                });
                hi.show();
            }
                
        });
        
        f.getToolbar().addCommandToOverflowMenu("Où reserver", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                f.removeAll();
                PatisserieService pse= new PatisserieService();
                ArrayList<Patisserie> patisseries= pse.resPatisserie();
                for( Patisserie p : patisseries){
                    addItem(p);        
                }
                
            }               
        });
        
        f.getToolbar().addCommandToLeftSideMenu("Reclamation", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }               
        });
        
        f.getToolbar().addCommandToLeftSideMenu("Evenement", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListEvenementsClient().start();
            }
                
        });
           f.getToolbar().addCommandToLeftSideMenu("Produits", null, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            AffichageProduit a=new AffichageProduit();
        a.getF().show();
        }
        });
        
        f.getToolbar().addCommandToLeftSideMenu("Recettes", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    
               MesRecetteForm a=new MesRecetteForm();
        a.getF().show();     
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
                  DetailsPatisserie dp=new DetailsPatisserie(p);
                  dp.getF().show();
            }
        });
        //pour que toute action sur l1 soit propagée a tt le conteneur
        //c1.setLeadComponent(l1);
        f.add(c1);
    }

}
