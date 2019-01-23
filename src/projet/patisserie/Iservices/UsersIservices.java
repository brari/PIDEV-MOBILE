/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.patisserie.Iservices;

import com.mycompany.entities.Users;



/**
 *
 * @author Bader
 */
public interface UsersIservices {
    boolean create_user(Users u);
    boolean edit_user(Users u);
    Users get_user(int id);
   
}
