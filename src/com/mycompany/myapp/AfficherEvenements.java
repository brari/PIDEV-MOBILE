/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.services.Hamburger;
import com.mycompany.entities.Evenements;
import com.mycompany.services.EvenementService;
import com.mycompany.ui.Affichage;
import com.mycompany.ui.AffichagePat;
//import com.restfb.DefaultFacebookClient;
//import com.restfb.FacebookClient;
//import com.restfb.Parameter;
//import com.restfb.types.FacebookType;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//

/**
 *
 * @author DELL
 */
public class AfficherEvenements {
     private Resources theme;
     Form formAffiche = new Form("♥ Detail Evénement ♥",new BoxLayout(2));

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

    }
    public void start(Evenements event, boolean isconnceted) {
//          new Hamburger().hamb(formAffiche);      

                    
           formAffiche.getToolbar().addCommandToRightBar("back",null,new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new ListEvenements().start();
                    }
                });           
                    
                    
        Container detailssup = BoxLayout.encloseY(
                        new Label("event id :"+event.getIde()),
                        new Label("event nom : "+event.getNomE())
                );
        
      formAffiche.add(detailssup);
      formAffiche.add(new Label("event desc :"+event.getDescriptionE()));
      formAffiche.add(new Label("event adresse :"+event.getAdresseE()));
      formAffiche.add(new Label("event type : "+event.getTypeE()));
//      formAffiche.add(new Label("event date : "+event.getDateE()));
       //formAffiche.add(new Label("user_id : "+event.getUser_id()));
//      formAffiche.add(new Label("event interesses :"+event.getInteresses()));
//      formAffiche.add(new Label("event capacite : "+event.getCapacite()));

    
      Button supprimer = new Button("Supprimer");
      Button modifier = new Button("Modifier");
      Button partager = new Button("partager");
      Button statistique = new Button("statistique");
       
      
      formAffiche.add(supprimer);
      formAffiche.add(modifier);
       formAffiche.add(statistique);
       
       formAffiche.getToolbar().addCommandToRightBar("Retour", null, (ev)->{
            AffichagePat a=new AffichagePat();
            a.getF().show();
        
        });
      
      try
      {
          supprimer.addPointerPressedListener(e -> {
                            
                            new EvenementService().supprimerEvenement(event.getIde());
                            Dialog.show("Confirmation", "voulez vous vraiment supprimer l'événement: "+event.getNomE(), "oui", "non");
                            new ListEvenements().start();
                            
          });
      }
      catch(NullPointerException e)
      {
          System.out.println(e.getMessage());
      }
      
      
          modifier.addActionListener(e -> {
                            
                            //new EvenementService().modifier(event);
                            
                            new ModifierEvenement().start(event);
                            
          });
     
//           b.addActionListener(e->{
//            new AjouterEvenement().start();
//        });
//  
// partager.addActionListener(new ActionListener() {
//                                      @Override
//                                      public void actionPerformed(ActionEvent evt) {
//                                              String accessToken = "EAASzdZBICtB0BAOKG5WMbQTNdgWZCTIszZB86CGr0NbbyJtR8MZCGJkdDpnRypnHCSuGTlYaobTyzzASjzJZCs3FZAoJZAOaV9HS4oQv2TU7jg4jenFJSYAOMoiTqjPXVTv1CnQG1ZAUIRWR3IWbgbjZAQIlu8xhRRHQ4sJN59fwjOhzlHoUycsmPgAyxf3MJ1rHtkHBRpOfcjQZDZD";
////        FacebookClient fb=new DefaultFacebookClient(accessToken);
//        FacebookClient fbClient= new DefaultFacebookClient(accessToken);
//         FacebookType response = fbClient.publish("me/feed", FacebookType.class,Parameter.with("message", "aa"));
//         System.out.println("fb.com/"+response.getId());
//         Dialog.show("Succes", "Votre post à été partagé sur facebook", "Fermer", null);
//                                      }
//                                  });
//             formAffiche.add(partager); 
             
      
      
      statistique.addActionListener(e -> 
                            
                            //new EvenementService().modifier(event);
                            
                            new Statistique(theme,event).show());
                            
          
     
    
formAffiche.show();
    }
    
}
