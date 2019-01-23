/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

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

/**
 *
 * @author hp
 */
public class RatingFormP {

    Form f;
    Slider rating;

    public Form getF() {
        return f;
    }

    public Slider getRating() {
        return rating;
    }

    public RatingFormP() {
        f = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
        rating = createStarRankSlider();
        f.add(FlowLayout.encloseCenter(rating));
        //int progress = rating.getProgress();
        Label lb = new Label("BB");
        f.add(lb);
        rating.addActionListener((evt) -> {
            lb.setText("Moyenne: " + rating.getProgress());
        });

    }

    public RatingFormP(Patisserie p) {
        rating = createStarRankSlider();
        float rt= Float.parseFloat(p.getRating().toString());
        rating.setProgress((int)rt);
        //f.add(FlowLayout.encloseCenter(rating));
        //int progress = rating.getProgress();
//        Label lb = new Label("BB");
//        f.add(lb);
//        rating.addActionListener((evt) -> {
//            lb.setText("Moyenne: " + rating.getProgress());
//        });

    }

    private void initStarRankStyle(Style s, Image star) {

        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    public Slider createStarRankSlider() {

        Slider starRank = new Slider();
        starRank.setEditable(true);

        starRank.setMinValue(0);
        starRank.setMaxValue(6);

        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
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
