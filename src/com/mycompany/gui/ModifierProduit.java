/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author Insaf-Nefzi
 */
public class ModifierProduit {
     Form f;
     
    TextField nompro;
    TextField prixpro;
     TextField categoriepro;
    TextField detailspro;
     TextField nompat;
    TextField stock;
    Button btnajout,btnaff,btnafff;
    
    public ModifierProduit() {
  
    
        f=new Form(new TableLayout(4, 2));
        Label l=new Label("nom : ");
        TextField t1 = new TextField();
        f.add(l);
        f.add(t1);
        
       l=new Label("prix : ");
        TextField t2 = new TextField();
        f.add(l);
        f.add(t2);
        
       l=new Label("Categorie : ");
        TextField t3 = new TextField();
        f.add(l);
        f.add(t3);
        
        
         l=new Label("details : ");
        TextField t4 = new TextField();
        f.add(l);
        f.add(t4);
            l=new Label("nom pat : ");
        TextField t5 = new TextField();
        f.add(l);
        f.add(t5);
            l=new Label("stock: ");
        TextField t6= new TextField();
        f.add(l);
        f.add(t6);
      
      
        ServiceProduit spp=new ServiceProduit();
        Produit s1=spp.getProduitById(Produit.focusedId);
        t1.setText(s1.getNompro());
        t2.setText(String.valueOf(s1.getPrixpro())) ;
        t3.setText(s1.getCategoriepro());
        t4.setText(s1.getDetailspro());
        t5.setText(s1.getNompat());
        t6.setText(String.valueOf(s1.getStock()));
        Label controle = new Label();
        f.add(controle);
        Container c = new Container(BoxLayout.x());
        Button b= new Button("Valider");
        
         b.addPointerPressedListener(lh->{
        
            
                Produit s=new Produit(Produit.focusedId, t1.getText(),Float.parseFloat(t2.getText()),t3.getText(), t4.getText(),t5.getText(),Integer.parseInt(t6.getText()));
                ServiceProduit sp=new ServiceProduit();
                sp.modifierProduit(s);
                  AffichageProduit a=new AffichageProduit();
        a.getF().show();
                });
       
   c.add(b);

        
    f.add(c);
        
         Toolbar tb2=f.getToolbar();
         tb2.addMaterialCommandToLeftSideMenu("About", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           // h3.show();
        }
        });
           tb2.addMaterialCommandToLeftSideMenu("Ajouter Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           AjouterProduit h = new AjouterProduit();
   h.getF().show();
        }
        });
             tb2.addMaterialCommandToLeftSideMenu("Afficher Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            AffichageProduit a=new AffichageProduit();
        a.getF().show();
        }
        });
               tb2.addMaterialCommandToLeftSideMenu("Afficher Commande", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           AfficherCommande a=new AfficherCommande();
        a.getF().show();
        }
        });
        
                       tb2.addMaterialCommandToLeftSideMenu("Afficher Favoris", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
              Fav a=new Fav();
        a.getH1().show();
        }
        });
                          tb2.addMaterialCommandToLeftSideMenu("Afficher Statistiques", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
             Stat a=new Stat();
        a.getF().show();
        }
                          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  

   
    
}
