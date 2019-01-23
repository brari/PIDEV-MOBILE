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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import projet.patisserie.Iservices.UsersIservices;

/**
 *
 * @author brari
 */
public class UsersServices  implements UsersIservices{
     @Override
    public boolean create_user(Users u) {
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(  "http://localhost/kk/kk/PI/Annuaireweb/web/app_dev.php/recette/usermob?FirstName="+u.getNom()+
                "&LastName="+u.getPrenom()+"&email="+u.getEmail()+"&password="+u.getPassword()+
                "&Mobile="+u.getTelephone()+"&Address="+u.getAdresse()+"&photo=profil.jpg");
        connection.addResponseListener(a->{
            Dialog.show(null, new String(connection.getResponseData()), "Ok", null);
        });
        NetworkManager.getInstance().addToQueueAndWait(connection);
        return true;
    }
    @Override
        public boolean edit_user(Users u) {
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl("http://localhost/kk/kk/PI/Annuaireweb/web/app_dev.php/recette/updateuser/"+authuser.user.getId()+"?FirstName="+u.getNom()+"&LastName="+u.getPrenom()+"&email="+u.getEmail()+"&password="+u.getPassword()+"&Mobile="+u.getTelephone()+"&Address="+u.getAdresse()+"&photo=profil.jpg");
        connection.addResponseListener(a->{
            Dialog.show(null, new String(connection.getResponseData()), "Ok", null);
        });
        NetworkManager.getInstance().addToQueueAndWait(connection);
        return true;
    }
    @Override
          public Users get_user(int id) {
             
        Users user = new Users();
         ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl("http://localhost/kk/kk/PI/Annuaireweb/web/app_dev.php/recette/recupereuser/"+id+"");
        connection.addResponseListener(e->{
        System.out.println(new String(connection.getResponseData()));
            JSONParser json = new JSONParser();
            String jsonresponse = new String(connection.getResponseData());
            try {
                Map <String, Object> user_info= json.parseJSON(new CharArrayReader(jsonresponse.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>)user_info.get("root");
//               for (Map<String, Object> obj : list) {
//                        user.setFirst_name(obj.get("First_name").toString());
//                        user.setLast_name(obj.get("Last_name").toString());
//                        user.setEmail(obj.get("email").toString());
//                        user.setAddress(obj.get("Address").toString());
//                        user.setPassword(obj.get("password").toString());
//                        user.setMobile_number(Integer.parseInt(obj.get("Mobile").toString()));
//                        
//            }
//              
            } catch (IOException ex) {
                System.out.println("lenna");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connection);
        return user;
      
    }
      public ArrayList<Users> getUser(String json) {

        ArrayList<Users> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");           
           
           // System.out.println(list);
            for (Map<String, Object> obj : list) {
                
                          Users user = new Users();
                        float bbbb=Float.parseFloat(obj.get("idu").toString());
                        user.setId((int)bbbb);
                            user.setNom(obj.get("firstName").toString());
                        user.setPrenom(obj.get("lastName").toString());
                        float f = Float.parseFloat(obj.get("mobile").toString());
                        user.setTelephone((int) f);
                        user.setEmail(  obj.get("email").toString());
                        user.setPassword(obj.get("password").toString());  
                        user.setAdresse(obj.get("address").toString());
                        
                       // System.out.println(user);
                        listTasks.add(user);
            }

        } catch (IOException ex) {
        }
        return listTasks;

    }  
      
          ArrayList<Users> listuserss = new ArrayList<>();
    
    public ArrayList<Users> getListuser(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/kk/kk/PI/Annuaireweb/web/app_dev.php/recette/juser");  
                con.setPost(false);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                UsersServices ser = new UsersServices();
                listuserss = ser.getUser(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
     //   System.out.println(listuserss);
        return listuserss;
    }

//    @Override
//    public users get_user(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
   
}
