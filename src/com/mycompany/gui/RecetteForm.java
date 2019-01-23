/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author esprit
 */
public class RecetteForm {
    
     Form f;
 Button btn_commenter;

Label Desc;
Label nom;
Label date;
 
    TextArea comment;
    public RecetteForm(){
    f=new Form("Recette" ,BoxLayout.y());
btn_commenter=new Button("commenter");

nom=new Label("lieu");

date=new Label("Date");
Desc=new Label("Description");
comment=new TextArea();
f.add(nom);
f.add(date);
 
f.add(Desc);
f.add(comment);
    f.add(btn_commenter);
    
    }
      public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }  
    
    
    
    
    
    
}
