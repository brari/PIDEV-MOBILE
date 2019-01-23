/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;












public class Accueil extends Form {
    private Form current;
    private Resources theme;
    private Container mainContainer;
    private Form home;
    private Form test;
    EncodedImage im;
    Image img;
    ImageViewer imgv;
    String url="http://localhost/kk/PI/AnnuaireWeb/web/Evenement/image/im1.jpg";
    
    
  private final Label title ;
    private final  Container  ctnAccueil ;
    
    public Accueil ()
            
    {  this.setLayout(new BorderLayout());
        title = new Label("Bienvenue");
     title.setAlignment(CENTER);
     ctnAccueil = new Container() ;
     ctnAccueil.add(title);
      try {
            im=EncodedImage.create("/patiss.jpg");
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        img=URLImage.createToStorage(im, "img3", url, URLImage.RESIZE_SCALE);
        imgv= new ImageViewer(img);//img de type image
      
        ctnAccueil.add(imgv);
      this.add(BorderLayout.NORTH, ctnAccueil);
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 2, p.getHeight() * 2), false);
    
    
    
    }
    
    
    
}