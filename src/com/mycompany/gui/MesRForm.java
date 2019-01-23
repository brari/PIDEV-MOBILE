/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.Recette;
//import com.mycompany.Entite.user;
import com.mycompany.services.MesRecettesServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esprit
 */
public class MesRForm {

    Form f = Display.getInstance().getCurrent();
    Container c;
    Button Modifier, Bloquer, Supprimer;
    SpanLabel l;
    Label Desc;
    Label lieu;
    Label date;
    Label titre;
    Image imgs;
    ImageViewer imgv;
    EncodedImage enc;
    int i=0;
 
    Database db;
    ArrayList<String> list1;

    public MesRForm() throws IOException {
       
//         Login lo=new Login();
//         user user=lo.getUserConnecte();
        Font fnt = Font.createTrueTypeFont("fontello", "fontello.ttf");
        int size = Display.getInstance().convertToPixels(4);
        FontImage mod = FontImage.createFixed("\ue834", fnt,0x000000, size, size);
        FontImage block = FontImage.createFixed("\ue84b", fnt,0x000000, size, size);
        FontImage sup = FontImage.createFixed("\uf1f8", fnt,0x000000, size, size);
        FontImage plus = FontImage.createFixed("\ue824", fnt, 0x000000, size, size);
        FontImage alert = FontImage.createFixed("\ue894", fnt, 0x000000, size, size);
        FontImage check = FontImage.createFixed("\ue81e", fnt, 0x000000, size, size);
      
    
        f = new Form("Mes Annonces", BoxLayout.y());
         AccueilForm ac=new AccueilForm();
         ac.ToolBarAcueil(f);
        db = Database.openOrCreate("Alert.db");
        db.execute("CREATE TABLE if not exists Recette (nom Text,etat Text);");

        MesRecettesServices MesAnnonces = new MesRecettesServices();
        try {
            Cursor cr;
            String nom = null;
            cr = db.executeQuery("SELECT nom FROM Recette;");
            list1 = new ArrayList<>();
            while (cr.next()) {
                Row r = cr.getRow();
                nom = r.getString(0);
                list1.add(nom);

            }

        } catch (IOException ex) {
            System.out.println(ex.getCause());

        }
        for (int i = 0; i < MesAnnonces.getList2(2).size(); i++) {

            Recette a = MesAnnonces.getList2(2).get(i);
            if ((a.getEtat().equalsIgnoreCase("Accepter")) && (!(list1.contains(a.getNom())))) {
               
                try {
                    db.execute("INSERT INTO Annonce (nom,etat) values('" + a.getNom() + "','non lu');");
                } catch (Exception e) {
                    System.out.println(e.getCause());
                }

            }

//            lieu = new Label("lieu");
            titre = new Label("titre");
            date = new Label("Date");
            Desc = new Label("Description");
//            lieu.setText(a.getLieu());
            titre.setText(a.getNom());
            Desc.setText(a.getDescription());
            c = new Container(BoxLayout.x());

            Modifier = new Button(mod);
            Modifier.setText("Modifier");
            Modifier.addActionListener(new ActionListener() {
                   @Override
                public void actionPerformed(ActionEvent evt) {
//                    ModifierAnnonceForme mo=new  ModifierAnnonceForme(a);
//                    mo.getF().show();
                }
            }            
);
            l = new SpanLabel();

            Bloquer = new Button(block);
            System.out.println(a.isIsdisabled());
            if (a.isIsdisabled() == true) {
                Bloquer.setText("Debloquer");
            } else if (a.isIsdisabled() == false) {
                Bloquer.setText("Bloquer");
            }
            Bloquer.addActionListener((e) -> {
                MesAnnonces.ChangeStatusRecette(Integer.toString(a.getId()));
                try {
                    reload().show();
                } catch (IOException ex) {

                }
            });
            Supprimer = new Button(sup);
            Supprimer.setText("Supprimer");
            String url = "http://localhost/integration_finaaaaal_ttequipe/kk/PI/AnnuaireWeb/web/ressources/" + a.getPhoto();

            if (url != null) {
                try {
                    enc = EncodedImage.create("/load.png");
                } catch (IOException ex) {
                }

                imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer(imgs);
                f.add(imgv);
            }
            c.add(Modifier);

            c.add(Bloquer);
            c.add(Supprimer);
            f.add(titre);
//            f.add(lieu);
            f.add(Desc);
            f.add(l);
            f.add(c);

            Supprimer.addActionListener((e) -> {
                MesAnnonces.SupprimerRecette(Integer.toString(a.getId()));
                c.getComponentForm().revalidate();

            });
            f.getComponentForm().revalidate();

        }
        try {
            Cursor cr;
            String nom = null;
            cr = db.executeQuery("SELECT nom FROM Recette where etat like 'non lu';");
            ArrayList<String> list = new ArrayList<>();
            while (cr.next()) {
                Row r = cr.getRow();
                nom = r.getString(0);
                list.add(nom);
                i++;

            }
            
            f.getToolbar().addCommandToRightBar(Integer.toString(i), alert,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                }
            });
          
           
          
            if (nom != null) {
                for (String s : list) {
                    f.getToolbar().addCommandToOverflowMenu("Votre Recette" + " " + s + " a étè modifier ",alert, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            try {

                                db.execute("UPDATE  Recette SET etat='lu' WHERE nom LIKE'" + s + "';");
                                 reload().show();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }

                    }); 
                }
                f.getToolbar().addCommandToOverflowMenu("Tous masquer comme lu",check,new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            try {

                                db.execute("UPDATE  Recette SET etat='lu';");
                                 reload().show();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }

                    });
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Form reload() throws IOException  {
        MesRForm aff = new MesRForm();
        return aff.getF();
    }
}
