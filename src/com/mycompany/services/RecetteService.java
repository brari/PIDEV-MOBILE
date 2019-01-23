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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;

import com.mycompany.entities.Recette;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author esprit
 */
public class RecetteService {
    
    
      public void ajoutRecette(Recette a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/recette/MobileAjoutannonce";
        con.setUrl(Url);
        con.setPost(true);
        con.addArgument("nom", a.getNom());
        con.addArgument("description", a.getDescription());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        con.addArgument("date", dateFormat.format(date));
        
        con.addArgument("etat", a.getEtat());
        con.addArgument("action", a.getAction());
        con.addArgument("photo", a.getPhoto());
        con.addArgument("iduser", Integer.toString(a.getIdUser()));
        con.addArgument("isdisabled", "false");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
      
      
    public ArrayList<Recette> getListRecette(String json) {

        ArrayList<Recette> listRecette = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> recette = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) recette.get("root");

            for (Map<String, Object> obj : list) {
              Recette e = new Recette();
               
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
               e.setPhoto(obj.get("photo").toString());
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
                //e.setDate(obj.get("Date").toString());
               
                e.setEtat(obj.get("etat").toString());
                e.setAction(obj.get("action").toString());
                System.out.println(e);
                listRecette.add(e);
            }

        } catch (IOException ex) {
        }
        return listRecette;

    }
    
     public ArrayList<Recette> getListAnnonce(String json) throws ParseException {

     ArrayList<Recette> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                Recette a = new Recette();

         a.setNom(obj.get("nom").toString());
             
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
//                SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-DDHH:MM:SS.mmm-HH:MM");
//                Date date = date1.parse(obj.get("date").toString());
//                a.setDate(date);
                  //  System.out.println(a);
                listTasks.add(a);
            }

        } catch (IOException ex) {
        }
        return listTasks;

    }
    
    
    ArrayList<Recette> listRecette = new ArrayList<>();
    
      public ArrayList<Recette> getList2(){       
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/recette/allrecette");
          System.out.println("ETAPEA"); 
          System.out.println(con.getUrl());
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                System.out.println("ETAPEXXX"); 
//                RecetteService ser = new RecetteService();
//                System.out.println("ETAPEb"); 
//                listRecette = ser.getListRecette(new String(con.getResponseData()));
//                System.out.println("ETAPEC"); 
//            }
//        });
        con.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 RecetteService ser= new RecetteService();
                 listRecette=ser.getListRecette(new String(con.getResponseData()));
             }
         }
        );
        System.out.println("ETAPEds"); 
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("ETAPEee"); 
        return listRecette;
    }
    
    
    
    
    
    
    
    
}
