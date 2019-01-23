/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author brari
 */
public class Users {
   private int id;
   private String username;
   private String username_canonical;
   private String email;
   private String email_canonical;
   private int enable;
   private String salt;
   private String password;
   private Date last_login;
   private String confirmation_token;
   private String confirmation_requested_at;
   private String roles;
   private String nom;
   private String prenom;
   private int telephone;
   private String adresse;
   private String photo;
   private String pdf;

    public Users(String username, String email, String password, String roles, String nom, String prenom, int telephone, String adresse, String photo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.photo = photo;
    }

    public Users() {
      
    }

  

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getConfirmation_requested_at() {
        return confirmation_requested_at;
    }

    public void setConfirmation_requested_at(String confirmation_requested_at) {
        this.confirmation_requested_at = confirmation_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
    public String toString(){
    return this.adresse+" "+this.confirmation_requested_at+" "+this.confirmation_token+" "+this.email+" "+this.email_canonical+" "+this.nom+" "+this.password+" "+this.pdf+" "+this.photo+" "+this.prenom+" "+this.roles+" "+this.salt+" "+this.username+" "+this.username_canonical;
    }
   
}
