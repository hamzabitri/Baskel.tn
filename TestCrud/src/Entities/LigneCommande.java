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
public class LigneCommande {
    private int idLC;
    private int idCommande;
    float prix ;

    public LigneCommande(int idLC, int idCommande, float prix) {
        this.idLC = idLC;
        this.idCommande = idCommande;
        this.prix = prix;
    }

    public LigneCommande(int idCommande,float prix) {
        this.idCommande = idCommande;
        this.prix = prix;
    }

    public int getIdLC() {
        return idLC;
    }

    public void setIdLC(int id) {
        this.idLC = id;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int id) {
        this.idCommande = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "idLigneCommande=" + idLC + ", idCommande=" + idCommande + ", prix=" + prix + '}';
    }
    
}

