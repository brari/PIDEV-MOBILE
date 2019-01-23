/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author DELL
 */
public class Participants {
    private int idParticipation;
    private int event;

    public Participants() {
    }

    public Participants(int idParticipation, int event) {
        this.idParticipation = idParticipation;
        this.event = event;
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public int getEvent() {
        return event;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Participants{" + "idParticipation=" + idParticipation + ", event=" + event + '}';
    }
    
    
    
    
    
}
