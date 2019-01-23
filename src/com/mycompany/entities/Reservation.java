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
public class Reservation {
    private int id;
    private String date;
    private String heure;
    private int places;
    private int idu;
    private int idp;

    public Reservation() {
    }

    public Reservation(int id, String date, String heure, int places, int idu, int idp) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.places = places;
        this.idu = idu;
        this.idp = idp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date=" + date + ", heure=" + heure + ", places=" + places + ", idu=" + idu + ", idp=" + idp + '}';
    }

    
    
    
    
}
