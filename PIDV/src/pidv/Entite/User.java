/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidv.Entite;

/**
 *
 * @author azizs
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private String type;


    public User(int id, String nom, String prenom, String login,String mdp,String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.type = type;

    }

    public User(String nom, String prenom,String login,String mdp,String type ) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", mot de passe=" + mdp + ", type=" + type + '}';
    }
    
}


