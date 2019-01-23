/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Insaf-Nefzi
 */
public class ServiceProduit {
     public void ajoutProduit(Produit pr) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/prods/new?" 
                + "&nompro=" + pr.getNompro() 
                + "&prixpro="  + pr.getPrixpro()
                 + "&categoriepro=" + pr.getCategoriepro() 
                + "&detailspro="  + pr.getDetailspro()  
                + "&nompat="  + pr.getNompat() 
                +"&image="+pr.getImage()
                + "&stock="  + pr.getStock();
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
      public ArrayList<Produit> getListProduit(String json) throws IOException {

        ArrayList<Produit> listProduits = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
          Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));
          List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");
          for (Map<String, Object> obj : list) {
              Produit e = new Produit();
              float id = Float.parseFloat(obj.get("idpro").toString());
              System.out.println(id);
              e.setId((int) id);
              e.setNompro(obj.get("nompro").toString());
              float prix = Float.parseFloat(obj.get("prixpro").toString());
             e.setPrixpro((float) prix);
              e.setCategoriepro(obj.get("categoriepro").toString());
              e.setDetailspro(obj.get("detailspro").toString());
              e.setNompat(obj.get("nompat").toString());
              e.setImage(obj.get("image").toString());
               float stockk = Float.parseFloat(obj.get("stock").toString());
             e.setStock((int) stockk);
           
                System.out.println(e);
               
                
     
                listProduits.add(e);
          }
           

        } catch (IOException ex) {
              System.out.println(""+ex.getMessage());   
        }
        return listProduits;

    }
    ArrayList<Produit> listProduits = new ArrayList<>();
    
    public ArrayList<Produit> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/prods/affiche");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                try {
          listProduits = ser.getListProduit(new String(con.getResponseData()));
                } catch (IOException ex) {
                 System.out.println(""+ex.getMessage());   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
    
        public void supprimerProduit(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/prods/delete/"+id);  
       
        con.setPost(true);
         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
                NetworkManager.getInstance().addToQueueAndWait(con);

    }


        public Produit getProduitById(int id){
            ServiceProduit sp= new ServiceProduit();
            ArrayList<Produit> lp=sp.getList2();
            for(Produit p:lp)
            {
                if(p.getId()==id)
                    return p;
            }
            return null;
            
        }
        
        public Produit getProduitbycat(String cat)
        {
             ServiceProduit sp= new ServiceProduit();
            ArrayList<Produit> lp=sp.getList2();
            for(Produit p:lp)
            {
                if(p.getCategoriepro()==cat)
                    return p;
            }
            return null;
        }
        public void modifierProduit(Produit s) { 
            ConnectionRequest cnx=new ConnectionRequest() ;
    String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/prods/update/"+ s.getId()+"/"+s.getNompro()+"/"+s.getPrixpro()+"/"+s.getCategoriepro()+"/"+s.getDetailspro()+"/"+s.getNompat()+"/"+s.getStock() ;
        cnx.setUrl(Url);
       cnx.setFailSilently(true);
        NetworkManager.getInstance().addToQueue(cnx);
    
}
        String s;
             public String etat(String b){      
               //  int i=0;
               
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/prods/finde/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                 s =new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         return s;
        
    }
    
}
