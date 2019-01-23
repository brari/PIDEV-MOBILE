/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Recette;
import com.mycompany.entities.CommentaireEvent;
import com.mycompany.services.CommentServices;

/**
 *
 * @author esprit
 */
public class RecetteDetailsForm {
Form fdetails;
  Container ca;
   Button btn_comment;
     TextField txt;
    public RecetteDetailsForm(Recette a) {
          Label id = new Label("");
        fdetails = new Form(" ", BoxLayout.y());

        Label DescDetails = new Label("Description");

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
                            CommentaireEvent c = new CommentaireEvent(Integer.parseInt(id.getText()), txt.getText(), 2);
                            ser.ajoutRecetteComment(c);
                            reload(a).show();
                        });

                        fdetails.add(txt);
                        fdetails.add(btn_comment);
                    }
                    for (int i = 0; i < cs.getList2(id.getText()).size(); i++) {

                        SpanLabel Comments = new SpanLabel("");
                        Label idannonce = new Label();
                        Label idUser = new Label();
                        Button btn_supprimer = new Button("Supprimer");
                        ca.add(Comments);

                        CommentaireEvent c = cs.getList2(id.getText()).get(i);
                        if (c.getId() == (Integer.parseInt(id.getText()))) {
                            Comments.setText(c.getComment());
                            idUser.setText(Integer.toString(c.getId_utilisateur()));
                            idannonce.setText(Integer.toString(c.getId()));
                            if ((c.getId_utilisateur() == 2)) {
                                ca.add(btn_supprimer);
                                btn_supprimer.addActionListener((e) -> {
                                    cs.SupprimerRecetteComment(Integer.toString(c.getId()));
                                    fdetails.refreshTheme();

                                });
                            }
                        }
                    }
                      fdetails.getToolbar().addCommandToLeftBar("<", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                fdetails.removeComponent(ca);
                RecettesForm f=new RecettesForm();
                f.getF().show();

            }
        });
                    fdetails.show();
                    DescDetails.setText(a.getDescription());

                }
 public Form reload(Recette a){
        RecetteDetailsForm aff=new RecetteDetailsForm(a);
        return aff.getFdetails();
    }
    public Form getFdetails() {
        return fdetails;
    }

    public void setFdetails(Form fdetails) {
        this.fdetails = fdetails;
    }
    
    }


