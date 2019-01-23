/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Recette;
import com.mycompany.entities.CommentaireEvent;
//import com.mycompany.Entite.user;
import com.mycompany.services.CommentServices;
import java.io.IOException;

/**
 *
 * @author esprit
 */
public class RDetailsForm {

    Form fdetails;
    Container ca,c2;
    Button btn_comment;
    TextField txt;
    Image imgs;
    ImageViewer imgv;
    EncodedImage enc;

    public RDetailsForm(Recette a) {
//        Login lo = new Login();
//        user user = lo.getUserConnecte();
         Font fnt = Font.createTrueTypeFont("fontello", "fontello.ttf");
        
        int size = Display.getInstance().convertToPixels(4);
  ;
        FontImage sup = FontImage.createFixed("\uf1f8", fnt,0x000000, size, size);
        
        FontImage dat = FontImage.createFixed("\uf271", fnt,0x000000, size, size);
        Label id = new Label("");
        id.setVisible(false);
        fdetails = new Form(" ", BoxLayout.y());
        AccueilForm ac=new AccueilForm();
         ac.ToolBarAcueil(fdetails);

        Label DescDetails = new Label("Description");
            String url = "http://localhost/integration_finaaaaal_ttequipe/kk/PI/AnnuaireWeb/web/imgRecette/" + a.getPhoto();

            if (url != null) {
                try {
                    enc = EncodedImage.create("/load.png");
                } catch (IOException ex) {
                }

                imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer(imgs);
                fdetails.add(imgv);
            }
        fdetails.add(DescDetails);

        fdetails.add(id);
        fdetails.setTitle(a.getNom());
        id.setText(Integer.toString(a.getId()));

        CommentServices cs = new CommentServices();
        ca = new Container(BoxLayout.y());
        fdetails.add(ca);
        if ((a.isIsdisabled() == false)) {
            txt = new TextField("", "Ajouter ton commentaire");
            btn_comment = new Button("post comment");
            System.out.println(a.isIsdisabled());
            btn_comment.addActionListener((e) -> {
                CommentServices ser = new CommentServices();
                CommentaireEvent c = new CommentaireEvent(Integer.parseInt(id.getText()), txt.getText(),2);
                ser.ajoutRecetteComment(c);
                reload(a).show();
            });
            fdetails.add(txt);
            fdetails.add(btn_comment);
        }
        for (int i = 0; i < cs.getList2(id.getText()).size(); i++) {

            SpanLabel Comments = new SpanLabel("");
            Label idannonce = new Label();
           
         
             Label supp=new Label(sup);
             Label date=new Label(dat);
             date.setText( cs.getList2(id.getText()).get(i).getDate().toString());
             
             System.out.println(a.getDate());
               c2=new Container(BoxLayout.x());
               c2.add(Comments);
               c2.add(date);
       

            CommentaireEvent c = cs.getList2(id.getText()).get(i);
            if (c.getId() == (Integer.parseInt(id.getText()))) {
                Comments.setText(c.getComment());
             
                idannonce.setText(Integer.toString(c.getId()));
                if ((c.getId_utilisateur() == 2)) {
                    c2.add(supp);
                   supp.addPointerPressedListener((e) -> {
                        cs.SupprimerRecetteComment(Integer.toString(c.getId()));
                        reload(a).show();

                    });
                }
                 ca.add(c2);
            }
        }
        fdetails.getToolbar().addCommandToLeftBar("<", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                fdetails.removeComponent(ca);
                RecetteForm f = new RecetteForm();
                f.getF().show();

            }
        });
       // fdetails.show();
        DescDetails.setText(a.getDescription());

    }

    public Form reload(Recette a) {
        RDetailsForm aff = new RDetailsForm(a);
        return aff.getFdetails();
    }

    public Form getFdetails() {
        return fdetails;
    }

    public void setFdetails(Form fdetails) {
        this.fdetails = fdetails;
    }

}