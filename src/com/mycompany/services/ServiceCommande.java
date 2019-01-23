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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commande;
import com.mycompany.entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Insaf-Nefzi
 */
public class ServiceCommande {
     public ArrayList<Commande> getListProduit(String json) throws IOException {

        ArrayList<Commande> listProduits = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
          Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));
          List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");
          
          for (Map<String, Object> obj : list) {
                Commande p = new Commande();
                float id = Float.parseFloat(obj.get("idcp").toString());
                p.setId((int) id);

//                Map<String, Object> f = (Map<String, Object>) obj.get("idu");
//                float id8 = Float.parseFloat(f.get("idu").toString());
                p.setIdu((int) 2);
                
                Map<String, Object> f2 = (Map<String, Object>) obj.get("idpro");
                float id9 = Float.parseFloat(f2.get("idpro").toString());
                p.setIdpro((int) id9);
                
        

                
                p.setNompro(obj.get("nompro").toString());
               p.setPrixpro(obj.get("prixpro").toString());
              p.setCategoriepro(obj.get("categoriepro").toString());
              p.setDetailspro(obj.get("detailspro").toString());
              p.setNompat(obj.get("nompat").toString());
           
                System.out.println(p);
                listProduits.add(p);
             //   System.out.println(p);

            }
          
          
//          for (Map<String, Object> obj : list) {
//              Commande e = new Commande();
//              float id = Float.parseFloat(obj.get("idcp").toString());
//              System.out.println(id);
//              e.setId((int) id);
//              float idp = Float.parseFloat(obj.get("idpro").toString());
//              System.out.println(idp);
//              e.setIdpro((int) idp);
//              e.setNompro(obj.get("nompro").toString());
//               e.setNompro(obj.get("prixpro").toString());
//              e.setCategoriepro(obj.get("categoriepro").toString());
//              e.setDetailspro(obj.get("detailspro").toString());
//              e.setNompat(obj.get("nompat").toString());
//           
//                System.out.println(e);
//               
//                
//     
//                listProduits.add(e);
//          }
           

        } catch (IOException ex) {
              System.out.println(""+ex.getMessage());   
        }
        return listProduits;

    }
    ArrayList<Commande> listProduits = new ArrayList<>();
    
    public ArrayList<Commande> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/cmd/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCommande ser = new ServiceCommande();
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
    
    
    public void ajoutCommande(Commande c ,Produit p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/cmd/new/"+Produit.focusedId
                + "&nompro=" + c.getNompro() 
                + "&prixpro="  + c.getPrixpro()
                 + "&categoriepro=" + c.getCategoriepro() 
                + "&detailspro="  + c.getDetailspro()  
                + "&nompat="  + c.getNompat() 
                + "&image="  + c.getImage() 
                + "&idpro="  + Produit.focusedId;
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
        public void supprimerCommande(int id,int idpro){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/prod/cmd/delete/"+id+"/"+idpro);  
       
        con.setPost(true);
         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
                NetworkManager.getInstance().addToQueueAndWait(con);

    }

    
}
