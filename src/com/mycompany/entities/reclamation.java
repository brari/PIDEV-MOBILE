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
public class reclamation {
    private String objet ;
    private int id;
    private String decription ;
    private Date Date;
    private String Reponse ;
    private String Decision;
 public reclamation(){};
 public reclamation(String objet,String description,Date Date,String Reponse,String Decision,int id)
 {
     this.id=id;
     this.Date=Date;
     this.Decision=Decision;
     this.Reponse=Reponse;
     this.objet=objet;
     this.decription=description;
 }
 public reclamation(String objet,String description,Date Date){
     this.Date=Date;
     this.objet=objet;
     this.decription=description;
 }
    public String getObjet() {
        return objet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

    public String getDecision() {
        return Decision;
    }

    public void setDecision(String Decision) {
        this.Decision = Decision;
    }
 
    public String toString(){
    return " id : "+this.id+" Name : "+this.Reponse+" Status : "+this.objet;
      
    }
}
