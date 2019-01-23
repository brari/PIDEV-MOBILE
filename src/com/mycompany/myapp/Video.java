/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenements;
import com.mycompany.services.Hamburger;

/**
 *
 * @author DELL
 */
public class Video {
    private Resources theme;
    
     Form hi = new Form("♥ Anciens Evénements ♥",new BoxLayout(2));

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

    }
    public void start() {
          new Hamburger().hamb(hi);      
 
        
        
       
                    
Form hi = new Form("♥ Anciens Evénements ♥", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_SCALE));
if (BrowserComponent.isNativeBrowserSupported()) {
    BrowserComponent browser = new BrowserComponent();
    browser.setURL("https://www.youtube.com/watch?v=CBwMTqJ2nEY");
    hi.add(BorderLayout.CENTER, browser);
} else {
    hi.add(BorderLayout.NORTH, "Your device is not supported");
}



hi.show();  
                    
                    
    }       

    
    
}
