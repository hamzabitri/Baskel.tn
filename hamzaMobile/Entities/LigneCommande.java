/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amine
 */
public class LigneCommande {
    
    int id,idUser,idProduit,quantite;
   
    

    public LigneCommande() {
    }

    public LigneCommande(int id, int idUser, int idProduit, int quantite) {
        this.id = id;
        this.idUser = idUser;
        this.idProduit = idProduit;
        this.quantite= quantite;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", idUser=" + idUser + ", idProduit=" + idProduit + ", quantite=" + quantite + '}';
    }

   
}
