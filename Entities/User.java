/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.jfoenix.controls.JFXTextField;
import java.sql.Date;

/**
 *
 * @author azizs
 */
public class User {
     private int id;
  private String email;
  private String username; 				
  private String password; 				
  String prenom; 				
  String nom; 					
  private String cin; 					
  private String sexe; 					
  private java.sql.Date date_naissance; 		
  private String adresse; 				
  private String num_tel; 				
  private String photo_profil;
  private int role;
  private int desactiver;
  private String code;
  
   public User(){
  this.nom="";
  this.prenom="";}
   
   public User(int id, String email, String username, String password, String prenom, String nom, String cin, String sexe, java.sql.Date date_naissance, String adresse, String num_tel, String photo_profil, int role,int desactiver) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
        this.role = role;
        this.desactiver=desactiver;
    }
   
    public User(String email, String username, String password, String prenom, String nom, String cin, String sexe, java.sql.Date date_naissance, String adresse, String num_tel, String photo_profil) {
         this.email = email;
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
        
    }

    public User(String email, String nom, String prenom, String cin, String adresse, String num_tel, String username,int id) {
        this.email = email;
        this.username = username;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;  
        this.id=id;}

    public User(String email, String nom, String prenom, String cin, String adresse, String num_tel, String username) {
        this.email = email;
        this.username = username;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;  
        }
      public User(int id,String email,String username,String cin,String prenom, String nom,String adresse, String num_tel) {
        this.id=id;
        this.email = email;
        this.username = username;
        this.cin = cin;        
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.num_tel = num_tel;  
        } 
      public User(int id,String email,String username,String password,String prenom, String nom,String adresse, String num_tel, String photo_profil) {
        this.id=id;
        this.email = email;
        this.username = username;
        this.password = password;        
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.num_tel = num_tel; 
        this.photo_profil=photo_profil;
        }
      
      
      

   
    public User(int id, String prenom, String nom) {
        this.id = id;
        this.prenom=prenom;
        this.nom=nom;
        
    }

    public User(String username, String password) {
        this.username=username;
        this.password=password;
    }

 
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public java.sql.Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(java.sql.Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getPhoto_profil() {
        return photo_profil;
    }

    public void setPhoto_profil(String photo_profil) {
        this.photo_profil = photo_profil;
    }

   

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    public int getDesactiver() {
        return desactiver;
    }

    public void setDesactiver(int desactiver) {
        this.desactiver = desactiver;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", prenom=" + prenom + ", nom=" + nom + ", cin=" + cin + ", sexe=" + sexe + ", date_naissance=" + date_naissance + ", adresse=" + adresse + ", num_tel=" + num_tel + ", photo_profil=" + photo_profil + ", role=" + role + ", desactiver=" + desactiver + '}';
    }
    
}
