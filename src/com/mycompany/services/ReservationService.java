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
import com.mycompany.entities.Patisserie;
import com.mycompany.entities.Reservation;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ReservationService {
    
    public void ajoutReservation(Reservation r){
        ConnectionRequest con=new ConnectionRequest();
        String url="http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/newResjson?idpat="+r.getIdp()+
                "&iduser="+r.getIdu()+"&places="+r.getPlaces()+"&date="+r.getDate()+"&heure="+r.getHeure();
        System.out.println(r);
        con.setUrl(url);
        con.setPost(true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("+++++ queue end");
    }
    
     public ArrayList<Reservation> afficherReservations(String json){
        ArrayList<Reservation> reservations= new ArrayList<>();
        
        try {
            JSONParser j= new JSONParser();
            Map<String, Object> pats = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) pats.get("root");
            
            for (Map<String, Object> obj : list) {
                Reservation p= new Reservation();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                p.setId((int) id);
                Date d=(Date)(obj.get("date"));
                System.out.println(d);
                p.setDate(obj.get("date").toString());
                p.setHeure(obj.get("heure").toString());
                
                //A cause de la jointure la valeur idprop est tout un user passé
                //en paramètre. C'est donc un format Json aussi et donc un ensemble K,V
                // d'où la récuperation dans une  kmap et ensuite la re récuperation de la 
                //valeur cherchée
                
                Map<String, Object> patisserie=(Map<String, Object>)obj.get("idpat");
   
                float idpr=Float.parseFloat(patisserie.get("idp").toString());
                p.setIdp((int) idpr);            
                System.out.println(p);
                reservations.add(p);
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }    
        
        return reservations;
    }   
    
    ArrayList<Reservation> reservations = new ArrayList<>();
    
    public ArrayList<Reservation> afficherRes(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/findURjson/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReservationService ser = new ReservationService();
                reservations = ser.afficherReservations(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return reservations;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
