/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Evenements {
    private int ide;
    private int user_id;
    private String nomE ;
    private String descriptionE ;
    private String adresseE ;
    private String typeE ;
    private String dateE ;
    private String imageE ;
    private int interesses ;
    private int capacite ;

    public Evenements() {
        
    }

    public Evenements(int user_id, String nomE, String descriptionE, String adresseE, String typeE,  String dateE, String imageE, int interesses, int capacite) {
        this.user_id = user_id;
        this.nomE = nomE;
        this.descriptionE = descriptionE;
        this.adresseE = adresseE;
        this.typeE = typeE;
        this.dateE = dateE;
        this.imageE = imageE;
        this.interesses = interesses;
        this.capacite = capacite;
    }

   

    public int getIde() {
        return ide;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNomE() {
        return nomE;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public String getAdresseE() {
        return adresseE;
    }

    public String getTypeE() {
        return typeE;
    }

    public  String getDateE() {
        return dateE;
    }

    public String getImageE() {
        return imageE;
    }

    public int getInteresses() {
        return interesses;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }

    public void setAdresseE(String adresseE) {
        this.adresseE = adresseE;
    }

    public void setTypeE(String typeE) {
        this.typeE = typeE;
    }

    public void setDateE(String dateE) {
        this.dateE = dateE;
    }

    public void setImageE(String imageE) {
        this.imageE = imageE;
    }

    public void setInteresses(int interesses) {
        this.interesses = interesses;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Evenements{" + "ide=" + ide + ", user_id=" + user_id + ", nomE=" + nomE + ", descriptionE=" + descriptionE + ", adresseE=" + adresseE + ", typeE=" + typeE + ", dateE=" + dateE + ", imageE=" + imageE + ", interesses=" + interesses + ", capacite=" + capacite + '}';
    }

    

    
    
   
   
    
    

    
   

    
    
    
    
    
    
    
     
    
    
}
