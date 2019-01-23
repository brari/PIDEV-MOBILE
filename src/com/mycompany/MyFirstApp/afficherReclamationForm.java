/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyFirstApp;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.reclamation;
import com.mycompany.services.ReclamationService;
import java.util.ArrayList;

/**
 *
 * @author brari
 */
public class afficherReclamationForm extends ClientBaseForm{
    public afficherReclamationForm(Resources res){
    
     ReclamationService sr = new ReclamationService() ; 
   ArrayList<reclamation>  reclamations = sr.afficherRec();
   super.addSideMenu(res);
        Container list = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                Component[] elements = new Component[amount];
                int i = 0;
                for (reclamation r : reclamations) {
                    //Creating custom container
                    Container element = new Container(BoxLayout.y());
                    Container line1 = new Container(BoxLayout.x());
                    Label nameLabel = new Label(r.getReponse());
                    Label ageLabel = new Label(r.getObjet());
                    line1.add(nameLabel);
                    line1.add(ageLabel);
                    element.add(line1);
                    Button b = new Button("fffffff");
                    element.setLeadComponent(b);
                    elements[i] = element;

                    //Using MultiButton
                    /*MultiButton mb = new MultiButton(p.getNom());
                    mb.setTextLine2(Integer.toString(p.getAge()));
                    mb.setTextLine3("Never show Id");
                    elements[i]=mb;*/
                    i++;
                }
                return elements;
            }
        };
        list.setScrollableY(false);
     this.add(list);
}

  
 
 
    
}
