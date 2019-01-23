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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Recette;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author esprit
 */
public class MesRecettesServices {
    
    public ArrayList<Recette> getListRecette(String json) {

        ArrayList<Recette> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                Recette a = new Recette();

                 a.setNom(obj.get("Nom").toString());
               
               a.setDescription(obj.get("description").toString());
               a.setPhoto(obj.get("photo").toString());
               a.setEtat(obj.get("etat").toString());
               
                float id = Float.parseFloat(obj.get("id").toString());
                 a.setId((int) id);
                  Map<String, Object> f = (Map<String, Object>) obj.get("iduser");
                float id1 = Float.parseFloat(f.get("id").toString());
                a.setIdUser((int) id1);
                  a.setAction(obj.get("action").toString());
                 String s=obj.get("isdisabled").toString();
                // System.out.println(s);
//                  float isdisabled = Float.parseFloat(( obj.get("isdisabled").toString()));
                  if(s.equalsIgnoreCase("false"))
                 a.setIsdisabled(false);
                  else a.setIsdisabled(true);

                //  System.out.println(a);
                listTasks.add(a);
            }
        } catch (IOException ex) {
        }
        return listTasks;

    }
    
    
    ArrayList<Recette> listRecette = new ArrayList<>();
    
      public ArrayList<Recette> getList2(int b){       
        ConnectionRequest con = new ConnectionRequest();
        con.addArgument("iduser", Integer.toString(b));
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/recette/MobileAffichageRecette");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RecetteService ser = new   RecetteService();
                listRecette = ser.getListRecette(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return   listRecette;
    }
       public void SupprimerRecette(String c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/recette/SupprimerRecetteMobile/"+c;

        
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
          public void ChangeStatusRecette(String c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/integration_finaaaaal_ttequipe/kk/PI/AnnuaireWeb/web/app_dev.php/api/StatusMobile/"+c;

        
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
