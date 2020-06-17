/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author amine
 */
public class Commande {
    
    private int id,total,idUser;
    private String etat;

    public Commande() {
    }

    public Commande(int total, int idUser, String etat) {
        this.total = total;
        this.idUser = idUser;
        this.etat = etat;
    }
    
    

    public Commande(int id, int total, int idUser, String etat) {
        this.id = id;
        this.total = total;
        this.idUser = idUser;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", total=" + total + ", idUser=" + idUser + ", etat=" + etat + '}';
    }
    
    
    
}
