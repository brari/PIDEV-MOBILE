/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Patisserie;
import com.mycompany.services.PatisserieService;
import com.mycompany.services.authuser;

/**
 *
 * @author hp
 */
public class RatingForm {
    Form f;

    public Form getF() {
        return f;
    }

    public RatingForm() {
        f = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
        Slider rating= createStarRankSlider();
        f.add(FlowLayout.encloseCenter(rating));
        //int progress = rating.getProgress();
        Label lb= new Label(""+rating.getProgress());
        f.add(lb);
        rating.addActionListener((evt) -> {
            lb.setText("Note: "+rating.getProgress());
        });
    }
    
    public RatingForm(Patisserie p) {
        f = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
        Slider rating= createStarRankSlider();
        f.add(FlowLayout.encloseCenter(rating));
        //int progress = rating.getProgress();
        Label lb= new Label("Note: "+rating.getProgress());
        f.add(lb);
        Button noter=new Button("Valider");
        f.add(noter);        
        rating.addActionListener((evt) -> {
            lb.setText("Note: "+rating.getProgress());
        });
       f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{
            DetailsPatisserie a=new DetailsPatisserie(p);
            a.getF().show();       
        });
       noter.addActionListener((evt) -> {
           if(Dialog.show("", "Doner la note "+rating.getProgress()+" ?", "Oui", "Non")){
               PatisserieService ps=new PatisserieService();
               ps.noterPatisserie(p, rating.getProgress(), authuser.user.getId());
               PatisserieService pser=new PatisserieService();
               Patisserie pat=pser.afficherdetail(p.getIdp());
               DetailsPatisserie a=new DetailsPatisserie(pat);
                a.getF().show();
           }
       });
        
    }
    private void initStarRankStyle(Style s, Image star) {
    
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }



private Slider createStarRankSlider() {

    Slider starRank = new Slider();
    starRank.setEditable(true);
    
    starRank.setMinValue(0);
    starRank.setMaxValue(6);

    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    
    s.setOpacity(100);
    s.setFgColor(0);

    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);

    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);

    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);

    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);

    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 6, fullStar.getHeight()));

    return starRank;

}
    
}
