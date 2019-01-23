/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.mycompany.services.RecetteService;
import com.mycompany.entities.Recette;

/**
 *
 * @author sana
 */
public class HomeForm {
    Form f;
    TextField tnom;
    TextArea tdesc;
    Button btnajout,btnaff;
    
    public HomeForm() {
        f = new Form("Recette");
        tnom = new TextField();
        tdesc = new TextArea();
        btnajout = new Button("Ajouter Recette");
        btnaff=new Button("Affichage");
        f.add(tnom);
        f.add(tdesc);
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            RecetteService ser = new RecetteService();
            Recette t = new Recette(tnom.getText(), tdesc.getText());
            ser.ajoutRecette(t);
        });
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

    
    
    
    
    
    
    
    
    
}
