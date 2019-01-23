/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.Recette;
import com.mycompany.services.MesRecettesServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esprit
 */
public class MesRecetteForm {

    Form f = Display.getInstance().getCurrent();
    Container c;
    Button Modifier, Bloquer, Supprimer;
    SpanLabel l;
    Label Desc;
    
    Label date;
    Label nom;
    Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
    ListeAccepted all=new ListeAccepted();
 
    public MesRecetteForm() {
        f = new Form("Mes Recettes", BoxLayout.y());
       AccueilForm acc=new AccueilForm();
         acc.ToolBarAcueil(f);
        MesRecettesServices MesRecette = new MesRecettesServices();
        for (int i = 0; i < MesRecette.getList2(2).size(); i++) {

            Recette a = MesRecette.getList2(2).get(i);
           if (a.getEtat().equalsIgnoreCase("Accepter")) {
                all.ajouterRecette(a);
            }

          
            nom = new Label("nom");
            date = new Label("Date");
            Desc = new Label("Description");

            
            nom.setText(a.getNom());
            Desc.setText(a.getDescription());
            c = new Container(BoxLayout.x());

            Modifier = new Button("Modifier");
            l = new SpanLabel();

            Bloquer = new Button();
            if (a.isIsdisabled() == false) {
                Bloquer.setText("Debloquer");
            } else if (a.isIsdisabled() == true) {
                Bloquer.setText("Bloquer");
            }
            Bloquer.addActionListener((e) -> {
                MesRecette.ChangeStatusRecette(Integer.toString(a.getId()));
                reload().show();
            });
            Supprimer = new Button("Supprimer");
            String url = "http://localhost/kk/PI/AnnuaireWeb/web/imgRecette/" + a.getPhoto();
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
            c.add(Modifier);

            c.add(Bloquer);
            c.add(Supprimer);
            f.add(nom);
           
            f.add(Desc);
            f.add(l);
            f.add(c);

            Supprimer.addActionListener((e) -> {
                MesRecette.SupprimerRecette(Integer.toString(a.getId()));
                c.getComponentForm().revalidate();

            });
            f.getComponentForm().revalidate();
            if (all.rechercherRecette(a)) {
                f.getToolbar().addCommandToOverflowMenu("Votre recette" + " " + a.getNom()+ " a étè modifier ", null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                            all.supprimerRecette(a);
                     

                        System.out.println(a.getId());
                          all.displayRecette();
                      //  reload().show();
                    }
                });
            }
        }
       f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{
            com.mycompany.ui.Affichage a=new com.mycompany.ui.Affichage();
            a.getF().show();
        
        });
        all.displayRecette();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Form reload() {
        MesRecetteForm aff = new MesRecetteForm();
        return aff.getF();
    }
}
