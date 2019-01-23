/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Recette;
import com.mycompany.services.RecetteService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author esprit
 */
public class RecettesForm {

    Form f;
    SpanLabel lb;
    Label Desc;
    
    Label date;
    Label titre;
    Button btn_details;
    Image imgs;
    ImageViewer imgv;
    EncodedImage enc;

    public RecettesForm() {
        f = new Form("Recette", BoxLayout.y());
         AccueilForm acc=new AccueilForm();
         acc.ToolBarAcueil(f);
        lb = new SpanLabel("");
       
        titre = new Label("nom");
        Desc = new Label("Description");
        Label id = new Label("");
    

        RecetteService serviceAnnonces = new RecetteService();
         //System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEST");
        //System.out.println("teeeeeeeeeeeeesqt "+serviceAnnonces.getList2().size());
        ArrayList <Recette> tab= serviceAnnonces.getList2();
        //System.out.println("taaaaaaaaaaaaaaab "+tab);
        for (int i = 0; i < serviceAnnonces.getList2().size(); i++) {
            //System.out.println("Allez********************************************");
            Recette a = serviceAnnonces.getList2().get(i);
             //System.out.println("********************************************"+a);
            titre = new Label("nom");
            date = new Label("Date");
            Desc = new Label("Description");

            f.add(titre);
           
            f.add(Desc);
            btn_details = new Button("Read More");
            f.add(btn_details);

           
            titre.setText(a.getNom());
            Desc.setText(a.getDescription());
            String url = "http://localhost/kk/PI/AnnuaireWeb/web/ilhem/" + a.getPhoto();
//            System.out.println(a.getPhoto());
            if (url != null) {
                try {
                    enc = EncodedImage.create("/load.png");
                } catch (IOException ex) {
                }

                imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer(imgs);
                f.add(imgv);
            }
//       date.setText(a.getDate().toString());
            btn_details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

//                   AnnonceDetailsForm aff=new AnnonceDetailsForm(a);
                  RecetteDetailsForm aff=new RecetteDetailsForm(a);

                   aff.getFdetails().show();

                }
            });
        }
      
    }
  public Form reload(){
        RecettesForm aff=new RecettesForm();
        return aff.getF();
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
 