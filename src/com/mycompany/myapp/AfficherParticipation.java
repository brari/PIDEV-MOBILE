/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenements;
import com.mycompany.services.EvenementService;
import com.mycompany.services.Hamburger;
import com.mycompany.services.Hamburger1;
import com.mycompany.ui.Affichage;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public class AfficherParticipation {
    private Resources theme;
     Form formAffiche = new Form("♥ Detail Participation ♥",new BoxLayout(2));

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

    }
    public void start(Evenements event, boolean isconnceted) {
          //new Hamburger().hamb(formAffiche);      

                    
                  
                    
                    
        Container detailssup = BoxLayout.encloseY(
                        //new Label("event id :"+event.getIde()),
                        new Label("event nom : "+event.getNomE())
                );
        
      formAffiche.add(detailssup);
      //formAffiche.add(new Label("event desc :"+event.getDescriptionE()));
      //formAffiche.add(new Label("event adresse :"+event.getAdresseE()));
      //formAffiche.add(new Label("event type : "+event.getTypeE()));
     //formAffiche.add(new Label("event date : "+event.getDateE()));
       //formAffiche.add(new Label("user_id : "+event.getUser_id()));
      Integer nb=event.getCapacite()-event.getInteresses();
      formAffiche.add(new Label("Nombre d'interessés :"+event.getInteresses()));
      formAffiche.add(new Label("Places restantes :"+nb));
      
      formAffiche.getToolbar().addCommandToRightBar("Retour", null, (ev)->{
            new ListEvenements().start();
        
        });
      
      
      //formAffiche.add(new Label("event capacite : "+event.getCapacite()));

    
//      Button Participer = new Button("Participer");
//      Button Annuler = new Button("Annuler");
//      formAffiche.add(Participer);
//      formAffiche.add(Annuler);
//      
//      if(event.getInteresses()<event.getCapacite())
//      {
//         System.out.println("not yet");
//         Annuler.setVisible(false);
//         Participer.setVisible(true);
//         Participer.addActionListener(ee -> {
//                           
//                                     new EvenementService().participer(event.getIde()); 
//                                     Dialog.show("succés", "Votre participation a l'événement "+event.getNomE()+"a été bien enregistrée", "ok", null);
//                                     new ListEvenementsClient().start();
//          });
//          
//          
//      }
//      else
//      {
//            System.out.println("depassser");
//            Annuler.setVisible(true);
//            Participer.setVisible(false);
//          
//            Annuler.addActionListener(ee -> {
//                           
//                                      new EvenementService().annuler(event.getIde());  
//                                     Dialog.show("succés", "l'annulation a été bien effectuée", "ok", null);
//                                      new ListEvenementsClient().start();
//                                        });
//      }
//      try
//      {
//           Participer.addActionListener(e -> {
//                            
//                                new EvenementService().participer(event.getIde()); 
//                                System.out.println(event.getInteresses());
//                                System.out.println(event.getCapacite());
//                                 if(event.getInteresses()<event.getCapacite())
//                                 {
//                                     System.out.println("depassssssser");
//                                     Participer.setVisible(false);
////                                     Annuler.setVisible(true);
//             
//                                   Annuler.addActionListener(ee -> {
//                           
//                                      new EvenementService().annuler(event.getIde());  
//                                        });
//                                 }
//                                 else
//                                 {
//                                     System.out.println("not yet");
//                                   
//                                 }
//                                
//            });
//           
           
           
//          System.out.println(event.getInteresses());
//          if(event.getInteresses()>=event.getCapacite())
//          {
//               System.out.println("depassssssser");
//              Participer.setVisible(false);
////              Annuler.setVisible(false);
//             
//             Annuler.addActionListener(e -> {
//                           
//                              new EvenementService().annuler(event.getIde());  
//                                });
//          }
//          else
//          {
//              System.out.println("not yet");
////              Participer.setVisible(false);
////              Annuler.setVisible(true);
//              Annuler.addActionListener(e -> {
//                            
//                                new EvenementService().annuler(event.getIde());  
//                                 });
//               Participer.addActionListener(e -> {
//                            
//                                new EvenementService().participer(event.getIde());  
//                                 });
//          }
//           
//                            
//          
//          
//                                
//                            
//                           
////                            Dialog.show("succés", "vous avez supprimer l'événement: "+event.getNomE(), "ok", null);
////                            new ListEvenements().start();
         
//      }
//      catch(NullPointerException e)
//      {
//          System.out.println(e.getMessage());
//      }
//      
      
      
       Media video = null;
        try {
             video = MediaManager.createMedia("src\\music\\video.mp4", true);
        } catch (IOException ex) {
        }
                    
                    video.play();
            
    
formAffiche.show();
    }
    
}
