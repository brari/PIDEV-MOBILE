/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.ListEvenements;
import com.mycompany.myapp.ListEvenementsClient;
import com.mycompany.myapp.ListeParticipations;

/**
 *
 * @author DELL
 */
public class Hamburger1 {
     public void hamb(Form form) {
     form.getToolbar().addMaterialCommandToSideMenu("Evenements", 
            FontImage.MATERIAL_WEB, e -> new ListEvenementsClient().start());
     
     }
}
