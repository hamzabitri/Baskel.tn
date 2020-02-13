/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Hamza
 */
public class Commande {
    private int idCommande;
    private int idUser;
    String etat ;

    public Commande(int idCommande, int idUser, String etat) {
        this.idCommande = idCommande;
        this.idUser = idUser;
        this.etat = etat;
    }

    public Commande(int idUser, String etat) {
        this.idUser = idUser;
        this.etat = etat;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int id) {
        this.idCommande = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int id) {
        this.idUser = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", idUser=" + idUser + ", etat=" + etat + '}';
    }
    
}

