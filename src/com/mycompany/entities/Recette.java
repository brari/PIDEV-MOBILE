/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author esprit
 */
public class Recette {
    
private int id;
private String nom;
private String description; 
private String etat;
private String action;  
private String photo;
private int IdUser;
private int rating; 
private Date date;
private  boolean isdisabled;
    
    
    
    
 public Recette() {
    }   

    public Recette(int id, String nom, String description, String etat, String action, String photo, int IdUser, int rating, Date date, boolean isdisabled) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
        this.rating = rating;
        this.date = date;
        this.isdisabled = isdisabled;
    }

    public Recette(int id, String nom, String description, String etat, String action) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
    }

    public Recette(String nom, String description, String etat, String action, String photo, int IdUser, Date date, boolean isdisabled) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
        this.date = date;
        this.isdisabled = isdisabled;
    }

    public Recette(int id, String nom, String description, String etat, String action, String photo, int IdUser, Date date, boolean isdisabled) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
        this.date = date;
        this.isdisabled = isdisabled;
    }

    public Recette(int id, String nom, String description, String etat, String action, String photo, int IdUser, int rating, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
        this.rating = rating;
        this.date = date;
    }

    public Recette(int id, String nom, String description, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
    }

    public Recette(String nom, String description, String etat, String action, String photo,  Date date, boolean isdisabled) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
        this.date = date;
        this.isdisabled = isdisabled;
    }

   

    public Recette(int id, String nom, String description, String etat, String action, String photo, int IdUser, int rating, boolean isdisabled) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
        this.rating = rating;
        
        this.isdisabled = isdisabled;
    }

    public Recette(int id, String nom, String description, String etat, String action, String photo, int IdUser, int rating) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
        this.rating = rating;
    }

    
    
    public Recette(String nom, String description, String etat, String action) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
    }

    public Recette(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Recette(int id, String nom, String description, String etat, String action, String photo, int IdUser) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
    }

    public Recette(String nom, String description, String etat, String action, String photo, int IdUser) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.photo = photo;
        this.IdUser = IdUser;
    }

    public Recette(String nom, String description, String etat, String action, int IdUser) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.IdUser = IdUser;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public String getAction() {
        return action;
    }

    public String getPhoto() {
        return photo;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

    public void setIsdisabled(boolean isdisabled) {
        this.isdisabled = isdisabled;
    }

    public boolean isIsdisabled() {
        return isdisabled;
    }

    @Override
    public String toString() {
        return "Recette{" + "nom=" + nom + ", description=" + description + ", etat=" + etat + ", action=" + action + ", photo=" + photo + ", IdUser=" + IdUser + ", rating=" + rating + ", date=" + date + ", isdisabled=" + isdisabled + '}';
    }

   
    
    
    
    
    
    
    
    
    
}
