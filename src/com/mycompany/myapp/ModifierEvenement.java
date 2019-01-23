/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.io.MultipartRequest;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenements;
import com.mycompany.services.EvenementService;
import com.mycompany.services.Hamburger;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public class ModifierEvenement {
     private Resources theme;
     String imageName;
     Form formAffiche = new Form("Detail Evénement",new BoxLayout(2));
       

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

    }
     public void start(Evenements ev) {
        //new Hamburger().hamb(formAffiche);
        TextField eventnom = new TextField(ev.getNomE());
        TextField eventdescription = new TextField(ev.getDescriptionE());
        TextField eventadresse = new TextField(ev.getAdresseE());
       
        ComboBox<String> eventtype = new ComboBox<>();
           eventtype.addItem("Coatching");
           eventtype.addItem("Reception");
           eventtype.addItem("Degustation");
           
//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);
        
        Button  btnAjouterPhoto = new Button("insérer une image");
        TextField eventinteresses = new TextField(String.valueOf(ev.getInteresses()));
        TextField eventcapacite = new TextField(String.valueOf(ev.getCapacite()));
        
         formAffiche.getToolbar().addCommandToRightBar("back",null,new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new ListEvenements().start();
                    }
                });  
        
         btnAjouterPhoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    MultipartRequest cr = new MultipartRequest();
                    String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                    
                    String mime = "image/jpg";
                    if (filePath != null) {
                        cr.addData("file", filePath, mime);
                        System.out.println(filePath);
                        cr.setFilename("file", filePath);//any unique name you want
                        imageName = filePath;
                        int index = imageName.lastIndexOf("/");
                        imageName = imageName.substring(index + 1);
                        System.out.println(imageName);
                        //InfiniteProgress prog = new InfiniteProgress();
                        //Dialog dlg = prog.showInifiniteBlocking();
                       
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            

        });
          
         
//     Container detailssup = BoxLayout.encloseY(
//                        annoncename
//                );
        
      formAffiche.add(eventnom);
      formAffiche.add(eventdescription);
      formAffiche.add(eventadresse);
      formAffiche.add(eventtype);
//      formAffiche.add(datePicker);
      formAffiche.add(btnAjouterPhoto);
      formAffiche.add(eventinteresses);
      formAffiche.add(eventcapacite);
      
      Button modifer = new Button("Confirmer la Modification");
      
      modifer.addActionListener(e -> {
                            ev.setNomE(eventnom.getText());
                            ev.setDescriptionE(eventdescription.getText());
                            ev.setAdresseE(eventadresse.getText());
                            ev.setTypeE(eventtype.getSelectedItem());
                            //ev.setDateE(dateString);
                            ev.setImageE(eventadresse.getText());
                            ev.setInteresses(Integer.valueOf(eventinteresses.getText()));
                            ev.setCapacite(Integer.valueOf(eventcapacite.getText()));
//                          annonce.setName(annoncename.getText());
                             EvenementService ser = new EvenementService();
            
                            ser.modifier(ev);
                            Dialog.show("Confirmation", "voulez vous vraiment modifier l'événement: "+ev.getNomE(), "oui", "non");
                           new AfficherEvenements().start(ev, true);
          });
      formAffiche.add(modifer);
      

formAffiche.show();
    }
    
}
