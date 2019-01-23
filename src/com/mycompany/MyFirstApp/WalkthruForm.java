/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MyFirstApp;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author brari
 */
public class WalkthruForm extends Form{
       public WalkthruForm(Resources res) {
        super(new LayeredLayout());
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        
        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        
        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        
        Image notes = res.getImage("SafeSitte.png");
        Image duke = res.getImage("LogoPIDEV.png");
       
        Label notesPlaceholder = new Label("","ProfilePic");
        Label notesLabel = new Label(notes, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Label bottomSpace = new Label();
        
        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                notesPlaceholder,
                new Label(duke, "ProfilePic"),
                new Label("Patisserie", "WalkthruWhite"),
                new SpanLabel("Notre¨annuaire vous permet de trouver des patisseries depuis n'importe quel endroit, "
                        + "vous aurez acces a l'application sur vos téléphones, sur internet et meme sur pc hors connexion, "
                        + "Quelle joie pour vous de rejoindre notre grande famille "
                        + "En espérznt que vous irez vraiment à la rencontre de vos saveurs.",  "WalkthruBody"),
                bottomSpace
        ));
        tab1.setUIID("WalkthruTab1");
        tab1.animateLayoutFadeAndWait(2650, 0);
        walkthruTabs.addTab("", tab1);
        
        Label bottomSpaceTab2 = new Label();
        
        Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
            
                
                new Label("Patisserie", "WalkthruWhite"),
                new SpanLabel("Patisserie, est une plateforme Mobile ,Web et desktop communautaire  "
                        + "de patisserie proposé par DEV_MASTERS. "
                        ,  "WalkthruBody"),
                bottomSpaceTab2
        ));
        
        tab2.setUIID("WalkthruTab2");
        tab2.animateLayoutFade(2588, 0);
        walkthruTabs.addTab("",tab2 );
        
        add(walkthruTabs);
        
        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i,ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Button skip = new Button("SKIP TUTORIAL");
        skip.setUIID("SkipButton");
        skip.addActionListener(e ->  new LoginInForm(res).show());
        
        Container southLayout = BoxLayout.encloseY(
                        radioContainer,
                        skip
                );
        add(BorderLayout.south(
                southLayout
        ));
        
        Component.setSameWidth(bottomSpace, bottomSpaceTab2, southLayout);
        Component.setSameHeight(bottomSpace, bottomSpaceTab2, southLayout);
        
        // visual effects in the first show
        addShowListener(e -> {
            notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));
        });
    }  
}
