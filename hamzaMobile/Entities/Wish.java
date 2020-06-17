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
public class Wish {
    
    private int id,idProduit,idUser;

    public Wish(int id, int idProduit, int idUser) {
        this.id = id;
        this.idProduit = idProduit;
        this.idUser = idUser;
    }

    public Wish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Wish{" + "id=" + id + ", idProduit=" + idProduit + ", idUser=" + idUser + '}';
    }
    
    
    
}
