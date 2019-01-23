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
public class CommentaireEvent {

    private int id_comment;
    private int id;
    private String comment;
    private int id_utilisateur;
    private Date date;

    public CommentaireEvent() {
    }

    public CommentaireEvent(int id_comment, int id, String comment) {
        this.id_comment = id_comment;
        this.id = id;
        this.comment = comment;
    }

    public CommentaireEvent(int id_comment, int id, String comment, int id_utilisateur, Date date) {
        this.id_comment = id_comment;
        this.id = id;
        this.comment = comment;
        this.id_utilisateur = id_utilisateur;
        this.date = date;
    }

    public CommentaireEvent( int id, String comment, int id_utilisateur) {
    
        this.id = id;
        this.comment = comment;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_comment=" + id_comment + ", id=" + id + ", comment=" + comment + ", id_utilisateur=" + id_utilisateur + ", date=" + date + '}';
    }

    
    

    
}
