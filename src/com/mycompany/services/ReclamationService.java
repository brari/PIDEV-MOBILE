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
import com.mycompany.entities.reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brari
 */
public class ReclamationService {
    public ArrayList<reclamation> afficherReclamation(String json){
        ArrayList<reclamation> reclamations= new ArrayList<>();
        
        try {
            JSONParser j= new JSONParser();
            Map<String, Object> pats = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) pats.get("root");
            
            for (Map<String, Object> obj : list) {
                reclamation r= new reclamation();
             float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                r.setId((int) id);
                r.setReponse(obj.get("name").toString());
                r.setObjet(obj.get("status").toString());                  
                System.out.println(r);
                reclamations.add(r);
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        return reclamations;
    }   
    
    ArrayList<reclamation> reclamations = new ArrayList<>();
    
    public ArrayList<reclamation> afficherRec(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SprintWebDemo/web/app_dev.php/api/tasks/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {         
                ReclamationService ser = new ReclamationService();
                reclamations = ser.afficherReclamation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return reclamations;
    }
    
}
