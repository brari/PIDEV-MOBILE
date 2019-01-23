/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author hp
 */
public class Patisserie {
   private int idp;
   private String nom;
   private String adresse;
   private int contact;
   private String mail;
   private String url;
   private Double rating;
   private String idprop;
   private boolean reservation;
   private int place;

    public Patisserie() {
    }

    public Patisserie(int idp, String nom, String adresse, int contact, String mail, String url, Double rating, String idprop, boolean reservation, int place) {
        this.idp = idp;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.mail = mail;
        this.url = url;
        this.rating = rating;
        this.idprop = idprop;
        this.reservation = reservation;
        this.place = place;
    }
   
   

    public Patisserie(String nom, String adresse, int contact, String mail, String url, Double rating, String idprop, boolean reservation, int place) {
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.mail = mail;
        this.url = url;
        this.rating = rating;
        this.idprop = idprop;
        this.reservation = reservation;
        this.place = place;
    }
   
   
    
   
    public int getIdp() {
        return idp;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getContact() {
        return contact;
    }

    public String getUrl() {
        return url;
    }

    public Double getRating() {
        return rating;
    }

    public String getIdprop() {
        return idprop;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    
    public void setIdprop(String idprop) {
        this.idprop = idprop;
    }
  

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
     
    
    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Patisserie{" + "idp=" + idp + ", nom=" + nom + ", adresse=" + adresse + ", contact=" + contact + ", mail=" + mail + ", rating=" + rating + ", Nom propriétaire=" + idprop + ", reservation=" + reservation + ", url=" + url+ ", place=" + place + '}';
    }

    
   

    

    

   

    
   
   
    
   
}
