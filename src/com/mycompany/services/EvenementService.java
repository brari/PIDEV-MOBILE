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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class EvenementService {
    static int user=1;
   
    public ArrayList<Evenements> getListTask(String json){
        ArrayList<Evenements> evenements= new ArrayList<>();
        
        try {
            JSONParser j= new JSONParser();
            Map<String, Object> ev = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ev.get("root");
            try
            {
                for (Map<String, Object> obj : list) {
                Evenements p= new Evenements();
                float ide = Float.parseFloat(obj.get("idE").toString());
                System.out.println(ide);
                p.setIde((int) ide);
                
               
                p.setNomE(obj.get("nomE").toString());
                p.setDescriptionE(obj.get("descriptionE").toString());
                p.setAdresseE(obj.get("adresseE").toString());
                p.setTypeE(obj.get("typeE").toString());
                
                
                
                //debut affichage dateee a revenir apres
//                System.out.println(((LinkedHashMap)obj.get("dateE")).get("timestamp").toString().substring(0,9 ));  
//                String f1 = ((LinkedHashMap)obj.get("dateE")).get("timestamp").toString().substring(0,1 );
//                String f2 = ((LinkedHashMap)obj.get("dateE")).get("timestamp").toString().substring(2,9 );
//                String f3 = f1+f2+"00";
//                System.out.println(f3);
//                System.out.println(f3);
//                 Long s1 = Long.parseLong(f3);
//                 Long s2 = Long.parseLong("86400");
//                 Long s3 = s1+s2;
//                 Long s4 = s3*1000;
//                 System.out.println("****************"+s4 );
//                 Date d = new Date(s4);
//                 DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//                 System.out.println(f.format(d));
//                 p.setDateE(f.format(d));

                 //fin affichage date a revenir apres 
                 
                 
//      DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//      Date d = new Date(obj.get("dateE").toString());
//      String d=;
//       p.setDateE(obj.get("dateE").toString());
                 
                 
                 
                
     //           float user_id = Float.parseFloat(obj.get("user_id").toString());
                //p.setUser_id((int) user_id);
                p.setImageE(obj.get("imageE").toString());
                
                float interesses = Float.parseFloat(obj.get("interesses").toString());
                p.setInteresses((int) interesses);
                float capacite = Float.parseFloat(obj.get("capacite").toString());
                p.setCapacite((int) capacite);
                
                System.out.println(p);
                evenements.add(p);
            }
            }
            catch (NullPointerException e)
            {
                System.out.println(e.getMessage());
            }
            
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        return evenements;
    }   
    
 
public void supprimerEvenement(int ide)
   {
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/event/deletee/" + ide;
        con.setUrl(Url);
        
//        con.addResponseListener((rl)->{
//            String msg=new String(con.getResponseData());
//            JSONParser j=new JSONParser();
//            try {
//                Map<String, Object> mess = j.parseJSON(new CharArrayReader(msg.toCharArray()));
//            
//            
//            if (mess.get("msg").toString().equalsIgnoreCase("ok"))
//            {
//                 
//             Dialog.show("Succes", "Votre evenement supprimer", "ok", null);
//            }
//            else
//            {
//               Dialog.show("error", "erreur lors de la suppression", "ok", null);
//               
//            }
//            } catch (IOException ex) {
//            
//            }
//        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      Message m = new Message("Vous Avez Supprimer un nouveau événement qui a l'id"+ide);
                 m.getAttachments().put("Vous Avez Supprimer un nouveau événement qui a l'id"+ide, "text/plain");
                 Display.getInstance().sendMessage(new String[] {"zahra.laabidi@esprit.tn"}, "Suppression événement", m);
       
       
       
       
   }


private static String Datereformat(String date)
    {
        return date.substring(date.indexOf('=')+1,date.lastIndexOf('-')+3);
    }

public boolean testint(String data){
         
   try
   {
      Integer.parseInt(data );
      return true;
   }
   catch( Exception e)
   {
      return false;
   }

     }   
  
//    ArrayList<Evenements> evenements = new ArrayList<>();
//    
//    public ArrayList<Evenements> getList2(){
//        
//            ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/AnnuaireWeb(1)/AnnuaireWeb/web/app_dev.php/alljson");  
//        con.addResponseListener((NetworkEvent evt) -> {
//            EvenementService ser = new EvenementService();
//            evenements=ser.getListTask(new String(con.getResponseData()));
//        });
//         
//        NetworkManager.getInstance().addToQueueAndWait(con);
//       
//                
//        return(evenements);
//        
//    
//    }
//       
//              
 public void ajoutEvenement(Evenements ev) 
 {
       
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/event/add?nomE="+ev.getNomE()+"&descriptionE="+ev.getDescriptionE()+"&adresseE="+ev.getAdresseE()+"&typeE="+ev.getTypeE()+"&dateE="+ev.getDateE()+"&imageE="+ev.getImageE()+"&interesses="+ev.getInteresses()+"&capacite="+ev.getCapacite();
        con.setUrl(Url);
       
        System.out.println(Url);
        con.setUrl(Url);
        System.out.println("test2");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method

 }
 
 
 public void participer(int ide)
   {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/event/part/" + ide;
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
             
   }
 
  public void annuler(int ide)
   {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/event/annule/" + ide;
        con.setUrl(Url);
        NetworkManager.getInstance().addToQueueAndWait(con);
             
   }
  
  
  public void modifier(Evenements ev) {
        
        String url = "http://localhost/kk/PI/AnnuaireWeb/web/app_dev.php/event/modifier?ide="+ev.getIde()
                +"&nomE="+ev.getNomE()
                +"&descriptionE="+ev.getDescriptionE()
                +"&adresseE="+ev.getAdresseE()
                +"&typeE="+ev.getTypeE()
                +"&dateE="+ev.getDateE()
                +"&imageE="+ev.getImageE()
                +"&interesses="+ev.getInteresses()
                +"&capacite="+ev.getCapacite();
                   
        System.out.println(url);
          
           ConnectionRequest con = new ConnectionRequest();
           con.setUrl(url);
           NetworkManager.getInstance().addToQueue(con);  
            Message m = new Message("Vous Avez Modifier un nouveau événement qui a l'id"+ev.getIde());
                 m.getAttachments().put("Vous Avez Modifier un nouveau événement qui a l'id"+ev.getIde(), "text/plain");
                 Display.getInstance().sendMessage(new String[] {"zahra.laabidi@esprit.tn"}, "Modification événement", m);
       

 
    }
 
 
}
