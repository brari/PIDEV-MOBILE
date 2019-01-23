/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;


import javafx.scene.image.ImageView;

/**
 *
 * @author esprit
 */
public class AccueilForm {

    Form f;
    Font fnt = Font.createTrueTypeFont("fontello", "fontello.ttf");
    int size = Display.getInstance().convertToPixels(4);
    FontImage e = FontImage.createFixed("\ue81a", fnt, 0x000000, size, size);
    FontImage a = FontImage.createFixed("\uf1f7", fnt, 0x000000, size, size);
    FontImage d = FontImage.createFixed("\uf21e", fnt, 0x000000, size, size);
    FontImage p = FontImage.createFixed("\uf235", fnt, 0x000000, size, size);
    FontImage en = FontImage.createFixed("\uf0d6", fnt, 0x000000, size, size);
    FontImage b = FontImage.createFixed("\uef235", fnt, 0x000000, size, size);

    public AccueilForm() {

        f = new Form("Coin Patissier");
        Label l=new Label("Choisissez votre meilleur patisserie");
        f.add(l);
        f.getToolbar().addCommandToLeftSideMenu("Recette", e, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RecettesForm A = new RecettesForm();
                A.getF().show();
            }
        });
       
           
                f.getToolbar().addCommandToLeftSideMenu("Mes Recettes", a, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    MesRForm E = new MesRForm();
                    E.getF().show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
      
      
        
        

    }
    public void ToolBarAcueil(Form f){
      
       
        
        f.getToolbar().addCommandToLeftSideMenu("Recettes", a, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RecettesForm E = new RecettesForm();
                E.getF().show();
            }
        });
            
             f.getToolbar().addCommandToLeftSideMenu("Mes Recettes", a, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MesRecetteForm E = new MesRecetteForm();
                E.getF().show();
            }
        });
     
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
