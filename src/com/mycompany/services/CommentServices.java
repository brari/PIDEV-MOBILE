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
import com.mycompany.entities.CommentaireEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author esprit
 */
public class CommentServices {

    public void ajoutRecetteComment(CommentaireEvent c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/integration_finaaaaal_ttequipe/kk/PI/AnnuaireWeb/web/app_dev.php/api/AjoutCommentMobile";

        con.addArgument("id", Integer.toString(c.getId()));
        con.addArgument("idUtilisateur", "13");
        con.addArgument("comment", c.getComment());
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<CommentaireEvent> getListComment(String json, String b) throws ParseException {

        ArrayList<CommentaireEvent> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                CommentaireEvent a = new CommentaireEvent();

                a.setComment(obj.get("comment").toString());
//                Map<String, Object> f = (Map<String, Object>) obj.get("id_utilisateur");
//                float id1 = Float.parseFloat(f.get("id").toString());
//                a.setId_utilisateur((int) id1);
////          
//                float id = Float.parseFloat(obj.get("id_comment").toString());
//                a.setId_comment((int) id);
//                a.setId(Integer.parseInt(b));

//                SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-DDHH:MM:SS.mmm-HH:MM");
//                Date date = date1.parse(obj.get("date").toString());
//                a.setDate(date);

                listTasks.add(a);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listTasks;

    }

    ArrayList<CommentaireEvent> listComments = new ArrayList<>();

    public ArrayList<CommentaireEvent> getList2(String b) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/integration_finaaaaal_ttequipe/kk/PI/AnnuaireWeb/web/app_dev.php/api/AfficheCommentMobile/" + b);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentServices cer = new CommentServices();
                try {
                    listComments = cer.getListComment(new String(con.getResponseData()), b);
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComments;
    }
       public void SupprimerRecetteComment(String c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/integration_finaaaaal_ttequipe/kk/PI/AnnuaireWeb/web/app_dev.php/api/SupprimerCommentMobile/"+c;

        
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
