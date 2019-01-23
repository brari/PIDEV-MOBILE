/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.ListEvenements;
import com.mycompany.myapp.ListeParticipations;
import com.mycompany.myapp.Video;

/**
 *
 * @author DELL
 */
public class Hamburger {
    public void hamb(Form form) {
     form.getToolbar().addMaterialCommandToSideMenu("Evenements", 
            FontImage.MATERIAL_WEB, e -> new ListEvenements().start());
     
     form.getToolbar().addMaterialCommandToSideMenu("Participants", 
            FontImage.MATERIAL_WEB, e -> new ListeParticipations().start());
         form.getToolbar().addMaterialCommandToSideMenu("Anciens Evénements", 
            FontImage.MATERIAL_WEB, e -> new Video().start());
     }
    
}
