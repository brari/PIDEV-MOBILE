package com.mycompany.entities;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Insaf-Nefzi
 */
public class Produit {
    private int id;
    private String nompro;
    private float prixpro;
    private String categoriepro;
    private String detailspro;
    private String nompat;
    private String image;
    private int stock;
    public static int focusedId;

    public Produit() {
    }

    public Produit(int id) {
        this.id = id;
    }

    public Produit(int id, String nompro, float prixpro, String categoriepro, String detailspro, String nompat, int stock) {
        this.id = id;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.stock = stock;
    }
    

    public Produit(String nompro, float prixpro, String categoriepro, String detailspro, String nompat,  int stock) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
       
        this.stock = stock;
    }

    public Produit(String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image, int stock) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
        this.stock = stock;
    }

   

    public int getId() {
        return id;
    }

    public String getNompro() {
        return nompro;
    }

    public float getPrixpro() {
        return prixpro;
    }

    public String getCategoriepro() {
        return categoriepro;
    }

    public String getDetailspro() {
        return detailspro;
    }

    public String getNompat() {
        return nompat;
    }

   

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }

    public void setPrixpro(float prixpro) {
        this.prixpro = prixpro;
    }

    public void setCategoriepro(String categoriepro) {
        this.categoriepro = categoriepro;
    }

    public void setDetailspro(String detailspro) {
        this.detailspro = detailspro;
    }

    public void setNompat(String nompat) {
        this.nompat = nompat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nompro=" + nompro + ", prixpro=" + prixpro + ", categoriepro=" + categoriepro + ", detailspro=" + detailspro + ", nompat=" + nompat + ", image=" + image + ", stock=" + stock + '}';
    }

    
    
}
