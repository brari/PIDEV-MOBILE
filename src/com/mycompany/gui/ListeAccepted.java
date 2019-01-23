/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Recette;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esprit
 */
public class ListeAccepted {

    List<Recette> listAccepted;

    public ListeAccepted() {
        listAccepted = new ArrayList<>();
    }

    public void ajouterRecette(Recette e) {
        listAccepted.add(e);
    }

    public void supprimerRecette(Recette e) {

        listAccepted.remove(e);

    }

    public boolean rechercherRecette(Recette e) {
        return (listAccepted.contains(e));
    }

    public void displayRecette() {
        for (Recette a : listAccepted) {
            System.out.println(a);
        }

        //System.out.println(etudiants);
    }

}
