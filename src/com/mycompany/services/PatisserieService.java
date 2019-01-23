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
//import com.mycompany.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class PatisserieService {
    ArrayList<Patisserie> patisseries = new ArrayList<>();
    
    //je ne fait pas l'ajout d'une patisserie en mobile (url)
    
    public void ajoutPatisserie(Patisserie pat,int id){
        int pl;
        if (pat.isReservation())
            pl=1;
        else
            pl=0;
        ConnectionRequest con= new ConnectionRequest();
        String url="http://localhostkk/PI/AnnuaireWeb/web/app_dev.php/newjson?nom="+pat.getNom()+
                "&adresse="+pat.getAdresse()+"&contact="+pat.getContact()+"&mail="
                +pat.getMail()+"&reservation="+pl+"&places="+pat.getPlace()+
                "&idprop="+id;
        con.setUrl(url);
        con.setPost(true);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("done Sabine keep calm");
    
    }
    
    public void Supprimer(int id){
        ConnectionRequest con= new ConnectionRequest();
        String url="http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/removepat/"+id;
        con.setUrl(url);
        con.setPost(true);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Suppression done Sabine keep calm");
    
    }
    
    public void noterPatisserie(Patisserie pat,int note,int idu){
        ConnectionRequest con= new ConnectionRequest();
        String url="http://localhost/AnnuaireWeb/web/app_dev.php/annuaire/ratejson?note="+note+"&idu="+idu+"&idp="+pat.getIdp();
        con.setUrl(url);
        con.setPost(true);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Rating OK Sabine keep calm");
    
    }
    
        public ArrayList<Patisserie> resPatisserie(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/AnnuaireWeb/web/app_dev.php/annuaire/reservejson");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PatisserieService ser = new PatisserieService();
                patisseries = ser.afficherPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return patisseries;
    }
    
    
     public void Modifier(Patisserie pat){
         int pl;
        if (pat.isReservation())
            pl=1;
        else
            pl=0;
        ConnectionRequest con= new ConnectionRequest();
        String url="http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/newjson?nom="+pat.getNom()+
                "&adresse="+pat.getAdresse()+"&contact="+pat.getContact()+"&mail="
                +pat.getMail()+"&reservation="+pl+"&places="+pat.getPlace()+
                "&id="+pat.getIdp();
        con.setUrl(url);
        con.setPost(true);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("Modification done Sabine keep calm");
    
    }
    
    
    //pourquoi???
    
    public ArrayList<Patisserie> afficherPatisserie(String json){
        ArrayList<Patisserie> patisseries= new ArrayList<>();
        
        try {
            JSONParser j= new JSONParser();
            Map<String, Object> pats = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) pats.get("root");
            
            for (Map<String, Object> obj : list) {
                Patisserie p= new Patisserie();
                float id = Float.parseFloat(obj.get("idp").toString());
                System.out.println(id);
                p.setIdp((int) id);
                p.setAdresse(obj.get("adresse").toString());
                p.setNom(obj.get("nom").toString());
                p.setMail(obj.get("mail").toString());
                try{
                p.setUrl(obj.get("url").toString());
                }catch(NullPointerException e){
                }
                p.setReservation(obj.get("reservation").equals("true"));
                
                float contact= Float.parseFloat(obj.get("contact").toString());
                p.setContact((int) contact);
                try{
                    
                double rating=Double.parseDouble(obj.get("rating").toString());
                p.setRating(rating);
                    System.out.println("Ratiiiiiiiiiiiiiing "+rating);
                //A cause de la jointure la valeur idprop est tout un user passé
                //en paramètre. C'est donc un format Json aussi et donc un ensemble K,V
                // d'où la récuperation dans une  kmap et ensuite la re récuperation de la 
                //valeur cherchée
                
                Map<String, Object> user=(Map<String, Object>)obj.get("idprop");
                p.setIdprop(user.get("nom").toString());
//                float idpr=Float.parseFloat(user.get("id").toString());
//                p.setIdprop((int) idpr);
                
                float place=Float.parseFloat(obj.get("place").toString());
                p.setPlace((int) place);
                
                }catch(NullPointerException e){
                }
                System.out.println(p);
                patisseries.add(p);
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        return patisseries;
    }   
    
    
    
    public ArrayList<Patisserie> afficherPat(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/alljson");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PatisserieService ser = new PatisserieService();
                patisseries = ser.afficherPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return patisseries;
    }
    public ArrayList<Patisserie> afficherMesPat(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/findpjson/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PatisserieService ser = new PatisserieService();
                patisseries = ser.afficherPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return patisseries;
    }
    
    public Patisserie detailPatisserie(String json){
        Patisserie p=new Patisserie();
        try {
            JSONParser j= new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
                float id = Float.parseFloat(obj.get("idp").toString());
                System.out.println(id);
                p.setIdp((int) id);
                p.setAdresse(obj.get("adresse").toString());
                p.setNom(obj.get("nom").toString());
                p.setMail(obj.get("mail").toString());
                try{
                p.setUrl(obj.get("url").toString());
                }catch(NullPointerException e){
                }
                p.setReservation(obj.get("reservation").equals("true"));
                
                float contact= Float.parseFloat(obj.get("contact").toString());
                p.setContact((int) contact);
                try{
                float place=Float.parseFloat(obj.get("place").toString());
                p.setPlace((int) place);
                
                double rating=Double.parseDouble(obj.get("rating").toString());
                p.setRating(rating);
                
                //A cause de la jointure la valeur idprop est tout un user passé
                //en paramètre. C'est donc un format Json aussi et donc un ensemble K,V
                // d'où la récuperation dans une  kmap et ensuite la re récuperation de la 
                //valeur cherchée
                
                Map<String, Object> user=(Map<String, Object>)obj.get("idprop");
                p.setIdprop(user.get("nom").toString());
//                float idpr=Float.parseFloat(user.get("id").toString());
//                p.setIdprop((int) idpr);
                
                }catch(NullPointerException e){
                }
                System.out.println(p);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        catch(NullPointerException e){
            System.out.println(""+e.getMessage());
        }
        
        return p;
    }  
    
    Patisserie pat=new Patisserie();
    public Patisserie afficherdetail(int id){
        ConnectionRequest con= new ConnectionRequest();
        con.setUrl("http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/findjson/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PatisserieService ser=new PatisserieService();
                pat=ser.detailPatisserie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return pat;
        
    }
    
}
