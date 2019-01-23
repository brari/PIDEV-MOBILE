/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.spinner.TimeSpinner;
import com.mycompany.entities.Patisserie;
import com.mycompany.entities.Reservation;
import com.mycompany.services.PatisserieService;
import com.mycompany.services.ReservationService;
import com.mycompany.services.authuser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author hp
 */
public class DetailsPatisserieP {
    Form f;
    Image imgs;
    EncodedImage enc;
    Label lb; String ch;
    
    public DetailsPatisserieP(Patisserie p){
        f= new Form("Détails", new BoxLayout(BoxLayout.Y_AXIS));
        
        PatisserieService servicep=new PatisserieService();
        Patisserie pat= servicep.afficherdetail(p.getIdp());
        
        String url;
        Container c1=new Container(new FlowLayout(CENTER,CENTER));
        ImageViewer img;
        
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
        }
        
        if(p.getUrl()!=null){
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
        Label l1=new Label("Nom: "+p.getNom());
        Label l2=new Label("Téléphone: "+p.getContact());
        Label l3=new Label ("Mail: "+p.getMail());
        Label l4=new Label ("Adresse: "+p.getAdresse());
        Label l5=new Label ("Propriétaire: "+p.getIdprop());
        Label l6=new Label ("Rating: "+ p.getRating());
        
        RatingFormP rf= new RatingFormP(p);
        Slider rating= rf.getRating();
        rating.setEditable(false);
                
        c2.addAll(l1,l2,l3,l4,l5,l6);
        c2.add(FlowLayout.encloseCenter(rating));
        
        if(p.isReservation()){
            Label l7=new Label ("Places: "+p.getPlace());
            c2.add(l7);
        }       
        c1.add(img);
        f.addAll(c1,c2);
        
        f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{
            Affichage a=new Affichage();
            a.getF().show();
        
        });
                
        f.getToolbar().addCommandToLeftSideMenu("Liste Produits", null, null);
        f.getToolbar().addCommandToLeftSideMenu("Commandes", null, null);
        
        f.getToolbar().addCommandToOverflowMenu("Supprimer", null, (evt) -> {
            if(Dialog.show("","Confirmez vous la suppression de cette patisserie?", "Oui", "Non")){
                PatisserieService serv=new PatisserieService();
                serv.Supprimer(p.getIdp());
                AffichagePat af=new AffichagePat();
                af.getF().show();
            }
        });
        
        f.getToolbar().addCommandToOverflowMenu("Modifier", null, (evt) -> {
            //Dialog.show("","Confirmez vous la suppression de cette patisserie?", "Oui", "Non"));
//                PatisserieService serv=new PatisserieService();
//                serv.Supprimer(p.getIdp());
                AjoutPatisserie af=new AjoutPatisserie(p);
                af.getF().show();
        });      
       
    }

    public Form getF() {
        return f;
    }
    
}