/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.services.Hamburger;
import com.mycompany.services.EvenementService;
import com.mycompany.entities.Evenements;
import java.io.IOException;
import java.util.Date;
////import java.util.logging.Level;
//import java.util.logging.Logger;



/**
 *
 * @author DELL
 */
public class AjouterEvenement {
   static int user=1;
   String imageName;

    private Resources theme;
     Form current;
     public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

    }
     
       public void start() 
       {
        if(current != null){
            current.show();
            return;
        }
       
       Form hi = new Form("♥ Ajouter un Evénement ♥", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        new Hamburger().hamb(hi);
        Label im=new Label();
        Button ajoutevenement = new Button("Poster votre événement!");
        Button  btnAjouterPhoto = new Button("insérer une image");
        TextField eventnom = new TextField("", "Nom");
        TextField eventdescription = new TextField("", "Description");
        TextField eventadresse = new TextField("", "Adresse");
       
        ComboBox<String> eventtype = new ComboBox<>();
           eventtype.addItem("Coatching");
           eventtype.addItem("Reception");
           eventtype.addItem("Degustation");
           
//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);
        

        //TextField eventimage = new TextField("", "Image");
        TextField eventinteresses = new TextField("", "Interesses");
        TextField eventcapacite = new TextField("", "Capacite");
        
          
       
        hi.addComponent(BorderLayout.CENTER, 
                LayeredLayout.encloseIn(
                        BoxLayout.encloseY(
                             eventnom,eventdescription,eventadresse,eventtype,
                                btnAjouterPhoto,eventinteresses,eventcapacite,ajoutevenement
                                
                              
                                
                        )
                        )
                    
        );
        
        hi.getToolbar().addCommandToRightBar("Retour",null,new ActionListener() {
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
            
       ajoutevenement.addActionListener((e) -> {
          
                EvenementService ser = new EvenementService();
                Evenements ev=new Evenements();
//                Date actuelle = new Date();
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String dat = dateFormat.format(actuelle);
//                
//                String dateStringe = null;
//                DateFormat sdfrr = new SimpleDateFormat("yyyy-MM-dd");
//                dateStringe = sdfrr.format(datePicker.getDate());
//                
//                
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                
                
//                Date date1 = null;
//            try {
//                date1 = format.parse(dateStringe);
//            } catch (ParseException ex) {
//                Logger.getLogger(AjouterEvenement.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//                Date date2 = null;
//            try {
//                date2 = format.parse(dat);
//            } catch (ParseException ex) {
//                Logger.getLogger(AjouterEvenement.class.getName()).log(Level.SEVERE, null, ex);
//            }
                
                
//            Date d=datePicker.getDate();
if(eventnom.getText().length()<4 || eventadresse.getText().length()<4 || eventdescription.getText().length()<4)
{
    Dialog.show("Error", "Vérifiez les données saisies", "ok", null);
}
//else if(date1.compareTo(date2) <= 0)
//{
//    Dialog.show("Error", "pppppppp", "ok", null);
//}
else
{
//    String dateString = null;
//    SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
//    dateString = sdfr.format(datePicker.getDate());
//    //
    ev.setUser_id(user);
    ev.setNomE(eventnom.getText());
    ev.setDescriptionE(eventdescription.getText());
    ev.setAdresseE(eventadresse.getText());
    ev.setTypeE(eventtype.getSelectedItem());
//    ev.setDateE(dateString);
    ev.setImageE(imageName);
    
    
    ev.setInteresses(Integer.valueOf(eventinteresses.getText()));
    ev.setCapacite(Integer.valueOf(eventcapacite.getText()));
    ser.ajoutEvenement(ev);
    Dialog.show("succés", "vous avez posté l'événement: "+ev.getNomE(), "ok", null);
}
           
            
        });
       
         
            
           
//      Evenements t = new Evenements(user,eventnom.getText(),eventdescription.getText(),eventadresse.getText(),eventtype.getSelectedItem(),datePicker.getDate().toString(),eventimage.getText(),Integer.valueOf(eventinteresses.getText()),Integer.valueOf(eventcapacite.getText()));
      
           
            //dateeee
           
       hi.show();
       }
       
       
     
    
    
}
