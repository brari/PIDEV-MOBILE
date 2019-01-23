/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Insaf-Nefzi
 */
public class Commande {
    
    private int id;
    private int idpro;
    private int idu;
     private String nompro;
    private String prixpro;
    private String categoriepro;
    private String detailspro;
    private String nompat;
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Commande() {
    }

    public Commande(String nompro, String prixpro, String categoriepro, String detailspro, String nompat) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }

    public void setPrixpro(String prixpro) {
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

    public int getId() {
        return id;
    }

    public int getIdpro() {
        return idpro;
    }

    public int getIdu() {
        return idu;
    }

    public String getNompro() {
        return nompro;
    }

    public String getPrixpro() {
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

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idpro=" + idpro + ", idu=" + idu + ", nompro=" + nompro + ", prixpro=" + prixpro + ", categoriepro=" + categoriepro + ", detailspro=" + detailspro + ", nompat=" + nompat + '}';
    }
    
    
}
