/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Patisserie;
import com.mycompany.services.PatisserieService;

/**
 *
 * @author hp
 */
public class AjoutPatisserie {
    Form f;

    public Form getF() {
        return f;
    }

    public AjoutPatisserie(Patisserie p) {
        f=new Form("Ajouter",new BoxLayout(BoxLayout.Y_AXIS));
        TextField nom= new TextField(""+p.getNom());
        nom.setHint("Nom");
        TextField adresse= new TextField(""+p.getAdresse());
        adresse.setHint("Adresse");
        TextField telephone= new TextField(""+p.getContact());
        telephone.setHint("Telephone"); 
        telephone.setConstraint(TextField.PHONENUMBER);
        TextField mail=new TextField(""+p.getMail());
        mail.setHint("Mail");
        mail.setConstraint(TextField.EMAILADDR);
        CheckBox cb=new CheckBox("Permettez vous la reservation?");
        TextField places=new TextField(""+p.getPlace());
        
        if(p.isReservation())
            cb.setSelected(true);
        Button aj=new Button("Modifier");
        f.addAll(nom,adresse,telephone,mail,cb,places,aj);
        f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{DetailsPatisserie h=new DetailsPatisserie(p);
          h.getF().show();
          });
        cb.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            if(cb.isSelected()){
                places.setVisible(true);
                places.setHint("Nombre de places");
            }
            else{
                places.setVisible(false);
                places.setHint(""); 
            }
            }
        });      
        aj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                p.setAdresse(adresse.getText());
                p.setNom(nom.getText());
                p.setMail(mail.getText());
                try{
                    p.setContact(Integer.parseInt(telephone.getText()));
                    p.setPlace(Integer.parseInt(places.getText()));
                }
                catch(NumberFormatException e){
                    System.out.println(""+e.getMessage());
                }
                System.out.println(places.getText());
                p.setReservation(cb.isSelected());
        System.out.println(p);
                PatisserieService ps=new PatisserieService();
                ps.Modifier(p);
                DetailsPatisserieP dp=new DetailsPatisserieP(p);
                dp.getF().show();
                //Dialog.show("", "Modifié", "ok",null);
            }
        });
        
    }
    
    
    
}
